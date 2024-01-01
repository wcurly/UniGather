package com.example.unigather;

import com.example.unigather.adapters.ActivityAdapter;
import com.example.unigather.adapters.ActivityItem;
import com.example.unigather.fragments.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EventsViewGroup eventsViewGroup;
    private int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
    private RecyclerView rvActivities;
    private ActivityAdapter activitiesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                //刷新？
                return true;
            } else if (item.getItemId() == R.id.navigation_publish) {
                // 跳转到 ReleaseActivity
                Intent publishIntent = new Intent(this, ReleaseActivity.class);
                startActivity(publishIntent);
                return true;
            } else if (item.getItemId() == R.id.navigation_profile) {
                //没登录跳转登录页面，已登录跳转个人页面
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                return true;
            }
            return false;
        });




        //热门活动部分
        eventsViewGroup = findViewById(R.id.eventsViewGroup);
        eventsViewGroup.setImages(images);


        TagDropdownView tagDropdownView = findViewById(R.id.tag_dropdown_view);


        //活动列表部分
        rvActivities = findViewById(R.id.rv_activities);
        rvActivities.setLayoutManager(new LinearLayoutManager(this));

        List<ActivityItem> activityItems = new ArrayList<>();
        // 添加一些模拟数据
        activityItems.add(new ActivityItem("活动标题1", R.drawable.image1, 123));
        activityItems.add(new ActivityItem("活动标题2", R.drawable.image2, 456));
        activityItems.add(new ActivityItem("活动标题3", R.drawable.image2, 456));
        activityItems.add(new ActivityItem("活动标题4", R.drawable.image2, 456));
        // ...更多数据...

        activitiesAdapter = new ActivityAdapter(activityItems);
        rvActivities.setAdapter(activitiesAdapter);
    }
}
