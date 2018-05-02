package com.mayi.yun.teachsystem.ui.classinfo;

import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.ClassVo;
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
public class ClassListPresenter extends BasePresenter<ClassListContract.View> implements ClassListContract.Presenter {
    @Inject
    public ClassListPresenter() {

    }

    @Override
    public void getClassList() {
        mView.showProgress();
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .getClassList(mView.getTeacherId())
                .compose(RxSchedulers.<Common<List<ClassVo>>>applySchedulers())
                .compose(mView.<Common<List<ClassVo>>>bindToLife())
                .subscribe(new MyCustomer<Common<List<ClassVo>>>(new MyCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                    }
                    @Override
                    public void setResult(Object t) {
                        mView.hideProgress();
                        List<ClassVo> classVoList = (List<ClassVo>) t;
                        mView.setClassList(classVoList);
                    }
                }));
    }
}
