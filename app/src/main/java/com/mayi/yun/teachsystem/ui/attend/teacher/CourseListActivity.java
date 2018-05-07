package com.mayi.yun.teachsystem.ui.attend.teacher;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.AttendVo;
import com.mayi.yun.teachsystem.bean.CourseVo;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CourseListActivity extends BaseClassActivity<CourseListPresenter> implements CourseListContract.View, OnItemClickListener, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.rb_unsign)
    RadioButton rbUnsign;
    @BindView(R.id.rb_sign)
    RadioButton rbSign;
    @BindView(R.id.rg_sign)
    RadioGroup rgSign;
    private List<Fragment> fragmentList;
    private SignedFragment signedFragment;
    private UnSignFragment unSignFragment;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_list;
    }

    @Override
    public void initView() {
        setTitleText("当天课程列表");
        fragmentList = new ArrayList<>();
        signedFragment = new SignedFragment();
        unSignFragment = new UnSignFragment();
        fragmentList.add(signedFragment);
        fragmentList.add(unSignFragment);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewpager.setAdapter(viewPagerAdapter);
        viewpager.setCurrentItem(0);
        viewpager.addOnPageChangeListener(this);
        rgSign.setOnCheckedChangeListener(this);
        if (mPresenter != null) {
            mPresenter.getCourseList();
        }
    }

    @Override
    public String getTeacherId() {
        return String.valueOf(UserMessage.getInstance().getUserId());
    }

    @Override
    public int getClassId() {
        return 0;
    }

    @Override
    public int getScheduleId() {
        return 0;
    }

    @Override
    public void setCourseList(List<CourseVo> courseVoList) {
        unSignFragment.setCourseList(courseVoList);
    }

    @Override
    public void setAttendVoList(List<AttendVo> attendVoList) {

    }

    @Override
    public void onClick(View view, int position) {

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }
}
