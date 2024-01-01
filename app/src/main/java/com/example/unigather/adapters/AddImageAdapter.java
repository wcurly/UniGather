package com.example.unigather.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unigather.R;

import java.util.List;

public class AddImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ADD = 0;
    private static final int TYPE_IMAGE = 1;
    private List<Uri> uris;
    private final Context context;
    private final Runnable onAddImageClicked;

    public AddImageAdapter(Context context, List<Uri> uris, Runnable onAddImageClicked) {
        this.context = context;
        this.uris = uris;
        this.onAddImageClicked = onAddImageClicked;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == uris.size()) {
            return TYPE_ADD;
        } else {
            return TYPE_IMAGE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ADD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_image, parent, false);
            return new AddViewHolder(view, onAddImageClicked);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
            return new ImageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageViewHolder) {
            Uri uri = uris.get(position);
            ((ImageViewHolder) holder).imageView.setImageURI(uri);

            ((ImageViewHolder) holder).deleteButton.setOnClickListener(v -> {
                uris.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, uris.size());
            });
        }
    }

    @Override
    public int getItemCount() {
        return uris.size() + 1; // 包括添加图片的视图
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageButton deleteButton;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    public static class AddViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewAdd;

        public AddViewHolder(@NonNull View itemView, Runnable onAddImageClicked) {
            super(itemView);
            imageViewAdd = itemView.findViewById(R.id.imageViewAdd);
            imageViewAdd.setOnClickListener(v -> onAddImageClicked.run());
        }
    }
}

