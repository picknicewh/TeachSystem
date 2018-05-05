package com.mayi.yun.teachsystem.ui.classinfo;


import com.mayi.yun.teachsystem.base.BaseContract;
import com.mayi.yun.teachsystem.bean.CourseVo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2018/3/12
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface AddMemberContract {
    interface View extends BaseContract.BaseView {
          int getUserType();
          String getClassId();
          String getUserSn();
          String getPhone();
          String getTrueName();
          String getAvatar();
          int getSex();
          String getPosition();
          String getBirthday();
          String getPath();
         void success();
        void setCourseList(List<CourseVo> courseVoList);
         void setImagePath(String path);
    }
    interface Presenter extends BaseContract.BasePresenter<View> {
        void addMember();
        void getScheduleList();
        void updateImage();

    }
}
