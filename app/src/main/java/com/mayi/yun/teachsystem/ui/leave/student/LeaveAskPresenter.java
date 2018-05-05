package com.mayi.yun.teachsystem.ui.leave.student;

import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.Common;
import com.mayi.yun.teachsystem.network.ApiService;
import com.mayi.yun.teachsystem.network.MyCustomer;
import com.mayi.yun.teachsystem.network.RetrofitManager;
import com.mayi.yun.teachsystem.network.RxSchedulers;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * 作者： wh
 * 时间：  2018/4/17
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class LeaveAskPresenter extends BasePresenter<LeaveAskContract.View> implements LeaveAskContract.Presenter {

    @Inject
    public LeaveAskPresenter() {

    }

    @Override
    public void addLeave() {
        mView.showProgress();
        Map<String, Object> params = new HashMap<>();
        params.put("userId", mView.getUserId());
        params.put("userName", mView.getUserName());
        params.put("reason", mView.getReason());
        params.put("starttime", mView.getStartTime());
        params.put("endtime", mView.getEndTime());
        params.put("days", mView.getDays());
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .addLeave(params)
                .compose(RxSchedulers.<Common<String>>applySchedulers())
                .compose(mView.<Common<String>>bindToLife())
                .subscribe(new MyCustomer<Common<String>>(new MyCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                        if (message.equals("添加成功！")){
                            mView.onSuccess();
                        }
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
