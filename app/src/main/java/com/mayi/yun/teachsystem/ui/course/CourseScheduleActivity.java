package com.mayi.yun.teachsystem.ui.course;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.CourseVo;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.utils.DateUtil;
import com.mayi.yun.teachsystem.utils.G;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;
import com.mayi.yun.teachsystem.widget.ConformDialog;
import com.mayi.yun.teachsystem.widget.CourseChooseDialog;
import com.mayi.yun.teachsystem.widget.MyGridView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class CourseScheduleActivity extends BaseClassActivity<CourseSchedulePresenter> implements CourseScheduleContract.View, OnItemClickListener, AdapterView.OnItemClickListener, CourseChooseDialog.OnConformCallBack, AdapterView.OnItemLongClickListener, ConformDialog.OnConformCallBack {
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
     * 点击课程的位置
     */
    private int clickPosition = 0;
    private CourseChooseDialog dialog;
    /**
     * 教师名称
     */
    private String classRoom = "";
    /**
     * 老师id
     */
    private String teacherId = "";
    /**
     * 老师名字
     */
    private String teacherName = "";
    /**
     * 课程名称
     */
    private String schedule = "";
    /**
     * 课程列表
     */
    private List<CourseVo> courseVoList;

    private String classId = "";
    private int scheduleId = -1;
    private ConformDialog conformDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_schedule;
    }

    @Override
    public void initView() {
        setTitleTextId(R.string.course);
        setSubTitleText(DateUtil.getFormatDate(new Date()));
        classId = getIntent().getStringExtra("classId");
        init();
        userInfoList = new ArrayList<>();
        adapter = new WeekAdapter(this, dateList);
        rvWeek.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvWeek.setAdapter(adapter);
        adapter.initData(DateUtil.getWeekOfPosition());

        adapter.setOnItemClickListener(this);
        courseVoList = new ArrayList<>();
        courseAdapter = new CourseScheduleAdapter(this, courseList);
        gvCourse.setAdapter(courseAdapter);
        gvCourse.setOnItemClickListener(this);
        gvCourse.setOnItemLongClickListener(this);
        dialog = new CourseChooseDialog(this);
        dialog.setOnConformCallBack(this);
        conformDialog = new ConformDialog(this);
        conformDialog.setOnConformCallBack(this);
        if (mPresenter != null) {
            mPresenter.getUserByClassId();
            mPresenter.getScheduleList();
        }
    }


    private void init() {
        courseList = new ArrayList<>();
        dateList = DateUtil.getWeekdays();
        for (int i = 0; i < 28; i++) {
            courseList.add("");
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
            G.log("ssxxssss" + "行：" + getNumberData(clickPosition) + "列：" + getWeekData(clickPosition));
            dialog.show();
            dialog.setUserInfoList(userInfoList);
        }
    }

    @Override
    public void onCallBack(String room, UserInfo userInfo) {
        teacherId = String.valueOf(userInfo.getUserId());
        teacherName = userInfo.getTruename();
        schedule = userInfo.getPosition();
        classRoom = room;
        if (mPresenter != null) {
            mPresenter.addSchedule();
        }
    }

    @Override
    public String getClassId() {
        if (G.isEmteny(classId)) {
            classId = String.valueOf(UserMessage.getInstance().getClassId());
        }
        return classId;
    }

    @Override
    public String getClassName() {
        return getIntent().getStringExtra("className");
    }

    @Override
    public String getTeacherId() {
        return teacherId;
    }

    @Override
    public String getTeacherName() {
        return teacherName;
    }

    @Override
    public String getSchedule() {
        return schedule;
    }

    @Override
    public int getScheduleId() {
        return getScheuleId(clickPosition);
    }

    @Override
    public int getNumber() {
        return getNumberData(clickPosition);
    }

    @Override
    public int getWeek() {
        return getWeekData(clickPosition);
    }


    @Override
    public String getClassRoom() {
        return classRoom;
    }

    @Override
    public String getUserType() {
        return "2";
    }

    private List<UserInfo> userInfoList;

    @Override
    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    @Override
    public void setCourseList(List<CourseVo> courseVoList) {
        this.courseVoList.clear();
        this.courseVoList.addAll(courseVoList);
        for (int i = 0; i < courseVoList.size(); i++) {
            CourseVo courseVo = courseVoList.get(i);
            int weekday = courseVo.getWeekday();
            int number = courseVo.getNumber();
            int position = number * 7 + weekday - 8;
            String info = courseVo.getSchedule() + courseVo.getTeacherName() + courseVo.getClassroom() + "室";
            courseList.set(position, info);
        }
        if (courseAdapter != null) {
            courseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSuccess() {
        if (mPresenter != null) {
            mPresenter.getScheduleList();
        }
    }

    private int getWeekData(int clickPosition) {
        int week = 1;
        if (clickPosition == 0 || clickPosition == 7 || clickPosition == 14 || clickPosition == 21) {
            week = 1;
        } else if (clickPosition == 1 || clickPosition == 8 || clickPosition == 15 || clickPosition == 22) {
            week = 2;
        } else if (clickPosition == 2 || clickPosition == 9 || clickPosition == 16 || clickPosition == 23) {
            week = 3;
        } else if (clickPosition == 3 || clickPosition == 10 || clickPosition == 17 || clickPosition == 24) {
            week = 4;
        } else if (clickPosition == 4 || clickPosition == 11 || clickPosition == 18 || clickPosition == 25) {
            week = 5;
        } else if (clickPosition == 5 || clickPosition == 12 || clickPosition == 19 || clickPosition == 26) {
            week = 6;
        } else if (clickPosition == 6 || clickPosition == 13 || clickPosition == 20 || clickPosition == 27) {
            week = 7;
        }
        return week;
    }

    private int getNumberData(int clickPosition) {
        int number = 1;
        if (clickPosition <= 6) {
            number = 1;
        } else if (clickPosition <= 13) {
            number = 2;
        } else if (clickPosition <= 20) {
            number = 3;
        } else if (clickPosition <= 27) {
            number = 4;
        }
        return number;
    }

    public int getScheuleId(int clickPosition) {
        int scheduleId = -1;
        int number = getNumberData(clickPosition);
        int week = getWeekData(clickPosition);
        for (int i = 0;i<courseVoList.size();i++){
            CourseVo courseVo =courseVoList.get(i);
            if (courseVo.getWeekday()==week && courseVo.getNumber()==number){
                scheduleId = courseVo.getId();
            }
        }
        return scheduleId;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (UserMessage.getInstance().getUserType() == 1) {
            conformDialog.show();
            conformDialog.setTitle("确定要删除此条课程吗？");
        }
        return false;
    }

    @Override
    public void onCallBack() {
        if (scheduleId == -1) {
            G.showToast(this, "当前时间课程表为空！");
            return;
        }
        if (mPresenter != null) {
            mPresenter.delSchedule();
        }
    }
}
