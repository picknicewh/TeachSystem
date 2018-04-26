package com.mayi.yun.teachsystem.bean;

/**
 * 作者： wh
 * 时间：  2018/4/24
 * 名称：用户信息
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class UserInfo {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 用户编码(学号，工号)
     */
    private String userSn;
    /**
     * 1 班主任 2 普通老师 3 学生
     */
    private int userType;
    /**
     *手机号
     */
    private String phone;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 真实名称
     */
    private String truename;
    /**
     * 头像
     */
    private String avatar;
    /**
     *性别
     */
    private int sex;
    /**
     *生日
     */
    private String birthday;
    /**
     * 班级id
     */
    private int classId;
    /**
     * 班级名称
     */
    private String className;
    private String position;
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserSn() {
        return userSn;
    }

    public void setUserSn(String userSn) {
        this.userSn = userSn;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
