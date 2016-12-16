package com.tiny.datapickerloopview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tiny.datapickerloopview.utils.DateUtils;
import com.tiny.datapickerloopview.view.dialog.DatePickerDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "date_picker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNormal = (Button) findViewById(R.id.btn_normal);
        Button btnSpecial = (Button) findViewById(R.id.btn_special);
        btnNormal.setOnClickListener(this);
        btnSpecial.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_normal:
                showDatePickerDialogNormal();
                break;
            case R.id.btn_special:
                showDatePickerDialogSpecial();
                break;
            default:
                break;
        }
    }

    //日期选择器
    private String dateCurr = DateUtils.getCurrentDate().replace("-", ".");

    /**
     * 显示日期选择器——以日为基本单位。
     */
    private void showDatePickerDialogSpecial() {
        //取三个月以前的日期。
        int maxYear = Integer.valueOf(DateUtils.getCurrentYear());
        int maxMonth = Integer.valueOf(DateUtils.getCurrentMonth());
        int maxDay = Integer.valueOf(DateUtils.getCurrentDay());
        String minDate = DateUtils.getDateGapMonths(3, true);//yyyy-MM-dd
        int minYear = Integer.valueOf(minDate.substring(0, 4));
        int minMonth = Integer.valueOf(minDate.substring(5, 7));
        int minDay = Integer.valueOf(minDate.substring(minDate.length() - 2, minDate.length()));

        DatePickerDialog pickerDialog = new DatePickerDialog.Builder(this, new DatePickerDialog.OnDatePickedListener() {

            @Override
            public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
//                dateCurr = dateDesc;
                Log.e(TAG, "选中的日期为 --> " + dateDesc);
            }
        }).textTitle("选择起始日期")
                .minYear(minYear) //min year in loop
                .minMonth(minMonth)
                .minDay(minDay)
                .maxYear(maxYear + 1) // max year in loop, need add 1.
                .maxMonth(maxMonth)
                .maxDay(maxDay)
                .setMode(DatePickerDialog.DatePickerMode.DAY)
                .dateChose(dateCurr) //2016.11.28
                .build();
        pickerDialog.showDialog(this);
    }

    /**
     * 显示日期选择器——以年为基本单位。
     */
    private void showDatePickerDialogNormal() {
        int minYear = 2000;
        int maxYear = 2020;
        String dateSelect = "2010.11.11";
        DatePickerDialog pickerDialog = new DatePickerDialog.Builder(this, new DatePickerDialog.OnDatePickedListener() {

            @Override
            public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
//                dateCurr = dateDesc;
                Log.e(TAG, "选中的日期为 --> " + dateDesc);
            }
        }).textTitle("选择起始日期")
                .minYear(minYear) //min year in loop
                .maxYear(maxYear + 1) // max year in loop, need add 1.
                .dateChose(dateSelect) //2016.11.28
                .build();
        pickerDialog.showDialog(this);
    }
}
