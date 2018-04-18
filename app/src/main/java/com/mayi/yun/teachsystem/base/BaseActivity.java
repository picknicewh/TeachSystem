package com.mayi.yun.teachsystem.base;

import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MenuItem;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mayi.yun.teachsystem.di.component.ActivityComponent;
import com.mayi.yun.teachsystem.di.component.DaggerActivityComponent;
import com.mayi.yun.teachsystem.di.module.ActivityModule;
import com.mayi.yun.teachsystem.utils.G;
import com.mayi.yun.teachsystem.utils.LoadType;
import com.mayi.yun.teachsystem.widget.LoadingDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.Unbinder;

/**
 * 作者： wh
 * 时间：  2018/2/26
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public abstract class BaseActivity extends RxAppCompatActivity implements BaseContract.BaseView {
    public Unbinder unbinder;

    //非静态，除了针对整个App的Component可以静态，其他一般都不能是静态的。
    protected ActivityComponent mActivityComponent;
    /**
     * 加载框
     */
    private LoadingDialog loadingDialog;

    public abstract void initInjector();

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void attachView();
    public  abstract  void detachView();
    /**
     * 是否显示返回键
     *
     * @return
     */
    protected boolean showHomeAsUp() {
        return false;
    }



    @Override
    public void showProgress() {
        if (loadingDialog==null){
            loadingDialog = new LoadingDialog(this);
        }
        loadingDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        detachView();
    }

    @Override
    public void hideProgress() {
        loadingDialog.dismiss();
    }
    @Override
    public void showMessage(String message) {
        G.showToast(this, message);
    }

    @Override
    public void showNoNetWork() {
        G.showToast(this, "无网络连接！");
    }


    protected void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
                break;
        }
        return true;
    }


    public void initComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    public void setLoadDataResult(BaseQuickAdapter baseQuickAdapter, SwipeRefreshLayout swipeRefreshLayout, List list, @LoadType.checker int loadType) {
        switch (loadType) {
            case LoadType.TYPE_REFRESH_SUCCESS:
                baseQuickAdapter.setNewData(list);
                swipeRefreshLayout.setRefreshing(false);
                break;
            case LoadType.TYPE_REFRESH_ERROR:
                swipeRefreshLayout.setRefreshing(false);
                break;
            case LoadType.TYPE_LOAD_MORE_SUCCESS:
                if (list != null) {
                    baseQuickAdapter.addData(list);
                }
                break;
            case LoadType.TYPE_LOAD_MORE_ERROR:
                baseQuickAdapter.loadMoreFail();
                break;
        }
        if (list == null || list.isEmpty() || list.size() < 10) {
            baseQuickAdapter.loadMoreEnd(false);
        } else {
            baseQuickAdapter.loadMoreComplete();
        }
    }
}
