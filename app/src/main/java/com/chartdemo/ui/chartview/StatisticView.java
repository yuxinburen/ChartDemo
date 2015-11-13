package com.chartdemo.ui.chartview;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chartdemo.R;

/**
 * @author yhw(email:yuhongwei@anhao.cn)
 * @date 2015-11-10 11:00
 * @package com.chartdemo.ui
 * @description StatisticView  TODO(界面功能描述)
 * @params TODO(进入界面传参描述)
 */
public class StatisticView extends LinearLayout {

    private LayoutInflater mInflater;
    private TextView tv_lower;
    private TextView tv_normal;
    private TextView tv_high;
    private TextView tv_dangenrous;

    private LinearLayout ll_statistic_top;
    private LinearLayout ll_statistic_bottom;
    private TextView tv_top_type_name;
    private TextView tv_top_lowest;
    private TextView tv_top_average;
    private TextView tv_top_highest;
    private TextView tv_bottom_type_name;
    private TextView tv_bottom_lowest;
    private TextView tv_bottom_average;
    private TextView tv_bottom_highest;

    public enum STATISTIC_NAME{
        TOP,//上半部分
        BOTTOM//下半部分
    }

    public void setVisibity(STATISTIC_NAME name, boolean flag){
        switch (name){
            case TOP:
                if (flag){
                    ll_statistic_top.setVisibility(View.VISIBLE);
                }else {
                    ll_statistic_top.setVisibility(View.GONE);
                }
                break;
            case BOTTOM:
                if(flag){
                    ll_statistic_bottom.setVisibility(View.VISIBLE);
                }else {
                    ll_statistic_bottom.setVisibility(View.GONE);
                }
                break;
        }
    }


    public StatisticView(Context context) {
        this(context, null);
    }

    public StatisticView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mInflater = LayoutInflater.from(context);
        View root = mInflater.inflate(R.layout.layout_statistic_chart_item, this);
        tv_lower = ((TextView) root.findViewById(R.id.tv_lower));
        tv_normal = ((TextView) root.findViewById(R.id.tv_normal));
        tv_high = ((TextView) root.findViewById(R.id.tv_high));
        tv_dangenrous = ((TextView) root.findViewById(R.id.tv_dangenrous));

        ll_statistic_bottom = ((LinearLayout) root.findViewById(R.id.ll_statistic_bottom));
        ll_statistic_top = ((LinearLayout) root.findViewById(R.id.ll_statistic_top));

