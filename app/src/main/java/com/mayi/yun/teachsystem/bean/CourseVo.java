package com.mayi.yun.teachsystem.bean;

/**
 * 作者： wh
 * 时间：  2018/5/2
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CourseVo {

    /**
     * id : 2
     * classId : 1
     * className : 信息技术
     * schedule : java web课程实践
     * weekday : 1
     * number : 2
     * teacherId : 4
     * teacherName : 老师1
     * classroom : 明德楼213
     */

    private int id;
    private int classId;
    private String className;
    private String schedule;
    private int weekday;
    private int number;
    private int teacherId;
    private String teacherName;
    private String classroom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
}
