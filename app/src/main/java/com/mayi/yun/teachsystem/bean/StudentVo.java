package com.mayi.yun.teachsystem.bean;

/**
 * 作者： wh
 * 时间：  2018/4/21
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StudentVo {
    /**
     * 名字
     */
    private String name;
    /**
     * 学好
     */
    private String number;
    /**
     * 职务
     */
    private String post;
    /**
     * 照片
     */
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
