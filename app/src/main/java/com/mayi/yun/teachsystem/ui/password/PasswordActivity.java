package com.mayi.yun.teachsystem.ui.password;

import android.widget.EditText;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PasswordActivity extends BaseClassActivity<PasswordPresenter> {

    /**
     * 原密码
     */
    @BindView(R.id.tv_ori_password)
    EditText tvOriPassword;
    /**
     * 编辑密码
     */
    @BindView(R.id.tv_edit_password)
    EditText tvEditPassword;
    /**
     * 确认编辑密码
     */
    @BindView(R.id.tv_com_password)
    EditText tvComPassword;

    @Override
    public void initInjector() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_password;
    }

    @Override
    public void initView() {
       setTitleTextId(R.string.attend_s);
    }
    @OnClick(R.id.bt_conform)
    public void conform() {

    }
}
