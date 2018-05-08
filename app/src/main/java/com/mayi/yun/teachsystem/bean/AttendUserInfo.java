package com.mayi.yun.teachsystem.bean;

/**
 * 作者： wh
 * 时间：  2018/5/8
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class AttendUserInfo {

    /**
     * id : 214
     * scheduleId : 18
     * scheduleName : java
     * classId : 1
     * className : 计算机科学与技术1班
     * teacherId : 4
     * teacherName : 王力宏
     * classroom : 503
     * userId : 13
     * userName : 匡匡
     * avatar : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJUCPelT8hcJkec4fic2zzk3kHV6y78wNRzl1fGc3fiaeEozpyu3QiaHrubqCMEtmgAsfzef7ZUO1bng/0
     * isSign : 1
     * signTime : May 8, 2018 3:15:31 PM
     * createTime : May 8, 2018 3:15:31 PM
     */

    private int id;
    private int scheduleId;
    private String scheduleName;
    private int classId;
    private String className;
    private int teacherId;
    private String teacherName;
    private String classroom;
    private int userId;
    private String userName;
    private String avatar;
    private int isSign;
    private String signTime;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
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

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getIsSign() {
        return isSign;
    }

    public void setIsSign(int isSign) {
        this.isSign = isSign;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
