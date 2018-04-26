package com.mayi.yun.teachsystem.ui.course;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.utils.G;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2018/4/20
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CourseScheduleAdapter extends BaseAdapter {
    private Activity context;
    private List<String>  dataList;
    public CourseScheduleAdapter(Activity context, List<String>  dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if(convertView == null || convertView.getTag() == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_course_schedule,null);
            holder = new ViewHolder(convertView);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textView.setText(dataList.get(position));
        return convertView;
    }
    public class ViewHolder{
        TextView textView;
        public ViewHolder(View view){
            G.initDisplaySize(context);
            textView =  view.findViewById(R.id.tv_course);
            int height = G.dp2px(context,170);
            int padding = G.dp2px(context,5);
            LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height);
            lps.gravity = Gravity.CENTER;
            textView.setLayoutParams(lps);
            textView.setPadding(padding,padding,padding,padding);
            textView.setGravity(Gravity.CENTER);
            view.setTag(this);
        }
    }
}
