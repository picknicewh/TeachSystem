package com.mayi.yun.teachsystem.ui.leave.teacher;


import com.mayi.yun.teachsystem.base.BaseContract;
import com.mayi.yun.teachsystem.bean.VacationVo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2018/3/12
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface LeaveListContract {
    interface View extends BaseContract.BaseView {
        int getUserId();

        int getTeacherId();

        void setVacationVoList(List<VacationVo> vacationVoList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getLeaveListTeacher();

        void getLeaveListUser();
    }
}
