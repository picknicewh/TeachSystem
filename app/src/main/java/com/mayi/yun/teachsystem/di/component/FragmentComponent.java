package com.mayi.yun.teachsystem.di.component;

import android.app.Activity;
import android.content.Context;

import com.mayi.yun.teachsystem.di.module.FragmentModule;
import com.mayi.yun.teachsystem.di.scope.ContextLife;
import com.mayi.yun.teachsystem.di.scope.PerFragment;
import com.mayi.yun.teachsystem.ui.home.HomeFragment;
import com.mayi.yun.teachsystem.ui.study.StudyFragment;
import com.mayi.yun.teachsystem.ui.study.StudySystemFragment;
import com.mayi.yun.teachsystem.ui.study.detail.ArticleListFragment;

import dagger.Component;

/**
 * 作者： wh
 * 时间：  2018/2/28
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
@PerFragment
@Component(dependencies = {ApplicationComponent.class}, modules = {FragmentModule.class})
public interface FragmentComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(HomeFragment homeFragment);
    //void inject(MyFragment myFragment);
    void inject(StudyFragment newsFragment);
    void inject(ArticleListFragment fragment);
    void inject(StudySystemFragment studySystemFragment);

}
