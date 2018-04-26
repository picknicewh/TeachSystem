package com.mayi.yun.teachsystem.ui.leave.teacher;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.bean.VacationVo;
import com.mayi.yun.teachsystem.db.UserMessage;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.List;


/**
 * 作者：Restring
 * 时间：2017/4/20
 * 描述：
 * 版本：
 */

public class LeaveListAdapter extends RecyclerView.Adapter<LeaveListAdapter.ViewHolder> {


    /**
     * 长按事件
     */
    private OnItemClickListener onItemClickListener;
    private List<VacationVo> vacationVoList;

    public LeaveListAdapter(List<VacationVo> vacationVoList) {
        this.vacationVoList = vacationVoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_leave_list_s, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        VacationVo vacationVo = vacationVoList.get(position);
        holder.tv_back_date.setText(vacationVo.getBack_school_date());
        holder.tv_course.setText(vacationVo.getVa_content());
        holder.tv_days.setText(vacationVo.getDay_number());
        holder.tv_name.setText(vacationVo.getTs_name());
        if (UserMessage.getInstance().getUserType() == 3) {
            holder.tv_name.setVisibility(View.GONE);
            holder.tv_name_text.setVisibility(View.GONE);
        } else {
            holder.tv_name.setVisibility(View.VISIBLE);
            holder.tv_name_text.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(view, position);
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_back_date;
        TextView tv_name;
        TextView tv_name_text;
        TextView tv_days;
        TextView tv_course;

        public ViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_name_text = view.findViewById(R.id.tv_name_text);
            tv_back_date = view.findViewById(R.id.tv_back_date);
            tv_days = view.findViewById(R.id.tv_days);
            tv_course = view.findViewById(R.id.tv_course);
            view.setTag(this);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
