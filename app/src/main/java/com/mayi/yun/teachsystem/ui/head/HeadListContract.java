package com.mayi.yun.teachsystem.ui.head;


import com.mayi.yun.teachsystem.base.BaseContract;
import com.mayi.yun.teachsystem.bean.UserInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2018/3/12
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface HeadListContract {
    interface View extends BaseContract.BaseView {
        String getUserType();
        String getClassId();
        void setUserInfoList(List<UserInfo> userInfoList);
    }
    interface Presenter extends BaseContract.BasePresenter<View> {
        void getUserByClassId();
    }
}
