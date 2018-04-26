package com.mayi.yun.teachsystem.ui.leave.teacher;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.VacationVo;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.ui.leave.student.LeaveAskActivity;
import com.mayi.yun.teachsystem.utils.G;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LeaveListActivity extends BaseClassActivity<LeaveListPresenter> implements View.OnClickListener, OnItemClickListener {

    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    @BindView(R.id.prv)
    PullToRefreshRecyclerView prv;
    private RecyclerView recyclerView;
    private LeaveListAdapter adapter;
     private List<VacationVo> vacationVoList;
    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_leave_t;
    }

    @Override
    public void initView() {
        setTitleTextId(R.string.leave);
        if (UserMessage.getInstance().getUserType()==3){
            setSubTitleTextId(R.string.leave_ask);
        }
        setSubtitleClickListener(this);
        recyclerView = prv.getRefreshableView();
        prv.setMode(PullToRefreshBase.Mode.BOTH);
        vacationVoList = new ArrayList<>();
        vacationVoList= G.getVacationVoList();
        adapter = new LeaveListAdapter(vacationVoList);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, LeaveAskActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view, int position) {
        if (UserMessage.getInstance().getUserType()==2){

        }
        Intent intent = new Intent(this, LeaveDetailActivity.class);
        startActivity(intent);
    }
}
