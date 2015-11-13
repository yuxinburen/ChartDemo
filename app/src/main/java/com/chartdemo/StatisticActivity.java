package com.chartdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.chartdemo.ui.chartview.StatisticView;
import com.chartdemo.ui.chartview.UNIT_TYPE;


public class StatisticActivity extends ActionBarActivity {

    private StatisticView chartView1;
    private StatisticView chartView2;
    private StatisticView chartView3;
    private StatisticView chartView4;
    private StatisticView chartView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        chartView1 = ((StatisticView) findViewById(R.id.sv_chart1));
        chartView2 = ((StatisticView) findViewById(R.id.sv_chart2));
        chartView3 = ((StatisticView) findViewById(R.id.sv_chart3));
        chartView4 = ((StatisticView) findViewById(R.id.sv_chart4));
        chartView5 = ((StatisticView) findViewById(R.id.sv_chart5));

        chartView1.setSumData(10, 11, 2, 3);//设置统计数据
        chartView1.setTargetData(UNIT_TYPE.ITEM_UNIT_TYPE_SUGAR,1.4,2.3,19.5);

        chartView2.setSumData(5, 1, 2, 0);
        chartView2.setTargetData(UNIT_TYPE.ITEM_UNIT_TYPE_CRUORIN, 0, 23, 67);

        chartView3.setSumData(1,1,2,0);
        chartView3.setTargetData(UNIT_TYPE.ITEM_UNIT_TYPE_PRESSURE, 30, 89, 300, 40, 50, 100);

        chartView4.setSumData(50,20,11,23);
        chartView4.setTargetData(UNIT_TYPE.ITEM_UNIT_TYPE_HEART, 76, 59, 98);

        chartView5.setSumData(1,2,3,1);
        chartView5.setTargetData(UNIT_TYPE.ITEM_UNIT_TYPE_STEP, 10000, 20000, 11223);
    }
}
