package com.mayi.yun.teachsystem.bean;

/**
 * 作者： wh
 * 时间：  2018/4/25
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CourseInfo {
   private String name;
   private String time;
   private String teacher;
   private boolean isAgree;
   private boolean isAttend;
   private String room;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean agree) {
        isAgree = agree;
    }

    public boolean isAttend() {
        return isAttend;
    }

    public void setAttend(boolean attend) {
        isAttend = attend;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
