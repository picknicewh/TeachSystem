package com.mayi.yun.teachsystem.ui.head;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.ClassVo;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.network.GlideUtils;
import com.mayi.yun.teachsystem.utils.Constant;
import com.mayi.yun.teachsystem.utils.DateUtil;
import com.mayi.yun.teachsystem.utils.FileUtils;
import com.mayi.yun.teachsystem.utils.G;
import com.mayi.yun.teachsystem.utils.PermissionsActivity;
import com.mayi.yun.teachsystem.widget.ConformDialog;
import com.mayi.yun.teachsystem.widget.PictureChooseDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.mayi.yun.teachsystem.utils.PermissionsChecker.REQUEST_CODE;

public class EditHeadActivity extends BaseClassActivity<EditHeadPresenter> implements View.OnClickListener, EditHeadContract.View, ConformDialog.OnConformCallBack {

    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_number)
    EditText tvNumber;
    @BindView(R.id.rb_men)
    RadioButton rbMen;
    @BindView(R.id.rv_feman)
    RadioButton rvFeman;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    private int operation;
    private UserInfo userInfo;
    /**
     * 记录选中开始日期
     */
    private Calendar mCalendar = Calendar.getInstance();
    /**
     * 开始时间选择
     */
    private TimePickerView mTimePickerView;
    /**
     * 开始时间
     */
    private Date mDate;
    /**
     * 选项
     */
    private OptionsPickerView optionsPickerView;
    /**
     * 班级列表
     */
    private List<String> classNameList;
    private List<ClassVo> classList;
    /**
     * 位置
     */
    private int position = 0;
    private String classId = "";
    private String avatar = "";
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
    private ConformDialog conformDialog;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_head;
    }

    @Override
    public void initView() {
        operation = getIntent().getIntExtra("operation", 1);
        userInfo = getIntent().getParcelableExtra("userInfo");

        switch (operation) {
            case Constant.ADD:
                setTitleText("添加班主任");
                mDate = DateUtil.getNeedTime(16, 0, 0, 0);
                String date = DateUtil.getChinaFormatDate(mDate);
                tvBirthday.setText(date);
                mCalendar.setTime(mDate);//默认日期选中的开始时间
                mTimePickerView = DateUtil.getDatePickerView("选择生日时间", this, mCalendar, TimeListener);
                classList = new ArrayList<>();
                classNameList = new ArrayList<>();
                if (mPresenter != null) {
                    mPresenter.getClassList();
                }
                break;
            case Constant.EDIT:
                setTitleText("编辑班主任");
                conformDialog = new ConformDialog(this);
                conformDialog.setOnConformCallBack(this);
                dialog = new PictureChooseDialog(this);
                tvBirthday.setText(userInfo.getBirthday());
                tvClass.setText(userInfo.getClassId() == 1 ? "信息技术" : "软件工程");
                tvNumber.setText(userInfo.getUserSn());
                tvName.setText(userInfo.getTruename());
                tvPhone.setText(userInfo.getPhone());
                rbMen.setChecked(userInfo.getSex() == 1);
                rvFeman.setChecked(userInfo.getSex() == 0);
                GlideUtils.loadImageView(this, userInfo.getAvatar(), ivHead);
                classId = String.valueOf(userInfo.getClassId());
                avatar = userInfo.getAvatar();
                break;
        }


    }

    private OptionsPickerView.OnOptionsSelectListener optionsSelectListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            position = options1;
            tvClass.setText(classList.get(position).getName());

        }
    };
    private TimePickerView.OnTimeSelectListener TimeListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            mCalendar.setTime(date);
            mDate = date;
            String mDate = DateUtil.getChinaFormatDate(date);
            tvBirthday.setText(mDate);
        }
    };

    @OnClick(R.id.tv_birthday)
    public void birthday() {
        mTimePickerView.show();
    }

    @Override
    public void onClick(View view) {
        if (G.isEmteny(getTrueName()) || G.isEmteny(getBirthday()) || G.isEmteny(getPosition()) || G.isEmteny(getPhone())) {
            G.showToast(this, "必要条件不能为空！");
            return;
        }
        if (mPresenter != null) {
            mPresenter.addMember();
        }
    }


    @Override
    public int getUserType() {
        return 1;
    }

    @Override
    public String getClassId() {
        if (G.isEmteny(classId)) {
            classId = String.valueOf(classList.get(position).getClassId());
        }
        return classId;
    }

    @Override
    public String getUserSn() {
        return tvNumber.getText().toString();
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
        if (G.isEmteny(imageUrl)) {
            imageUrl = avatar;
        }
        return imageUrl;
    }

    @Override
    public int getSex() {
        int sex = rbMen.isChecked() ? 1 : 0;
        return sex;
    }

    @Override
    public String getPosition() {
        String position = "班主任";
        return position;
    }

    @Override
    public String getBirthday() {
        return tvBirthday.getText().toString();
    }

    @Override
    public String getPath() {
        return imagePath;
    }

    @Override
    public void setImagePath(String imagePath) {
        this.imageUrl = imagePath;
    }

    @Override
    public int getUserId() {
        return userInfo.getUserId();
    }

    @Override
    public void success() {
        finish();
    }

    @Override
    public void setClassList(List<ClassVo> classList) {
        this.classList.clear();
        this.classList.addAll(classList);
        for (int i = 0; i < classList.size(); i++) {
            classNameList.add(classList.get(i).getName());
        }
        optionsPickerView = DateUtil.getOptionPickerView("请选择班级", classNameList, position, this, optionsSelectListener);
    }


    @OnClick(R.id.tv_class)
    public void Class() {
        if (optionsPickerView != null) {
            optionsPickerView.show();
        }
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

    private int source = 0;

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
}
