package com.mayi.yun.teachsystem.bean;

/**
 * 作者： wh
 * 时间：  2018/4/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class Common2<T> {
    private int code;
    private String desc;
    private T date;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return date;
    }

    public void setData(T data) {
        this.date = data;
    }
}
