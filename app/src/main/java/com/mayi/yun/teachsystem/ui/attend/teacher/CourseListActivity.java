package com.mayi.yun.teachsystem.ui.attend.teacher;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.CourseVo;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CourseListActivity extends BaseClassActivity<CourseListPresenter> implements CourseListContract.View, OnItemClickListener {


    @BindView(R.id.rv_course)
    RecyclerView rvCourse;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private CourseListAdapter adapter;
    private List<CourseVo> courseVoList;
    private String classId;
    private String className;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_list;
    }

    @Override
    public void initView() {
        setTitleText("当天课程列表");
        //  classId = getIntent().getStringExtra("classId");
        //    className = getIntent().getStringExtra("className");
        courseVoList = new ArrayList<>();
        adapter = new CourseListAdapter(courseVoList);
        rvCourse.setLayoutManager(new LinearLayoutManager(this));
        rvCourse.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        if (mPresenter != null) {
            mPresenter.getCourseList();
        }
    }

    @Override
    public String getTeacherId() {
        return String.valueOf(UserMessage.getInstance().getUserId());
    }

    @Override
    public void setCourseList(List<CourseVo> courseVoList) {
        tvNodata.setVisibility(courseVoList.size()==0?View.VISIBLE:View.GONE);
        this.courseVoList.clear();
        this.courseVoList.addAll(courseVoList);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view, int position) {
        CourseVo courseVo = courseVoList.get(position);
        Intent intent = new Intent(this, AttentionActivityT.class);
        intent.putExtra("scheduleId", courseVo.getId());
        intent.putExtra("scheduleName", courseVo.getSchedule());
        intent.putExtra("classId", String.valueOf(courseVo.getClassId()));
        intent.putExtra("className", courseVo.getClassName());
        startActivity(intent);
    }


}
