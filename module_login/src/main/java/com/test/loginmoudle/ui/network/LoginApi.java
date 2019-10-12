package com.test.loginmoudle.ui.network;

import com.test.basemoudle.bean.NetworkResult;
import com.test.basemoudle.bean.UserInfoResult;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author zhuj 2019-09-03 18:13.
 */
public interface LoginApi {

    /**
     * 登录账号
     */
    @POST("teachapi/v1/account/login")
    Observable<UserInfoResult> login(@Body RequestBody data);

    /**
     * 发送验证码
     */
    @POST("teachapi/v1/account/sendVerifyCode")
    Observable<NetworkResult> sendVerifyCode(@Body RequestBody data);

    /**
     * 找回密码
     */
    @POST("teachapi/v1/account/findPassword")
    Observable<NetworkResult> findPassword(@Body RequestBody data);
}
