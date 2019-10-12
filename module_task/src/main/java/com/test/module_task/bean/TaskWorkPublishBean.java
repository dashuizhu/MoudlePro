package com.test.module_task.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**
 * @author zhuj 2018/9/17 下午6:14.
 */
public class TaskWorkPublishBean implements Parcelable {

    /**
     * id : 48
     * isCheckState : false
     * isTodayCall : false
     * studentNum : 6
     * submitNum : 0
     * publishTime : 565837
     * endTime : 565837
     * task : {"id":3,"title":"排队上车","subTitle":"认识前后","publishNum":0,"taskNum":5,"compleTime":500,"lastPublishTime":0,"state":0,"icon":null}
     * taskCategory : {"id":3,"categoryName":"集合与分类","isShow":true}
     */

    private int                       id;
    private boolean                   isCheckState;
    private boolean                   isTodayCall;
    private int                       studentNum;
    private int                       submitNum;
    private int                       publishTime;
    private int                       endTime;
    private boolean                   isState;
    private TaskBean                  task;
    private TaskCategoryBean          taskCategory;
    private List<TaskWorkStudentBean> students;

    /**
     * 是否全部交齐
     */
    public boolean isStudentAllSuccess() {
        return studentNum == submitNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeByte(this.isCheckState ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isTodayCall ? (byte) 1 : (byte) 0);
        dest.writeInt(this.studentNum);
        dest.writeInt(this.submitNum);
        dest.writeInt(this.publishTime);
        dest.writeInt(this.endTime);
        dest.writeByte(this.isState ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.task, flags);
        dest.writeParcelable(this.taskCategory, flags);
        dest.writeTypedList(this.students);
    }

    protected TaskWorkPublishBean(Parcel in) {
        this.id = in.readInt();
        this.isCheckState = in.readByte() != 0;
        this.isTodayCall = in.readByte() != 0;
        this.studentNum = in.readInt();
        this.submitNum = in.readInt();
        this.publishTime = in.readInt();
        this.endTime = in.readInt();
        this.isState = in.readByte() != 0;
        this.task = in.readParcelable(TaskBean.class.getClassLoader());
        this.taskCategory = in.readParcelable(TaskCategoryBean.class.getClassLoader());
        this.students = in.createTypedArrayList(TaskWorkStudentBean.CREATOR);
    }

    public static final Creator<TaskWorkPublishBean> CREATOR = new Creator<TaskWorkPublishBean>() {
        @Override
        public TaskWorkPublishBean createFromParcel(Parcel source) {
            return new TaskWorkPublishBean(source);
        }

        @Override
        public TaskWorkPublishBean[] newArray(int size) {
            return new TaskWorkPublishBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCheckState() {
        return isCheckState;
    }

    public void setCheckState(boolean checkState) {
        isCheckState = checkState;
    }

    public boolean isTodayCall() {
        return isTodayCall;
    }

    public void setTodayCall(boolean todayCall) {
        isTodayCall = todayCall;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public int getSubmitNum() {
        return submitNum;
    }

    public void setSubmitNum(int submitNum) {
        this.submitNum = submitNum;
    }

    public int getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(int publishTime) {
        this.publishTime = publishTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public boolean isState() {
        return isState;
    }

    public void setState(boolean state) {
        isState = state;
    }

    public TaskBean getTask() {
        return task;
    }

    public void setTask(TaskBean task) {
        this.task = task;
    }

    public TaskCategoryBean getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(TaskCategoryBean taskCategory) {
        this.taskCategory = taskCategory;
    }

    public List<TaskWorkStudentBean> getStudents() {
        return students;
    }

    public void setStudents(List<TaskWorkStudentBean> students) {
        this.students = students;
    }
}
