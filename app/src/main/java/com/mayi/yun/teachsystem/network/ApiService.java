package com.mayi.yun.teachsystem.network;


import com.mayi.yun.teachsystem.bean.Common;
import com.mayi.yun.teachsystem.bean.Common2;
import com.mayi.yun.teachsystem.bean.UserInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 作者： wh
 * 时间：  2018/2/27
 * 名称：网络请求地址
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface ApiService {
    String HOST = "http://101.132.138.122:8080/";

    /**
     * 登录
     * @param username username
     * @param password password
     */
    @POST("member/login")
    @FormUrlEncoded
    Observable<Common<UserInfo>> login(@Field("userSn") String username, @Field("password") String password);

    /**
     * 根据班级和用户类型 查询所有的人员 （userType 默认3        1 班主任 2 普通老师 3 学生 4 管理员）
     * @param classId
     * @param userType
     */
    @POST("member/selectUserByClassId")
    @FormUrlEncoded
    Observable<Common2<List<UserInfo>>> getUserByClassId(@Field("classId") String classId, @Field("userType") String userType);


    @POST("member/add")
    @FormUrlEncoded
    Observable<Common<String>> addMember(@FieldMap Map<String,Object> params);

}
