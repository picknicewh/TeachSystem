package com.mayi.yun.teachsystem.ui.head;

import com.mayi.yun.teachsystem.base.BasePresenter;
import com.mayi.yun.teachsystem.bean.ClassVo;
import com.mayi.yun.teachsystem.bean.Common;
import com.mayi.yun.teachsystem.network.ApiService;
import com.mayi.yun.teachsystem.network.MyCustomer;
import com.mayi.yun.teachsystem.network.RetrofitManager;
import com.mayi.yun.teachsystem.network.RxSchedulers;
import com.mayi.yun.teachsystem.utils.G;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 作者： wh
 * 时间：  2018/4/17
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class EditHeadPresenter extends BasePresenter<EditHeadContract.View> implements  EditHeadContract.Presenter {

    @Inject
    public EditHeadPresenter(){

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
                        if (message.equals("添加成功")){
                            mView.success();
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

    @Override
    public void getClassList() {
        mView.showProgress();
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .getClassList("")
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

    @Override
    public void delUser() {
        mView.showProgress();
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .deleteUser(mView.getUserId())
                .compose(RxSchedulers.<Common<String>>applySchedulers())
                .compose(mView.<Common>bindToLife())
                .subscribe(new MyCustomer<Common>(new MyCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                        G.log("xxxxxxx" + "");
                        if (message.equals("删除成功")) {
                            mView.success();
                        }
                    }

                    @Override
                    public void setResult(Object t) {
                        mView.hideProgress();
                    }
                }));
    }

    @Override
    public void updateImage() {
        mView.showProgress();
        File file = new File(mView.getPath());
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("file", file.getName(), imageBody);
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .uploadFile(imageBodyPart)
                .compose(RxSchedulers.<Common<String>>applySchedulers())
                .compose(mView.<Common<String>>bindToLife())
                .subscribe(new MyCustomer<Common<String>>(new MyCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                    }

                    @Override
                    public void setResult(Object t) {
                        mView.hideProgress();
                        String imagePath = (String) t;
                        mView.setImagePath(imagePath);

                    }
                }));
    }

    @Override
    public void updateUser() {
        mView.showProgress();
        Map<String, Object> params = new HashMap<>();
        params.put("userId", mView.getUserId());
        params.put("userType", mView.getUserType());
        params.put("classId", mView.getClassId());
        params.put("userSn", mView.getUserSn());
        params.put("phone", mView.getPhone());
        params.put("truename", mView.getTrueName());
        params.put("avatar", mView.getAvatar());
        params.put("sex", mView.getSex());
        params.put("position", mView.getPosition());
        params.put("birthday", mView.getBirthday());
        RetrofitManager.create(ApiService.class, ApiService.HOST)
                .updateUser(params)
                .compose(RxSchedulers.<Common<String>>applySchedulers())
                .compose(mView.<Common>bindToLife())
                .subscribe(new MyCustomer<Common>(new MyCustomer.CallBack() {
                    @Override
                    public void getErrorMessage(String message) {
                        mView.showMessage(message);
                        mView.hideProgress();
                        if (message.contains("成功")) {
                            mView.success();
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
