package com.example.unigather.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unigather.DetailActivity;
import com.example.unigather.R;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {

    private int[] images;

    public EventsAdapter(int[] images) {
        this.images = images;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event, parent, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.imageView.setImageResource(images[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建Intent来启动DetailActivity
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                // 添加所需数据作为额外信息传递
                intent.putExtra("image_id", images[position]);
                // 启动Activity
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public EventViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageViewEvent);
        }
    }
}