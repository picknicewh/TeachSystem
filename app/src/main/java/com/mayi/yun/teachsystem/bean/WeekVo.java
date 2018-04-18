package com.mayi.yun.teachsystem.bean;

/**
 * 作者： wh
 * 时间：  2018/4/18
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class WeekVo {
    private String week;
    private int date;

    public WeekVo(String week, int date) {
        this.week = week;
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
