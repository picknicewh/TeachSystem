package com.mayi.yun.teachsystem.ui.attend.teacher;

import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.Common;
import com.mayi.yun.teachsystem.bean.CourseVo;
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
public class CourseListPresenter extends BasePresenter<CourseListContract.View> implements CourseListContract.Presenter {
    @Inject
    public CourseListPresenter() {

    }

    @Override
    public void getCourseList() {
        mView.showProgress();
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .getScheduleListByTeacherId(mView.getTeacherId())
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
                        List<CourseVo> courseVoList = (List<CourseVo>) t;
                        mView.setCourseList(courseVoList);
                    }
                }));
    }
}
