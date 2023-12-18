package com.example.unigather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class ReleaseActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private RadioGroup radioGroupLocation;
    private static final int RADIO_BUTTON_LOCATION_1 = R.id.radioButtonLocation1;
    private static final int RADIO_BUTTON_LOCATION_2 = R.id.radioButtonLocation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription=findViewById(R.id.editTextDescription);
        radioGroupLocation = findViewById(R.id.radioGroupLocation);
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
}