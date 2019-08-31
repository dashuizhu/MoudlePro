package com.test.basemoudle.utils;

import android.content.Context;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author zhuj 2019-08-30 14:49.
 */
public class ActivityUtils {

    public static void startHome() {
        ARouter.getInstance().build("/app/mainActivity").navigation();
    }

    public static void startLogin() {
        ARouter.getInstance().build("/test/activity").navigation();
    }

    public static void startMessage() {
        ARouter.getInstance().build("/message/messageListActivity").navigation();
    }
}
