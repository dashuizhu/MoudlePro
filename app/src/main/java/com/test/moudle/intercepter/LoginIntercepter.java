package com.test.moudle.intercepter;

import android.content.Context;
import android.util.Log;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.test.basemoudle.utils.ActivityUtils;

/**
 * @author zhuj 2019-10-12 15:57.
 */
@Interceptor(priority = 1)
public class LoginIntercepter implements IInterceptor {

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.w("loginIntercepter", " loginIntercepter process " + postcard.getGroup()+" " +postcard.getPath()+ " thread:" + Thread.currentThread().getName());
        //ActivityUtils.startMessage();
        if (postcard.getPath().contains("message")) {
            callback.onInterrupt(new Exception("asdf"));
            ActivityUtils.startLogin();
        } else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        Log.w("loginIntercepter", " loginIntercepter init");
    }
}
