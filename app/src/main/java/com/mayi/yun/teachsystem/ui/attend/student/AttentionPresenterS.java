package com.mayi.yun.teachsystem.ui.attend.student;

import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.AttendVo;
import com.mayi.yun.teachsystem.bean.Common;
import com.mayi.yun.teachsystem.network.ApiService;
import com.mayi.yun.teachsystem.network.MyCustomer;
import com.mayi.yun.teachsystem.network.RetrofitManager;
import com.mayi.yun.teachsystem.network.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

/**
 * 作者： wh
 * 时间：  2018/4/18
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class AttentionPresenterS extends BasePresenter<AttentionContractS.View> implements AttentionContractS.Presenter {
    @Inject
    public AttentionPresenterS() {

    }

    @Override
    public void getSignListByUserId() {
        mView.showProgress();
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .getSignListByUserIdDay(mView.getUserId())
                .compose(RxSchedulers.<Common<List<AttendVo>>>applySchedulers())
                .compose(mView.<Common<List<AttendVo>>>bindToLife())
                .subscribe(new MyCustomer<Common<List<AttendVo>>>(new MyCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                    }

                    @Override
                    public void setResult(Object t) {
                        List<AttendVo> attendVoList = (List<AttendVo>) t;
                        mView.setAttendList(attendVoList);
                        mView.hideProgress();
                    }
                }));
    }
}
