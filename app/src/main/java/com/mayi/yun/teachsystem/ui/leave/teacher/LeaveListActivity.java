package com.mayi.yun.teachsystem.ui.leave.teacher;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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

public class LeaveListActivity extends BaseClassActivity<LeaveListPresenter> implements View.OnClickListener, OnItemClickListener, LeaveListContract.View {

    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    @BindView(R.id.prv)
    RecyclerView recyclerView;
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
        if (UserMessage.getInstance().getUserType() == 3) {
            setSubTitleTextId(R.string.leave_ask);
        }
        setSubtitleClickListener(this);
        vacationVoList = new ArrayList<>();
        adapter = new LeaveListAdapter(vacationVoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UserMessage.getInstance().getUserType() == 3) {
            if (mPresenter != null) {
                mPresenter.getLeaveListUser();
            }
        } else {
            if (mPresenter != null) {
                mPresenter.getLeaveListTeacher();
            }
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, LeaveAskActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view, int position) {
        if (UserMessage.getInstance().getUserType() == 1) {
            if (vacationVoList.get(position).getStatus() == 0) {
                Intent intent = new Intent(this, LeaveDetailActivity.class);
                intent.putExtra("vacationVo", vacationVoList.get(position));
                startActivity(intent);
            } else {
                G.showToast(this, "已经审核完成！");
            }
        }

    }

    @Override
    public int getUserId() {
        return UserMessage.getInstance().getUserId();
    }

    @Override
    public int getTeacherId() {
        return UserMessage.getInstance().getUserId();
    }

    @Override
    public void setVacationVoList(List<VacationVo> vacationVoList) {
        this.vacationVoList.clear();
        this.vacationVoList.addAll(vacationVoList);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
