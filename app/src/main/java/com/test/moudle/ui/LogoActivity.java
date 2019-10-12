package com.test.moudle.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import com.person.commonlib.qmui.QMUINotchHelper;
import com.person.commonlib.qmui.QMUIStatusBarHelper;
import com.person.commonlib.utils.ToastUtils;
import com.test.basemoudle.utils.ActivityUtils;
import com.test.basemoudle.utils.sharedPresenter.SharedPreApp;
import com.test.basemoudle.utils.sharedPresenter.SharedPreUser;
import com.test.moudle.BuildConfig;
import com.test.moudle.R;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Logo页面，
 * 未登入过 就跳登入页
 * 登入过后，就跳首页
 */
public class LogoActivity extends Activity {

    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            // Android launched another instance of the root activity into an existing task
            //  so just quietly finish and go away, dropping the user back into the activity
            //  at the top of the stack (ie: the last state of this task)
            // Don't need to finish it again since it's finished in super.onCreate .
            finish();
            return;
        }
        setContentView(R.layout.activity_logo);
        if (!QMUIStatusBarHelper.setStatusBarLightMode(this)) {
            //5.0到6.0以下，无法修改黑色状态图标，就修改状态栏背景为灰色
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.light_status_bg));
            }
        }
        loadSplashFragment();

        ToastUtils.toast(this, " " + BuildConfig.FLAVOR);
    }

    /**
     * 装载登录界面
     */
    private void loadSplashFragment() {
        mSubscription = Observable.timer(1500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {

                        //退出全屏
                        getWindow().setFlags(
                                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                        //boolean isGuide = (boolean) SharedPreApp.getInstance()
                        //        .get(LogoActivity.this, SharedPreApp.KEY_GUIDE, true);
                        ////优先引导页
                        //if (isGuide) {
                        //    //ActivityUtils.startGuide(LogoActivity.this);
                        //    ActivityUtils.startHome();
                        //    finish();
                        //    return;
                        //}

                        long expiredTime = (long) SharedPreUser.getInstance()
                                .get(LogoActivity.this, SharedPreUser.KEY_TOKEN_EXPIRED, 0L);
                        //有效时间, 小于10分钟
                        long time = expiredTime - System.currentTimeMillis();

                        if (time > 10 * 60) {
                            ActivityUtils.startHome();
                        } else {
                            ActivityUtils.startLogin();
                        }
                        finish();
                    }
                });

        if (!SharedPreApp.getInstance().contains(this, SharedPreApp.KEY_NOTCH)) {
            final View decorView = getWindow().getDecorView();

            decorView.post(new Runnable() {

                @Override
                public void run() {
                    boolean isNotch = QMUINotchHelper.hasNotch(LogoActivity.this);
                    SharedPreApp.getInstance()
                            .put(LogoActivity.this, SharedPreApp.KEY_NOTCH, isNotch);
                    if (isNotch) {
                        int saftTop = QMUINotchHelper.getSafeInsetTop(LogoActivity.this);
                        int safeLeft = QMUINotchHelper.getSafeInsetLeft(LogoActivity.this);
                        int statusHeight =
                                QMUIStatusBarHelper.getStatusbarHeight(LogoActivity.this);
                        Log.w("test", " saftTop " + saftTop + " " + safeLeft + " " + statusHeight);
                        SharedPreApp.getInstance()
                                .put(LogoActivity.this, SharedPreApp.NOTCH_TOP, statusHeight);
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mSubscription != null) {
            if (!mSubscription.isUnsubscribed()) {
                mSubscription.unsubscribe();
            }
            mSubscription = null;
        }
    }
}
