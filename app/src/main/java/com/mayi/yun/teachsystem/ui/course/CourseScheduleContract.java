package com.mayi.yun.teachsystem.ui.course;


import com.mayi.yun.teachsystem.base.BaseContract;
import com.mayi.yun.teachsystem.bean.CourseVo;
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
public interface CourseScheduleContract {
    interface View extends BaseContract.BaseView {
        String getClassId();
        String getClassName();
        String getTeacherId();
        String getTeacherName();
        String getSchedule();
        int getNumber();
        int getWeek();
        String getClassRoom();
        String getUserType();
        void setUserInfoList(List<UserInfo> userInfoList);
        void setCourseList(List<CourseVo> courseVoList);
        void addSuccess();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
       void addSchedule();
        void getUserByClassId();
        void getScheduleList();
    }
}
