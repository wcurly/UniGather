package com.example.unigather.fragments;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.unigather.R;

public class FilterTabView extends LinearLayout {
    private View indicatorView;
    private TextView textView;
    private boolean isChecked;

    public FilterTabView(Context context) {
        super(context);
        init(context);
    }

    public FilterTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        // Inflate the custom layout
        View layout = LayoutInflater.from(context).inflate(R.layout.custom_filter_tabview, this, true);
        indicatorView = layout.findViewById(R.id.indicatorView); // 例如底部横线
        textView = layout.findViewById(R.id.textView); // 文本视图

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 可以在这里处理点击事件
                if (!isChecked) {
                    setChecked(true);
                    // TODO: 显示下拉菜单或执行其他操作
                }
            }
        });
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        indicatorView.setVisibility(isChecked ? VISIBLE : INVISIBLE);
    }

    public boolean isChecked() {
        return isChecked;
    }
}


