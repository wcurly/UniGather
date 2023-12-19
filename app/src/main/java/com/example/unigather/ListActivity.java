package com.example.unigather;

import com.example.unigather.adapters.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ActivityAdapter activityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 设置适配器
        List<ActivityItem> activityList = generateSampleData();
        activityAdapter = new ActivityAdapter(activityList);
        recyclerView.setAdapter(activityAdapter);
    }

    private List<ActivityItem> generateSampleData() {
        List<ActivityItem> activityList = new ArrayList<>();
        activityList.add(new ActivityItem("活动标题活动标题活动标题活动标题",R.drawable.image1,111));
        activityList.add(new ActivityItem("活动标题2", R.drawable.image1,222));
        activityList.add(new ActivityItem("活动标题3", R.drawable.image1,2222));
        activityList.add(new ActivityItem("活动标题1",R.drawable.image1,111));
        activityList.add(new ActivityItem("活动标题2", R.drawable.image1,222));
        activityList.add(new ActivityItem("活动标题3", R.drawable.image1,2222));
        activityList.add(new ActivityItem("活动标题1",R.drawable.image1,111));
        activityList.add(new ActivityItem("活动标题2", R.drawable.image1,222));
        activityList.add(new ActivityItem("活动标题3", R.drawable.image1,2222));
        // 添加更多活动...

        return activityList;
    }
}