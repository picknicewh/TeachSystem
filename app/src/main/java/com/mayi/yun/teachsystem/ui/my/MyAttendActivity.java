package com.mayi.yun.teachsystem.ui.my;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.ScheduleVo;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.utils.G;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyAttendActivity extends BaseClassActivity<MyPresenter> implements MyContract.View {

    @BindView(R.id.rv_attend)
    RecyclerView rvAttend;
    private List<ScheduleVo> scheduleVoList;
    private MyAttentionAdapter adapter;
    private String userId="";
    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home_attend_list;
    }

    @Override
    public void initView() {
        setTitleText("考勤列表");
        userId = getIntent().getStringExtra("userId");
        scheduleVoList = new ArrayList<>();
        adapter = new MyAttentionAdapter(scheduleVoList);
        rvAttend.setLayoutManager(new LinearLayoutManager(this));
        rvAttend.setAdapter(adapter);
        if (mPresenter != null) {
            mPresenter.getSignListByUserId();
        }
    }

    @Override
    public String getUserId() {
        if (G.isEmteny(userId)){
            userId = String.valueOf(UserMessage.getInstance().getUserId());
        }
        return userId;
    }

    @Override
    public void setScheduleVoList(List<ScheduleVo> scheduleVoList) {
        this.scheduleVoList.clear();
        this.scheduleVoList.addAll(scheduleVoList);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }



}
