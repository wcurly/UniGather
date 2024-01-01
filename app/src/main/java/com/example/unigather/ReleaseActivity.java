package com.example.unigather;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unigather.adapters.AddImageAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ReleaseActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private TextView tvSelectDate;
    private RadioGroup radioGroupLocation;
    private static final int RADIO_BUTTON_LOCATION_1 = R.id.radioButtonLocation1;
    private static final int RADIO_BUTTON_LOCATION_2 = R.id.radioButtonLocation2;

    private List<Uri> imageUrisImage;
    private List<Uri> imageUrisPoster;
    private AddImageAdapter addImageAdapter;
    private AddImageAdapter addPosterAdapter;
    private ActivityResultLauncher<Intent> galleryLauncherForImages;
    private ActivityResultLauncher<Intent> galleryLauncherForPosters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription=findViewById(R.id.editTextDescription);
        radioGroupLocation = findViewById(R.id.radioGroupLocation);
        tvSelectDate = findViewById(R.id.tvSelectDate);
        tvSelectDate.setOnClickListener(view -> showDatePickerDialog());

        //类型选择
        String[] TYPES = new String[]{"Belgium", "France", "Italy", "Germany", "Spain"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, TYPES);

        Spinner textView = findViewById(R.id.spinnerType);
        textView.setAdapter(adapter);

        //上传图片
        imageUrisImage = new ArrayList<>();
        imageUrisPoster = new ArrayList<>();

        addImageAdapter = new AddImageAdapter(this, imageUrisImage, this::openGalleryForImages);
        addPosterAdapter = new AddImageAdapter(this, imageUrisPoster, this::openGalleryForPosters);

        RecyclerView recyclerViewImages = findViewById(R.id.recyclerViewImages);
        RecyclerView recyclerViewPosters = findViewById(R.id.recyclerViewPosters);

        int numberOfColumns = 3; // 可以根据需要调整列数

        recyclerViewImages.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerViewImages.setAdapter(addImageAdapter);

        recyclerViewPosters.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerViewPosters.setAdapter(addPosterAdapter);

        // 初始化ActivityResultLauncher并注册合同，用于图片
        galleryLauncherForImages = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            if (data.getClipData() != null) {
                                ClipData clipData = data.getClipData();
                                for (int i = 0; i < clipData.getItemCount(); i++) {
                                    Uri imageUri = clipData.getItemAt(i).getUri();
                                    imageUrisImage.add(imageUri);
                                }
                            } else if (data.getData() != null) {
                                Uri imageUri = data.getData();
                                imageUrisImage.add(imageUri);
                            }
                            addImageAdapter.notifyDataSetChanged();
                        }
                    }
                });

        // 初始化ActivityResultLauncher并注册合同，用于海报
        galleryLauncherForPosters = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            if (data.getClipData() != null) {
                                ClipData clipData = data.getClipData();
                                for (int i = 0; i < clipData.getItemCount(); i++) {
                                    Uri imageUri = clipData.getItemAt(i).getUri();
                                    imageUrisPoster.add(imageUri);
                                }
                            } else if (data.getData() != null) {
                                Uri imageUri = data.getData();
                                imageUrisPoster.add(imageUri);
                            }
                            addPosterAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    // 打开图库的方法，用于图片
    public void openGalleryForImages() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galleryLauncherForImages.launch(intent);
    }

    // 打开图库的方法，用于海报
    public void openGalleryForPosters() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galleryLauncherForPosters.launch(intent);
    }

    private void showDatePickerDialog() {
        // 获取当前日期
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //用户选择的日期
                        String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                        tvSelectDate.setText(selectedDate);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }
    public void onReleaseButtonClick(View view) {
        // 获取用户输入的标题
        String title = editTextTitle.getText().toString();
        // 处理获取到的数据
        if (isValidTitle(title)) {
            // 数据有效，可以保存到数据库或进行其他操作
//            saveDataToDatabase(title);

            // 在这里添加其他处理逻辑，例如跳转到其他页面
            // ...

            // 提示用户发布成功
            Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
        } else {
            // 数据无效，提示用户重新输入
            Toast.makeText(this, "请输入有效的标题", Toast.LENGTH_SHORT).show();
        }

        //处理活动简介
        String description=editTextDescription.getText().toString();
        if(isValidTitle(description)){
//            saveDataToDatabase(description);
        }else{
            Toast.makeText(this, "请输入有效的活动简介", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidTitle(String title) {
        // 在这里添加验证标题的逻辑，例如检查是否为空或是否符合特定格式
        return !TextUtils.isEmpty(title);
    }

    private void saveDataToDatabase(String title) {
        // 在这里添加将数据保存到数据库的逻辑
        // 使用数据库操作方法、Room Persistence Library 等
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.release_menu, menu);
        return true;
    }

    //返回按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();  // 关闭当前活动，返回到MainActivity
            return true;
        }else if (item.getItemId() == R.id.action_publish) {
            // 处理发布按钮的点击事件
            return true;
        }
        // 其他菜单项...
        return super.onOptionsItemSelected(item);
    }

}