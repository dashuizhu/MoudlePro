package com.test.basemoudle.network;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.person.commonlib.utils.AppUtils;
import com.test.basemoudle.bean.NetworkResult;
import com.test.basemoudle.utils.sharedPresenter.SharedPreUser;
import dagger.Module;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装okhttp ， 可做缓存、拦截器等操作
 *
 * @author zhuj
 * @date 2017/6/9 下午4:14
 */
@Module
public class RetrofitManager {

    private final String TAG = RetrofitManager.class.getSimpleName();

    public static final String DOMAIN      = "https://pwe.buddyniu.com";
    public static final String DOMAIN_TEST = "http://pwetest.buddyniu.com";

    /**
     * 接口缓存天数， 14天
     */
    public static final int CACHE_TIME = 14 * 24 * 60 * 60;

    /**
     * 获取缓存，
     * 添加了only-if_cached， 没有缓存时，会显示504错误，  不加就有缓存显示缓存，没有就网络请求
     *
     * @see RetrofitManager#CACHE_TIME
     */
    public final static String CHACHE =
            "public,only-if_cached, max-stale=" + RetrofitManager.CACHE_TIME;

    /**
     * 缓存设备定位
     *
     * @see RetrofitManager#CACHE_TIME
     */
    public final static String DEVICE_POSITION_CHACHE = "public, max-stale=60";
    /**
     * 获取网络
     */
    public final static String NO_CACHE               = "max-age=0";

    /**
     * 统一超时时间 ,单位秒
     */
    private final static int DEFAULT_TIMEOUT = 60;

    /**
     * 测试服务器渠道
     */
    private final static String CHANNEL_TEST = "develop";

    /**
     * oss域名  http:// bucket + endpoint
     */
    //public static final String OSS_DOMAIN = "http://buddyapp.oss-cn-zhangjiakou.aliyuncs.com";

    private        Application          mAppApplication;
    private        Retrofit             mRetrofit;
    private        OkHttpClient.Builder mHttpClientBuilder;
    private        OkHttpClient         mOkHttpClient;
    private        Gson                 mGson;
    private static String               mChannelType;
    private        AppModel             mAppModel;


    private static RetrofitManager sRetrofitManager;

