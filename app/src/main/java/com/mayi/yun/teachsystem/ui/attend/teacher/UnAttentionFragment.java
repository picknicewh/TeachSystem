package com.mayi.yun.teachsystem.ui.attend.teacher;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseFragment;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

public class UnAttentionFragment extends BaseFragment<UnAttentionPresenter> implements OnSwipeListener<UserInfo>, UnAttentionContract.View {


    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.rv_member)
    RecyclerView rvMember;
    @BindView(R.id.iv_attend)
    TextView ivAttend;
    @BindView(R.id.iv_un_attend)
    TextView ivUnAttend;
    @BindView(R.id.ll_nodata)
    LinearLayout llNodata;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    Unbinder unbinder;
    private UnAttentionAdapter attentionAdapterT;
    private List<UserInfo> userInfoList;
    private UserInfo currentUserInfo;
    /**
     * 是否到场
     */
    private int sign;

    private Bundle bundle;

    public static UnAttentionFragment newInstance(int scheduleId, int classId, String className) {
        UnAttentionFragment fragment = new UnAttentionFragment();
        Bundle args = new Bundle();
        args.putInt("scheduleId", scheduleId);
        args.putInt("classId", classId);
        args.putString("className", className);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        bundle = getArguments();
        String className = bundle.getString("className");
        tvClass.setText(className);
        Date date = new Date();
        tvDate.setText(DateUtil.getFormatTimeDate(date));
        tvWeek.setText(DateUtil.getWeekOfDate(date));
        initViews();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_un_attend;
    }

    public void getUserByClassId() {
        if (mPresenter != null) {
            mPresenter.getUserByClassId();
        }

    }

    private void initViews() {
        userInfoList = new ArrayList<>();
        attentionAdapterT = new UnAttentionAdapter(userInfoList, getActivity());
        rvMember.setItemAnimator(new DefaultItemAnimator());
        rvMember.setAdapter(attentionAdapterT);
        CardItemTouchHelperCallback<UserInfo> cardCallback = new CardItemTouchHelperCallback<>(rvMember.getAdapter(), userInfoList);
        cardCallback.setOnSwipedListener(this);
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(rvMember, touchHelper);
        rvMember.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(rvMember);
        if (mPresenter != null) {
            mPresenter.getUserByClassId();
        }
    }

    @Override
    public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
        UnAttentionAdapter.ViewHolder holder = (UnAttentionAdapter.ViewHolder) viewHolder;
        viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
        if (direction == CardConfig.SWIPING_LEFT) {
            holder.likeImageView.setAlpha(Math.abs(ratio));
            sign = 1;
            ivAttend.setAlpha(Math.abs(ratio));
        } else if (direction == CardConfig.SWIPING_RIGHT) {
            holder.dislikeImageView.setAlpha(Math.abs(ratio));
            ivUnAttend.setAlpha(Math.abs(ratio));
            sign = 0;
        } else {
            holder.dislikeImageView.setAlpha(0f);
            holder.likeImageView.setAlpha(0f);
            ivAttend.setAlpha(1f);
            ivUnAttend.setAlpha(1f);
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, UserInfo userInfo, int direction) {
        UnAttentionAdapter.ViewHolder holder = (UnAttentionAdapter.ViewHolder) viewHolder;
        currentUserInfo = userInfo;
        viewHolder.itemView.setAlpha(1f);
        holder.dislikeImageView.setAlpha(0f);
        holder.likeImageView.setAlpha(0f);
        ivAttend.setAlpha(1f);
        ivUnAttend.setAlpha(1f);
        if (mPresenter != null) {
            mPresenter.addSign();
        }
    }

    @Override
    public void onSwipedClear() {
        rvMember.setVisibility(View.GONE);
        llNodata.setVisibility(View.VISIBLE);

    }


    @OnClick(R.id.ll_nodata)
    public void nodata() {
        rvMember.setVisibility(View.VISIBLE);
        llNodata.setVisibility(View.GONE);
        if (mPresenter != null) {
            mPresenter.getUserByClassId();
        }
    }


    @Override
    public String getUserType() {
        return "3";
    }

    @Override
    public String getClassId() {
        return String.valueOf(bundle.getInt("classId", -1));
    }

    @Override
    public String getUserId() {
        return String.valueOf(currentUserInfo.getUserId());
    }


    @Override
    public String getUserName() {
        return currentUserInfo.getTruename();
    }

    @Override
    public int getScheduleId() {
        return bundle.getInt("scheduleId", 0);
    }

    @Override
    public int isSign() {
        return sign;
    }

    @Override
    public void setUserInfoList(List<UserInfo> userInfoList) {
        if (userInfoList.size() == 0) {
            rvMember.setVisibility(View.GONE);
            llNodata.setVisibility(View.VISIBLE);
        }else {
            rvMember.setVisibility(View.VISIBLE);
            llNodata.setVisibility(View.GONE);
        }
        String data = "总人数:" + userInfoList.size() + "人";
        tvNumber.setText(data);
        this.userInfoList.clear();
        this.userInfoList.addAll(userInfoList);
        if (attentionAdapterT != null) {
            attentionAdapterT.notifyDataSetChanged();
        }
    }
}
