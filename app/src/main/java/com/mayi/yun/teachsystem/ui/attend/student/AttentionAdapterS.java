package com.mayi.yun.teachsystem.ui.attend.student;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.bean.AttendVo;
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
public class AttentionAdapterS extends RecyclerView.Adapter<AttentionAdapterS.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<AttendVo> attendVoList;

    public AttentionAdapterS(List<AttendVo> attendVoList) {
        this.attendVoList = attendVoList;
    }

    @Override
    public AttentionAdapterS.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attend_s, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttentionAdapterS.ViewHolder holder, final int position) {
        AttendVo attendVo = attendVoList.get(position);
        holder.tvCourse.setText(attendVo.getSchedule());
        holder.tvStatus.setText(attendVo.getIsSign() == 1 ? "已考勤" : "未考勤");
        String time = DateUtil.getCourse(attendVo.getNumber()) + "(" + DateUtil.getCouresTime(attendVo.getNumber()) + ")";
        holder.tvTime.setText(time);
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
        return attendVoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCourse;
        TextView tvTime;
        TextView tvStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCourse = itemView.findViewById(R.id.tv_course);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvTime = itemView.findViewById(R.id.tv_time);

        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
