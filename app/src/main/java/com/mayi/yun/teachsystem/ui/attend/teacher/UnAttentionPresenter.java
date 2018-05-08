package com.mayi.yun.teachsystem.ui.attend.teacher;

import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.Common;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.network.ApiService;
import com.mayi.yun.teachsystem.network.MyCustomer;
import com.mayi.yun.teachsystem.network.RetrofitManager;
import com.mayi.yun.teachsystem.network.RxSchedulers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * 作者： wh
 * 时间：  2018/4/18
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class UnAttentionPresenter extends BasePresenter<UnAttentionContract.View> implements UnAttentionContract.Presenter {
    @Inject
    public UnAttentionPresenter() {

    }

    @Override
    public void getUserByClassId() {
        mView.showProgress();
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .getUserListSign(mView.getScheduleId())
                .compose(RxSchedulers.<Common<List<UserInfo>>>applySchedulers())
                .compose(mView.<Common<List<UserInfo>>>bindToLife())
                .subscribe(new MyCustomer<Common<List<UserInfo>>>(new MyCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                    }

                    @Override
                    public void setResult(Object t) {
                        List<UserInfo> result = (List<UserInfo>) t;
                        mView.setUserInfoList(result);
                        mView.hideProgress();
                    }
                }));

    }

    @Override
    public void addSign() {
        mView.showProgress();
        Map<String, Object> params = new HashMap<>();
        params.put("userId", mView.getUserId());
        params.put("userName", mView.getUserName());
        params.put("scheduleId", mView.getScheduleId());
        params.put("isSign", mView.isSign());
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .addSign(params)
                .compose(RxSchedulers.<Common<String>>applySchedulers())
                .compose(mView.<Common>bindToLife())
                .subscribe(new MyCustomer<Common>(new MyCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                    }

                    @Override
                    public void setResult(Object t) {
                        String result = (String) t;
                        mView.showMessage(result);
                        mView.hideProgress();
                    }
                }));
    }
}
