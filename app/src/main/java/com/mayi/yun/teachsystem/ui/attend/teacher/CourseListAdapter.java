package com.mayi.yun.teachsystem.ui.attend.teacher;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.bean.CourseVo;
import com.mayi.yun.teachsystem.utils.DateUtil;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2018/3/1
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;

    private List<CourseVo> courseVoList;

    public CourseListAdapter(List<CourseVo> courseVoList) {
        this.courseVoList = courseVoList;
    }

    @Override
    public CourseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_current_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseListAdapter.ViewHolder holder, final int position) {
        CourseVo courseVo = courseVoList.get(position);
        holder.tvCourseName.setText(courseVo.getSchedule());
        String time = DateUtil.getCourse(courseVo.getNumber()) + "(" + DateUtil.getCouresTime(courseVo.getNumber()) + ")";
        holder.tvCourseTime.setText(time);
        holder.tvCourseRoom.setText(courseVo.getClassroom());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseVoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCourseName;
        TextView tvCourseTime;
        TextView tvCourseRoom;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvCourseTime = itemView.findViewById(R.id.tv_course_time);
            tvCourseRoom =  itemView.findViewById(R.id.tv_course_room);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
