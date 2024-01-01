package com.example.unigather.fragments;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unigather.R;
import com.example.unigather.adapters.EventsAdapter;

public class EventsViewGroup extends LinearLayout {

    private RecyclerView recyclerView;
    private EventsAdapter adapter;
    private int[] images;

    public EventsViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        // Inflate the layout for this view
        LayoutInflater.from(context).inflate(R.layout.activity_event, this, true);

        recyclerView = findViewById(R.id.recyclerViewEvents);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

    }

    public void setImages(int[] images) {
        this.images = images;
        adapter = new EventsAdapter(images);
        recyclerView.setAdapter(adapter);
    }
}
