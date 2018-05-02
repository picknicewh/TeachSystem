package com.mayi.yun.teachsystem.ui.classinfo;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.ClassVo;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.ui.attend.teacher.AttentionActivityT;
import com.mayi.yun.teachsystem.ui.course.CourseScheduleActivity;
import com.mayi.yun.teachsystem.utils.Constant;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClassListActivity extends BaseClassActivity<ClassListPresenter> implements ClassListContract.View, OnItemClickListener {

    /**
     * 班级列表
     */
    @BindView(R.id.rv_class)
    RecyclerView rvClass;
    private ClassListAdapter adapter;
    private List<ClassVo> classList;
    private int source;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_class_list;
    }

    @Override
    public void initView() {
        setTitleText("班级列表");
        source = getIntent().getIntExtra("source", Constant.SOURCE_MEMBER);
        classList = new ArrayList<>();
        adapter = new ClassListAdapter(classList);
        rvClass.setLayoutManager(new LinearLayoutManager(this));
        rvClass.setAdapter(adapter);
        if (mPresenter != null) {
            mPresenter.getClassList();
        }
        adapter.setOnItemClickListener(this);
    }


    @Override
    public String getTeacherId() {
        return String.valueOf(UserMessage.getInstance().getUserId());
    }

    @Override
    public void setClassList(List<ClassVo> classList) {
        this.classList.clear();
        this.classList.addAll(classList);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent();
        switch (source) {
            case Constant.SOURCE_ATTEBD:
                intent.setClass(this, AttentionActivityT.class);
                break;
            case Constant.SOURCE_MEMBER:
                intent.setClass(this, ClassMemberInfoActivity.class);
                break;
            case Constant.SOURCE_COURSE:
                intent.setClass(this, CourseScheduleActivity.class);
                break;
        }
        intent.putExtra("classId", String.valueOf(classList.get(position).getClassId()));
        intent.putExtra("className",classList.get(position).getName());
        startActivity(intent);
    }
}
