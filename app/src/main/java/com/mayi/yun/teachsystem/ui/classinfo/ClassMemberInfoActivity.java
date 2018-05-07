package com.mayi.yun.teachsystem.ui.classinfo;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClassMemberInfoActivity extends BaseClassActivity<ClassMemberInfoPresenter> implements View.OnClickListener, OnItemClickListener, ClassMemberInfoContract.View {

    /**
     * 班级信息
     */
    @BindView(R.id.rv_class_info)
    RecyclerView rvClassInfo;
    /**
     * 班级名称
     */
    @BindView(R.id.tv_class)
    TextView tvClass;
    /**
     * 班级人数
     */
    @BindView(R.id.tv_count)
    TextView tvCount;
    private ClassMemberAdapter adapter;
    private List<UserInfo> userInfoList;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_class_info;
    }

    @Override
    public void initView() {
        setTitleTextId(R.string.member);
        userInfoList = new ArrayList<>();
        if (UserMessage.getInstance().getUserType() == 1) {
            setSubTitleText("添加");
        }
        setSubtitleClickListener(this);
        adapter = new ClassMemberAdapter(userInfoList, this);
        rvClassInfo.setLayoutManager(new LinearLayoutManager(this));
        rvClassInfo.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.getUserByClassId();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, AddMemberActivity.class);
        intent.putExtra("className",getIntent().getStringExtra("className"));
        startActivity(intent);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, EditMemberActivity.class);
        intent.putExtra("userInfo", userInfoList.get(position));

        startActivity(intent);
    }

    @Override
    public String getUserType() {
        return "3";
    }

    @Override
    public String getClassId() {
        String classId;
        if (UserMessage.getInstance().getUserType() == 2) {
            classId = getIntent().getStringExtra("classId");
        } else {
            classId = String.valueOf(UserMessage.getInstance().getClassId());
        }
        return classId;
    }

    @Override
    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList.clear();
        this.userInfoList.addAll(userInfoList);
        tvCount.setText(String.valueOf(userInfoList.size()) + "人");
        if (userInfoList.size() > 0) {
            //
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

}