        tv_top_type_name = ((TextView) root.findViewById(R.id.tv_top_type_name));
        tv_top_lowest = ((TextView) root.findViewById(R.id.tv_top_lowest));
        tv_top_average = ((TextView) root.findViewById(R.id.tv_top_average));
        tv_top_highest = ((TextView) root.findViewById(R.id.tv_top_highest));
        tv_bottom_type_name = ((TextView) root.findViewById(R.id.tv_bottom_type_name));
        tv_bottom_lowest = ((TextView) root.findViewById(R.id.tv_bottom_lowest));
        tv_bottom_average = ((TextView) root.findViewById(R.id.tv_bottom_average));
        tv_bottom_highest = ((TextView) root.findViewById(R.id.tv_bottom_highest));
    }

    /**
     * 设置统计图表中的各个数据
     * @param lower
     * @param normal
     * @param high
     * @param dangenrous
     */
    public void setSumData(int lower, int normal,
                           int high, int dangenrous){
        int sum = lower + normal + high + dangenrous;
        tv_lower.setText(lower + "/" + sum);
        tv_normal.setText(normal + "/" + sum);
        tv_high.setText(high + "/" + sum);
        tv_dangenrous.setText(dangenrous + "/" + sum);
    }


    public void setTargetData(UNIT_TYPE unit_type, double top_lowest, double top_average,
                              double top_highest, double bottom_lowest, double bottom_average,
                              double bottom_highest){
        switch (unit_type){
            case ITEM_UNIT_TYPE_STEP:
                tv_top_type_name.setText(
                        getResources().getString(R.string.health_data_step)
                );
                tv_top_lowest.setText(
                        String.format(getResources().getString(R.string.health_data_lowest),
                                top_lowest, "步")
                );
                tv_top_average.setText(
                        String.format(getResources().getString(R.string.health_data_average),
                        top_average, "步"));
                tv_top_highest.setText(
                        String.format(getResources().getString(R.string.health_data_highest),
                                top_highest, "步")
                );
                break;
            case ITEM_UNIT_TYPE_HEART:
                tv_top_type_name.setText(getResources().getString(R.string.health_data_heart));
                String heartStr = String.format(
                        getResources().getString(R.string.health_data_lowest),
                        top_lowest, "次/min");
                SpannableString ssHeart = new SpannableString(heartStr);

                if(top_lowest < WarningUtils.ITEM_HEART_LOWER){
                    ssHeart.setSpan(new ForegroundColorSpan(Color.RED),
                            2, heartStr.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
                tv_top_lowest.setText(ssHeart);

                heartStr = String.format(
                        getResources().getString(R.string.health_data_average),
                        top_average, "次/min");
                tv_top_average.setText(heartStr);

                heartStr = String.format(
                        getResources().getString(R.string.health_data_highest),
                        top_highest, "次/min");
                ssHeart = new SpannableString(heartStr);
                if(top_highest > WarningUtils.ITEM_HEART_HIGH){
                    ssHeart.setSpan(new ForegroundColorSpan(Color.RED),
                            2, heartStr.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
                tv_top_highest.setText(ssHeart);
                break;
            case ITEM_UNIT_TYPE_CRUORIN://血红蛋白
                tv_top_type_name.setText(getResources().getString(R.string.health_data_cruorin));
                String cruorinStr = String.format(
                        getResources().getString(R.string.health_data_lowest),
                        top_lowest, "%");
                SpannableString ssCruorin = new SpannableString(cruorinStr);

                if(top_lowest < WarningUtils.ITEM_CRUORIN_LOWER){
                    ssCruorin.setSpan(new ForegroundColorSpan(Color.RED),
                            2, cruorinStr.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
                tv_top_lowest.setText(ssCruorin);

                cruorinStr = String.format(
                        getResources().getString(R.string.health_data_average),
                        top_average, "%");
                tv_top_average.setText(cruorinStr);

                cruorinStr = String.format(
                        getResources().getString(R.string.health_data_highest),
                        top_highest, "%");
                ssCruorin = new SpannableString(cruorinStr);
                if(top_highest > WarningUtils.ITEM_CRUORIN_HIGH){
                    ssCruorin.setSpan(new ForegroundColorSpan(Color.RED),
                            2, cruorinStr.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
                tv_top_highest.setText(ssCruorin);
                break;
            case ITEM_UNIT_TYPE_SUGAR://血糖
                tv_top_type_name.setText(getResources().getString(R.string.health_data_sugar));
                String sugarStr = String.format(
                        getResources().getString(R.string.health_data_lowest),
                        top_lowest, "mk");
                SpannableString ssSugar = new SpannableString(sugarStr);

                if(top_lowest < WarningUtils.ITEM_SUGAR_LOWER){
                    ssSugar.setSpan(new ForegroundColorSpan(Color.RED),
                            2, sugarStr.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
                tv_top_lowest.setText(ssSugar);

                sugarStr = String.format(
                        getResources().getString(R.string.health_data_average),
                        top_average, "mk");
                tv_top_average.setText(sugarStr);

                sugarStr = String.format(
                        getResources().getString(R.string.health_data_highest),
                        top_highest, "mk");
                ssSugar = new SpannableString(sugarStr);
                if(top_highest > WarningUtils.ITEM_SUGAR_HIGHER){
                    ssSugar.setSpan(new ForegroundColorSpan(Color.RED),
                            2, sugarStr.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
                tv_top_highest.setText(ssSugar);
                break;
            case ITEM_UNIT_TYPE_PRESSURE:
                tv_top_type_name.setText(getResources().getString(R.string.health_data_systolic));
                String pressureStr = String.format(
                        getResources().getString(R.string.health_data_lowest),
                        top_lowest, "mk");
                SpannableString ssPresure = new SpannableString(pressureStr);

                if(top_lowest < WarningUtils.ITEM_PRESSURE_LOWER){
                    ssPresure.setSpan(new ForegroundColorSpan(Color.RED),
                            2, pressureStr.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
                tv_top_lowest.setText(ssPresure);

                pressureStr = String.format(
                        getResources().getString(R.string.health_data_average),
                        top_average, "mk");
                tv_top_average.setText(pressureStr);

                pressureStr = String.format(
                        getResources().getString(R.string.health_data_highest),
                        top_highest, "mk");
                ssPresure = new SpannableString(pressureStr);
                if(top_highest > WarningUtils.ITEM_PRESSURE_HIGHER){
                    ssPresure.setSpan(new ForegroundColorSpan(Color.RED),
                            2, pressureStr.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
                tv_top_highest.setText(ssPresure);

                //舒张压数值
                ll_statistic_bottom.setVisibility(View.VISIBLE);
                tv_bottom_type_name.setText(getResources().getString(R.string.health_data_release));
                pressureStr = String.format(
                        getResources().getString(R.string.health_data_lowest),
                        bottom_lowest, "mmHg");
                ssPresure = new SpannableString(pressureStr);

                if(bottom_lowest < WarningUtils.ITEM_PRESSURE_LOWER){
                    ssPresure.setSpan(new ForegroundColorSpan(Color.RED),
                            2, pressureStr.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
                tv_bottom_lowest.setText(ssPresure);

                pressureStr = String.format(
                        getResources().getString(R.string.health_data_average),
                        bottom_average, "mmHg");
                tv_bottom_average.setText(pressureStr);

                pressureStr = String.format(
                        getResources().getString(R.string.health_data_highest),
                        bottom_lowest, "mmHg");
                ssPresure = new SpannableString(pressureStr);
                if(bottom_highest > WarningUtils.ITEM_PRESSURE_HIGHER){
                    ssPresure.setSpan(new ForegroundColorSpan(Color.RED),
                            2, pressureStr.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
                tv_bottom_highest.setText(ssPresure);
                break;
        }
    }

    public void setTargetData(UNIT_TYPE unit_type, double lowest, double average, double highest){
        setTargetData(unit_type, lowest, average, highest, 0, 0, 0);
    }
}