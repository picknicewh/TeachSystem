package com.mayi.yun.teachsystem.ui.classinfo;


import com.mayi.yun.teachsystem.base.BaseContract;

/**
 * 作者： wh
 * 时间：  2018/3/12
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface EditMemberContract {
    interface View extends BaseContract.BaseView {
        int getUserId();
        int getUserType();
        String getClassId();
        String getUserSn();
        String getPhone();
        String getTrueName();
        String getAvatar();
        int getSex();
        String getPath();
        String getPosition();
        String getBirthday();
        void setImagePath(String path);
        void success();
    }
    interface Presenter extends BaseContract.BasePresenter<View> {
        void updateUser();
        void delUser();
        void updateImage();
    }
}
