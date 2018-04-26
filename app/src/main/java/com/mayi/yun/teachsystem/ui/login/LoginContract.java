package com.mayi.yun.teachsystem.ui.login;


import com.mayi.yun.teachsystem.base.BaseContract;
import com.mayi.yun.teachsystem.bean.UserInfo;

/**
 * 作者： wh
 * 时间：  2018/3/12
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface LoginContract {
    interface View extends BaseContract.BaseView {
        String getMemberSn();

        String getPassword();

        void setUseInfo(UserInfo useInfo);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void login();
    }
}
