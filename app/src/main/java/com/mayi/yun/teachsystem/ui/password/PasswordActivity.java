package com.mayi.yun.teachsystem.ui.password;

import android.widget.EditText;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.utils.G;

import butterknife.BindView;
import butterknife.OnClick;

public class PasswordActivity extends BaseClassActivity<PasswordPresenter> implements PasswordContract.View {

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
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_password;
    }

    @Override
    public void initView() {
        setTitleText("修改密码");
    }

    @OnClick(R.id.bt_conform)
    public void conform() {
        if (!tvEditPassword.getText().toString().equals(tvComPassword.getText().toString())) {
            G.showToast(this, "两次密码不一致");
            return;
        }
        if (mPresenter != null) {
            mPresenter.updateUser();
        }
    }

    @Override
    public String getUserId() {
        return String.valueOf(UserMessage.getInstance().getUserId());
    }

    @Override
    public String getPassword() {
        return tvEditPassword.getText().toString();
    }

    @Override
    public String getOldPassword() {
        return tvOriPassword.getText().toString();
    }

    @Override
    public void onSuccess() {
        finish();
    }
}
