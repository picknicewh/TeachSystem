package com.mayi.yun.teachsystem.ui.classinfo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.base.BaseClassActivity;
import com.mayi.yun.teachsystem.bean.CourseVo;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.utils.Constant;
import com.mayi.yun.teachsystem.utils.DateUtil;
import com.mayi.yun.teachsystem.utils.FileUtils;
import com.mayi.yun.teachsystem.utils.G;
import com.mayi.yun.teachsystem.utils.PermissionsActivity;
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

public class AddMemberActivity extends BaseClassActivity<AddMemberPresenter> implements AddMemberContract.View, RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    /**
     * 头像
     */
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    /**
     * 名字
     */
    @BindView(R.id.tv_name)
    EditText tvName;
    /**
     * 角色学生
     */
    @BindView(R.id.rb_student)
    RadioButton rbStudent;
    /**
     * 角色老师
     */
    @BindView(R.id.rb_teacher)
    RadioButton rbTeacher;
    /**
     * 性别男
     */
    @BindView(R.id.rb_men)
    RadioButton rbMen;
    /**
     * 性别女
     */
    @BindView(R.id.rb_feman)
    RadioButton rvFeman;
    /**
     * 班级信息
     */
    @BindView(R.id.tv_class)
    TextView tvClass;
    /**
     * 角色
     */
    @BindView(R.id.rg_role)
    RadioGroup rgRole;
    /**
     * 职务文本
     */
    @BindView(R.id.tv_position_text)
    TextView tvPositionText;
    /**
     * 职务
     */
    @BindView(R.id.tv_position)
    EditText tvPosition;
    /**
     * 电话号码
     */
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    /**
     * s生日
     */
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_user_sn)
    EditText tvUserSn;

    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    /**
     * 选项
     */
   // private OptionsPickerView optionsPickerView;

    /**
     * 学生职务列表
     */
    private List<String> positionList;
    /**
     * 老师授课列表
     */
    private List<String> courseList;
    /**
     * 位置
     */
    private int position = 0;
    /**
     * 选择角色类型
     */
    private int role=3;
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
    private String className;

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_member;
    }

    @Override
    public void initView() {
        setTitleTextId(R.string.add_member);
        if (UserMessage.getInstance().getUserType() == 1) {
            setSubTitleText("完成");
        }
        setSubtitleClickListener(this);
        className = getIntent().getStringExtra("className");
        tvClass.setText(className);
        dialog = new PictureChooseDialog(this);

        courseList = new ArrayList<>();
     //   positionList = DataUtil.getPositionList();
    //    optionsPickerView = DateUtil.getOptionPickerView("请选择职务", positionList, position, this, optionsSelectListener);

        mDate = DateUtil.getNeedTime(16, 0, 0, 0);
        String date = DateUtil.getChinaFormatDate(mDate);
        tvBirthday.setText(date);
        mCalendar.setTime(mDate);//默认日期选中的开始时间
        mTimePickerView = DateUtil.getDatePickerView("选择生日时间", this, mCalendar, TimeListener);
       rgRole.setOnCheckedChangeListener(this);
       if (mPresenter!=null){
           mPresenter.getScheduleList();
       }
    }


    private TimePickerView.OnTimeSelectListener TimeListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            mCalendar.setTime(date);
            mDate = date;
            String mDate = DateUtil.getChinaFormatDate(date);
            tvBirthday.setText(mDate);
        }
    };

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        switch (id) {
            case R.id.rb_student:

             //   optionsPickerView = DateUtil.getOptionPickerView("请选择职务", positionList, position, this, optionsSelectListener);
                tvPositionText.setText("职务");
                tvPosition.setHint("请输入职务");
                role = 3;
                break;
            case R.id.rb_teacher:
           //     optionsPickerView = DateUtil.getOptionPickerView("请选择授课", courseList, position, this, optionsSelectListener);
                tvPositionText.setText("授课");
                tvPosition.setHint("请输入授课");
                role = 2;
                break;
        }
    }

    private OptionsPickerView.OnOptionsSelectListener optionsSelectListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            position = options1;
            switch (role) {
                case 3:
                    tvPosition.setText(positionList.get(options1));
                    break;
                case 2:
                    tvPosition.setText(courseList.get(options1));
                    break;
            }

        }
    };


    @OnClick(R.id.tv_position)
    public void position() {
     //   optionsPickerView.show();
    }

    @OnClick(R.id.iv_head)
    public void head() {
        dialog.show();
    }

    @OnClick(R.id.tv_birthday)
    public void birthday() {
        mTimePickerView.show();
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
    public int getUserType() {
        return role;
    }

    @Override
    public String getClassId() {
        return String.valueOf(UserMessage.getInstance().getClassId());
    }

    @Override
    public String getUserSn() {
        return tvUserSn.getText().toString();
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
        return imageUrl;
    }

    @Override
    public int getSex() {
        return rbMen.isChecked() ? 1 : 2;
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
    public String getPath() {
        return imagePath;
    }

    @Override
    public void success() {
        finish();
    }

    @Override
    public void setCourseList(List<CourseVo> courseVoList) {
        for (int i = 0; i < courseVoList.size(); i++) {
            courseList.add(courseVoList.get(i).getSchedule());
        }
    }

    @Override
    public void setImagePath(String path) {
        this.imageUrl = path;
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
}
