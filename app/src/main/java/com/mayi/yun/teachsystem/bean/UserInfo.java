package com.mayi.yun.teachsystem.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者： wh
 * 时间：  2018/4/24
 * 名称：用户信息
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class UserInfo implements Parcelable {
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
     * 手机号
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
     * 性别
     */
    private int sex;
    /**
     * 生日
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

    public UserInfo() {

    }

    protected UserInfo(Parcel in) {
        userId = in.readInt();
        userSn = in.readString();
        userType = in.readInt();
        phone = in.readString();
        nickname = in.readString();
        truename = in.readString();
        avatar = in.readString();
        sex = in.readInt();
        birthday = in.readString();
        classId = in.readInt();
        className = in.readString();
        position = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeString(userSn);
        parcel.writeInt(userType);
        parcel.writeString(phone);
        parcel.writeString(nickname);
        parcel.writeString(truename);
        parcel.writeString(avatar);
        parcel.writeInt(sex);
        parcel.writeString(birthday);
        parcel.writeInt(classId);
        parcel.writeString(className);
        parcel.writeString(position);
    }
}
