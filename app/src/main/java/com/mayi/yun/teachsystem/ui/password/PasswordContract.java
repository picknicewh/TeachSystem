package com.mayi.yun.teachsystem.ui.password;


import com.mayi.yun.teachsystem.base.BaseContract;

/**
 * 作者： wh
 * 时间：  2018/3/12
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface PasswordContract {
    interface View extends BaseContract.BaseView {
        String getUserId();
        String getPassword();
        String getOldPassword();
        void onSuccess();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void updateUser();
    }
}
