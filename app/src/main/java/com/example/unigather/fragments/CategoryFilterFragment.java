package com.example.unigather.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.unigather.R;

import java.util.Arrays;
import java.util.List;
import com.example.unigather.fragments.FilterTabView;


public class CategoryFilterFragment extends Fragment {
    private List<String> mCampusOptions = Arrays.asList("中北", "闵行");
    private List<String> mTypeOptions = Arrays.asList("类型1", "类型2", "类型3");
    private List<String> mOfficialOptions = Arrays.asList("官方", "非官方");
    private List<String> mTimeOptions = Arrays.asList("今天", "昨天", "一周内");
    private Button buttonCampus, buttonType;

    private PopupWindow campusPopupWindow;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category_filter, container, false);

        FilterTabView tabType = view.findViewById(R.id.tabType);
        FilterTabView tabOfficial = view.findViewById(R.id.tabOfficial);
        FilterTabView tabCampus = view.findViewById(R.id.tabCampus);
        FilterTabView tabTime = view.findViewById(R.id.tabTime);

        tabType.setText("类型");
        tabOfficial.setText("是否官方");
        tabCampus.setText("校区");
        tabTime.setText("时间");

//        // 初始化按钮并设置监听器
//        buttonCampus = view.findViewById(R.id.buttonCampus);
//        buttonType = view.findViewById(R.id.tabType);
//        // ... 初始化其他按钮 ...
//
//        buttonCampus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showPopupWindow(v, mCampusOptions);
//            }
//        });
//
        tabType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, mTypeOptions);
            }
        });

        tabOfficial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, mOfficialOptions);
            }
        });

        tabCampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, mCampusOptions);
            }
        });

        tabTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, mTimeOptions);
            }
        });
//
//        // ... 为其他按钮设置监听器 ...
//
        return view;
    }

    private void showPopupWindow(View anchorView, List<String> options) {
        // 创建PopupWindow的内容视图
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        for (String option : options) {
            Button button = new Button(getContext());
            button.setText(option);
            button.setBackgroundColor(Color.WHITE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 处理选项点击事件
                    campusPopupWindow.dismiss();
                    Toast.makeText(getContext(), "选中: " + option, Toast.LENGTH_SHORT).show();
                    // TODO: 更新筛选结果
                }
            });
            layout.addView(button);
        }

        // 初始化并显示PopupWindow
        campusPopupWindow = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        campusPopupWindow.showAsDropDown(anchorView, 0, 0);
    }
}