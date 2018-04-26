package com.mayi.yun.teachsystem.ui.attend.teacher;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
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
public class AttentionAdapterT extends RecyclerView.Adapter<AttentionAdapterT.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<Integer> list;

    public AttentionAdapterT(List<Integer> list) {
      this.list = list;
    }

    @Override
    public AttentionAdapterT.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attend_t, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttentionAdapterT.ViewHolder holder, final int position) {
        holder.ivImage.setImageResource(list.get(position));
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
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNumber;
        TextView tvName;
        ImageView ivImage;
        TextView tvPosition;
        ImageView dislikeImageView;
        ImageView likeImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tv_number);
            ivImage = itemView.findViewById(R.id.iv_image);
            tvName = itemView.findViewById(R.id.tv_name);
            dislikeImageView = itemView.findViewById(R.id.iv_dislike);
            likeImageView = itemView.findViewById(R.id.iv_like);
            tvPosition = itemView.findViewById(R.id.tv_position);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
