package com.mayi.yun.teachsystem.ui.course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2018/4/25
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MySpinnerAdapter extends BaseAdapter {
    List<String> dataList = new ArrayList<>();
    private Context mContext;

    public MySpinnerAdapter(Context context) {
        this.mContext = context;
    }

    public void setDatas(List<String> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList == null ? null : dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder hodler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_spinner, null);
            hodler = new ViewHolder(convertView);
        } else {
            hodler = (ViewHolder) convertView.getTag();
        }
        hodler.mTextView.setText(dataList.get(position));
        return convertView;
    }

    private class ViewHolder {
        TextView mTextView;

        public ViewHolder(View itemView) {
            mTextView = itemView.findViewById(R.id.tv_info);
            itemView.setTag(this);
        }
    }
}
