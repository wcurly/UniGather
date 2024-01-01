package com.example.unigather.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.LinearLayout;

import com.example.unigather.R;

import java.util.Locale;

public class TagDropdownView extends LinearLayout {

    private Button buttonType;
    private Button buttonCampus;
    private Button buttonTime; // 新增的时间按钮
    private Context context;

    public TagDropdownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(context).inflate(R.layout.tag_dropdown_view, this, true);
        buttonType = findViewById(R.id.button_type);
        buttonCampus = findViewById(R.id.button_campus);
        buttonTime = findViewById(R.id.button_time); // 初始化时间选择按钮

        buttonType.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, new String[]{"所有类型","类型1", "类型2", "类型3", "类型4", "类型5", "类型6"});
            }
        });

        buttonCampus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, new String[]{"全部校区","中北", "闵行"});
            }
        });

        buttonTime.setOnClickListener(new OnClickListener() { // 设置时间选择按钮的点击事件
            @Override
            public void onClick(View v) {
//                showPopupWindow(v, new String[]{"今天", "三天内", "一周内"});
                showDatePickerDialog();
            }
        });
    }

    private void showPopupWindow(View anchorView, String[] items) {
        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_tags_layout, null);
        ListView listView = popupView.findViewById(R.id.listview_tags);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, 600, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = items[position];
            if (anchorView instanceof Button) {
                ((Button) anchorView).setText(selectedItem);
            }
            popupWindow.dismiss();
        });

        popupWindow.showAsDropDown(anchorView, 0, 0);
    }

    private void showDatePickerDialog() {
        // 获取当前的年、月、日
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // 创建一个DatePickerDialog实例并显示
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 用户选择了一个日期，处理用户的选择
                String selectedDate = String.format(Locale.getDefault(), "%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
                buttonTime.setText(selectedDate); // 设置按钮的文本为选中的日期
            }
        }, year, month, day);

        // 设置DatePickerDialog的最小日期为今天，阻止选择过去的时间
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());


        // 当用户取消选择时重置时间文本
        datePickerDialog.setOnCancelListener(dialog -> {
            resetTimeButton();
        });

        datePickerDialog.show();
    }
    public void resetTimeButton() {
        buttonTime.setText("时间");
    }
}

