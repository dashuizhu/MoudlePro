package com.test.module_work.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author zhuj 2018/9/18 上午10:48.
 */
public class TaskWorkStudentSubmitBean implements Parcelable {

    /**
     * id : 7
     * completeNum : 0
     * isComplete : true
     * student : {"id":10,"name":"阙一","avatar":"avatar","sex":null,"birthday":"","blood":null,"interest":"","speciality":"","parentPhone":null}
     */

    private int id;
    private int taskRightNum;
    //private double score;
    private int accuracy;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.taskRightNum);
        //dest.writeDouble(this.score);
        dest.writeInt(this.accuracy);
    }

    protected TaskWorkStudentSubmitBean(Parcel in) {
        this.id = in.readInt();
        this.taskRightNum = in.readInt();
        //this.score = in.readDouble();
        this.accuracy = in.readInt();
    }

    public static final Creator<TaskWorkStudentSubmitBean> CREATOR = new Creator<TaskWorkStudentSubmitBean>() {
        @Override
        public TaskWorkStudentSubmitBean createFromParcel(Parcel source) {
            return new TaskWorkStudentSubmitBean(source);
        }

        @Override
        public TaskWorkStudentSubmitBean[] newArray(int size) {
            return new TaskWorkStudentSubmitBean[size];
        }
    };
}
