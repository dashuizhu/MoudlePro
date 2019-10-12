package com.test.basemoudle.bean;

import lombok.Data;

/**
 * @author zhuj 2019-08-28 20:43.
 */
public class NetworkResult {

    public final static int CODE_SUCCESS             = 0;
    public static final int CODE_TOKEN_ERROR         = 40102;
    public static final int CODE_TOKEN_REFRESH_ERROR = 40103;
    public static final int CODE_NO_GRADE = 40305;

    public static final int TAG_REFRESH   = 1;
    public static final int TAG_LOADMORE  = 2;

    private int    code;
    private String message;
    private long   timestamp;
    /**
     * 用来记录请求类别，是添加、修改、删除等
     * 参考 TAG_DEVICE_XXXXX
     */
    private int    tag;
    private int    position;

    public boolean isNetworkSuccess() {
        return code == CODE_SUCCESS;
    }

    public static int getCodeSuccess() {
        return CODE_SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
