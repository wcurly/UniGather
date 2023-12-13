package com.example.unigather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;

// RegisterActivity.java
public class RegisterActivity extends AppCompatActivity {

    private Handler handler = new Handler(Looper.getMainLooper());
    private int timeLeft = 30; // 倒计时秒数
    Button buttonGetCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText editTextNewUsername = findViewById(R.id.editTextNewUsername);
        EditText editTextNewPassword = findViewById(R.id.editTextNewPassword);
        EditText editTextNewEmail = findViewById(R.id.editTextNewEmail);
        Button buttonCreateAccount = findViewById(R.id.buttonCreateAccount);
        buttonGetCode = findViewById(R.id.buttonGetCode);

        buttonGetCode.setOnClickListener(v->{
            //
            startCountdown();
        });

        buttonCreateAccount.setOnClickListener(v -> {
            // 注册逻辑
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // 关闭注册页面
        });
    }

    private void startCountdown() {
        buttonGetCode.setEnabled(false); // 禁用按钮
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (timeLeft > 0) {
                    buttonGetCode.setText(timeLeft + "s");
                    timeLeft--;
                    handler.postDelayed(this, 1000); // 每秒更新一次
                } else {
                    buttonGetCode.setText("获取验证码");
                    buttonGetCode.setEnabled(true); // 倒计时结束，重新启用按钮
                    timeLeft = 30; // 重置倒计时
                }
            }
        };
        handler.post(runnable);
    }
}
