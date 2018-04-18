package com.mayi.yun.teachsystem.ui.course;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigkoo.pickerview.TimePickerView;
import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.utils.DateUtil;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class CourseScheduleActivity extends BaseClassActivity<CourseSchedulePresenter> implements CourseScheduleContract.View, TimePickerView.OnTimeSelectListener, View.OnClickListener, OnItemClickListener {
    @BindView(R.id.rv_week)
    RecyclerView rvWeek;
    /**
     * 时间选择器
     */
    private TimePickerView mTimePickerView;
    /**
     * 当前时间
     */
    private Calendar calendar;
    /**
     * 选择的日期
     */
    private String mDate;
   private List<String> dateList;
   private WeekAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_course_schedule;
    }

    @Override
    public void initView() {
        setTitleTextId(R.string.course);
        calendar = Calendar.getInstance();
        mDate = DateUtil.getFormatDate(new Date());
        setSubTitleText(mDate);
        mTimePickerView = DateUtil.getTimePickerView("选择时间", this, calendar, this);
        setSubtitleClickListener(this);
        init();
        adapter =new WeekAdapter(this,dateList);
        rvWeek.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rvWeek.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }
   private void init(){
       dateList = new ArrayList<>();
       dateList.add("30");
       dateList.add("31");
       dateList.add("01");
       dateList.add("02");
       dateList.add("03");
       dateList.add("04");
       dateList.add("05");
   }
    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void onTimeSelect(Date date, View v) {
        mDate = DateUtil.getFormatDate(date);
        setSubTitleText(mDate);
    }

    @Override
    public void onClick(View view) {
        mTimePickerView.show();
    }


    @Override
    public void onClick(View view, int position) {

    }
}
