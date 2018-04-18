package com.mayi.yun.teachsystem.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.mayi.yun.teachsystem.R;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * 作者： wh
 * 时间：  2018/2/26
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public abstract class BaseNavActivity<T extends BaseContract.BasePresenter>  extends BaseActivity implements BaseContract.BaseView {
    @Nullable
    @Inject
    protected T mPresenter;
    public Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
        int layoutId = getLayoutId();
        setContentView(layoutId);
        initInjector();
        unbinder = ButterKnife.bind(this);
        attachView();
        initToolBar();
        initView();

    }

    public abstract void initInjector();

    public abstract int getLayoutId();

    public abstract void initView();

    /**
     * 是否显示返回键
     *
     * @return
     */
    protected boolean showHomeAsUp() {
        return false;
    }

    private void initToolBar() {
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar == null) {
            throw new NullPointerException("toolbar can not be null");
        }
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp());
        /**toolbar除掉阴影*/
        getSupportActionBar().setElevation(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setElevation(0);
        }

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
}
