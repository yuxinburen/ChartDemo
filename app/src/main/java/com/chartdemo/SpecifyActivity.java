package com.chartdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.chartdemo.ui.chartview.LineChartView;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;


public class SpecifyActivity extends ActionBarActivity {


    private LineChartView[] arrayLineViews = new LineChartView[5];
    private String[] titles = {"血糖", "血压", "血红蛋白", "心率", "记步"};
    private String[] todayValues = {"午餐前:2.4mml/ml", "80/120mmHg", "9.5%", "75c/min", "1280步"};
    private ArrayList<String> xVals = null;
    private ArrayList<Entry> yVals = null;
    private int[] colors = {
            Color.rgb(137, 230, 81),
            Color.rgb(240, 240, 30),
            Color.rgb(89, 199, 250),
            Color.rgb(250, 104, 104),
            Color.rgb(150, 37, 98)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specify);

        arrayLineViews[0] = ((LineChartView) findViewById(R.id.specifyChartView1));
        arrayLineViews[1] = ((LineChartView) findViewById(R.id.specifyChartView2));
        arrayLineViews[2] = ((LineChartView) findViewById(R.id.specifyChartView3));
        arrayLineViews[3] = ((LineChartView) findViewById(R.id.specifyChartView4));
        arrayLineViews[4] = ((LineChartView) findViewById(R.id.specifyChartView5));

        getData(24, 100);

        for (int i = 0; i < 5; i++) {
            arrayLineViews[i].setBackgroundColor(colors[i]);
            arrayLineViews[i].setTodayValue(todayValues[i]);
            arrayLineViews[i].setData(xVals, yVals, titles[i]);
            arrayLineViews[i].setupChart("","暂无数据");
            arrayLineViews[i].setViewType(LineChartView.SHOW_TYPE.SPECIFY);
            arrayLineViews[i].setLeftText("最低5.8mmk");
            arrayLineViews[i].setCenterText("平均10.3mmk");
            arrayLineViews[i].setRightText("最高25.8mmk");
        }
    }

    //count 36 range 100
    private void getData(int count, float range) {
        xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(i % 12 + "");
        }
        yVals = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            yVals.add(new Entry(val, i));
        }
    }

}
