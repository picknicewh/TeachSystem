package com.mayi.yun.teachsystem.ui.head;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.utils.Constant;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import butterknife.BindView;

public class HeadListActivity extends BaseClassActivity<HeadListPresenter> implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.rv_head)
    RecyclerView rvHead;

private  HeadListAdapter adapter;

    @Override
    public void initInjector() {

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
        adapter = new HeadListAdapter();
        rvHead.setLayoutManager(new LinearLayoutManager(this));
        rvHead.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,EditHeadActivity.class);
        intent.putExtra("operation", Constant.ADD);
        startActivity(intent);

    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this,EditHeadActivity.class);
        intent.putExtra("operation", Constant.EDIT);
        startActivity(intent);
    }
}
