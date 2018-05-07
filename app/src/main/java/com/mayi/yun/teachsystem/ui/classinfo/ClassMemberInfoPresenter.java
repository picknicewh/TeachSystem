package com.mayi.yun.teachsystem.ui.classinfo;

import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.Common2;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.network.ApiService;
import com.mayi.yun.teachsystem.network.RetrofitManager;
import com.mayi.yun.teachsystem.network.RxSchedulers;
import com.mayi.yun.teachsystem.network.ThrowCustomer;

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
public class ClassMemberInfoPresenter extends BasePresenter<ClassMemberInfoContract.View> implements ClassMemberInfoContract.Presenter {

    @Inject
    public ClassMemberInfoPresenter() {

    }

    @Override
    public void getUserByClassId() {
        mView.showProgress();
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .getUserByClassId(mView.getClassId(), mView.getUserType())
                .compose(RxSchedulers.<Common2<List<UserInfo>>>applySchedulers())
                .compose(mView.<Common2<List<UserInfo>>>bindToLife())
                .subscribe(new Consumer<Common2<List<UserInfo>>>() {
                    @Override
                    public void accept(Common2<List<UserInfo>> listCommon) throws Exception {
                        mView.hideProgress();
                        mView.setUserInfoList(listCommon.getData());
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
