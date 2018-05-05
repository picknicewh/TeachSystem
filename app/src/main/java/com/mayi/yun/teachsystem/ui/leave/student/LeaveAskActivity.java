package com.mayi.yun.teachsystem.ui.leave.student;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.utils.DataUtil;
import com.mayi.yun.teachsystem.utils.DateUtil;
import com.mayi.yun.teachsystem.utils.G;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间： 2016/7/18
 * 名称：请假申请
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class LeaveAskActivity extends BaseClassActivity<LeaveAskPresenter> implements LeaveAskContract.View, View.OnClickListener {
    /**
     * 用户名字
     */
    @BindView(R.id.tv_name)
    TextView tvName;
    /**
     * 开始时间
     */
    @BindView(R.id.tv_startTime)
    TextView tvStartTime;
    /**
     * 结束时间
     */
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
    /**
     * 事由
     */
    @BindView(R.id.tv_course)
    TextView tvCourse;
    /**
     * 事由
     */
    @BindView(R.id.et_cause)
    EditText etCause;
    /**
     * 记录选中开始日期
     */
    private Calendar startCalendar = Calendar.getInstance();
    /**
     * 记录选中结束日期
     */
    private Calendar endCalendar = Calendar.getInstance();
    /**
     * 开始时间选择
     */
    private TimePickerView startTimePickerView;
    /**
     * 结束时间选择
     */
    private TimePickerView endTimePickerView;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 选项
     */
    private OptionsPickerView optionsPickerView;
    /**
     * 事由列表
     */
    private List<String> courseList;
    /**
     * 位置
     */
    private int position = 0;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_leave_ask;
    }

    @Override
    public void initView() {
        setTitleTextId(R.string.leave_ask);
        setSubTitleText("完成");
        setSubtitleClickListener(this);
        startDate = DateUtil.getNeedTime(8, 0, 0, -1);
        endDate = DateUtil.getNeedTime(8, 0, 0, 0);
        tvStartTime.setText(DateUtil.getFormatHourDate(startDate));    //默认日期
        tvEndTime.setText(DateUtil.getFormatHourDate(endDate));  //默认日期的后7天
        startCalendar.setTime(startDate);//默认日期选中的开始时间
        endCalendar.setTime(endDate);
        startTimePickerView = DateUtil.getTimePickerView("请选择开始时间", this, startCalendar, startListener);
        endTimePickerView = DateUtil.getTimePickerView("请选择结束时间", this, endCalendar, endListener);
        courseList = DataUtil.getCauseList();
        optionsPickerView = DateUtil.getOptionPickerView("请选择请假事由", courseList, position, this, optionsSelectListener);
        tvName.setText(UserMessage.getInstance().getTruename());
    }

    private OptionsPickerView.OnOptionsSelectListener optionsSelectListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            position = options1;
            tvCourse.setText(courseList.get(options1));
        }
    };
    private TimePickerView.OnTimeSelectListener startListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            startCalendar.setTime(date);
            startDate = date;
            String mDate = DateUtil.getFormatHourDate(date);
            tvStartTime.setText(mDate);

        }
    };
    private TimePickerView.OnTimeSelectListener endListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            endCalendar.setTime(date);
            endDate = DateUtil.geteEndDate(date);
            String mDate = DateUtil.getFormatHourDate(date);
            tvEndTime.setText(mDate);

        }
    };


    @OnClick(R.id.ll_startTime)
    public void startTime() {
        startTimePickerView.show();
    }

    @OnClick(R.id.ll_endTime)
    public void endTime() {
        endTimePickerView.show();
    }


    @OnClick(R.id.ll_course)
    public void course() {
        optionsPickerView.show();
    }

    @Override
    public String getUserId() {
        return String.valueOf(UserMessage.getInstance().getUserId());
    }

    @Override
    public String getUserName() {
        return UserMessage.getInstance().getUsername();
    }

    @Override
    public String getReason() {
        return tvCourse.getText().toString();
    }

    @Override
    public int getDays() {
        int days = DateUtil.getBetweenDays(getStartTime(), getEndTime());
        return days;
    }

    @Override
    public String getStartTime() {
        return tvStartTime.getText().toString();
    }

    @Override
    public String getEndTime() {
        return tvEndTime.getText().toString();
    }

    @Override
    public void onSuccess() {
        finish();
    }

    @Override
    public void onClick(View view) {
        if (G.isEmteny(getReason())) {
            G.showToast(this, "请假是由不能为空！");
            return;
        }
        if (mPresenter != null) {
            mPresenter.addLeave();
        }
    }
}
