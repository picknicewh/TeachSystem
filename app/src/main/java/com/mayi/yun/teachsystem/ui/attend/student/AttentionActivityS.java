package com.mayi.yun.teachsystem.ui.attend.student;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.AttendVo;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.utils.DateUtil;
import com.mayi.yun.teachsystem.utils.G;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class AttentionActivityS extends BaseClassActivity<AttentionPresenterS> implements AttentionContractS.View {


    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.rv_course)
    RecyclerView rvCourse;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private AttentionAdapterS adapter;
    private List<AttendVo> attendVoList;
    private String userId;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_attention;
    }

    @Override
    public void initView() {
        setTitleTextId(R.string.attend_s);
        attendVoList = new ArrayList<>();
        tvDate.setText(DateUtil.getFormatDate(new Date()));
        tvWeek.setText(DateUtil.getWeekOfDate(new Date()));
        userId = getIntent().getStringExtra("userId");
        adapter = new AttentionAdapterS(attendVoList);
        rvCourse.setLayoutManager(new LinearLayoutManager(this));
        rvCourse.setAdapter(adapter);
        if (mPresenter != null) {
            mPresenter.getSignListByUserId();
        }
    }

    @Override
    public String getUserId() {
        if (G.isEmteny(userId)) {
            userId = String.valueOf(UserMessage.getInstance().getUserId());
        }
        return userId;
    }

    @Override
    public void setAttendList(List<AttendVo> attendVoList) {
        if (attendVoList.size()==0){
            tvNodata.setVisibility(View.VISIBLE);
            rvCourse.setVisibility(View.GONE);
        }else {
            tvNodata.setVisibility(View.GONE);
            rvCourse.setVisibility(View.VISIBLE);
        }
        this.attendVoList.clear();
        this.attendVoList.addAll(attendVoList);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
