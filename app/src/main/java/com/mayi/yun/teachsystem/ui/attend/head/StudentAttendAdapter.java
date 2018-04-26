package com.mayi.yun.teachsystem.ui.attend.head;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.bean.UserInfo;
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
public class StudentAttendAdapter extends RecyclerView.Adapter<StudentAttendAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<UserInfo> userInfoList;

    public StudentAttendAdapter(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    @Override
    public StudentAttendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_attent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentAttendAdapter.ViewHolder holder, final int position) {
        UserInfo userInfo = userInfoList.get(position);
        holder.tvName.setText(userInfo.getTruename());
        holder.tvNumber.setText(userInfo.getUserSn());
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
        return userInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
