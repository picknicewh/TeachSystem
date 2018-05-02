package com.mayi.yun.teachsystem.ui.classinfo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.bean.ClassVo;
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
public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;

    private List<ClassVo> classList;

    public ClassListAdapter(List<ClassVo> classList) {
        this.classList = classList;
    }

    @Override
    public ClassListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassListAdapter.ViewHolder holder, final int position) {
        ClassVo classVo = classList.get(position);
        holder.tvClass.setText(classVo.getName());
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
        return classList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvClass;

        public ViewHolder(View itemView) {
            super(itemView);
            tvClass = itemView.findViewById(R.id.tv_class);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
