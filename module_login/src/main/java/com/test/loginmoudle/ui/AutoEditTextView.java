package com.test.loginmoudle.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.test.loginmoudle.R;
import rx.functions.Action1;

/**
 * Description:
 * Author: jws
 * Date: 2018/9/7
 */
public class AutoEditTextView extends RelativeLayout{
    private int mAutoBackground;
    private int mAutoCloseImg;
    private int mAutoTextColor;
    private int mAutoTextHintColor;
    private String mAutoTextContent;
    private String mAutoTextHintContent;
    private float mAutoTextSize;
    private int mAutoMaxEms;
    private int mAutoLeftDrawable;
    private boolean mIsPassword;
    private boolean mIsNumber;
    private boolean mIsShowLine;

    private ImageView mIvAutoLeft;
    private EditText mEtAuto;
    private ImageView mIvClose;
    private CheckBox mCbShowPassword;
    private RelativeLayout mRlAutoEdit;
    private View mVline;


    private boolean isGetHistory = false;
    private boolean isFocusUser = false;


    public AutoEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AutoEditTextView);
        mAutoBackground = ta.getResourceId(R.styleable.AutoEditTextView_mAutoBackground, R.color.transparent);
        mAutoCloseImg = ta.getResourceId(R.styleable.AutoEditTextView_mAutoCloseImg, R.mipmap.login_shut_down);
        mAutoTextColor = ta.getColor(R.styleable.AutoEditTextView_mAutoTextColor,context.getResources().getColor(R.color.text_normal));
        mAutoTextHintColor = ta.getColor(R.styleable.AutoEditTextView_mAutoTextHintColor, context.getResources().getColor(R.color.text_normal));
        mAutoTextContent = ta.getString(R.styleable.AutoEditTextView_mAutoTextContent);
        mAutoTextHintContent = ta.getString(R.styleable.AutoEditTextView_mAutoTextHintContent);
        mAutoTextSize = ta.getDimension(R.styleable.AutoEditTextView_mAutoTextSize, 14);
        mAutoLeftDrawable = ta.getResourceId(R.styleable.AutoEditTextView_mAutoLeftDrawable, 0);
        mAutoMaxEms = ta.getInt(R.styleable.AutoEditTextView_mAutoMaxEms, 99);
        mIsPassword = ta.getBoolean(R.styleable.AutoEditTextView_mIsPassword, false);
        mIsNumber = ta.getBoolean(R.styleable.AutoEditTextView_mIsNumber, false);
        mIsShowLine = ta.getBoolean(R.styleable.AutoEditTextView_mIsShowLine, false);

        ta.recycle();
        View itemView = LayoutInflater.from(context).inflate(R.layout.view_auto_edit_view, this);
        mRlAutoEdit  = itemView.findViewById(R.id.rl_auto_edit);
        mIvAutoLeft  = itemView.findViewById(R.id.iv_auto_left);
        mEtAuto  = itemView.findViewById(R.id.et_auto);
        mIvClose  = itemView.findViewById(R.id.iv_close);
        mCbShowPassword  = itemView.findViewById(R.id.cb_show_password);
        mVline  = itemView.findViewById(R.id.v_line);

        initViewInfo();
        initLister();

    }

    public boolean isGetHistory() {
        return isGetHistory;
    }

    public void setGetHistory(boolean getHistory) {
        isGetHistory = getHistory;
    }


    public String getText(){
        return mEtAuto.getText().toString().trim();
    }

    public EditText getEditTextView(){
        return mEtAuto;
    }


    private void initViewInfo() {
        mRlAutoEdit.setBackgroundResource(mAutoBackground);
        if (mAutoLeftDrawable != 0) {
            mIvAutoLeft.setBackgroundResource(mAutoLeftDrawable);
        } else {
            mIvAutoLeft.setVisibility(GONE);
        }
        InputFilter[] filters = {new InputFilter.LengthFilter(mAutoMaxEms)};
        mEtAuto.setFilters(filters);
        mIvClose.setImageResource(mAutoCloseImg);
        mEtAuto.setHintTextColor(mAutoTextHintColor);
        mEtAuto.setTextColor(mAutoTextColor);
        mEtAuto.setMaxLines(1);
        mEtAuto.setSingleLine();
        mEtAuto.setTextSize(mAutoTextSize);
        mEtAuto.setHint(mAutoTextHintContent);
        mEtAuto.setText(mAutoTextContent);
        mVline.setVisibility(mIsShowLine?VISIBLE:GONE);
        if(mIsPassword){
            mEtAuto.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        if(mIsNumber){
            mEtAuto.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

    }

    private void initLister() {
        mIvClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtAuto.setText("");
            }
        });
        RxTextView.textChanges(mEtAuto).subscribe(new Action1<CharSequence>() {
            @Override public void call(CharSequence charSequence) {
                boolean isHasContent = !TextUtils.isEmpty(charSequence);
                mIvClose.setVisibility(isHasContent && isFocusUser?View.VISIBLE:View.GONE);
                mCbShowPassword.setVisibility(isHasContent && mIsPassword?View.VISIBLE:View.GONE);
                mEtAuto.setSelected(isHasContent);
                if(listener!=null){
                    listener.onChange(charSequence);
                }
            }
        });

        mEtAuto.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                isFocusUser = b;
                if(!isFocusUser){
                    mIvClose.setVisibility(View.GONE);
                }else{
                    if(mEtAuto.getText().length()>0){
                        if(mIsPassword){
                            mCbShowPassword.setVisibility(VISIBLE);
                        }
                        mIvClose.setVisibility(View.VISIBLE);
                    }else{
                        mCbShowPassword.setVisibility(GONE);
                    }
                }
            }
        });

        mCbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(isGetHistory){
                        mEtAuto.setText("");
                        isGetHistory = false;
                    }
                    mEtAuto.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mEtAuto.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mEtAuto.requestFocus();
                //AppUtils.initSelecton(mEtAuto);
            }
        });
    }

    public void setListener(TextChangeListener listener) {
        this.listener = listener;
    }

    private TextChangeListener listener;

    public interface  TextChangeListener{
        void onChange(CharSequence charSequence);
    }
}
