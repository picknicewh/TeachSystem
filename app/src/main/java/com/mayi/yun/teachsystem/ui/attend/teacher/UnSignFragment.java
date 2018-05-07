package com.mayi.yun.teachsystem.ui.attend.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.bean.CourseVo;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class UnSignFragment extends Fragment implements OnItemClickListener {
    @BindView(R.id.rv_course)
    RecyclerView rvCourse;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;

    private CourseListAdapter adapter;
    private List<CourseVo> courseVoList;
    private String classId;
    private String className;

    public UnSignFragment() {
    }
    public static UnSignFragment newInstance(){
        UnSignFragment fragment = new UnSignFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseVoList = new ArrayList<>();
        adapter = new CourseListAdapter(courseVoList);
        rvCourse.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCourse.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_un_sign, container, false);
    }

    public void setCourseList(List<CourseVo> courseVoList) {
        tvNodata.setVisibility(courseVoList.size() == 0 ? View.VISIBLE : View.GONE);
        this.courseVoList.clear();
        this.courseVoList.addAll(courseVoList);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onClick(View view, int position) {
        CourseVo courseVo = courseVoList.get(position);
        Intent intent = new Intent(getActivity(), AttentionActivityT.class);
        intent.putExtra("scheduleId", courseVo.getId());
        intent.putExtra("scheduleName", courseVo.getSchedule());
        intent.putExtra("classId", String.valueOf(courseVo.getClassId()));
        intent.putExtra("className", courseVo.getClassName());
        startActivity(intent);
    }
}
