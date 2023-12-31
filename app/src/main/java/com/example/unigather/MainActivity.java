package com.example.unigather;

import com.example.unigather.fragments.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EventsViewGroup eventsViewGroup;
    private int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.containerHotEvents, new HotEventsFragment())
//                    .commit();
//
//        }
        eventsViewGroup = findViewById(R.id.eventsViewGroup);
        eventsViewGroup.setImages(images);

//         加载 CategoryFilterFragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerCategoryFilter, new CategoryFilterFragment())
                    .commit();
        }
    }
}
