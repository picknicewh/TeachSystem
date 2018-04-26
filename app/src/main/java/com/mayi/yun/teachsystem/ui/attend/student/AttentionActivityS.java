package com.mayi.yun.teachsystem.ui.attend.student;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.CourseInfo;
import com.mayi.yun.teachsystem.utils.G;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AttentionActivityS extends BaseClassActivity<AttentionPresenterS> {


    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.rv_course)
    RecyclerView rvCourse;
   private AttentionAdapterS adapter;
   private List<CourseInfo> courseInfoList;
    @Override
    public void initInjector() {
      mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_attention;
    }

    @Override
    public void initView() {
        setTitleTextId(R.string.attend_s);
        courseInfoList = new ArrayList<>();
        courseInfoList = G.getCourseInfoList();
        adapter = new AttentionAdapterS(courseInfoList);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
        rvCourse.setLayoutManager(new LinearLayoutManager(this));
        rvCourse.setAdapter(adapter);
    }

}
