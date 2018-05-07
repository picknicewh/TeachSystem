package com.mayi.yun.teachsystem.ui.head;


import com.mayi.yun.teachsystem.base.BaseContract;
import com.mayi.yun.teachsystem.bean.ClassVo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2018/3/12
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface EditHeadContract {
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

        void setImagePath(String imagePath);

        int getUserId();

        void success();

        void setClassList(List<ClassVo> classList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void addMember();

        void getClassList();

        void delUser();

        void updateImage();

        void updateUser();
    }
}
