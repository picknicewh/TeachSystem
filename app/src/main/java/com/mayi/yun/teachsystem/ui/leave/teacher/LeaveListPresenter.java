package com.mayi.yun.teachsystem.ui.leave.teacher;

import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.Common;
import com.mayi.yun.teachsystem.bean.VacationVo;
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
public class LeaveListPresenter extends BasePresenter<LeaveListContract.View> implements LeaveListContract.Presenter {

    @Inject
    public LeaveListPresenter() {

    }

    @Override
    public void getLeaveListTeacher() {
        mView.showProgress();
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .getLeaveListTeacher(mView.getUserId())
                .compose(RxSchedulers.<Common<List<VacationVo>>>applySchedulers())
                .compose(mView.<Common<List<VacationVo>>>bindToLife())
                .subscribe(new MyCustomer<Common<List<VacationVo>>>(new MyCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                    }

                    @Override
                    public void setResult(Object t) {
                        mView.hideProgress();
                        List<VacationVo> vacationVoList = (List<VacationVo>) t;
                        mView.setVacationVoList(vacationVoList);

                    }
                }));
    }

    @Override
    public void getLeaveListUser() {
        mView.showProgress();
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .getLeaveListUser(mView.getTeacherId())
                .compose(RxSchedulers.<Common<List<VacationVo>>>applySchedulers())
                .compose(mView.<Common<List<VacationVo>>>bindToLife())
                .subscribe(new MyCustomer<Common>(new MyCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                    }
                    @Override
                    public void setResult(Object t) {
                        mView.hideProgress();
                        List<VacationVo> vacationVoList = (List<VacationVo>) t;
                        mView.setVacationVoList(vacationVoList);
                    }
                }));
    }
}
