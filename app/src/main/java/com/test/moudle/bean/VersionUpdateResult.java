package com.test.moudle.bean;

import com.test.basemoudle.bean.NetworkResult;
import lombok.Data;

/**
 * 版本检测
 * @author zhuj
 * @date 2017/7/20 上午10:14.
 */
public class VersionUpdateResult extends NetworkResult {

  /**
   * 已经是最新版
   */
  public static final int STATUS_ALREAD_LAST = 0;
  /**
   * 有新版
   */
  public static final int STATUS_NEW  = 1;
  /**
   * 强制升级
   */
  public static final int STATUS_FORCED = 2;
  private DataBean data;

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public boolean isForced() {
    if (data == null) return false;
    return data.getUpgradeFlag() == STATUS_FORCED;
  }

  @Data
  public static class DataBean {
    private int upgradeFlag;
    private String downloadLink;
    private String upgradeContent;

  }
}
