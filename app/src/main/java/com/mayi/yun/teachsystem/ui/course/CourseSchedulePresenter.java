package com.mayi.yun.teachsystem.ui.course;

import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.Common;
import com.mayi.yun.teachsystem.bean.Common2;
import com.mayi.yun.teachsystem.bean.CourseVo;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.network.ApiService;
import com.mayi.yun.teachsystem.network.MyCustomer;
import com.mayi.yun.teachsystem.network.RetrofitManager;
import com.mayi.yun.teachsystem.network.RxSchedulers;
import com.mayi.yun.teachsystem.network.ThrowCustomer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 作者： wh
 * 时间：  2018/4/18
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CourseSchedulePresenter extends BasePresenter<CourseScheduleContract.View> implements CourseScheduleContract.Presenter {
    @Inject
    public CourseSchedulePresenter() {

    }

    @Override
    public void addSchedule() {
        mView.showProgress();
        Map<String, Object> params = new HashMap<>();
        params.put("classId", mView.getClassId());
        params.put("className", mView.getClassName());
        params.put("schedule", mView.getSchedule());
        params.put("weekday", mView.getWeek());
        params.put("number", mView.getNumber());
        params.put("teacherName", mView.getTeacherName());
        params.put("teacherId", mView.getTeacherId());
        params.put("classroom", mView.getClassRoom());
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .addSchedule(params)
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
                       mView.addSuccess();
                        mView.hideProgress();
                    }
                }));
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

    @Override
    public void getScheduleList() {
        mView.showProgress();
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .getScheduleList(mView.getClassId())
                .compose(RxSchedulers.<Common<List<CourseVo>>>applySchedulers())
                .compose(mView.<Common<List<CourseVo>>>bindToLife())
                .subscribe(new MyCustomer<Common<List<CourseVo>>>(new MyCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                    }

                    @Override
                    public void setResult(Object t) {
                        mView.hideProgress();
                        List<CourseVo> courseVos = (List<CourseVo>) t;
                        mView.setCourseList(courseVos);
                    }
                }));
    }
}
