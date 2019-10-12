package com.test.basemoudle.network;

import android.util.Base64;
import com.test.basemoudle.AppConstants;
import com.test.basemoudle.BaseApplication;
import com.test.basemoudle.bean.RefreshTokenResult;
import com.test.basemoudle.utils.JsonUtils;
import com.test.basemoudle.utils.sharedPresenter.SharedPreUser;
import java.nio.charset.Charset;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;

/**
 * @author zhuj 2019-09-03 17:57.
 */
public class AppModel {

    /**
     * 同步刷新token
     */
    public void getRefreshTokenSnyc() throws Exception {

        AppApi iHttpApi = RetrofitManager.getInstance().getApi(AppApi.class);
        boolean isDev = RetrofitManager.isDevelopTestEnv(BaseApplication.getContext());
        String clientId = isDev ? AppConstants.APP_KEY_TEST : AppConstants.APP_KEY;
        String clientSecret = isDev ? AppConstants.APP_SECRET_TEST : AppConstants.APP_SECRET;
        String refreshToken = SharedPreUser.getInstance().getRefreshToken();

        JSONObject obj = new JSONObject();
        try {
            obj.put("clientId", clientId);
            obj.put("clientSecret", clientSecret);
            obj.put("refreshToken", refreshToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = JsonUtils.toRequestBody(obj);
        Call<RefreshTokenResult> call = iHttpApi.refreshTokenSync(requestBody);
        RefreshTokenResult result = call.execute().body();

        byte[] b1 = Base64.encode(("Basic:" + result.getData().getAccessToken()).getBytes(),
                Base64.NO_WRAP);
        String baseToken = new String(b1, Charset.forName("UTF-8"));

        SharedPreUser.getInstance()
                .put(BaseApplication.getContext(), SharedPreUser.KEY_TOKEN, baseToken);
        SharedPreUser.getInstance()
                .put(BaseApplication.getContext(), SharedPreUser.KEY_REFRESH_TOKEN,
                        result.getData().getRefreshToken());
        SharedPreUser.getInstance()
                .put(BaseApplication.getContext(), SharedPreUser.KEY_TOKEN_EXPIRED,
                        result.getTimestamp() + ((long) result.
                                getData().getExpiresIn() * 1000L));

    }
}
