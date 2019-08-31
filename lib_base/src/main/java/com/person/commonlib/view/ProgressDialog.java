package com.person.commonlib.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.TextView;
import com.person.commonlib.R;

/**
 * 加载进度框
 *
 * @author zhuj
 *         2017/6/19 上午10:40.
 */
public class ProgressDialog extends Dialog {

  private TextView mTvContent;
  private String mContent;

  public ProgressDialog(@NonNull Context context,String str) {
    super(context, R.style.MyDialog);
    //super(context);

    if (TextUtils.isEmpty(str)) {
      mContent = context.getString(R.string.com_label_loading);
    } else {
      mContent = str;
    }

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.com_dialog_progress);
    mTvContent = findViewById(R.id.tv_content);
    mTvContent.setText(mContent);
  }

  public void setText(String content) {
    mContent = content;
    if (mTvContent != null) {
      mTvContent.setText(content);
    }
  }
}
