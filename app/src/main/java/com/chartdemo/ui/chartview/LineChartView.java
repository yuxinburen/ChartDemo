package com.chartdemo.ui.chartview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chartdemo.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * @author yhw(email:yuhongwei@anhao.cn)
 * @date 2015-11-09 11:22
 * @package com.chartdemo.ui
 * @description LineChartView  TODO(界面功能描述)
 * @params TODO(进入界面传参描述)
 */
public class LineChartView extends LinearLayout {

    private LayoutInflater mInflater;
    private LineChart lineChart;
    private TextView tvLeft;
    private TextView tvCenter;
    private TextView tvRight;
    private TextView tvValue;

    private LineDataSet mDataSet;
    private LineData mLineData;

    public enum SHOW_TYPE{
        DEFAULT, //默认显示
        SPECIFY //指定日期显示
    }

    public void setViewType(SHOW_TYPE show_type){
        switch (show_type){
            case DEFAULT:
                Drawable drawable = getResources().getDrawable(android.R.drawable.ic_menu_camera);
                /// 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                tvLeft.setCompoundDrawables(drawable,null,null,null);
                tvCenter.setVisibility(View.GONE);
                tvRight.setText("今日");
                setBackground(getResources().getDrawable(R.drawable.background_item_lingchart));
                break;
            case SPECIFY:
                tvValue.setVisibility(View.GONE);
                break;
        }
    }

    public void setData(ArrayList<String> xValues, ArrayList<Entry> yValues, String title){
        mDataSet =  new LineDataSet(yValues, title);
        mDataSet.setLineWidth(1.75f);
        mDataSet.setCircleSize(3f);
        mDataSet.setColor(Color.WHITE);
        mDataSet.setCircleColor(Color.WHITE);
        mDataSet.setHighLightColor(Color.WHITE);
        mDataSet.setDrawValues(false);

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(mDataSet); // add the datasets
        mLineData = new LineData(xValues, dataSets);
        mLineData.setValueTextSize(10);
        mLineData.setValueTextColor(Color.BLACK);
        mLineData.setDrawValues(false);//设置是否显示点值

        tvLeft.setText(TextUtils.isEmpty(title) ? "" : title);
    }

    public LineChartView(Context context) {
        this(context, null);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mInflater = LayoutInflater.from(context);
        View root = mInflater.inflate(R.layout.item_linechart, this);
        tvLeft = ((TextView) root.findViewById(R.id.tv_left));
        tvCenter = ((TextView) root.findViewById(R.id.tv_center));
        tvRight = ((TextView) root.findViewById(R.id.tv_right));
        tvValue = ((TextView) root.findViewById(R.id.tv_value));
        lineChart = ((LineChart) root.findViewById(R.id.chart));
    }

    public void setCenterText(String text){
        tvCenter.setText(TextUtils.isEmpty(text)? "暂无" : text);
    }

    public void setLeftText(String text){
        tvLeft.setText(TextUtils.isEmpty(text)? "暂无" : text);
    }

    public void setRightText(String text){
        tvRight.setText(TextUtils.isEmpty(text)? "暂无" : text);
    }

    public void setTodayValue(String todayValue){
        tvValue.setText(TextUtils.isEmpty(todayValue) ? "暂无" : todayValue);
    }

    /**
     *
     * @param desc 文字描述
     * @param noDataDesc 无数据时显示的内容
     */
    public void setupChart(String desc, String noDataDesc){
        // no description text
        lineChart.setDescription(TextUtils.isEmpty(desc) ? "" : desc);
        lineChart.setNoDataTextDescription(TextUtils.isEmpty(noDataDesc) ? "" : noDataDesc);

        lineChart.setDrawGridBackground(false);
        lineChart.setTouchEnabled(false);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.animateX(2500);
        // lineChart.setViewPortOffsets(10, 0, 10, 0);
        // mChart.setDrawHorizontalGrid(false);
        // lineChart.setBackgroundColor(color);

        Legend l = lineChart.getLegend();
        l.setEnabled(false);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineWidth(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawLimitLinesBehindData(true);
//        xAxis.setDrawLabels(true);
//        xAxis.setEnabled(true);
//        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
//        xAxis.setAxisLineColor(Color.BLACK);
//        xAxis.setSpaceBetweenLabels(2);
//        xAxis.setAvoidFirstLastClipping(true);
//        xAxis.setTextColor(Color.BLACK);
        lineChart.setData(mLineData);
    }

    public void setMaxYValue(int maxYValue){
        lineChart.setMaxVisibleValueCount(maxYValue);
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public LineDataSet getmDataSet() {
        return mDataSet;
    }

    public LineData getLineData() {
        return mLineData;
    }
}