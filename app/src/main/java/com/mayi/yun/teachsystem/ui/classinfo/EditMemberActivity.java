package com.mayi.yun.teachsystem.ui.classinfo;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.network.GlideUtils;
import com.mayi.yun.teachsystem.utils.G;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditMemberActivity extends BaseClassActivity<EditMemberPresenter> implements View.OnClickListener ,EditMemberContract.View{

    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.rb_student)
    RadioButton rbStudent;
    @BindView(R.id.rb_teacher)
    RadioButton rbTeacher;
    @BindView(R.id.ll_role)
    LinearLayout llRole;
    @BindView(R.id.rg_role)
    RadioGroup rgRole;
    @BindView(R.id.rb_men)
    RadioButton rbMen;
    @BindView(R.id.rv_feman)
    RadioButton rvFeman;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.tv_position_text)
    TextView tvPositionText;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_dept)
    TextView tvDept;
    private UserInfo userInfo;

    @Override
    public void initInjector() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_memeber_info_detail;
    }

    @Override
    public void initView() {
        setTitleText("信息详情");
        if (UserMessage.getInstance().getUserType() == 1) {
            setSubTitleText("完成");
        }else {
            llRole.setVisibility(View.GONE);
        }
        setSubtitleClickListener(this);
        userInfo = getIntent().getParcelableExtra("userInfo");
        tvName.setText(userInfo.getTruename());
        tvPhone.setText(userInfo.getPhone());
        tvBirthday.setText(userInfo.getBirthday());
        tvPosition.setText(G.isEmteny(userInfo.getPosition()) ? "无" : userInfo.getPosition());
        GlideUtils.loadImageView(this, userInfo.getAvatar(), ivHead);
        if (userInfo.getSex()==1){
            rbMen.setVisibility(View.VISIBLE);
            rvFeman.setVisibility(View.GONE);
        }else {
            rbMen.setVisibility(View.GONE);
            rvFeman.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public int getUserType() {
        return userInfo.getUserType();
    }

    @Override
    public String getClassId() {
        return String.valueOf(userInfo.getClassId());
    }

    @Override
    public String getUserSn() {
        return userInfo.getUserSn();
    }

    @Override
    public String getPhone() {
        return tvPhone.getText().toString();
    }

    @Override
    public String getTrueName() {
        return userInfo.getTruename();
    }

    @Override
    public String getAvatar() {
        return userInfo.getAvatar();
    }

    @Override
    public int getSex() {
        return rbMen.isChecked()?1:2;
    }

    @Override
    public String getPosition() {
        return userInfo.getPosition();
    }

    @Override
    public String getBirthday() {
        return userInfo.getBirthday();
    }


    @Override
    public void success() {
         finish();
    }
}