    public static RetrofitManager getInstance() {
        if (sRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (sRetrofitManager == null) {
                    sRetrofitManager = new RetrofitManager();
                }
            }
        }
        return sRetrofitManager;
    }

    public void init(Application appApplication) {
        this.mAppApplication = appApplication;
        File httpCacheDirectory = new File(appApplication.getCacheDir(), "responses");
        // 15 MiB
        int cacheSize = 15 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        //手动创建一个OkHttpClient并设置超时时间
        mHttpClientBuilder = new OkHttpClient.Builder();
        mHttpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mHttpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mHttpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //-------------------------------------------
        //添加拦截器，切记注意是否有特殊接口会被影响，是否需要额外处理不做拦截
        //-------------------------------------------

        //CustomTrust ct = new CustomTrust();

        mOkHttpClient = mHttpClientBuilder.addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
                .addInterceptor(TOKEN_INTERCEPTOR)
                //去除，网络差重复请求
                .retryOnConnectionFailure(false)
                .cache(cache)
                //.sslSocketFactory(ct.sslSocketFactory, ct.trustManager)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
        mRetrofit = new Retrofit.Builder().client(mOkHttpClient)
                .baseUrl(getDomain(appApplication))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 缓存拦截器
     */
    private final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request.Builder request = originalRequest.newBuilder();
            if (originalRequest.header("force_cache") != null) {
                //只访问缓存
                request.cacheControl(CacheControl.FORCE_CACHE);
            }
            Response originalResponse = chain.proceed(chain.request());
            originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_TIME)
                    .build();
            return originalResponse;
        }
    };

    /**
     * 重写token
     */
    private final Interceptor TOKEN_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder();

            //get 去除connection
            if (request.method().equals("GET")) {
                requestBuilder.header("Connection", "close");
            }

            String token = SharedPreUser.getInstance().getToken();
            if (!TextUtils.isEmpty(token)) {
                requestBuilder.header("Authorization", token);
            }

            //requestBuilder.url(String.format("%s&access_token=e3408cd2-a3d5-4423-8455-fcc2f698df0f", request.url()));

            Response response = chain.proceed(requestBuilder.build());
            //return response;

            //部分接口不需要走，不需要走刷新token
            if (request.header(ApiConstants.SKIP_REFRESH_TOKEN) != null) {
                Log.d("appModule","跳过  token过期 ->刷新token流程->重新请求流程");
                return response;
            }
            MediaType mediaType = response.body().contentType();
            //string() 会关闭source会close掉，导致body()结果只能获取一次
            String str = response.body().string();
            if (mGson == null) {
                mGson = new Gson();
            }
            NetworkResult result = mGson.fromJson(str, NetworkResult.class);
            //如果是token失效， 就重新刷新token ，再走一次网络请求
            if (result.getCode() == NetworkResult.CODE_TOKEN_ERROR
                    || result.getCode() == NetworkResult.CODE_TOKEN_REFRESH_ERROR) {
                //同步刷新token
                if (mAppModel == null) {
                    mAppModel = new AppModel();
                }
                try {
                    mAppModel.getRefreshTokenSnyc();
                    //用新token刷新请求
                    String newToken = SharedPreUser.getInstance().getToken();

                    Request newRequest =
                            chain.request().newBuilder().header("Authorization", newToken).build();
                    //重新请求
                    return chain.proceed(newRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //用之前的  数据类型个、数据结果 重新组装ResoponseBody 返回
            return response.newBuilder().body(ResponseBody.create(mediaType, str)).build();

        }
    };

    /**
     * 获得当前渠道类型
     */
    public static String getChannelType(Context mContext) {
        if (mChannelType == null) {
            mChannelType = CHANNEL_TEST;
            mChannelType = AppUtils.getChannel(mContext);
            Log.v("appMoudle", "channel：" + mChannelType);
        }
        return mChannelType;
    }

    /**
     * 获取服务器域名
     *
     * @param context 上下文环境
     * @return 返回域名服务器
     */
    public static String getDomain(Context context) {
        String channelType = getChannelType(context);
        if (channelType.equals(CHANNEL_TEST)) {
            return DOMAIN_TEST;
        } else {
            return DOMAIN;
        }
    }

    /**
     * 是否是测试环境
     *
     * @param context 上下文环境
     * @return true 测试环境 false 正式环境
     */
    public static boolean isDevelopTestEnv(Context context) {
        String channelType = getChannelType(context);
        if (channelType.equals(CHANNEL_TEST)) {
            return true;
        }
        return false;
    }

    /**
     * 获得缓存控制header值
     *
     * @param isCache 是否需要缓存
     */
    public static String getCacheControlString(boolean isCache) {
        String cacheControl = isCache ? RetrofitManager.CHACHE : RetrofitManager.NO_CACHE;
        return cacheControl;
    }


    Map<String ,Object> map = new HashMap<>();
    @Singleton
    public <T> T getApi(Class<T> api) {
        if (map.containsKey(api.getName())) {
            return (T) map.get(api.getName());
        }
        T a= mRetrofit.create(api);
        map.put(api.getName(), a);
        Log.w(TAG, "create " + api.getSimpleName());
        return a;
    }

    @Singleton
    OkHttpClient.Builder getClientBuilder() {
        return mHttpClientBuilder;
    }

    @Singleton
    Gson provideGson() {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }

    /**
     * 获取SSLSocketFactory
     *
     * @param certificates 证书流文件
     */
    private static SSLSocketFactory getSSLSocketFactory(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias,
                        certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null) {
                        certificate.close();
                    }
                } catch (IOException e) {
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
