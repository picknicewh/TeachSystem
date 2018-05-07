package com.mayi.yun.teachsystem.ui.head;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.utils.Constant;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HeadListActivity extends BaseClassActivity<HeadListPresenter> implements View.OnClickListener, OnItemClickListener, HeadListContract.View {

    @BindView(R.id.rv_head)
    RecyclerView rvHead;

    private HeadListAdapter adapter;
    private List<UserInfo> userInfoList;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_head_list;
    }

    @Override
    public void initView() {
        setTitleText("班主任列表");
        setSubTitleText("添加");
        setSubtitleClickListener(this);
        userInfoList = new ArrayList<>();
        adapter = new HeadListAdapter(userInfoList);
        rvHead.setLayoutManager(new LinearLayoutManager(this));
        rvHead.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        if (mPresenter != null) {
            mPresenter.getUserByClassId();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, EditHeadActivity.class);
        intent.putExtra("operation", Constant.ADD);
        startActivity(intent);

    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, EditHeadActivity.class);
        intent.putExtra("userInfo", userInfoList.get(position));
        intent.putExtra("operation", Constant.EDIT);
        startActivity(intent);
    }

    @Override
    public String getUserType() {
        return "1";
    }


    @Override
    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList.clear();
        this.userInfoList.addAll(userInfoList);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
