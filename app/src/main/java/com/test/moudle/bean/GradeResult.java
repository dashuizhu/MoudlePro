package com.test.moudle.bean;

import com.test.basemoudle.bean.NetworkResult;
import com.test.basemoudle.bean.UserBean;
import lombok.Data;

/**
 * Version: 1.0
 */
public class GradeResult extends NetworkResult {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Data
    public static class DataBean {
        private UserBean.SchoolBean school;
        private UserBean.GradeBean  grade;
    }
}
