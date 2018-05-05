package com.mayi.yun.teachsystem.bean;

/**
 * 作者： wh
 * 时间：  2018/5/5
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ScheduleVo {

    /**
     * id : 48
     * scheduleId : 3
     * scheduleName : java web课程实践
     * classId : 1
     * className : 信息技术
     * teacherId : 4
     * teacherName : 老师1
     * classroom : 明德楼213
     * userId : 5
     * userName : 学生1
     * isSign : 1
     * signTime : May 4, 2018 4:16:53 PM
     * createTime : May 4, 2018 4:16:53 PM
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
