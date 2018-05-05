package com.mayi.yun.teachsystem.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.ui.course.MySpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2018/01/06
 * 名称：课程选择对话框
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CourseChooseDialog extends AlertDialog implements AdapterView.OnItemSelectedListener {
    /**
     * 教室
     */
    @BindView(R.id.tv_class)
    EditText tvClass;
    /**
     * 老师
     */
    @BindView(R.id.sp_teacher)
    Spinner spTeacher;
    /**
     * 课程名称
     */
    @BindView(R.id.tv_course)
    TextView tvCourse;
    /**
     * 上线文本
     */
    private Context context;
    /**
     * 老师选择适配器
     */
    private MySpinnerAdapter teacherAdapter;
    /**
     * 回调
     */
    private OnConformCallBack onConformCallBack;

    private List<UserInfo> userInfoList;
    private UserInfo userInfo;

    public CourseChooseDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_course_choose);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        //解决无法弹出软键盘
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        userInfoList = new ArrayList<>();


    }

  /*  public void setClassInfo(CourseVo classInfo) {
        tvClass.setText(classInfo.getClassroom());
        tvCourse.setText(classInfo.getSchedule());
        spTeacher.setPrompt(classInfo.getTeacherName());
        userInfo = new UserInfo();
        userInfo.setPosition(classInfo.getSchedule());
        userInfo.setTruename(classInfo.getTeacherName());
        userInfo.setUserId(classInfo.getTeacherId());
    }
*/
    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList.clear();
        this.userInfoList.addAll(userInfoList);
        teacherAdapter = new MySpinnerAdapter(context, userInfoList);
        spTeacher.setAdapter(teacherAdapter);
        spTeacher.setOnItemSelectedListener(this);

    }


    @OnClick({R.id.bt_cancel, R.id.bt_conform})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cancel:
                dismiss();
                break;
            case R.id.bt_conform:
                if (onConformCallBack != null) {
                    String room = tvClass.getText().toString();
                    onConformCallBack.onCallBack(room, userInfo);
                }
                dismiss();
                break;
        }
    }

    public void setOnConformCallBack(OnConformCallBack onConformCallBack) {
        this.onConformCallBack = onConformCallBack;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        userInfo = userInfoList.get(position);
        spTeacher.setPrompt(userInfo.getTruename());
        tvCourse.setText(userInfo.getPosition());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public interface OnConformCallBack {
        void onCallBack(String room, UserInfo userInfo);
    }
}
