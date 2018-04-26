package com.mayi.yun.teachsystem.ui.study;


import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.Result;
import com.mayi.yun.teachsystem.bean.StudyVo;
import com.mayi.yun.teachsystem.network.RetrofitManager;
import com.mayi.yun.teachsystem.network.RxSchedulers;
import com.mayi.yun.teachsystem.network.ThrowCustomer;
import com.mayi.yun.teachsystem.network.WeXinApiServer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 作者： wh
 * 时间：  2018/3/1
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StudySystemPresenter extends BasePresenter<StudySystemContract.View> implements StudySystemContract.Presenter {

    @Inject
    public StudySystemPresenter() {

    }

    @Override
    public void getKnowledgeSystemList() {
        RetrofitManager.create(WeXinApiServer.class, WeXinApiServer.HOST)
                .getKnowledgeSystem()
                .compose(RxSchedulers.<Result<List<StudyVo>>>applySchedulers())
                .compose(mView.<Result<List<StudyVo>>>bindToLife())
                .subscribe(new Consumer<Result<List<StudyVo>>>() {
                    @Override
                    public void accept(Result<List<StudyVo>> listResult) throws Exception {
                        List<StudyVo> knowledgeSystemList = listResult.getData();
                        mView.setKnowledgeSystemList(knowledgeSystemList);
                    }
                }, new ThrowCustomer(new ThrowCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                    }
                }));
    }
}
