package com.mayi.yun.teachsystem.ui.my;

import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.Common;
import com.mayi.yun.teachsystem.bean.ScheduleVo;
import com.mayi.yun.teachsystem.network.ApiService;
import com.mayi.yun.teachsystem.network.MyCustomer;
import com.mayi.yun.teachsystem.network.RetrofitManager;
import com.mayi.yun.teachsystem.network.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

/**
 * 作者： wh
 * 时间：  2018/4/17
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MyPresenter extends BasePresenter<MyContract.View> implements MyContract.Presenter {

    @Inject
    public MyPresenter() {

    }

    @Override
    public void getSignListByUserId() {
        mView.showProgress();
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .getSignListByUserId(mView.getUserId())
                .compose(RxSchedulers.<Common<List<ScheduleVo>>>applySchedulers())
                .compose(mView.<Common<List<ScheduleVo>>>bindToLife())
                .subscribe(new MyCustomer<Common<List<ScheduleVo>>>(new MyCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                    }

                    @Override
                    public void setResult(Object t) {
                        mView.hideProgress();
                        List<ScheduleVo> scheduleVoList = (List<ScheduleVo>) t;
                        mView.setScheduleVoList(scheduleVoList);
                    }
                }));
    }
}
