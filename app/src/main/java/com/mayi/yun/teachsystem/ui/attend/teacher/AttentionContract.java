package com.mayi.yun.teachsystem.ui.attend.teacher;


import com.mayi.yun.teachsystem.base.BaseContract;
import com.mayi.yun.teachsystem.bean.AttendUserInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2018/3/12
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface AttentionContract {
    interface View extends BaseContract.BaseView {
        String getTeacherId();

        int getClassId();

        int getScheduleId();

        void setAttendUserInfoList(List<AttendUserInfo> attendUserInfoList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void getSignListByParams();
    }
}
