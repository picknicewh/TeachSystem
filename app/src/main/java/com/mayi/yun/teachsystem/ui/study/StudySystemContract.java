package com.mayi.yun.teachsystem.ui.study;


import com.mayi.yun.teachsystem.base.BaseContract;
import com.mayi.yun.teachsystem.bean.StudyVo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2018/3/1
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface StudySystemContract {
    interface View extends BaseContract.BaseView {
        void setKnowledgeSystemList(List<StudyVo> knowledgeSystemList);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getKnowledgeSystemList();
    }
}
