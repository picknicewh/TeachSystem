package com.mayi.yun.teachsystem.network;


import com.mayi.yun.teachsystem.bean.Common;
import com.mayi.yun.teachsystem.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
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
    String HOST = " http://kuang.free.ngrok.cc/";

    /**
     * 登录
     * @param username username
     * @param password password
     */
    @POST("member/login")
    @FormUrlEncoded
    Observable<Common<UserInfo>> login(@Field("userSn") String username, @Field("password") String password);

}
