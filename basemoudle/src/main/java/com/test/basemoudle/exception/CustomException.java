package com.test.basemoudle.exception;

import com.test.basemoudle.bean.NetworkResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义错误
 * @author zhuj 2018/8/7 上午10:39
 */
@Data @EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

  private String errorMessage;
  private String interfaceName;//接口名称
  private int code;

  public CustomException(String error) {
    errorMessage = error;
  }

  public CustomException(int errorCode, String error) {
    code = errorCode;
    errorMessage = error;
  }

  public CustomException(NetworkResult result, String interfaceName) {
    code = result.getCode();
    errorMessage = result.getMessage();
    this.interfaceName = interfaceName;
  }

  public CustomException(NetworkResult result) {
    code = result.getCode();
    errorMessage = result.getMessage();
  }

  @Override
  public String getMessage() {
    if (errorMessage != null) {
      return errorMessage;
    }
    return String.valueOf(code);
  }
}
