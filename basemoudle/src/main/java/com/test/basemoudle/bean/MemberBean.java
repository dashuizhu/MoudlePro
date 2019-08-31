package com.test.basemoudle.bean;


/**
 * Description:
 * Author: jws
 * Date: 2018/9/19
 */
public class MemberBean {
    /**
     * phone : 16675593504
     * sex : 2
     * name : wang
     * id : 1
     * avatar : http://buddyapp.685b0.jpg
     */

    private String phone;
    private int sex;
    private String name;
    private int id;
    private String avatar;
    private String birthday;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}