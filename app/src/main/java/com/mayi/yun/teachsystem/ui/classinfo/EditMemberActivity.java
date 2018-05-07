package com.mayi.yun.teachsystem.ui.classinfo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
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
import com.mayi.yun.teachsystem.utils.Constant;
import com.mayi.yun.teachsystem.utils.FileUtils;
import com.mayi.yun.teachsystem.utils.G;
import com.mayi.yun.teachsystem.utils.PermissionsActivity;
import com.mayi.yun.teachsystem.widget.ConformDialog;
import com.mayi.yun.teachsystem.widget.PictureChooseDialog;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.mayi.yun.teachsystem.utils.PermissionsChecker.REQUEST_CODE;

public class EditMemberActivity extends BaseClassActivity<EditMemberPresenter> implements View.OnClickListener, EditMemberContract.View, ConformDialog.OnConformCallBack {

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
    @BindView(R.id.ll_edit)
    LinearLayout llEdit;
    private UserInfo userInfo;
    private ConformDialog conformDialog;
    /**
     * 图片选择对话框
     */
    private PictureChooseDialog dialog;
    /**
     * 图片路径
     */
    private String imagePath;
    /**
     * 图片地址
     */
    private String imageUrl = "";
    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_memeber_info_detail;
    }

    @Override
    public void initView() {
        setTitleText("信息详情");
        if (UserMessage.getInstance().getUserType() == 1) {
            llEdit.setVisibility(View.VISIBLE);
        } else {
            llRole.setVisibility(View.GONE);
            llRole.setVisibility(View.GONE);
        }
        setSubtitleClickListener(this);
        dialog = new PictureChooseDialog(this);
        userInfo = getIntent().getParcelableExtra("userInfo");
        tvName.setText(userInfo.getTruename());
        tvPhone.setText(userInfo.getPhone());
        tvBirthday.setText(userInfo.getBirthday());
        tvPosition.setText(G.isEmteny(userInfo.getPosition()) ? "无" : userInfo.getPosition());
        GlideUtils.loadImageView(this, userInfo.getAvatar(), ivHead);
        rbStudent.setChecked(userInfo.getUserType() == 3);
        rbTeacher.setChecked(userInfo.getUserType() == 2);
        if (userInfo.getSex() == 1) {
            rbMen.setChecked(true);
            rvFeman.setChecked(false);
        } else {
            rbMen.setChecked(false);
            rvFeman.setChecked(true);
        }
        conformDialog = new ConformDialog(this);
        conformDialog.setOnConformCallBack(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public int getUserId() {
        return userInfo.getUserId();
    }

    @Override
    public int getUserType() {
        return rbStudent.isChecked() ? 3 : 2;
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
        return tvName.getText().toString();
    }

    @Override
    public String getAvatar() {
        if (G.isEmteny(imageUrl)){
            imageUrl = userInfo.getAvatar();
        }
        return imageUrl;
    }

    @Override
    public int getSex() {
        return rbMen.isChecked() ? 1 : 2;
    }

    @Override
    public String getPath() {
        return imagePath;
    }

    @Override
    public String getPosition() {
        return tvPosition.getText().toString();
    }

    @Override
    public String getBirthday() {
        return tvBirthday.getText().toString();
    }

    @Override
    public void setImagePath(String path) {
     this.imageUrl = path;
    }


    @Override
    public void success() {
        finish();
    }


    @OnClick({R.id.tv_del, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_del:
                conformDialog.show();
                conformDialog.setTitle("确认删除该用户信息吗？");
                source = 0;
                break;
            case R.id.tv_save:
                conformDialog.show();
                conformDialog.setTitle("确认修改该用户信息吗？");
                source = 1;
                break;
        }
    }
    /**
     * 操作
     */
    private int source = 0;

    @Override
    public void onCallBack() {
        switch (source) {
            case 0:
                if (mPresenter != null) {
                    mPresenter.delUser();
                }
                break;
            case 1:
                if (mPresenter != null) {
                    mPresenter.updateUser();
                }
                break;
        }
    }
    @OnClick(R.id.iv_head)
    public void head() {
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //获取图片路径
            if (requestCode == Constant.RESULT_IMAG) {
                imagePath = FileUtils.getChoosePicture(this, data.getData());
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                ivHead.setImageBitmap(bitmap);
            } else if (requestCode == Constant.RESULT_CAMERA) {
                update(imagePath);
                if (Build.VERSION.SDK_INT >= 24) {
                    imagePath = dialog.getImagePath();
                    FileUtils.savePhoto(imagePath, null);
                    ivHead.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                } else {
                    imagePath = FileUtils.getUploadPhotoFile(this);
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    FileUtils.savePhoto(imagePath, bitmap);
                    ivHead.setImageBitmap(bitmap);
                }
            }
            G.log("xxxxdddxxxxxxx" + imagePath);
            if (mPresenter != null) {
                mPresenter.updateImage();
            }
        }
        if (requestCode == REQUEST_CODE) {
            if (resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
                G.showToast(this, "权限没有授取，本次操作取消，请到权限中心授权");
            }
        }
    }
    private void update(String imagePath) {
        //这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(imagePath));
        intent.setData(uri);
    }
}
