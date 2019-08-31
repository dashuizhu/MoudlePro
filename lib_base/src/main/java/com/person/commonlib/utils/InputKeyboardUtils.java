package com.person.commonlib.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @author zhuj 2017/10/23 上午11:27.
 */
public class InputKeyboardUtils {

  /**
   * 显示软键盘
   * @param context
   * @param mEtComment
   */
  public static void showKeyboard(Context context, EditText mEtComment) {
    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    //imm.showSoftInput(mEtComment, InputMethodManager.SHOW_IMPLICIT);
    mEtComment.requestFocus();
    imm.showSoftInput(mEtComment, InputMethodManager.SHOW_IMPLICIT);
    //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
  }

  public static void hideKeyborad(Context context, EditText mEtComment) {
    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    if (imm != null) {
      imm.hideSoftInputFromWindow(mEtComment.getWindowToken(), 0);
    }
  }

}
