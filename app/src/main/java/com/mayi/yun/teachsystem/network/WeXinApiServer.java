package com.mayi.yun.teachsystem.network;


import com.mayi.yun.teachsystem.bean.BannerVo;
import com.mayi.yun.teachsystem.bean.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 作者： wh
 * 时间：  2018/3/12
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface WeXinApiServer {
    String HOST = "http://wanandroid.com/";

    /**
     * 首页Banner
     *
     * @return BannerResponse
     */
    @GET("/banner/json")
   Observable<Result<List<BannerVo>>> getHomeBanners();

    /**
  /*   * 首页数据
     * http://www.wanandroid.com/article/list/0/json
     *
     * @param page page页码
     *//*
    @GET("/article/list/{page}/json")
    Observable<Result<ArticleVo>> getHomeArticles(@Path("page") int page);*/
}
