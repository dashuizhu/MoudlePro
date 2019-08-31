package com.test.loginmoudle.ui.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.test.basemoudle.BaseActivity;
import com.test.loginmoudle.R;
import rx.Observable;
import rx.Observer;
import rx.functions.Func3;

/**
 * 忘记密码页面
 *
 * @author jws
 * @date 2017-06-014 09：55：33
 */
public class ForgetPasswordActivity extends BaseActivity {

    ///**
    // * 获取验证码倒计时时间
    // */
    //@BindView(R.id.aet_phone)    AutoEditTextView mAetPhone;
    //@BindView(R.id.aet_pwd)      AutoEditTextView mAetPwd;
    //@BindView(R.id.aet_code)     AutoEditTextView mAetCode;
    //@BindView(R.id.tv_get_code)  TextView         tvGetCode;
    //@BindView(R.id.btn_register) Button           btnRegister;
    //@BindView(R.id.ll_all)       LinearLayout     mLlAll;
    //
    //private UserPresenter mUserPresenter;
    //private static final int GET_CODE_STARTING = 1;
    //private static final int GET_CODE_STARTED = 2;
    //private Handler mHandler = new Handler();
    //private String mOldPhone;
    //private String phone;
    //private int currentDownTime = 60;
    //

    public final static String KEY_PHONE = "key_phone";

