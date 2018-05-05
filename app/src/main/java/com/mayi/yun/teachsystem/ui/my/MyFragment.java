package com.mayi.yun.teachsystem.ui.my;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseFragment;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.network.GlideUtils;
import com.mayi.yun.teachsystem.ui.attend.head.AttentionActivityH;
import com.mayi.yun.teachsystem.ui.login.LoginActivity;
import com.mayi.yun.teachsystem.ui.password.PasswordActivity;
import com.mayi.yun.teachsystem.utils.Constant;
import com.mayi.yun.teachsystem.widget.ConformDialog;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends BaseFragment implements ConformDialog.OnConformCallBack {

    /**
     * 人的头像
     */
    @BindView(R.id.iv_person)
    CircleImageView ivPerson;

    @BindView(R.id.tv_study_num)
    TextView tvStudyNum;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.tv_dept)
    TextView tvDept;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.ll_class)
    LinearLayout llClass;
    Unbinder unbinder;

    private ConformDialog conformDialog;

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initInjector() {
        //  mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        conformDialog = new ConformDialog(getActivity());
        conformDialog.setOnConformCallBack(this);
        UserMessage userMessage = UserMessage.getInstance();
        tvName.setText(userMessage.getTruename());
        tvClass.setText(userMessage.getClassName());
        String text = userMessage.getUserType() == 3 ? "学号" : "工号";
        llClass.setVisibility(userMessage.getUserType() != 3 ? View.GONE : View.VISIBLE);
        tvStudyNum.setText(text + "  " + userMessage.getUserSn());
        GlideUtils.loadImageView(getActivity(), userMessage.getAvatar(), ivPerson);
        //  tvPosition.setText(userMessage.get);
    }


    @OnClick(R.id.rl_exit)
    public void exit() {
        conformDialog.show();
    }

    @Override
    public void onCallBack() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_list)
    public void rllist() {
        Intent intent = new Intent();
        if (UserMessage.getInstance().getUserType() == 3) {
            intent.setClass(getActivity(), MyAttendActivity.class);
        } else {
            intent.setClass(getActivity(), AttentionActivityH.class);
            intent.putExtra("source", Constant.SOURCE_ATTEND_LIST);
        }
        startActivity(intent);
    }




    @OnClick(R.id.rl_password)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), PasswordActivity.class);
        startActivity(intent);
    }
}
