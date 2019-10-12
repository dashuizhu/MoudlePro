package com.test.module_work.bean;

import com.test.basemoudle.bean.NetworkResult;
import java.util.List;

/**
 * @author zhuj 2018/9/17 下午6:12.
 */
public class TaskWorkPublishResult extends NetworkResult {


    /**
     * data : {"pageSize":10,"currPage":1,"contList":[{"id":1,"courseThemeId":2,"title":"天赋认知课程","subTitle":"天赋认知课程副标题","isCheck":false,"isTodayCall":false,"totalNum":4,"conductNum":1,"studentSum":2,"studentDone":0,"publishTime":1536981577,"endTime":1538882414}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pageSize : 10
         * currPage : 1
         * contList : [{"id":1,"courseThemeId":2,"title":"天赋认知课程","subTitle":"天赋认知课程副标题","isCheck":false,"isTodayCall":false,"totalNum":4,"conductNum":1,"studentSum":2,"studentDone":0,"publishTime":1536981577,"endTime":1538882414}]
         */

        private int pageSize;
        private int currPage;
        private List<TaskWorkPublishBean> contList;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public List<TaskWorkPublishBean> getContList() {
            return contList;
        }

        public void setContList(List<TaskWorkPublishBean> contList) {
            this.contList = contList;
        }
    }
}
