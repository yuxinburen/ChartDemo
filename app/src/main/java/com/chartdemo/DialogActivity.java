package com.chartdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;

import com.chartdemo.ui.AreaPicker;
import com.chartdemo.ui.DateTimePicker;


public class DialogActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        findViewById(R.id.btn_datetime).setOnClickListener(this);
        findViewById(R.id.btn_date).setOnClickListener(this);
        findViewById(R.id.btn_time).setOnClickListener(this);
        findViewById(R.id.btn_area).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_datetime:
                DateTimePicker datetimepicker = new DateTimePicker(this);
                datetimepicker.showAtLocation(findViewById(R.id.ll_main), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.btn_date:
                DateTimePicker datePicker = new DateTimePicker(this);
                datePicker.setVisibility(4,false);
                datePicker.setVisibility(5,false);
                datePicker.showAtLocation(findViewById(R.id.ll_main), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.btn_time:
                DateTimePicker timePicker = new DateTimePicker(this);
                timePicker.setVisibility(1,false);
                timePicker.setVisibility(2,false);
                timePicker.setVisibility(3,false);
                timePicker.showAtLocation(findViewById(R.id.ll_main), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.btn_area:
                AreaPicker areaPicker = new AreaPicker(this);
                areaPicker.showAtLocation(findViewById(R.id.ll_main), Gravity.BOTTOM, 0, 0);
                break;
        }
    }
}
