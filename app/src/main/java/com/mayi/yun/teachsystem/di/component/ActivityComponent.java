package com.mayi.yun.teachsystem.di.component;

import android.app.Activity;
import android.content.Context;

import com.mayi.yun.teachsystem.di.module.ActivityModule;
import com.mayi.yun.teachsystem.di.scope.ContextLife;
import com.mayi.yun.teachsystem.di.scope.PerActivity;
import com.mayi.yun.teachsystem.ui.attend.head.AttentionActivityH;
import com.mayi.yun.teachsystem.ui.attend.student.AttentionActivityS;
import com.mayi.yun.teachsystem.ui.attend.teacher.CourseListActivity;
import com.mayi.yun.teachsystem.ui.attend.teacher.UnAttentionFragment;
import com.mayi.yun.teachsystem.ui.classinfo.AddMemberActivity;
import com.mayi.yun.teachsystem.ui.classinfo.ClassListActivity;
import com.mayi.yun.teachsystem.ui.classinfo.ClassMemberInfoActivity;
import com.mayi.yun.teachsystem.ui.classinfo.EditMemberActivity;
import com.mayi.yun.teachsystem.ui.course.CourseScheduleActivity;
import com.mayi.yun.teachsystem.ui.head.EditHeadActivity;
import com.mayi.yun.teachsystem.ui.head.HeadListActivity;
import com.mayi.yun.teachsystem.ui.leave.student.LeaveAskActivity;
import com.mayi.yun.teachsystem.ui.leave.teacher.LeaveDetailActivity;
import com.mayi.yun.teachsystem.ui.leave.teacher.LeaveListActivity;
import com.mayi.yun.teachsystem.ui.login.LoginActivity;
import com.mayi.yun.teachsystem.ui.my.MyAttendActivity;
import com.mayi.yun.teachsystem.ui.password.PasswordActivity;

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
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(CourseScheduleActivity courseScheduleActivity);

    void inject(ClassMemberInfoActivity classMemberInfoActivity);

    void inject(AttentionActivityS attentionActivityS);

    void inject(UnAttentionFragment attentionActivityT);


    void inject(AttentionActivityH attentionActivityH);

    void inject(LeaveAskActivity leaveAskActivity);

    void inject(LeaveListActivity leaveListActivity);

    void inject(AddMemberActivity addMemberActivity);

    void inject(LoginActivity loginActivity);

    void inject(ClassListActivity classListActivity);

    void inject(CourseListActivity courseListActivity);

    void inject(MyAttendActivity myAttendActivity);

    void inject(LeaveDetailActivity leaveDetailActivity);

    void inject(HeadListActivity headListActivity);

    void inject(EditHeadActivity editHeadActivity);

    void inject(PasswordActivity passwordActivity);

    void inject(EditMemberActivity editMemberActivity);

   // void inject(AttendResultActivity attendResultActivity);
}
