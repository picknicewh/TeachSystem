package com.mayi.yun.teachsystem;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mayi.yun.teachsystem.base.BaseNavActivity;
import com.mayi.yun.teachsystem.base.BaseFragment;
import com.mayi.yun.teachsystem.ui.home.HomeFragment;
import com.mayi.yun.teachsystem.ui.my.MyFragment;
import com.mayi.yun.teachsystem.ui.study.StudySystemFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseNavActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    /**
     * 标题栏
     */
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    /**
     * 底部导航栏
     */
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private List<BaseFragment> fragmentList;

    @Override
    public void initInjector() {

    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    public void initView() {
        initFragment();
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(StudySystemFragment.newInstance());
        fragmentList.add(MyFragment.newInstance());
        navigation.setOnNavigationItemSelectedListener(this);
        switchFragment(0);
    }

    private int currentPosition = 0;


    public void switchFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        BaseFragment targetFragment = fragmentList.get(position);
        BaseFragment lastFragment = fragmentList.get(currentPosition);
        currentPosition = position;
        transaction.hide(lastFragment);
        if (!targetFragment.isAdded()) {
            transaction.add(R.id.content, targetFragment);
        }
        transaction.show(targetFragment);
        transaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                toolbar.setTitle(R.string.home);
                switchFragment(0);
                break;
            case R.id.menu_study:
                toolbar.setTitle(R.string.study);
                switchFragment(1);
                break;
            case R.id.menu_mine:
                toolbar.setTitle(R.string.mine);
                switchFragment(2);
                break;
        }
        return true;
    }
}
