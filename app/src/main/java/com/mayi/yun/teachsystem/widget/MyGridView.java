package com.mayi.yun.teachsystem.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 作者： wh
 * 时间：  2018/4/20
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MyGridView  extends GridView {
    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

