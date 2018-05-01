package com.mayi.yun.teachsystem.ui.login;

import android.content.Intent;
import android.widget.EditText;

import com.mayi.yun.teachsystem.MainActivity;
import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.db.UserAction;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.ui.head.HeadListActivity;
import com.mayi.yun.teachsystem.ui.password.PasswordActivity;
import com.mayi.yun.teachsystem.utils.G;
import com.mayi.yun.teachsystem.utils.NetworkUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseClassActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    private String usename;
    private String password;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        setLeftIconGone();
        setTitleTextId(R.string.login);
        usename = UserMessage.getInstance().getUsername();
        password = UserMessage.getInstance().getPassword();
        etPassword.setText(password);
        etUsername.setText(usename);
    }


    @OnClick(R.id.b_login)
    public void login() {
        usename = etUsername.getText().toString();
        password = etPassword.getText().toString();
        if (G.isEmteny(usename) || G.isEmteny(password)) {
            G.showToast(this, "用户名或密码不能为空！");
            return;
        }
        if (NetworkUtil.isNetworkAvailable()) {
            mPresenter.login();
        } else {
            G.showToast(this, "请连接网络..");
        }
        if (mPresenter != null) {
            mPresenter.login();
        }
    }

    @Override
    public String getMemberSn() {
        return usename;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setUseInfo(UserInfo useInfo) {
        UserAction.saveLoginInfo(useInfo);
        UserAction.saveLoginMessage(usename, password);
        if (UserMessage.getInstance().getUserType()==4){
            Intent intent = new Intent(this, HeadListActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }



    @OnClick(R.id.tv_edit_password)
    public void editPassword() {
        Intent intent = new Intent(this, PasswordActivity.class);
        startActivity(intent);

    }
}
