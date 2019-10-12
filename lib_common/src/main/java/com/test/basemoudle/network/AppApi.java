package com.test.basemoudle.network;

import com.test.basemoudle.bean.RefreshTokenResult;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author zhuj 2019-09-03 17:54.
 */
public interface AppApi {

    /**
     * 同步获取SOEclient 信息
     */
    @POST("teachapi/v1/account/refreshToken")
    @Headers(ApiConstants.SKIP_REFRESH_TOKEN+":skip")
    Call<RefreshTokenResult> refreshTokenSync(@Body RequestBody data);
}
