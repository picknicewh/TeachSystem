package com.mayi.yun.teachsystem.ui.attend.teacher;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseFragment;
import com.mayi.yun.teachsystem.bean.AttendUserInfo;
import com.mayi.yun.teachsystem.db.UserMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class AttentionFragment extends BaseFragment<AttentionPresenter> implements AttentionContract.View {

    @BindView(R.id.rv_attend)
    RecyclerView rvAttend;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private AttentionAdapter attendAdapter;
    private List<AttendUserInfo> attendUserInfoList;

    public AttentionFragment() {

    }

    public static AttentionFragment newInstance(int scheduleId, int classId) {
        AttentionFragment fragment = new AttentionFragment();
        Bundle args = new Bundle();
        args.putInt("scheduleId", scheduleId);
        args.putInt("classId", classId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public String getTeacherId() {
        return String.valueOf(UserMessage.getInstance().getUserId());
    }

    @Override
    public int getClassId() {
        return getArguments().getInt("classId", -1);
    }

    @Override
    public int getScheduleId() {
        return getArguments().getInt("scheduleId", -1);
    }

    @Override
    public void setAttendUserInfoList(List<AttendUserInfo> attendUserInfoList) {
        tvNodata.setVisibility(attendUserInfoList.size() == 0 ? View.VISIBLE : View.GONE);
        rvAttend.setVisibility(attendUserInfoList.size() == 0 ? View.GONE : View.VISIBLE);
        this.attendUserInfoList.clear();
        this.attendUserInfoList.addAll(attendUserInfoList);
        if (attendAdapter != null) {
            attendAdapter.notifyDataSetChanged();
        }
    }


    public void getSignListByParams() {
        if (mPresenter != null) {
            mPresenter.getSignListByParams();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_attend;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        attendUserInfoList = new ArrayList<>();
        attendAdapter = new AttentionAdapter(attendUserInfoList);
        rvAttend.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAttend.setAdapter(attendAdapter);
        getSignListByParams();
    }
}
