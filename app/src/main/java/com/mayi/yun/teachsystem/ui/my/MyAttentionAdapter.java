package com.mayi.yun.teachsystem.ui.my;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.bean.ScheduleVo;
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
public class MyAttentionAdapter extends RecyclerView.Adapter<MyAttentionAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<ScheduleVo> scheduleVoList;

    public MyAttentionAdapter(List<ScheduleVo> scheduleVoList) {
        this.scheduleVoList = scheduleVoList;
    }

    @Override
    public MyAttentionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attend_s, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAttentionAdapter.ViewHolder holder, final int position) {
        ScheduleVo scheduleVo = scheduleVoList.get(position);
        holder.tvCourse.setText(scheduleVo.getScheduleName());
        holder.tvStatus.setText(scheduleVo.getIsSign() == 1 ? "已考勤" : "未考勤");
        holder.tvTime.setText(scheduleVo.getCreateTime());
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
        return scheduleVoList.size();
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
