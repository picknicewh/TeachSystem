package com.mayi.yun.teachsystem.utils;

/**
 * 作者： wh
 * 时间：  2018/2/27
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class Constant {
    public static final String REQUEST_BASE_URL = "http://wanandroid.com/";
    /**
     * 每页数量
     */
    public static final int PAGE_SIZE = 20;
    /**
     * 缓存位置
     */
    public static final String APP_CACAHE_DIRNAME="/cache";

    /**
     * hotFriend key
     */
    public static final String CONTENT_HOT_FRIEND_KEY = "hotFriend";
    /**
     * hot key
     */
    public static final String CONTENT_HOT_KEY = "hotKey";

    /**
     *大家都在搜
     */
    public static final int TAG_HOT = 1;
    /**
     *常用网站
     */
    public static final int TAG_FRIEDND = 2;

    /**选择照片来源*/
    public static final int RESULT_IMAG =1;
    public static final int RESULT_CAMERA = 10;

    /**
     * 班主任
     */
    public static final int HEAD = 1;
    /**
     * 老师
     */
    public static final int TEACHER = 2;
    /**
     * 学生
     */
    public static final int STUDENT = 3;
    /**
     * 管理员
     */
    public static final int ADMI = 4;

    /**
     * 编辑
     */
    public static final int EDIT = 1;
    /**
     * 添加
     */
    public static final int ADD = 2;

    /**
     * 进入学生列表来源----学生信息按钮
     */
    public static final int SOURCE_MEMBER = 1;

    /**
     * 进入学生列表来源----考勤按钮
     */
    public static final int SOURCE_ATTEBD = 2;

    /**
     * 进入学生列表来源----课程表按钮
     */
    public static final int SOURCE_COURSE = 3;

    /**
     * 进入学生列表来源----我的-考勤记录
     */
    public static final int SOURCE_ATTEND_LIST = 4;


    /**
     * 已考勤
     */
    public static final int HAS_ATTEND = 1;

    /**
     * 未考勤
     */
    public static final int UN_ATTEND = 0;

}
