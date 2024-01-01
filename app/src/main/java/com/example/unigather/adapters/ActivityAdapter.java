package com.example.unigather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unigather.R;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {

    private List<ActivityItem> activityList;
    private Context context;

    public ActivityAdapter(List<ActivityItem> activityList) {
        this.activityList = activityList;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        ActivityItem item = activityList.get(position);
        holder.textViewActivityTitle.setText(item.getTitle());
        holder.textViewCollectionCount.setText("收藏数：" + item.getCollectionCount());
        holder.imageViewActivityImage.setImageResource(item.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewActivityImage;
        TextView textViewActivityTitle;
        TextView textViewCollectionCount;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewActivityImage = itemView.findViewById(R.id.imageViewActivityImage);
            textViewActivityTitle = itemView.findViewById(R.id.textViewActivityTitle);
            textViewCollectionCount = itemView.findViewById(R.id.textViewCollectionCount);
        }
    }
}
