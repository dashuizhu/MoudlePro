package com.test.module_work.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class TaskBean implements Parcelable {
    /**
     * id : 3
     * title : 排队上车
     * subTitle : 认识前后
     * publishNum : 0
     * taskNum : 5
     * compleTime : 500
     * lastPublishTime : 0
     * state : 0
     * icon : null
     */

    private int                         id;
    private String                      title;
    private String                      subTitle;
    private String                      taskCode;
    private String                      mediaFileUrl;
    private int                         mediaFileSize;
    private int                         mediaFilePlayTime;
    //private int    publishNum;
    private int                         taskNum;
    //private int    compleTime;
    //private int    lastPublishTime;
    //private int    state;
    //private String icon;
    private List<ContTaskTemplatesBean> taskTemplates;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.subTitle);
        dest.writeString(this.taskCode);
        dest.writeString(this.mediaFileUrl);
        dest.writeInt(this.mediaFileSize);
        dest.writeInt(this.mediaFilePlayTime);
        dest.writeInt(this.taskNum);
        dest.writeTypedList(this.taskTemplates);
    }

    protected TaskBean(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.subTitle = in.readString();
        this.taskCode = in.readString();
        this.mediaFileUrl = in.readString();
        this.mediaFileSize = in.readInt();
        this.mediaFilePlayTime = in.readInt();
        this.taskNum = in.readInt();
        this.taskTemplates = in.createTypedArrayList(ContTaskTemplatesBean.CREATOR);
    }

    public static final Creator<TaskBean> CREATOR = new Creator<TaskBean>() {
        @Override
        public TaskBean createFromParcel(Parcel source) {
            return new TaskBean(source);
        }

        @Override
        public TaskBean[] newArray(int size) {
            return new TaskBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getMediaFileUrl() {
        return mediaFileUrl;
    }

    public void setMediaFileUrl(String mediaFileUrl) {
        this.mediaFileUrl = mediaFileUrl;
    }

    public int getMediaFileSize() {
        return mediaFileSize;
    }

    public void setMediaFileSize(int mediaFileSize) {
        this.mediaFileSize = mediaFileSize;
    }

    public int getMediaFilePlayTime() {
        return mediaFilePlayTime;
    }

    public void setMediaFilePlayTime(int mediaFilePlayTime) {
        this.mediaFilePlayTime = mediaFilePlayTime;
    }

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    public List<ContTaskTemplatesBean> getTaskTemplates() {
        return taskTemplates;
    }

    public void setTaskTemplates(List<ContTaskTemplatesBean> taskTemplates) {
        this.taskTemplates = taskTemplates;
    }
}