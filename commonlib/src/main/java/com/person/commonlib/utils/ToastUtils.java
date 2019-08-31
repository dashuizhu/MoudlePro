package com.person.commonlib.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * toast 工具
 *
 * @author zhuj 2018/8/1 下午2:28
 */
public class ToastUtils extends Toast {
    private volatile static Toast mToast;

    private ToastUtils(Context context) {
        super(context);
    }

    private static Toast getToast(Context context) {
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        }
        return mToast;
    }

    public static void toast(Context context, @StringRes int message) {
        toast(context, context.getString(message));
    }

    public static void toast(Context context, String message) {
        toastRes(context, message);
    }

    private static void toastRes(Context context, String str) {
        Toast toast = getToast(context);
        //View view = toast.getView();
        //
        //TextView tv = view.findViewById(R.id.tv_toast);
        ////Drawable top = ContextCompat.getDrawable(AppApplication.getContext(), resId);
        ////top.setBounds(0, 0, top.getIntrinsicWidth(), (int) (top.getMinimumHeight()));
        ////tv.setCompoundDrawables(null, top, null, null);
        toast.setText(str);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}