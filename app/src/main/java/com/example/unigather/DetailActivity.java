package com.example.unigather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // 从Intent获取数据
        int imageId = getIntent().getIntExtra("image_id", -1); // '-1' 是默认值

        // 使用数据（例如，设置ImageView的图片）
//        ImageView imageView = findViewById(R.id.yourImageView);
//        if (imageId != -1) {
//            imageView.setImageResource(imageId);
//        }

        //标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView btnFavorite = findViewById(R.id.btn_favorite);
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            private boolean isFavorite = false; // 初始状态
            @Override
            public void onClick(View v) {
                // 改变图标状态
                isFavorite = !isFavorite;
                btnFavorite.setImageResource(isFavorite ? R.drawable.ic_favorite_filled : R.drawable.ic_favorite_border);
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();  // 关闭当前活动
            return true;
        }
        // 其他菜单项...
        return super.onOptionsItemSelected(item);
    }
}