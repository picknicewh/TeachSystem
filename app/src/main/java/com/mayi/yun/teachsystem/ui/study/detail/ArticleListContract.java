package com.mayi.yun.teachsystem.ui.study.detail;


import com.mayi.yun.teachsystem.base.BaseContract;
import com.mayi.yun.teachsystem.bean.Article;
import com.mayi.yun.teachsystem.utils.LoadType;

/**
 * 作者： wh
 * 时间：  2018/3/2
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface ArticleListContract {
    interface View extends BaseContract.BaseView {
        void setKnowledgeSysArticle(Article article, @LoadType.checker int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getKnowledgeSysArticle(int cid);
        void refresh();
        void loadMore();

    }
}
