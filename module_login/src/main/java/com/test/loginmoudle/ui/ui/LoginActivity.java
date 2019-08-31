package com.test.loginmoudle.ui.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.person.commonlib.utils.AppUtils;
import com.test.basemoudle.BaseActivity;
import com.test.basemoudle.bean.UserInfoResult;
import com.test.basemoudle.utils.ActivityUtils;
import com.test.basemoudle.utils.sharedPresenter.SharedPreApp;
import com.test.basemoudle.utils.sharedPresenter.SharedPreUser;
import com.test.loginmoudle.R;
import com.test.loginmoudle.ui.AutoEditTextView;

/**
 * 登入
 *
 * @author zhuj
 * @date 2017/6/15 上午9:21
 */
@Route(path = "/test/activity")
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    Button           mBtnLogin;
    AutoEditTextView mAetPhone;
    AutoEditTextView mAetPwd;
    LinearLayout     mLlAll;
    TextView         mTvForgetPsd;

    //private UserPresenter mUserPresenter;
    private Bundle mSavedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerRxBus();
        //initializeDependencyInjector();
        initViews();
        initUserInfo();
        initLoginState();
        if (savedInstanceState != null) {
            mSavedInstanceState = savedInstanceState;
        }

        mLlAll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cleanFoces();
                return false;
            }
        });

        mAetPhone.setListener(new AutoEditTextView.TextChangeListener() {
            @Override
            public void onChange(CharSequence charSequence) {
                mAetPwd.getEditTextView().setText("");
            }
        });
        mBtnLogin.setOnClickListener(this);
        mTvForgetPsd.setOnClickListener(this);
    }

    private void initViews() {
        mAetPhone = findViewById(R.id.aet_phone);
        mAetPwd = findViewById(R.id.aet_pwd);
        mBtnLogin = findViewById(R.id.btn_login);
        mLlAll = findViewById(R.id.ll_all);
        mTvForgetPsd = findViewById(R.id.tv_forget_password);
    }

    private void initUserInfo() {
        String userName =
                SharedPreApp.getInstance().get(this, SharedPreApp.KEY_USER_NAME, "").toString();
        String passWord =
                SharedPreApp.getInstance().get(this, SharedPreApp.KEY_PASSWORD, "").toString();
        mAetPhone.getEditTextView().setText(userName);

        AppUtils.initSelecton(mAetPhone.getEditTextView());
        if (!TextUtils.isEmpty(passWord)) {
            mAetPwd.setGetHistory(true);
        }
        mAetPwd.getEditTextView().setText(passWord);
        AppUtils.initSelecton(mAetPwd.getEditTextView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSavedInstanceState != null) {
            initUserInfo();
            mSavedInstanceState = null;
        }
    }

    private void initLoginState() {
        long expiredTime =
                (long) SharedPreUser.getInstance().get(this, SharedPreUser.KEY_TOKEN_EXPIRED, 0L);
        //有效时间
        long time = expiredTime - System.currentTimeMillis();
        if (time > 10 * 60) {
            ActivityUtils.startHome();
            finish();
        } else {
            //清空用户id
            SharedPreUser.getInstance().remove(this, SharedPreUser.KEY_MEMBER);
        }
    }

    @Override
    protected void onDestroy() {
        //if (mUserPresenter != null) {
        //    mUserPresenter.destroy();
        //}
        super.onDestroy();
    }

    @Override
    public void onSuccess(Object successObj) {
        super.onSuccess(successObj);
        if (successObj instanceof UserInfoResult) {
            int rank = SharedPreUser.getInstance().getKeyGradeRank(-1);
            if (rank != -1) {
                showToast("登录成功");
            }
            ActivityUtils.startHome();
            //startService(new Intent(this, QueryMessageService.class));
            finish();
        }
    }

    /**
     * 校验手机号是否符合规则
     */
    private boolean isValidPhone(CharSequence phoneNumber) {
        return AppUtils.isRightPhoneNumber(phoneNumber.toString());
    }

    private boolean checkInfo(String phone, String password) {
        if (TextUtils.isEmpty(phone)) {
            showToast(getResources().getString(R.string.login_toast_phone));
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            showToast(getResources().getString(R.string.login_toast_phone));
            return false;
        }
        if (!isValidPhone(phone)) {
            showToast(getResources().getString(R.string.login_toast_phone_format));
            return false;
        }
        if (password.length() > 20 || password.length() < 6) {
            showToast(getResources().getString(R.string.login_toast_password_length));
            return false;
        }
        return true;
    }

    private void initializeDependencyInjector() {
        //AppPresnterComponent authenticationComponent =
        //        DaggerAppPresnterComponent.builder().appModelModule(new AppModelModule(this)).build();
        //mUserPresenter = authenticationComponent.getUserPresenter();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            String userName = mAetPhone.getText();
            String passWord = mAetPwd.getText();
            if (checkInfo(userName, passWord)) {
                //mUserPresenter.login(userName, passWord, true);
            }
        } else if (view.getId() == R.id.tv_forget_password) {
            ForgetPasswordActivity.startForgetPassword(this, mAetPhone.getText());
        }
        cleanFoces();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        initUserInfo();
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgress();
    }

    private void cleanFoces() {
        mLlAll.setFocusable(true);
        mLlAll.setFocusableInTouchMode(true);
        mLlAll.requestFocus();
        hideKeyboard();
    }
}
