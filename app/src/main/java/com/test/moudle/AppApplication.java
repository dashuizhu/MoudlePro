package com.test.moudle;

import android.content.Context;
import com.test.basemoudle.BaseApplication;
import com.test.basemoudle.network.RetrofitManager;

/**
 * @author zhuj 2019-08-30 16:45.
 */
public class AppApplication extends BaseApplication {

    public static boolean isCheckVersion = false;

    @Override
    public void onCreate() {
        super.onCreate();
        AppApplication.getContext();

    }


}
