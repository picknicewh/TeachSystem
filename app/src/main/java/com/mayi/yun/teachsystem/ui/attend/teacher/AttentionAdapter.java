package com.mayi.yun.teachsystem.ui.attend.teacher;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.bean.AttendUserInfo;
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
public class AttentionAdapter extends RecyclerView.Adapter<AttentionAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<AttendUserInfo> userInfoList;

    public AttentionAdapter(List<AttendUserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    @Override
    public AttentionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attention, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttentionAdapter.ViewHolder holder, final int position) {
        AttendUserInfo userInfo = userInfoList.get(position);
        holder.tvName.setText(userInfo.getUserName());
        holder.tvCourseName.setText(userInfo.getSignTime());
        holder.tvSign.setText(userInfo.getIsSign() == 1 ? "到场" : "缺席");
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
        TextView tvCourseName;
        TextView tvSign;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvSign = itemView.findViewById(R.id.tv_sign);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
