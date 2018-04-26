package com.mayi.yun.teachsystem.ui.course;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.utils.DateUtil;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;
import com.mayi.yun.teachsystem.widget.CourseChooseDialog;
import com.mayi.yun.teachsystem.widget.MyGridView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class CourseScheduleActivity extends BaseClassActivity<CourseSchedulePresenter> implements CourseScheduleContract.View, OnItemClickListener, AdapterView.OnItemClickListener, CourseChooseDialog.OnConformCallBack {
    /**
     * 周
     */
    @BindView(R.id.rv_week)
    RecyclerView rvWeek;
    /**
     * 课程列表
     */
    @BindView(R.id.gv_course)
    MyGridView gvCourse;


    /**
     * 日期列表
     */
    private List<String> dateList;
    /**
     * 周末适配器
     */
    private WeekAdapter adapter;
    /**
     * 课程列表
     */
    private List<String> courseList;
    /**
     * 课程适配器
     */
    private CourseScheduleAdapter courseAdapter;

    /**
     * 位置
     */
    private int position = 0;
    /**
     * 点击课程的位置
     */
    private int clickPosition = 0;
    private CourseChooseDialog dialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_schedule;
    }

    @Override
    public void initView() {
        setTitleTextId(R.string.course);
        setSubTitleText(DateUtil.getFormatDate(new Date()));
        init();
        adapter = new WeekAdapter(this, dateList);
        rvWeek.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvWeek.setAdapter(adapter);

        adapter.initData(DateUtil.getWeekOfPosition());
        adapter.setOnItemClickListener(this);
        courseAdapter = new CourseScheduleAdapter(this, courseList);
        gvCourse.setAdapter(courseAdapter);
        gvCourse.setOnItemClickListener(this);

        dialog = new CourseChooseDialog(this);
        dialog.setOnConformCallBack(this);


    }


    private void init() {
        courseList = new ArrayList<>();
        dateList = DateUtil.getWeekdays();
        for (int i = 0; i < 28; i++) {
            if (i % 6 == 0)
                courseList.add("java程序设计与艺术");
            else if (i % 3 == 0)
                courseList.add("C程序程序设计");
            else courseList.add("" + i);
        }
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }


    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (UserMessage.getInstance().getUserType() == 1) {
            this.clickPosition = position;
            dialog.show();
        }
    }

    @Override
    public void onCallBack(String room, String teacher, String course) {
        /*course + "(" + teacher + ")" +*/
        String info =  room;
        courseList.set(clickPosition, info);
        if (courseAdapter!=null){
             courseAdapter.notifyDataSetChanged();
        }
    }

    private String[] courses = new String[]{

    };
}
