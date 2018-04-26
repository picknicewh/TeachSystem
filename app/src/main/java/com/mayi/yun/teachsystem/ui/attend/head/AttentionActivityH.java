package com.mayi.yun.teachsystem.ui.attend.head;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.ui.attend.student.AttentionActivityS;
import com.mayi.yun.teachsystem.utils.G;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AttentionActivityH extends BaseClassActivity<AttentionPresenterH> implements OnItemClickListener {


    @BindView(R.id.rv_attend)
    RecyclerView rvAttend;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private StudentAttendAdapter adapter;
    private List<UserInfo> userInfoList = new ArrayList<>();

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_attention_h;
    }

    @Override
    public void initView() {
        setTitleTextId(R.string.attend_h);
        userInfoList = G.getUserInfoList();
        adapter = new StudentAttendAdapter(userInfoList);
        adapter.notifyDataSetChanged();
        rvAttend.setLayoutManager(new LinearLayoutManager(this));
        rvAttend.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, AttentionActivityS.class);
        startActivity(intent);
    }


}
