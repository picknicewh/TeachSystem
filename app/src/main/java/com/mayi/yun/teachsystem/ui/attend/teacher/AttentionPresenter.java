package com.mayi.yun.teachsystem.ui.attend.teacher;

import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.AttendUserInfo;
import com.mayi.yun.teachsystem.bean.Common;
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
public class AttentionPresenter extends BasePresenter<AttentionContract.View> implements AttentionContract.Presenter {
    @Inject
    public AttentionPresenter() {

    }

    @Override
    public void getSignListByParams() {
        mView.showProgress();
        Map<String, Object> params = new HashMap<>();
        params.put("teacherId", mView.getTeacherId());
        params.put("classId", mView.getClassId());
        params.put("scheduleId", mView.getScheduleId());
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .getSignListByParams(params)
                .compose(RxSchedulers.<Common<List<AttendUserInfo>>>applySchedulers())
                .compose(mView.<Common<List<AttendUserInfo>>>bindToLife())
                .subscribe(new MyCustomer<Common<List<AttendUserInfo>>>(new MyCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                    }

                    @Override
                    public void setResult(Object t) {
                        mView.hideProgress();
                        List<AttendUserInfo> attendVoList = (List<AttendUserInfo>) t;
                        mView.setAttendUserInfoList(attendVoList);
                    }
                }));
    }
}
