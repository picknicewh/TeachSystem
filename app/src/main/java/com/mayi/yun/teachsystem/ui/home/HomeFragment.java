package com.mayi.yun.teachsystem.ui.home;

import android.content.Intent;
import android.view.View;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseFragment;
import com.mayi.yun.teachsystem.bean.BannerVo;
import com.mayi.yun.teachsystem.network.GlideImageLoader;
import com.mayi.yun.teachsystem.ui.course.CourseScheduleActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, OnBannerListener {
    /**
     * 轮播图
     */
    @BindView(R.id.banner)
    Banner banner;
    Unbinder unbinder;
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

    }
    @OnClick(R.id.tv_course)
    public void course() {
        Intent intent = new Intent(getActivity(), CourseScheduleActivity.class);
        startActivity(intent);
    }
}
