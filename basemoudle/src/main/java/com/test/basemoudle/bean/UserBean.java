package com.test.basemoudle.bean;


/**
 * 用户设备
 * Created by zhuj on 2017/6/8 下午2:47.
 */
public class UserBean {

  /**
   * auth : {"expiresIn":35998,"accessToken":"8a9b94cd-57d0-4f2e-8113-9d5967b54aba","refreshToken":"269c47d5-1c2c-4dd8-914b-b32246d10723"}
   * school : {"name":"童真园所","id":1}
   * grade : {"name":"笨笨熊","rank":0,"id":1,"studentNum":0}
   * member : {"phone":"16675593504","sex":2,"name":"wang","id":1,"avatar":"http://buddyapp.685b0.jpg"}
   */

  private AuthBean auth;
  private SchoolBean school;
  private GradeBean grade;
  private MemberBean member;

  public static class SchoolBean {
    /**
     * name : 童真园所
     * id : 1
     */

    private String name;
    private int id;

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
  }
  public static class GradeBean {
    /**
     * name : 笨笨熊
     * rank : 0
     * id : 1
     * studentNum : 0
     */

    private String name;
    private int rank;
    private int id;
    private int studentNum;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getRank() {
      return rank;
    }

    public void setRank(int rank) {
      this.rank = rank;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public int getStudentNum() {
      return studentNum;
    }

    public void setStudentNum(int studentNum) {
      this.studentNum = studentNum;
    }
  }

  public AuthBean getAuth() {
    return auth;
  }

  public void setAuth(AuthBean auth) {
    this.auth = auth;
  }

  public SchoolBean getSchool() {
    return school;
  }

  public void setSchool(SchoolBean school) {
    this.school = school;
  }

  public GradeBean getGrade() {
    return grade;
  }

  public void setGrade(GradeBean grade) {
    this.grade = grade;
  }

  public MemberBean getMember() {
    return member;
  }

  public void setMember(MemberBean member) {
    this.member = member;
  }
}
