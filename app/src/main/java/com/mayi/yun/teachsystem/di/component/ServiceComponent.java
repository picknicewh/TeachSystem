package com.mayi.yun.teachsystem.di.component;

import android.content.Context;

import com.mayi.yun.teachsystem.di.module.ServiceModule;
import com.mayi.yun.teachsystem.di.scope.ContextLife;
import com.mayi.yun.teachsystem.di.scope.PerService;

import dagger.Component;

;

/**
 * 作者： wh
 * 时间：  2018/2/28
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
@PerService
@Component(dependencies = {ApplicationComponent.class}, modules = {ServiceModule.class})
public interface ServiceComponent {

    @ContextLife("Service")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

}
