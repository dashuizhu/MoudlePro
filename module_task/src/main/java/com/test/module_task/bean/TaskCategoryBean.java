package com.test.module_task.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TaskCategoryBean implements Parcelable {
    /**
     * id : 3
     * categoryName : 集合与分类
     * isShow : true
     */

    private int     id;
    private String  categoryName;
    private boolean isShow;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.categoryName);
        dest.writeByte(this.isShow ? (byte) 1 : (byte) 0);
    }

    protected TaskCategoryBean(Parcel in) {
        this.id = in.readInt();
        this.categoryName = in.readString();
        this.isShow = in.readByte() != 0;
    }

    public static final Creator<TaskCategoryBean> CREATOR = new Creator<TaskCategoryBean>() {
        @Override
        public TaskCategoryBean createFromParcel(Parcel source) {
            return new TaskCategoryBean(source);
        }

        @Override
        public TaskCategoryBean[] newArray(int size) {
            return new TaskCategoryBean[size];
        }
    };
}