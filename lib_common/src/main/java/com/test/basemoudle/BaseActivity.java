package com.test.basemoudle;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.hwangjr.rxbus.RxBus;
import com.person.commonlib.qmui.QMUIStatusBarHelper;
import com.person.commonlib.utils.AppUtils;
import com.person.commonlib.utils.DensityHelp;
import com.person.commonlib.utils.ToastUtils;
import com.person.commonlib.view.ProgressDialog;
import com.test.basemoudle.exception.CustomException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import retrofit2.HttpException;

/**
 * Created by zhuj on 2017/6/6 下午2:19.
 */
public class BaseActivity extends AppCompatActivity implements IBaseView {

    protected final String TAG = this.getClass().getSimpleName();

    private boolean        isRxRegister;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DensityHelp.setOrientationWidth(this, false);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if (!QMUIStatusBarHelper.setStatusBarLightMode(this)) {
            //5.0到6.0以下，无法修改黑色状态图标，就修改状态栏背景为灰色
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.light_status_bg));
            }
        }
    }

    @Override
    public void showProgress() {
        showProgress(R.string.com_label_loading);
    }

    public void showProgress(@StringRes int resId) {
        if (mProgressDialog == null) {
            synchronized (this) {
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialog(this, getString(resId));
                }
            }
        } else {
            mProgressDialog.setText(getString(resId));
        }
        mProgressDialog.show();
    }

    /**
     * 透明状态栏
     * 必须在setContentView下面调用
     */
    protected void transitionSystemBar() {
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            lp.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        hideProgress();
        e.printStackTrace();
        if (isFinishing()) {
            return;
        }
        if (e instanceof CustomException) { //服务器端业务异常

            if (!TextUtils.isEmpty(e.getMessage())) {
                //showToast(R.string.toast_server_error+ ((CustomException) e).getInterfaceName());
                showToast(e.getMessage());
            }
        } else if (!AppUtils.isNetworkAvailable(this)) {
            showToast(R.string.toast_network_none);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException
                || e instanceof InterruptedException
                || e instanceof SocketTimeoutException
                || e instanceof HttpException
                || e instanceof IOException) {
            showToast(R.string.toast_network_error);
        } else {
            showToast(R.string.toast_unknown_error);
        }
    }

    @Override
    public void onLoadError(Throwable e) {
        onError(e);
    }

    @Override
    public void onSuccess(Object successObj) {

    }

    @Override
    protected void onDestroy() {
        hideProgress();
        super.onDestroy();
    }

    protected void registerRxBus() {
        if (!isRxRegister) {
            synchronized (this) {
                if (!isRxRegister) {
                    RxBus.get().register(this);
                    isRxRegister = true;
                }
            }
        }
    }

    protected void unRegisterRxBus() {
        if (isRxRegister) {
            synchronized (this) {
                if (isRxRegister) {
                    RxBus.get().unregister(this);
                    isRxRegister = false;
                }
            }
        }
    }

    protected void showToast(@StringRes int resId) {
        showToast(getString(resId));
    }

    protected void showToast(String str) {
        ToastUtils.toast(this, str);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!AppUtils.isApkDebugModel()) {
            //MobclickAgent.onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!AppUtils.isApkDebugModel()) {
            //MobclickAgent.onPause(this);
        }
    }

    @Override
    public void finish() {
        hideKeyboard();
        super.finish();
    }

    public void hideKeyboard() {
        View view = getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) BaseActivity.this.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        if (view != null && imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}