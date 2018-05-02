package com.mayi.yun.teachsystem.ui.home;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseFragment;
import com.mayi.yun.teachsystem.bean.BannerVo;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.network.GlideImageLoader;
import com.mayi.yun.teachsystem.ui.attend.head.AttentionActivityH;
import com.mayi.yun.teachsystem.ui.attend.student.AttentionActivityS;
import com.mayi.yun.teachsystem.ui.classinfo.ClassListActivity;
import com.mayi.yun.teachsystem.ui.classinfo.ClassMemberInfoActivity;
import com.mayi.yun.teachsystem.ui.course.CourseScheduleActivity;
import com.mayi.yun.teachsystem.ui.leave.teacher.LeaveListActivity;
import com.mayi.yun.teachsystem.ui.study.detail.ArticleContentActivity;
import com.mayi.yun.teachsystem.utils.Constant;
import com.mayi.yun.teachsystem.utils.DateUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, OnBannerListener {
    /**
     * 轮播图
     */
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_date)
    TextView tvDate;


    /**
     * 图片列表
     */
    private List<String> linkList;
    /**
     * 标题列表
     */
    private List<String> titleList;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        Date date = new Date();
        String dateStr = DateUtil.getChinaFormatDate(date) + "  " + DateUtil.getWeekOfDate(date);
        tvDate.setText(dateStr);
        titleList = new ArrayList<>();
        linkList = new ArrayList<>();
        banner.setOnBannerListener(this);
        if (mPresenter != null) {
            mPresenter.getHomeBanner();
        }
    }


    @Override
    public void setHomeBanner(List<BannerVo> bannerList) {
        List<String> imageUrlList = new ArrayList<>();
        titleList.clear();
        for (int i = 0; i < bannerList.size(); i++) {
            BannerVo banner = bannerList.get(i);
            imageUrlList.add(banner.getImagePath());
            titleList.add(banner.getTitle());
            linkList.add(banner.getUrl());
        }
        banner.setImages(imageUrlList)
                .setBannerTitles(titleList)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new GlideImageLoader())
                .start();
    }

    @Override
    public void OnBannerClick(int position) {
         Intent intent = new Intent(getActivity(), ArticleContentActivity.class);
         intent.putExtra("link",linkList.get(position));
         intent.putExtra("title",titleList.get(position));
         startActivity(intent);
    }

    @OnClick(R.id.tv_course)
    public void course() {
        Intent intent = new Intent();
        if (UserMessage.getInstance().getUserType()!=3){
            intent.setClass(getActivity(),ClassListActivity.class);
            intent.putExtra("source",Constant.SOURCE_COURSE);
        }else {
            intent.setClass(getActivity(),CourseScheduleActivity.class);
        }
        startActivity(intent);
    }


    @OnClick(R.id.tv_info)
    public void info() {
        Intent intent = new Intent();
        if (UserMessage.getInstance().getUserType()!=3){
            intent.setClass(getActivity(),ClassListActivity.class);
            intent.putExtra("source",Constant.SOURCE_MEMBER);
        }else {
            intent.setClass(getActivity(),ClassMemberInfoActivity.class);
        }
        startActivity(intent);
    }

    @OnClick(R.id.tv_attend)
    public void attend() {
        int type = UserMessage.getInstance().getUserType();
        Intent intent = new Intent();
        switch (type) {
            case Constant.HEAD:
                intent.setClass(getActivity(), AttentionActivityH.class);
                break;
            case Constant.TEACHER:
                intent.setClass(getActivity(), ClassListActivity.class);
                intent.putExtra("source",Constant.SOURCE_ATTEBD);
                break;
            case Constant.STUDENT:
                intent.setClass(getActivity(), AttentionActivityS.class);
                break;
        }
        startActivity(intent);
    }

    @OnClick(R.id.tv_leave)
    public void leave() {
        Intent intent = new Intent(getActivity(), LeaveListActivity.class);
        startActivity(intent);
    }
}
