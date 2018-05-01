package com.mayi.yun.teachsystem.ui.classinfo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.network.GlideUtils;
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
public class ClassMemberAdapter extends RecyclerView.Adapter<ClassMemberAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;
   private List<UserInfo> userInfoList;
   private Context context;
    public ClassMemberAdapter(List<UserInfo> userInfoList, Context context) {
      this.userInfoList = userInfoList;
      this.context = context;
    }

    @Override
    public ClassMemberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassMemberAdapter.ViewHolder holder, final int position) {
         UserInfo userInfo = userInfoList.get(position);
         holder.tvName.setText(userInfo.getTruename());
         GlideUtils.loadImageView(context,userInfo.getAvatar(),holder.ivImage);
         holder.tvNumber.setText(userInfo.getUserSn());
         holder.tvPosition.setText(userInfo.getPosition());
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
        ImageView ivImage;
        TextView tvNumber;
        TextView tvPosition;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            ivImage = itemView.findViewById(R.id.iv_image);
            tvNumber = itemView.findViewById(R.id.tv_number);
            tvPosition = itemView.findViewById(R.id.tv_position);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
