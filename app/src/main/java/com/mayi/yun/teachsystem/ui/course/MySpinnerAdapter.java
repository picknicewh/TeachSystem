package com.mayi.yun.teachsystem.ui.course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mayi.yun.teachsystem.R;
import com.mayi.yun.teachsystem.bean.UserInfo;

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
    private Context mContext;
    private List<UserInfo> userInfoList;

    public MySpinnerAdapter(Context context, List<UserInfo> userInfoList) {
        this.mContext = context;
        this.userInfoList = userInfoList;
    }

    @Override
    public int getCount() {
        return userInfoList == null ? 0 : userInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return userInfoList == null ? null : userInfoList.get(position);
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
        hodler.mTextView.setText(userInfoList.get(position).getTruename());
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
