package com.example.unigather.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.example.unigather.R;
import com.google.android.material.tabs.TabLayout;

public class CategoryFilterFragment extends Fragment {

    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_filter, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("类型"));
        tabLayout.addTab(tabLayout.newTab().setText("举办方"));
        tabLayout.addTab(tabLayout.newTab().setText("校区"));
        tabLayout.addTab(tabLayout.newTab().setText("时间"));
        // 设置监听器等

        return view;
    }
}
