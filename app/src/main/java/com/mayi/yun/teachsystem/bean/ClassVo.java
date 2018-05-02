package com.mayi.yun.teachsystem.bean;

/**
 * 作者： wh
 * 时间：  2018/5/2
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ClassVo {

    /**
     * classId : 1
     * name : 信息技术
     * instituteName : 计算机科学学院
     * schoolName : 榆林学院
     */

    private int classId;
    private String name;
    private String instituteName;
    private String schoolName;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
