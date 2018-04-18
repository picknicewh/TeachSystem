package com.mayi.yun.teachsystem.ui.study;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.bean.StudyVo;
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
public class StudySystemAdapter extends RecyclerView.Adapter<StudySystemAdapter.ViewHolder> {
    private List<StudyVo> studyVoList;
    private OnItemClickListener onItemClickListener;

    public StudySystemAdapter(List<StudyVo> studyVoList) {
        this.studyVoList = studyVoList;
    }

    @Override
    public StudySystemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study_system, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudySystemAdapter.ViewHolder holder, final int position) {
        StudyVo studyVo = studyVoList.get(position);
        holder.tvTitle.setText(studyVo.getName());
        holder.tvChildren.setText(getChildren(studyVo.getChildren()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(view, position);
                }
            }
        });
    }

    private String getChildren(List<StudyVo.Children> childrenList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < childrenList.size(); i++) {
            StudyVo.Children children = childrenList.get(i);
            stringBuffer.append(children.getName()).append("   ");
        }
        return stringBuffer.toString();
    }

    @Override
    public int getItemCount() {
        return studyVoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle;
        TextView tvChildren;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_arrow_right);
            tvChildren = itemView.findViewById(R.id.tv_children);
            tvTitle = itemView.findViewById(R.id.tv_title);

            itemView.setTag(this);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
