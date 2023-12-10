package com.example.unigather;

import com.example.unigather.fragments.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new HotEventsFragment())
                .commit();

//        // 添加分类筛选Fragment
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fragmentContainer, new CategoryFilterFragment())
//                .commit();
    }
}
