package com.test.module_task.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author zhuj 2019/7/2 下午2:26.
 */
public class ContTaskTemplatesBean implements Parcelable {

    /**
     * id : 22
     * taskNum : 5
     * compleTime : 300
     * templateCode : 0103005
     */

    private int    id;
    private int    taskNum;
    private int    compleTime;
    private String templateCode;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.taskNum);
        dest.writeInt(this.compleTime);
        dest.writeString(this.templateCode);
    }

    protected ContTaskTemplatesBean(Parcel in) {
        this.id = in.readInt();
        this.taskNum = in.readInt();
        this.compleTime = in.readInt();
        this.templateCode = in.readString();
    }

    public static final Creator<ContTaskTemplatesBean> CREATOR =
            new Creator<ContTaskTemplatesBean>() {
                @Override
                public ContTaskTemplatesBean createFromParcel(Parcel source) {
                    return new ContTaskTemplatesBean(source);
                }

                @Override
                public ContTaskTemplatesBean[] newArray(int size) {
                    return new ContTaskTemplatesBean[size];
                }
            };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    public int getCompleTime() {
        return compleTime;
    }

    public void setCompleTime(int compleTime) {
        this.compleTime = compleTime;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
