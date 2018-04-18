package com.mayi.yun.teachsystem.ui.home;

import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.BannerVo;
import com.mayi.yun.teachsystem.bean.Result;
import com.mayi.yun.teachsystem.network.RetrofitManager;
import com.mayi.yun.teachsystem.network.RxSchedulers;
import com.mayi.yun.teachsystem.network.ThrowCustomer;
import com.mayi.yun.teachsystem.network.WeXinApiServer;
import com.mayi.yun.teachsystem.utils.G;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 作者： wh
 * 时间：  2018/4/17
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements  HomeContract.Presenter {

    @Inject
    public HomePresenter(){

    }

    @Override
    public void getHomeBanner() {
        RetrofitManager.create(WeXinApiServer.class,WeXinApiServer.HOST).
                getHomeBanners()
                .compose(RxSchedulers.<Result<List<BannerVo>>>applySchedulers())
                .compose(mView.<Result<List<BannerVo>>>bindToLife())
                .subscribe(new Consumer<Result<List<BannerVo>>>() {
                    @Override
                    public void accept(Result<List<BannerVo>> listResult) throws Exception {
                        List<BannerVo> bannerList = listResult.getData();
                        mView.setHomeBanner(bannerList);
                    }
                }, new ThrowCustomer(new ThrowCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        G.log("xxx" + message);
                        mView.showMessage(message);
                    }
                }));
    }
}
