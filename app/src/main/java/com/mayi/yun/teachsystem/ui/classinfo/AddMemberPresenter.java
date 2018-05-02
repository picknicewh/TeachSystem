package com.mayi.yun.teachsystem.ui.classinfo;

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
public class AddMemberPresenter extends BasePresenter<AddMemberContract.View> implements  AddMemberContract.Presenter {

    @Inject
    public AddMemberPresenter(){

    }

    @Override
    public void addMember() {
        mView.showProgress();
        Map<String,Object> params = new HashMap<>();
        params.put("userType",mView.getUserType());
        params.put("classId",mView.getClassId());
        params.put("userSn",mView.getUserSn());
        params.put("phone",mView.getPhone());
        params.put("truename",mView.getTrueName());
        params.put("avatar",mView.getAvatar());
        params.put("sex",mView.getSex());
        params.put("position",mView.getPosition());
        params.put("birthday",mView.getBirthday());
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .addMember(params)
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
