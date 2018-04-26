package com.mayi.yun.teachsystem.ui.course;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.utils.G;
import com.mayi.yun.teachsystem.utils.OnItemClickListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： wh
 * 时间：  2018/3/1
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.ViewHolder> {
    private List<String> dateList;
    private String[] weeks = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日",};
    private OnItemClickListener onItemClickListener;
    private Activity context;
    private Map<Integer, Boolean> selectList;

    public WeekAdapter(Activity context, List<String> dateList) {
        this.dateList = dateList;
        this.context = context;
    }

    public void initData(int position) {
        selectList = new HashMap<>();
        for (int i = 0; i < weeks.length; i++) {
            if (position == i) {
                selectList.put(i, true);
            } else {
                selectList.put(i, false);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public WeekAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_week, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeekAdapter.ViewHolder holder, final int position) {
        holder.tvDate.setText(String.valueOf(dateList.get(position)));
        holder.tvWeek.setText(weeks[position]);
        if (selectList.get(position)) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            holder.tvDate.setTextColor(ContextCompat.getColor(context,R.color.white));
            holder.tvWeek.setTextColor(ContextCompat.getColor(context,R.color.white));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            holder.tvDate.setTextColor(ContextCompat.getColor(context,R.color.main_text_color));
            holder.tvWeek.setTextColor(ContextCompat.getColor(context,R.color.main_text_color));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData(position);
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return weeks.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvWeek;
        TextView tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            G.initDisplaySize(context);
            int width = G.size.W / 7 * 6;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    width / 7, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.topMargin = G.dp2px(context, 5);
            tvWeek = itemView.findViewById(R.id.tv_week);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvWeek.setGravity(Gravity.CENTER);
            tvDate.setGravity(Gravity.CENTER);
            tvWeek.setLayoutParams(lp);
            tvDate.setLayoutParams(lp);
            itemView.setTag(this);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
