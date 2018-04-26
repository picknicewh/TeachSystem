package com.mayi.yun.teachsystem.ui.my;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseFragment;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.network.GlideUtils;
import com.mayi.yun.teachsystem.ui.login.LoginActivity;
import com.mayi.yun.teachsystem.widget.ConformDialog;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends BaseFragment<MyPresenter> implements MyContract.View, ConformDialog.OnConformCallBack {

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
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        conformDialog = new ConformDialog(getActivity());
        conformDialog.setOnConformCallBack(this);
        UserMessage userMessage = UserMessage.getInstance();
        tvName.setText(userMessage.getTruename());
        tvClass.setText(userMessage.getClassName());
        tvStudyNum.setText("学号  " + userMessage.getUserSn());
        GlideUtils.loadImageView(getActivity(),userMessage.getAvatar(),ivPerson);
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
}
