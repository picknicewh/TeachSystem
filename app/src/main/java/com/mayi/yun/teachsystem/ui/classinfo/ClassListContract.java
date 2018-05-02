package com.mayi.yun.teachsystem.ui.classinfo;


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
public interface ClassListContract {
    interface View extends BaseContract.BaseView {
        String getTeacherId();
        void setClassList(List<ClassVo> classList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getClassList();
    }
}
