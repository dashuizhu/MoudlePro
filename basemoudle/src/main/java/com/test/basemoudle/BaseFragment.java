package com.test.basemoudle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import com.hwangjr.rxbus.RxBus;
import com.person.commonlib.utils.AppUtils;
import com.person.commonlib.utils.ToastUtils;
import com.person.commonlib.view.ProgressDialog;
import com.test.basemoudle.exception.CustomException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import retrofit2.HttpException;

/**
 * Created by zhuj on 17/6/8.
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    protected final String TAG = getClass().getSimpleName();

    private boolean        isRxRegister;
    private ProgressDialog mProgressDialog;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected void showToast(String str) {
        ToastUtils.toast(getContext(), str);
    }

    protected void showToast(@StringRes int resId) {
        showToast(getString(resId));
    }

    @Override
    public void showProgress() {
        showProgress(R.string.com_label_loading);
    }


    public void showProgress(@StringRes int resId) {
        if (mProgressDialog == null) {
            synchronized (this) {
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialog(getContext(), getString(resId));
                }
            }
        } else {
            mProgressDialog.setText(getString(resId));
        }
        mProgressDialog.show();
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
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        if (e instanceof CustomException) { //服务器端业务异常

            if (!TextUtils.isEmpty(e.getMessage())) {
                //showToast(R.string.toast_server_error+ ((CustomException) e).getInterfaceName());
                showToast(e.getMessage());
            }
        } else if (!AppUtils.isNetworkAvailable(getContext())) {
            showToast(R.string.toast_network_none);
        } else if (e instanceof ConnectException
                || e instanceof SocketTimeoutException
                || e instanceof HttpException) {
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
    public void onDestroy() {
        hideProgress();
        unRegisterRxBus();
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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
