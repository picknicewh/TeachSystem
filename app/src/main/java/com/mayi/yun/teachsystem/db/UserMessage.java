package com.mayi.yun.teachsystem.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.mayi.yun.teachsystem.base.App;

/**
 * ================================================
 * 作    者：wh
 * 时    间：2016/7/19
 * 描    述：用户信息存储类
 * 版    本：
 * 修订历史：
 * 主要接口：
 * ================================================
 */
public class UserMessage {
    private SharedPreferences spf;
    private SharedPreferences.Editor editor;
    private static UserMessage userInfo;

    public UserMessage(Context context) {
        spf = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = spf.edit();
    }

    public synchronized static UserMessage getInstance() {
        if (null == userInfo) {
            userInfo = new UserMessage(App.getInstance().getApplicationContext());
        }
        return userInfo;
    }

    /**
     * 当前用户查询用户信息id
     */
    public int getUserId() {
        return spf.getInt("userId", 1);
    }

    /**
     * 设置当前用户查询用户信息id
     *
     * @param id 用户信息id
     */
    public void setUserId(int id) {
        editor.putInt("userId", id);
        editor.commit();
    }
    /**
     * 获取用户编码(学号，工号)
     */
    public String getUserSn() {
        return spf.getString("userSn", "");
    }

    /**
     * 设置用户编码
     * @param  userSn 学号，工号
     */
    public void setUserSn(String userSn) {
        editor.putString("userSn", userSn);
        editor.commit();
    }
    /**
     * 用户的姓名（登陆名）
     */
    public String getUsername() {
        return spf.getString("username", "");
    }
    /**
     * 设置用户的名字
     *
     * @param username 用户的名字
     */
    public void setUsername(String username) {
        editor.putString("username", username);
        editor.commit();
    }
    /**
     * 设置用户的密码（登陆的密码）
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        editor.putString("password", password);
        editor.commit();
    }
    /**
     * 用户的密码（登陆的密码）
     */
    public String getPassword() {
        return spf.getString("password", "");
    }

    /**
     * 获取用户类型1 班主任 2 普通老师 3 学生
     * @param userType 用户类型
     */
    public void setUserType(int userType) {
        editor.putInt("userType", userType);
        editor.commit();
    }
    public int getUserType() {
        return spf.getInt("userType", 1);
    }
    /**
     *设置手机号
     * @param  phone 手机号
     */
    public void setPhone(String phone) {
        editor.putString("phone", phone);
        editor.commit();
    }
    /**
     *获取手机号
     */
    public String getPhone() {
        return spf.getString("phone", "");
    }
    /**
     * 获取昵称
     */
    public String getNickname() {
        return  spf.getString("nickname", "");
    }
    /**
     * 设置昵称
     * @param  nickname
     */
    public void setNickname(String nickname) {
        editor.putString("nickname", nickname);
        editor.commit();
    }

    /**
     * 真实名称
     */
    public String getTruename() {
        return  spf.getString("truename", "");
    }

    /**
     * 真实名称
     * @param  truename 真实名称
     */
    public void setTruename(String truename) {
        editor.putString("truename", truename);
        editor.commit();
    }
    /**
     * 头像
     */
    public String getAvatar() {
        return spf.getString("avatar", "");
    }
    /**
     * 设置头像
     * @param  avatar
     */
    public void setAvatar(String avatar) {
        editor.putString("avatar", avatar);
        editor.commit();
    }
    /**
     *性别
     */
    public int getSex() {
        return spf.getInt("sex", 1);
    }
    /**
     *设置性别
     * @param  sex 性别
     */
    public void setSex(int sex) {
        editor.putInt("sex", sex);
        editor.commit();
    }
    /**
     *生日
     */
    public String getBirthday() {
        return spf.getString("birthday", "");
    }
    /**
     *设置生日
     * @param  birthday 生日
     */
    public void setBirthday(String birthday) {
        editor.putString("birthday", birthday);
        editor.commit();
    }

    /**
     * 班级id
     */
    public int getClassId() {
        return spf.getInt("classId", 1);
    }
    /**
     * 班级id
     * @param  classId 班级id
     */
    public void setClassId(int classId) {
        editor.putInt("classId", classId);
        editor.commit();
    }
    /**
     * 班级名称
     */
    public String getClassName() {
        return spf.getString("className", "");
    }
    /**
     * 班级名称
     * @param  className
     */
    public void setClassName(String className) {
        editor.putString("className", className);
        editor.commit();
    }
}


