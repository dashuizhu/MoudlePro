package com.test.loginmoudle.ui.ui.model;

import android.util.Base64;
import com.test.basemoudle.AppConstants;
import com.test.basemoudle.BaseApplication;
import com.test.basemoudle.bean.NetworkResult;
import com.test.basemoudle.bean.UserInfoResult;
import com.test.basemoudle.exception.CustomException;
import com.test.basemoudle.network.RetrofitManager;
import com.test.basemoudle.utils.JsonUtils;
import com.test.basemoudle.utils.encrypt.MD5;
import com.test.basemoudle.utils.sharedPresenter.SharedPreApp;
import com.test.basemoudle.utils.sharedPresenter.SharedPreUser;
import com.test.loginmoudle.ui.network.LoginApi;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.nio.charset.Charset;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author zhuj 2019-09-03 18:14.
 */
public class LoginModel {

    public Observable<UserInfoResult> login(final String userName, final String password) {
        LoginApi httpApi = RetrofitManager.getInstance().getApi(LoginApi.class);
        boolean idDev = RetrofitManager.isDevelopTestEnv(BaseApplication.getContext());
        String clientId = idDev ? AppConstants.APP_KEY_TEST : AppConstants.APP_KEY;
        String clientSecret = idDev ? AppConstants.APP_SECRET_TEST : AppConstants.APP_SECRET;
        JSONObject obj = new JSONObject();
        try {
            obj.put("clientId", clientId);
            obj.put("clientSecret", clientSecret);
            obj.put("phone", userName);
            obj.put("password", MD5.getStringMD5(password));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = JsonUtils.toRequestBody(obj);
        return httpApi.login(body).doOnNext(new Consumer<UserInfoResult>() {
            @Override
            public void accept(UserInfoResult networkResult) {
                if (!networkResult.isNetworkSuccess()) {
                    throw new CustomException(networkResult, "login");
                } else {
                    String token = networkResult.getData().getAuth().getAccessToken();
                    byte[] b1 = Base64.encode(("Basic:" + token).getBytes(), Base64.NO_WRAP);
                    String baseToken = new String(b1, Charset.forName("UTF-8"));

                    SharedPreUser.getInstance()
                            .put(BaseApplication.getContext(), SharedPreUser.KEY_TOKEN, baseToken);
                    SharedPreApp.getInstance()
                            .put(BaseApplication.getContext(), SharedPreApp.KEY_USER_NAME,
                                    userName);
                    SharedPreApp.getInstance()
                            .put(BaseApplication.getContext(), SharedPreApp.KEY_PASSWORD, password);

                    SharedPreUser.getInstance()
                            .put(BaseApplication.getContext(), SharedPreUser.KEY_MEMBER,
                                    networkResult.getData().getMember().getId());
                    SharedPreUser.getInstance()
                            .put(BaseApplication.getContext(), SharedPreUser.KEY_USER_AVATOR,
                                    networkResult.getData().getMember().getAvatar());
                    SharedPreUser.getInstance()
                            .put(BaseApplication.getContext(), SharedPreUser.KEY_USER_NAME,
                                    networkResult.getData().getMember().getName());
                    SharedPreUser.getInstance()
                            .put(BaseApplication.getContext(), SharedPreUser.KEY_USER_GANDER,
                                    String.valueOf(networkResult.getData().getMember().getSex()));
                    if (networkResult.getData().getSchool() != null) {
                        SharedPreUser.getInstance()
                                .put(BaseApplication.getContext(), SharedPreUser.KEY_SCHOOL_ID,
                                        networkResult.getData().getSchool().getId());
                        SharedPreUser.getInstance()
                                .put(BaseApplication.getContext(), SharedPreUser.KEY_SCHOOL_NAME,
                                        networkResult.getData().getSchool().getName());
                    }
                    if (networkResult.getData().getGrade() != null) {
                        SharedPreUser.getInstance()
                                .put(BaseApplication.getContext(), SharedPreUser.KEY_GRADE_ID,
                                        networkResult.getData().getGrade().getId());
                        SharedPreUser.getInstance()
                                .put(BaseApplication.getContext(), SharedPreUser.KEY_GRADE_NAME,
                                        networkResult.getData().getGrade().getName());
                        SharedPreUser.getInstance()
                                .put(BaseApplication.getContext(), SharedPreUser.KEY_GRADE_RANK,
                                        networkResult.getData().getGrade().getRank());
                        SharedPreUser.getInstance()
                                .put(BaseApplication.getContext(), SharedPreUser.KEY_GRADE_NUM,
                                        networkResult.getData().getGrade().getStudentNum());
                    }

                    SharedPreUser.getInstance()
                            .put(BaseApplication.getContext(), SharedPreUser.KEY_TOKEN_EXPIRED,
                                    networkResult.getTimestamp() + ((long) networkResult.
                                            getData().getAuth().getExpiresIn() * 1000L));
                }
            }
        });
    }

    public Observable<NetworkResult> sendVerifyCode(String phone, String templateId) {
        LoginApi httpApi = RetrofitManager.getInstance().getApi(LoginApi.class);
        JSONObject obj = new JSONObject();
        try {
            obj.put("phone", phone);
            obj.put("templateId", templateId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = JsonUtils.toRequestBody(obj);
        return httpApi.sendVerifyCode(body).doOnNext(new Consumer<NetworkResult>() {
            @Override
            public void accept(NetworkResult networkResult) {
                if (!networkResult.isNetworkSuccess()) {
                    throw new CustomException(networkResult, "sendCode");
                }
            }
        });
    }

    public Observable<NetworkResult> findPassword(String phone, String password,
            String verifyCode) {
        LoginApi httpApi = RetrofitManager.getInstance().getApi(LoginApi.class);
        JSONObject obj = new JSONObject();
        try {
            obj.put("phone", phone);
            obj.put("password", password);
            obj.put("verifyCode", verifyCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = JsonUtils.toRequestBody(obj);
        return httpApi.findPassword(body).doOnNext(new Consumer<NetworkResult>() {
            @Override
            public void accept(NetworkResult networkResult) {
                if (!networkResult.isNetworkSuccess()) {
                    throw new CustomException(networkResult, "findPassword");
                }
            }
        });
    }
}
