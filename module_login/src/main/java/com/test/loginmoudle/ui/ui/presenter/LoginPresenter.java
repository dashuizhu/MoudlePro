package com.test.loginmoudle.ui.ui.presenter;

import com.test.basemoudle.BasePresenter;
import com.test.basemoudle.IBaseView;
import com.test.basemoudle.bean.NetworkResult;
import com.test.basemoudle.bean.UserInfoResult;
import com.test.basemoudle.utils.encrypt.MD5;
import com.test.loginmoudle.ui.ui.model.LoginModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhuj 2019-09-03 18:14.
 */
public class LoginPresenter extends BasePresenter {

    private LoginModel mUserModel;


    public LoginPresenter(IBaseView baseView) {
        this.mBaseView = baseView;
        this.mUserModel = new LoginModel();
    }

    public void login(String userName, String passWord,boolean isShowProgress){
        if (isShowProgress) {
            mBaseView.showProgress();
        }
        mUserModel.login(userName, passWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfoResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override public void onError(Throwable e) {
                        mBaseView.onError(e);
                    }

                    @Override public void onNext(UserInfoResult networkResult) {
                        mBaseView.hideProgress();
                        mBaseView.onSuccess(networkResult);
                    }
                });
    }




    public void sendVerifyCode(String phone,String templateId) {
        mBaseView.showProgress();
        mUserModel.sendVerifyCode(phone, templateId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NetworkResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override public void onError(Throwable e) {
                        mBaseView.onError(e);
                    }

                    @Override public void onNext(NetworkResult networkResult) {
                        mBaseView.hideProgress();
                        mBaseView.onSuccess(networkResult);
                    }
                });
    }

    public void findPassword(String phone,String password,String verifyCode) {
        mBaseView.showProgress();
        mUserModel.findPassword(phone, MD5.getStringMD5(password),verifyCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NetworkResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onComplete() {

                    }
                    @Override public void onError(Throwable e) {
                        mBaseView.onError(e);
                    }

                    @Override public void onNext(NetworkResult networkResult) {
                        mBaseView.hideProgress();
                        //networkResult.setTag(NetworkResult.TAG_FORGET_PASSWORD);
                        mBaseView.onSuccess(networkResult);
                    }
                });
    }

}
