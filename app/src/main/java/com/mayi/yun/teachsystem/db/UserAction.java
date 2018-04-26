package com.mayi.yun.teachsystem.db;


import com.mayi.yun.teachsystem.bean.UserInfo;

/**
 * ================================================
 * 作    者：wh
 * 时    间：2018/1/2
 * 描    述：
 * 版    本：
 * 修订历史：
 * 主要接口：
 * ================================================
 */
public class UserAction {

    public static void saveLoginInfo(UserInfo info) {
        UserMessage userMessage = UserMessage.getInstance();
        userMessage.setAvatar(info.getAvatar());
        userMessage.setBirthday(info.getBirthday());
        userMessage.setClassId(info.getClassId());
        userMessage.setNickname(info.getNickname());
        userMessage.setPhone(info.getPhone());
        userMessage.setClassName(info.getClassName());
        userMessage.setUserType(info.getUserType());
        userMessage.setSex(info.getSex());
        userMessage.setTruename(info.getTruename());
        userMessage.setUserSn(info.getUserSn());
        userMessage.setUserId(info.getUserId());
    }

    /**
     * 保存用户登录名和密码
     *
     * @param loginName
     * @param passWord
     */
    public static void saveLoginMessage(String loginName, String passWord) {
        UserMessage um = UserMessage.getInstance();
        um.setUsername(loginName);
        um.setPassword(passWord);
    }
}