    public static void startForgetPassword(Activity context, String phone) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        intent.putExtra(KEY_PHONE, phone);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        //initializeDependencyInjector();
        //initViews();
    }
    //
    //@OnClick(R.id.layout_header_back)
    //public void onBack() {
    //    finish();
    //}
    //
    //@Override
    //protected void onStart() {
    //    super.onStart();
    //}
    //
    //@Override
    //protected void onDestroy() {
    //    if (mUserPresenter != null) {
    //        mUserPresenter.destroy();
    //    }
    //    if (mRunnable != null) {
    //        mHandler.removeCallbacks(mRunnable);
    //    }
    //    super.onDestroy();
    //}
    //
    //private void initViews() {
    //    String phone = getIntent().getStringExtra(AppString.KEY_PHONE);
    //    mAetPhone.getEditTextView().setText(phone);
    //    mAetPhone.setListener(new AutoEditTextView.TextChangeListener() {
    //        @Override
    //        public void onChange(CharSequence charSequence) {
    //            checkPhone(isValidPhone(charSequence));
    //        }
    //    });
    //
    //    checkPhone(isValidPhone(phone));
    //    Observable<CharSequence> rxPhone = RxTextView.textChanges(mAetPhone.getEditTextView());
    //    Observable<CharSequence> rxPwd = RxTextView.textChanges(mAetPwd.getEditTextView());
    //    Observable<CharSequence> rxSmsCode = RxTextView.textChanges(mAetCode.getEditTextView());
    //    Observable.combineLatest(rxPhone, rxPwd, rxSmsCode,
    //            new Func3<CharSequence, CharSequence, CharSequence, Boolean>() {
    //                @Override
    //                public Boolean call(CharSequence phone, CharSequence pwd, CharSequence code) {
    //
    //                    return isValidRegister();
    //                }
    //            }).subscribe(new Observer<Boolean>() {
    //        @Override
    //        public void onCompleted() {
    //
    //        }
    //
    //        @Override
    //        public void onError(Throwable e) {
    //            e.printStackTrace();
    //        }
    //
    //        @Override
    //        public void onNext(Boolean isValid) {
    //            btnRegister.setEnabled(isValid);
    //            if(isValid){
    //                btnRegister.setBackground(getResources().
    //                        getDrawable(R.drawable.bg_solid_light_orange_radius_16));
    //            }else{
    //                btnRegister.setBackground(getResources().
    //                        getDrawable(R.drawable.bg_solid_light_gray_radius_16));
    //            }
    //        }
    //    });
    //
    //    mLlAll.setOnTouchListener(new View.OnTouchListener() {
    //        @Override
    //        public boolean onTouch(View v, MotionEvent event) {
    //            cleanFoces();
    //            return false;
    //        }
    //    });
    //}
    //
    //private boolean isValidRegister() {
    //    String phone = mAetPhone.getText();
    //    if (TextUtils.isEmpty(phone)) {
    //        return false;
    //    }
    //    if (mAetCode.getText().length() < 6) {
    //        return false;
    //    }
    //    if (mAetPwd.getText().length() == 0) {
    //        return false;
    //    }
    //    return true;
    //}
    //
    //private void checkPhone(boolean validPhone) {
    //    if (validPhone) {
    //        tvGetCode.setTextColor(getResources().getColor(R.color.orange));
    //        tvGetCode.setEnabled(true);
    //    } else {
    //        tvGetCode.setTextColor(getResources().getColor(R.color.text_light));
    //        tvGetCode.setEnabled(false);
    //    }
    //}
    //
    //
    //@Override
    //public void onSuccess(Object successObj) {
    //    super.onSuccess(successObj);
    //    if (successObj instanceof UserInfoResult) {
    //
    //    } else {
    //        int tag = ((NetworkResult) successObj).getTag();
    //        if (tag == NetworkResult.TAG_FORGET_PASSWORD) {
    //            ToastUtils.toast("修改成功");
    //            SharedPreApp.getInstance().clearUserInfo();
    //            setResult(RESULT_OK);
    //            finish();
    //        } else {
    //            mOldPhone = mAetPhone.getText();
    //            mHandler.postDelayed(mRunnable, 1000);
    //        }
    //    }
    //}
    //
    //private void initializeDependencyInjector() {
    //    AppPresnterComponent authenticationComponent =
    //            DaggerAppPresnterComponent.builder().appModelModule(new AppModelModule(this)).build();
    //    mUserPresenter = authenticationComponent.getUserPresenter();
    //}
    //
    //
    ///**
    // * 校验手机号是否符合规则
    // */
    //private boolean isValidPhone(CharSequence phoneNumber) {
    //    return AppUtils.isRightPhoneNumber(phoneNumber.toString());
    //}
    //
    //private boolean checkInfo(String phone, String password, String code) {
    //    if (TextUtils.isEmpty(phone)) {
    //        showToast(getResources().getString(R.string.toast_phone));
    //        return false;
    //    }
    //    if (TextUtils.isEmpty(code)) {
    //        showToast(getResources().getString(R.string.toast_code));
    //        return false;
    //    }
    //    if (TextUtils.isEmpty(password)) {
    //        showToast(getResources().getString(R.string.toast_password));
    //        return false;
    //    }
    //    if (!isValidPhone(phone)) {
    //        showToast(getResources().getString(R.string.toast_phone_format));
    //        return false;
    //    }
    //    if (code.length() != 6) {
    //        showToast(getResources().getString(R.string.toast_code_error));
    //        return false;
    //    }
    //    if (password.length() > 16 || password.length() < 6) {
    //        showToast(getResources().getString(R.string.toast_password_length));
    //        return false;
    //    }
    //    return true;
    //}
    //
    //@OnClick({R.id.tv_get_code, R.id.btn_register})
    //public void onViewClicked(View view) {
    //    String smsCode = mAetCode.getText();
    //    phone = mAetPhone.getText();
    //    String password = mAetPwd.getText();
    //    switch (view.getId()) {
    //        case R.id.tv_get_code:
    //            if (TextUtils.isEmpty(phone)) {
    //                showToast("手机号码不能为空");
    //                return;
    //            }
    //            if (!isValidPhone(phone)) {
    //                showToast("手机号码格式不正确");
    //                return;
    //            }
    //            mUserPresenter.sendVerifyCode(phone, "0");
    //            break;
    //        case R.id.btn_register:
    //            if (checkInfo(phone, password, smsCode)) {
    //                mUserPresenter.findPassword(phone, password, smsCode);
    //            }
    //            break;
    //        default:
    //            break;
    //    }
    //    cleanFoces();
    //}
    //
    //Runnable mRunnable = new Runnable() {
    //    @Override
    //    public void run() {
    //        if (currentDownTime != 1) {
    //            currentDownTime--;
    //            changeGetCodeStyle(GET_CODE_STARTING);
    //            mHandler.postDelayed(this, 1000);
    //        } else {
    //            changeGetCodeStyle(GET_CODE_STARTED);
    //            currentDownTime = 60;
    //        }
    //    }
    //};
    //
    //private void changeGetCodeStyle(int state) {
    //    switch (state) {
    //        case GET_CODE_STARTING:
    //            String countDownNum = "(" + currentDownTime + ")重新获取";
    //            tvGetCode.setText(countDownNum);
    //            tvGetCode.setTextColor(getResources().getColor(R.color.text_light));
    //            tvGetCode.setEnabled(false);
    //            break;
    //        case GET_CODE_STARTED:
    //            tvGetCode.setTextColor(getResources().getColor(R.color.orange));
    //            if (!TextUtils.isEmpty(mOldPhone) && mAetPhone.getText().equals(mOldPhone)) {
    //                tvGetCode.setText(getResources().getString(R.string.label_rest_get));
    //            } else {
    //                tvGetCode.setText("获取验证码");
    //            }
    //            tvGetCode.setEnabled(true);
    //            break;
    //        default:
    //            break;
    //    }
    //}
    //
    //private void cleanFoces() {
    //    mLlAll.setFocusable(true);
    //    mLlAll.setFocusableInTouchMode(true);
    //    mLlAll.requestFocus();
    //    hideKeyboard();
    //}


}
