package com.mayi.yun.teachsystem.bean;

import java.io.Serializable;

/**
 * 作者： wanghua
 * 时间： 2017/6/5
 * 名称：新版请假类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class VacationVo implements Serializable {

    /**
     * id : 3
     * starttime : 2018-05-04 20:09:23
     * endtime : 2018-05-04 20:09:23
     * createtime : May 4, 2018 9:11:25 PM
     * userId : 10
     * userName : 张三
     * reason : 身体不舒服
     * days : 0.5
     * status : 1
     * teacherId : 1
     * teacherName : 班主任
     * updatetime : May 4, 2018 9:11:14 PM
     */

    private int id;
    private String starttime;
    private String endtime;
    private String createtime;
    private int userId;
    private String userName;
    private String reason;
    private String days;
    private int status;
    private int teacherId;
    private String teacherName;
    private String updatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
