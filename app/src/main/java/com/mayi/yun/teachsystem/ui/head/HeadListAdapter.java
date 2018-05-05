package com.mayi.yun.teachsystem.ui.head;

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
 * 作者：Restring
 * 时间：2017/4/20
 * 描述：
 * 版本：
 */

public class HeadListAdapter extends RecyclerView.Adapter<HeadListAdapter.ViewHolder> {


    /**
     * 长按事件
     */
    private OnItemClickListener onItemClickListener;

    private List<UserInfo> userInfoList;

    public HeadListAdapter(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_head_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
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
