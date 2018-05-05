package com.mayi.yun.teachsystem.ui.my;


import com.mayi.yun.teachsystem.base.BaseContract;
import com.mayi.yun.teachsystem.bean.ScheduleVo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2018/3/12
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface MyContract {
    interface View extends BaseContract.BaseView {
        String getUserId();
        void setScheduleVoList(List<ScheduleVo> scheduleVoList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getSignListByUserId();
    }
}
