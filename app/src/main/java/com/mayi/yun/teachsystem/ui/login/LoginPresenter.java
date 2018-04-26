package com.mayi.yun.teachsystem.ui.login;

import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.Common;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.network.ApiService;
import com.mayi.yun.teachsystem.network.RetrofitManager;
import com.mayi.yun.teachsystem.network.RxSchedulers;
import com.mayi.yun.teachsystem.network.ThrowCustomer;

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
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    public LoginPresenter() {

    }

    @Override
    public void login() {
        mView.showProgress();
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .login(mView.getMemberSn(), mView.getPassword())
                .compose(RxSchedulers.<Common<UserInfo>>applySchedulers())
                .compose(mView.<Common<UserInfo>>bindToLife())
                .subscribe(new Consumer<Common<UserInfo>>() {
                    @Override
                    public void accept(Common<UserInfo> result) throws Exception {
                        mView.setUseInfo(result.getData());
                        mView.hideProgress();
                    }
                }, new ThrowCustomer(new ThrowCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                    }
                }));
    }
}
