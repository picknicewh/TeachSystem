package com.mayi.yun.teachsystem.ui.home;


import com.mayi.yun.teachsystem.base.BaseContract;
import com.mayi.yun.teachsystem.bean.BannerVo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2018/3/12
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface HomeContract {
    interface View extends BaseContract.BaseView {
        void setHomeBanner(List<BannerVo> bannerList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getHomeBanner();
    }
}
