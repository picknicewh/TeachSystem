package com.mayi.yun.teachsystem.ui.leave.teacher;

import android.view.View;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.VacationVo;
import com.mayi.yun.teachsystem.db.UserMessage;

import butterknife.BindView;
import butterknife.OnClick;

public class LeaveDetailActivity extends BaseClassActivity<LeaveDetailPresenter> implements LeaveDetailContract.View {


    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_startTime)
    TextView tvStartTime;
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
    @BindView(R.id.tv_course)
    TextView tvCourse;
    private VacationVo vacationVo;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_leave_detail;
    }

    @Override
    public void initView() {
        setTitleText("审核请假");
        vacationVo = (VacationVo) getIntent().getSerializableExtra("vacationVo");
        tvName.setText(vacationVo.getUserName());
        tvStartTime.setText(vacationVo.getStarttime());
        tvEndTime.setText(vacationVo.getEndtime());
        tvCourse.setText(vacationVo.getReason());
    }


    @Override
    public int getScheduleId() {
        return vacationVo.getId();
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public int getTeacherId() {
        return UserMessage.getInstance().getUserId();
    }

    @Override
    public String getTeacherName() {
        return UserMessage.getInstance().getTruename();
    }

    @Override
    public void onSuccess() {
        finish();
    }

    private int status;

    @OnClick({R.id.tv_dis_agree, R.id.tv_agree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_dis_agree:
                status = 2;
                break;
            case R.id.tv_agree:
                status = 1;
                break;
        }
        if (mPresenter != null) {
            mPresenter.updateLeave();
        }
    }
}
