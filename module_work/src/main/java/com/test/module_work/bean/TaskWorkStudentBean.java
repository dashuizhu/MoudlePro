package com.test.module_work.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author zhuj 2018/9/18 上午10:48.
 */
public class TaskWorkStudentBean implements Parcelable {

    /**
     * id : 7
     * completeNum : 0
     * isComplete : true
     * student : {"id":10,"name":"阙一","avatar":"avatar","sex":null,"birthday":"","blood":null,"interest":"","speciality":"","parentPhone":null}
     */


    private int id;
    private String name;
    private String avatar;
    private TaskWorkStudentSubmitBean taskSubmit;

    public boolean isSubmit() {
        return taskSubmit != null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.avatar);
    }

    protected TaskWorkStudentBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.avatar = in.readString();
    }

    public static final Creator<TaskWorkStudentBean> CREATOR = new Creator<TaskWorkStudentBean>() {
        @Override
        public TaskWorkStudentBean createFromParcel(Parcel source) {
            return new TaskWorkStudentBean(source);
        }

        @Override
        public TaskWorkStudentBean[] newArray(int size) {
            return new TaskWorkStudentBean[size];
        }
    };
}
