package com.mayi.yun.teachsystem.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.mayi.yun.teachsystem.R;
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
public class CourseChooseDialog extends AlertDialog {
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
    @BindView(R.id.sp_course)
    Spinner spCourse;
    /**
     * 上线文本
     */
    private Context context;
    /**
     * 老师选择适配器
     */
    private MySpinnerAdapter teacherAdapter;
    /**
     * 课程选择适配器
     */
    private MySpinnerAdapter courseAdapter;
    /**
     * 回调
     */
    private OnConformCallBack onConformCallBack;
    /**
     * 选择的老师
     */
    private String teacher = "";
    /**
     * 选择的课程
     */
    private String course = "";
    List<String> teacherDatas = new ArrayList<>();
    List<String> courseDatas = new ArrayList<>();

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
        teacherDatas.add("叶挺挺");
        for (int i = 0; i < 10; i++) {

            teacherDatas.add("老师" + i);
        }
        courseDatas.add("信息安全技术");
        for (int i = 0; i < 10; i++) {
            courseDatas.add("课程" + i);
        }
        teacherAdapter = new MySpinnerAdapter(context);
        spTeacher.setAdapter(teacherAdapter);
        teacherAdapter.setDatas(teacherDatas);
        spTeacher.setOnItemSelectedListener(teacherListener);

        courseAdapter = new MySpinnerAdapter(context);
        spCourse.setAdapter(courseAdapter);
        courseAdapter.setDatas(courseDatas);
        spCourse.setOnItemSelectedListener(courseListener);
    }

    private AdapterView.OnItemSelectedListener courseListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            course = courseDatas.get(position);
            spCourse.setPrompt(course);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    private AdapterView.OnItemSelectedListener teacherListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            teacher = teacherDatas.get(position);
            spTeacher.setPrompt(teacher);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


    @OnClick({R.id.bt_cancel, R.id.bt_conform})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cancel:
                dismiss();
                break;
            case R.id.bt_conform:
                if (onConformCallBack != null) {
                    String room = tvClass.getText().toString();
                    onConformCallBack.onCallBack(room, teacher, course);
                }
                dismiss();
                break;
        }
    }

    public void setOnConformCallBack(OnConformCallBack onConformCallBack) {
        this.onConformCallBack = onConformCallBack;
    }


    public interface OnConformCallBack {
        void onCallBack(String room, String teacher, String course);
    }
}
