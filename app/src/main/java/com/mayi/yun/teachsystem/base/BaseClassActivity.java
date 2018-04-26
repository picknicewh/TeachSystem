package com.mayi.yun.teachsystem.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： wh
 * 时间：  2018/2/26
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public abstract   class BaseClassActivity<T extends BaseContract.BasePresenter> extends BaseActivity implements BaseContract.BaseView {
    @Nullable
    @Inject
    protected T mPresenter;
    /**
     * 左边的图标（返回键）
     */
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView tvTitle;
    /**
     * 副标题
     */
    @BindView(R.id.tv_subtitle)
    TextView tvSubtitle;
    /**
     * 右边图标
     */
    @BindView(R.id.iv_right)
    ImageView ivRight;
    /**
     * 导航栏
     */
    RelativeLayout llNav;
    /**
     * 内容
     */
    LinearLayout flContent;
    /**
     * 返回内容
     */
    @BindView(R.id.tv_back_text)
    TextView tvBackText;
    /**
     * 返回
     */
    @BindView(R.id.ll_back)
    LinearLayout llBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
        setContentView(R.layout.activity_base);
        llNav = findViewById(R.id.rl_nav);
        flContent = findViewById(R.id.fl_content);
        flContent.requestFocus();
        getLayoutInflater().inflate(getLayoutId(), flContent, true);
        unbinder = ButterKnife.bind(this);
        initInjector();
        attachView();
        initView();
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    /**
     * 贴上view
     */
    @Override
    public void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 分离view
     */
    @Override
    public void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
    public void setLeftIconGone(){
        ivLeft.setVisibility(View.GONE);
    }
    /**
     * 设置左图标
     *
     * @param resId
     */
    public void setLeftIcon(int resId) {
        ivLeft.setImageResource(resId);
    }

    /**
     * 设置左文字
     *
     * @param textId
     */
    public void setLeftTextId(int textId) {

        tvBackText.setText(getResources().getText(textId));
    }

    /**
     * 设置左文字
     *
     * @param textId
     */
    public void setSubTitleTextId(int textId) {

        tvSubtitle.setText(getResources().getText(textId));
    }
    /**
     * 设置左文字
     *
     * @param text
     */
    public void setSubTitleText(String text) {

        tvSubtitle.setText(text);
    }
    /**
     * 设置右图标
     *
     * @param resId
     */
    public void setRightIcon(int resId) {
        ivRight.setImageResource(resId);
    }

    /**
     * 设置标题
     *
     * @param textId int资源
     */
    public void setTitleTextId(int textId) {
        tvTitle.setText(getResources().getText(textId));
    }

    /**
     * 设置标题
     *
     * @param text
     */
    public void setTitleText(String text) {
        tvTitle.setText(text);
    }

    /**
     * 设置副标题监听
     *
     * @param onClickListener 监听
     */
    public void setSubtitleClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            tvSubtitle.setOnClickListener(onClickListener);
        }
    }

    /**
     * 设置右边监听
     *
     * @param onClickListener 监听
     */
    public void setRightClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            ivRight.setOnClickListener(onClickListener);
        }
    }

    public void back() {
        finish();
    }

    /**
     * 设置返回监听
     *
     * @param onClickListener 监听
     */
    public void setBackClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            llBack.setOnClickListener(onClickListener);
        }
    }
}
