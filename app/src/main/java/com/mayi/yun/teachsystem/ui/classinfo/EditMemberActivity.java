package com.mayi.yun.teachsystem.ui.classinfo;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.db.UserMessage;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditMemberActivity extends BaseClassActivity<EditMemberPresenter> {

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
     if (UserMessage.getInstance().getUserType()==1){
         setSubTitleText("完成");
     }
    }
}
