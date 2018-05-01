package com.mayi.yun.teachsystem.ui.head;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.utils.Constant;

public class EditHeadActivity extends BaseClassActivity<EditHeadPresenter> {

     private int operation;
    @Override
    public void initInjector() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_head;
    }

    @Override
    public void initView() {
        operation = getIntent().getIntExtra("operation",1);
        switch (operation){
            case Constant.ADD:
                setTitleText("添加班主任");
                break;
            case Constant.EDIT:
                setTitleText("编辑班主任");
                setSubTitleText("完成");
                break;
        }
    }
}
