package com.mayi.yun.teachsystem.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mayi.yun.teachsystem.di.component.ApplicationComponent;
import com.mayi.yun.teachsystem.di.component.DaggerApplicationComponent;
import com.mayi.yun.teachsystem.di.module.ApplicationModule;

import java.util.LinkedList;

import static android.content.ContentValues.TAG;


/**
 * 作者： wh
 * 时间：  2018/2/27
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class App extends Application {
    private static App mInstance;
    private ApplicationComponent mApplicationComponent;
    /**
     * actvity集合
     */
    private static LinkedList<Activity> activityLinkedList;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        activityLinkedList = new LinkedList<>();
        initComponent();
        registerActivityStaus();
    }

    private void initComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder().
                applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static App getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    /**
     * 注册页面状态
     */
    private void registerActivityStaus() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.d(TAG, "onActivityCreated: " + activity.getLocalClassName());
                activityLinkedList.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d(TAG, "onActivityStarted: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d(TAG, "onActivityResumed: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d(TAG, "onActivityPaused: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d(TAG, "onActivityStopped: " + activity.getLocalClassName());
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d(TAG, "onActivityDestroyed: " + activity.getLocalClassName());
                activityLinkedList.remove(activity);
            }
        });
    }



    /**
     * 退出app
     */
    public static void exitApp() {
        for (Activity activity : activityLinkedList) {
            activity.finish();
        }
    }
}
