package com.test.basemoudle.utils;

import android.content.Context;
import com.alibaba.android.arouter.launcher.ARouter;
import com.test.basemoudle.utils.sharedPresenter.SharedPreUser;

/**
 * @author zhuj 2019-08-30 14:49.
 */
public class ActivityUtils {

    public static void startHome() {
        ARouter.getInstance().build("/app/mainActivity").navigation();
    }

    public static void startLogin() {
        ARouter.getInstance().build("/login/activity").navigation();
    }

    public static void startMessage() {
        ARouter.getInstance().build("/message/messageActivity").navigation();
    }

    public static void exit2Login(Context context) {
        SharedPreUser.getInstance().clear(context);
        startLogin();
    }
}
