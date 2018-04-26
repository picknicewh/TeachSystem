package com.mayi.yun.teachsystem.ui.leave.teacher;

import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LeaveDetailActivity extends BaseClassActivity<LeaveDetailPresenter> {


    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_startTime)
    TextView tvStartTime;
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
    @BindView(R.id.tv_course)
    TextView tvCourse;

    @Override
    public void initInjector() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_leave_detail;
    }

    @Override
    public void initView() {

    }


    @OnClick(R.id.tv_conform)
    public void conform() {
    }
}
