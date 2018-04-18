package com.mayi.yun.teachsystem.di.component;

import android.app.Activity;
import android.content.Context;

import com.mayi.yun.teachsystem.di.module.ActivityModule;
import com.mayi.yun.teachsystem.di.scope.ContextLife;
import com.mayi.yun.teachsystem.di.scope.PerActivity;
import com.mayi.yun.teachsystem.ui.course.CourseScheduleActivity;

import dagger.Component;

/**
 * 作者： wh
 * 时间：  2018/2/28
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class} ,modules = {ActivityModule.class})
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(CourseScheduleActivity courseScheduleActivity);
}
