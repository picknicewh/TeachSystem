package com.mayi.yun.teachsystem.ui.attend.teacher;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.utils.Constant;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AttendResultActivity extends BaseClassActivity implements OnItemClickListener, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.rb_unsign)
    RadioButton rbUnsign;
    @BindView(R.id.rb_sign)
    RadioButton rbSign;
    @BindView(R.id.rg_sign)
    RadioGroup rgSign;
    @BindView(R.id.line_sign)
    View lineSign;
    @BindView(R.id.line_un_sign)
    View lineUnSign;
    @BindView(R.id.ll_line)
    LinearLayout llLine;
    /**
     * 页面列表
     */
    private List<Fragment> fragmentList;
    /**
     * 已考勤
     */
    private AttentionFragment signedFragment;
    /**
     * 未考勤
     */
    private UnAttentionFragment unAttentionFragment;
    /**
     * 适配器
     */
    private ViewPagerAdapter viewPagerAdapter;
    /**
     * 状态
     */
    private int status = 0;

    @Override
    public void initInjector() {
        //   mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_attend_result;
    }

    @Override
    public void initView() {
        setTitleText("当天课程列表");
        int scheduleId = getIntent().getIntExtra("scheduleId", -1);
        int classId = getIntent().getIntExtra("classId", -1);
        String className = getIntent().getStringExtra("className");
        fragmentList = new ArrayList<>();
        signedFragment = AttentionFragment.newInstance(scheduleId, classId);
        unAttentionFragment = UnAttentionFragment.newInstance(scheduleId, classId, className);
        fragmentList.add(unAttentionFragment);
        fragmentList.add(signedFragment);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewpager.setAdapter(viewPagerAdapter);
        viewpager.setCurrentItem(0);
        viewpager.addOnPageChangeListener(this);
        rgSign.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case Constant.UN_ATTEND:
                rbSign.setChecked(false);
                rbUnsign.setChecked(true);
                status = Constant.UN_ATTEND;
                lineSign.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
                lineUnSign.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
                break;
            case Constant.HAS_ATTEND:
                rbSign.setChecked(true);
                rbUnsign.setChecked(false);
                status = Constant.HAS_ATTEND;
                lineSign.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
                lineUnSign.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
                break;
        }
        viewpager.setCurrentItem(status);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        switch (id) {
            case R.id.rb_unsign:
                status = Constant.UN_ATTEND;
                lineSign.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
                lineUnSign.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
                unAttentionFragment.getUserByClassId();
                break;
            case R.id.rb_sign:
                status = Constant.HAS_ATTEND;
                lineSign.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
                lineUnSign.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
                signedFragment.getSignListByParams();
                break;
        }
        viewpager.setCurrentItem(status);
    }

}
