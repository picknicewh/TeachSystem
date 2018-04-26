package com.mayi.yun.teachsystem.ui.attend.teacher;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

public class AttentionActivityT extends BaseClassActivity<AttentionPresenterT> implements OnSwipeListener<Integer> {


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
    private List<Integer> list = new ArrayList<>();
    private AttentionAdapterT attentionAdapterT;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_attend_t;
    }

    @Override
    public void initView() {
        setTitleTextId(R.string.attend_p);
        initData();
        initViews();
    }

    private void initData() {
        list.add(R.mipmap.ic_person);
        list.add(R.mipmap.image1);
        list.add(R.mipmap.image2);
        list.add(R.mipmap.image3);
        list.add(R.mipmap.image4);
        list.add(R.mipmap.image5);
        list.add(R.mipmap.image6);
    }

    private void initViews() {
        attentionAdapterT = new AttentionAdapterT(list);
        rvMember.setItemAnimator(new DefaultItemAnimator());
        rvMember.setAdapter(attentionAdapterT);
        CardItemTouchHelperCallback<Integer> cardCallback = new CardItemTouchHelperCallback<>(rvMember.getAdapter(), list);
        cardCallback.setOnSwipedListener(this);
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(rvMember, touchHelper);
        rvMember.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(rvMember);
    }

    @Override
    public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
        AttentionAdapterT.ViewHolder holder = (AttentionAdapterT.ViewHolder) viewHolder;
        viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
        if (direction == CardConfig.SWIPING_LEFT) {
            holder.likeImageView.setAlpha(Math.abs(ratio));
            ivAttend.setAlpha(Math.abs(ratio));
        } else if (direction == CardConfig.SWIPING_RIGHT) {
            holder.dislikeImageView.setAlpha(Math.abs(ratio));
            ivUnAttend.setAlpha(Math.abs(ratio));
        } else {
            holder.dislikeImageView.setAlpha(0f);
            holder.likeImageView.setAlpha(0f);
            ivAttend.setAlpha(1f);
            ivUnAttend.setAlpha(1f);
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, Integer integer, int direction) {
        AttentionAdapterT.ViewHolder holder = (AttentionAdapterT.ViewHolder) viewHolder;
        viewHolder.itemView.setAlpha(1f);
        holder.dislikeImageView.setAlpha(0f);
        holder.likeImageView.setAlpha(0f);
        ivAttend.setAlpha(1f);
        ivUnAttend.setAlpha(1f);
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
        initData();
        rvMember.getAdapter().notifyDataSetChanged();
    }


}
