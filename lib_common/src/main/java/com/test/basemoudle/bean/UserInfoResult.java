package com.test.basemoudle.bean;

/**
 * Description: 用户信息返回
 * Author: jws
 * Date: 2017/7/26 10:42
 * Version: 1.0
 */
public class UserInfoResult extends NetworkResult {
  private UserBean data;

  public UserBean getData() {
    return data;
  }

  public void setData(UserBean data) {
    this.data = data;
  }
}
