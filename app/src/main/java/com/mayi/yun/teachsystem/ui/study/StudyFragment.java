package com.mayi.yun.teachsystem.ui.study;

import android.view.View;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseFragment;


public class StudyFragment extends BaseFragment<StudyPresenter> implements StudyContract.View {
    public static StudyFragment newInstance() {
        StudyFragment fragment = new StudyFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initInjector() {
       mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

    }
}
