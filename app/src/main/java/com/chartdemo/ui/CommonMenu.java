package com.chartdemo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chartdemo.R;
import com.chartdemo.ui.wheelview.WheelView;
import com.chartdemo.ui.window.AbstractPopupWindow;

/**
 * @author yhw(email:yuhongwei@anhao.cn)
 * @date 2015-11-11 09:45
 * @package com.chartdemo.uis
 * @description CommonMenu  TODO(界面功能描述)
 * @params TODO(进入界面传参描述)
 */
public class CommonMenu extends AbstractPopupWindow implements View.OnClickListener {
    private Context context;
    private TextView cancel, ok;
    private WheelView wheelView1;
    private WheelView wheelView2;
    private WheelView wheelView3;
    private WheelView wheelView4;
    private WheelView wheelView5;


//    private WheelView wheelView6;

//    private String curYear = "2000年";
//    private String[] realDays;
//    private int maxDayCount = 31;
//    private int maxYearIndex;// 可选择的最大的年份
//    private int maxMonthIndex;// 可选择的最大的月份
    private OnMenuSelectedListener listener;
    private onProvinceSelectedListener provinceSelectedListener;

//    private int dayIndex;// 当前日期天的下标

    public CommonMenu(Context context) {
        super(context);
        this.context = context;
        View root = LayoutInflater.from(context).inflate(
                R.layout.layout_common_menu, null);
        cancel = (TextView) root.findViewById(R.id.tv_cancel);
        ok = (TextView) root.findViewById(R.id.tv_submit);
        cancel.setOnClickListener(this);
        ok.setOnClickListener(this);
        wheelView1 = (WheelView) root
                .findViewById(R.id.layout_common_menu_wheelView1);
        wheelView1.setVisibleItems(5);
        wheelView2 = (WheelView) root
                .findViewById(R.id.layout_common_menu_wheelView2);
        wheelView2.setVisibleItems(5);
        wheelView3 = (WheelView) root
                .findViewById(R.id.layout_common_menu_wheelView3);
        wheelView3.setVisibleItems(5);
        wheelView4 = (WheelView) root
                .findViewById(R.id.layout_common_menu_wheelView4);
        wheelView4.setVisibleItems(5);
        wheelView5 = (WheelView) root
                .findViewById(R.id.layout_common_menu_wheelView5);
        wheelView5.setVisibleItems(5);
//        wheelView6 = (WheelView) root
//                .findViewById(R.id.layout_common_menu_wheelView6);
//        wheelView6.setVisibleItems(5);
        setContentView(root);
    }

//    /**
//     * 设置日期时间数据
//     *
//     * @param data1
//     *            年
//     * @param data2
//     *            月
//     * @param data3
//     *            日
//     * @param data4
//     *            时
//     * @param data5
//     *            分
//     * @param data6
//     *            秒
//     */
//    public void setData(String[] data1, String[] data2, String[] data3,
//                        String[] data4, String[] data5, String[] data6) {
//        String dateTime = DateTimeUtils.getWesternNowDate();
//        String[] dateArray = dateTime.split("-");
//        String year = dateArray[0] + "年"; // 年
//        String month = dateArray[1] + "月";// 月
//        String day = dateArray[2] + "日";// 日
//        maxYearIndex = index(year, data1);
//        maxMonthIndex = index(month, data2);
//        dayIndex = index(day, data3);
//
//        // 设置年
//        if (data1 != null) {// 年
//            wheelView1.setVisibility(View.VISIBLE);
//            final ArrayWheelAdapter<String> adapter1 = new ArrayWheelAdapter<String>(
//                    context, data1);
//            wheelView1.setViewAdapter(adapter1);
//            wheelView1.addChangingListener(new OnWheelChangedListener() {
//                @Override
//                public void onChanged(WheelView wheel, int oldValue,
//                                      int newValue) {
//                    curYear = adapter1.getItemText(newValue).toString()
//                            .replace("年", "");
//
//                    // 选择的年大于当前年份,将年指定为当前年份
//                    if (newValue > maxYearIndex) {
//                        wheelView1.setCurrentItem(maxYearIndex);
//                    }
//                    // 判断所选年份的二月份 有多少天
//                    if (wheelView2.getCurrentItem() == 1) {
//                        if (isLeapYear(curYear)) {
//                            maxDayCount = 29;
//                        } else {
//                            maxDayCount = 28;
//                        }
//                        if (wheelView3.getCurrentItem() >= maxDayCount) {
//                            wheelView3.setCurrentItem(maxDayCount - 1);
//                        }
//                    }
//                    // 所选年份大于等于当前年份时,指定月份与日期为当前月份和日期
//                    if (newValue >= maxYearIndex
//                            && wheelView2.getCurrentItem() >= maxMonthIndex) {
//                        wheelView2.setCurrentItem(maxMonthIndex);
//                        wheelView3.setCurrentItem(dayIndex);
//                    }
//                }
//            });
//            // 设置年为当前年份
//            wheelView1.setCurrentItem(maxYearIndex);
//        } else {
//            wheelView1.setVisibility(View.GONE);
//        }
//
//        // 设置月
//        if (data2 != null) {// 月
//            wheelView2.setVisibility(View.VISIBLE);
//            ArrayWheelAdapter<String> adapter2 = new ArrayWheelAdapter<String>(
//                    context, data2);
//            wheelView2.setViewAdapter(adapter2);
//            wheelView2.addChangingListener(new OnWheelChangedListener() {
//                @Override
//                public void onChanged(WheelView wheel, int oldValue,
//                                      int newValue) {// 选择的下标
//                    // 获得当前月份的最大天数值
//                    maxDayCount = getRealDays(newValue);
//                    // 如果所选年月大于等于当前年月,指定日期最大只能是当天
//                    if (wheelView1.getCurrentItem() >= maxYearIndex
//                            && wheelView2.getCurrentItem() >= maxMonthIndex) {
//                        wheelView2.setCurrentItem(maxMonthIndex);
//                        wheelView3.setCurrentItem(dayIndex);
//                    }
//                    // 如果所选日期大于等于了当前月的最大的天数,指定为最大天数
//                    if (wheelView3.getCurrentItem() >= maxDayCount) {
//                        wheelView3.setCurrentItem(maxDayCount - 1);
//                    }
//                }
//            });
//            // 设置年为当前月份
//            wheelView2.setCurrentItem(maxMonthIndex);
//        } else {
//            wheelView2.setVisibility(View.GONE);
//        }
//
//        // 设置日
//        if (data3 != null) {// 日
//            wheelView3.setVisibility(View.VISIBLE);
//            ArrayWheelAdapter<String> adapter3 = new ArrayWheelAdapter<String>(
//                    context, data3);
//            wheelView3.setViewAdapter(adapter3);
//            wheelView3.addChangingListener(new OnWheelChangedListener() {
//                @Override
//                public void onChanged(WheelView wheel, int oldValue,
//                                      int newValue) {
//                    // 如果所选日期大于当天日期,则指定为当前日期
//                    if (wheelView1.getCurrentItem() >= maxYearIndex
//                            && wheelView2.getCurrentItem() >= maxMonthIndex
//                            && newValue > dayIndex) {
//                        wheelView3.setCurrentItem(dayIndex);
//                    }
//
//                    if (newValue >= maxDayCount
//                            || (oldValue == newValue && newValue != 1)) {
//                        wheelView3.setCurrentItem(maxDayCount - 1);
//                    }
//                }
//            });
//            // 设置年为当前日
//            wheelView3.setCurrentItem(dayIndex);
//        } else {
//            wheelView3.setVisibility(View.GONE);
//        }
//
//        // 选择并设置小时
//        if (data4 != null) {// 时
//            wheelView4.setVisibility(View.VISIBLE);
//            ArrayWheelAdapter<String> adapter4 = new ArrayWheelAdapter<String>(
//                    context, data4);
//            wheelView4.setViewAdapter(adapter4);
//            wheelView4.setCurrentItem(data4.length / 2);
//        } else {
//            wheelView4.setVisibility(View.GONE);
//        }
//
//        // 设置分钟
//        if (data5 != null) {// 分
//            wheelView5.setVisibility(View.VISIBLE);
//            ArrayWheelAdapter<String> adapter5 = new ArrayWheelAdapter<String>(
//                    context, data5);
//            wheelView5.setViewAdapter(adapter5);
//            wheelView5.setCurrentItem(data5.length / 2);
//        } else {
//            wheelView5.setVisibility(View.GONE);
//        }
//
//        // 设置秒
//        if (data6 != null) {// 秒
//            wheelView6.setVisibility(View.VISIBLE);
//            ArrayWheelAdapter<String> adapter6 = new ArrayWheelAdapter<String>(
//                    context, data6);
//            wheelView6.setViewAdapter(adapter6);
//            wheelView6.setCurrentItem(data6.length / 2);
//        } else {
//            wheelView6.setVisibility(View.GONE);
//        }
//    }


//    private int index(String date, String[] data) {
//        for (int i = 0; i < data.length; i++) {
//            if (date.equals(data[i])) {
//                return i;
//            } else {
//                continue;
//            }
//        }
//        return -1;
//    }

//    public void setData(String[] data1, String[] data2, String[] data3,
//                        int firstViewIndex) {
//        if (data1 != null) {
//            wheelView1.setVisibility(View.VISIBLE);
//            ArrayWheelAdapter<String> adapter1 = new ArrayWheelAdapter<String>(
//                    context, data1);
//            wheelView1.setViewAdapter(adapter1);
//            if (data1.length > firstViewIndex + 1) {
//                wheelView1.setCurrentItem(firstViewIndex);
//            }
//        } else {
//            wheelView1.setVisibility(View.GONE);
//        }
//
//        if (data2 != null) {
//            wheelView2.setVisibility(View.VISIBLE);
//            ArrayWheelAdapter<String> adapter2 = new ArrayWheelAdapter<String>(
//                    context, data2);
//            wheelView2.setViewAdapter(adapter2);
//            wheelView2.setCurrentItem(data2.length / 2);
//        } else {
//            wheelView2.setVisibility(View.GONE);
//        }
//
//        if (data3 != null) {
//            wheelView3.setVisibility(View.VISIBLE);
//            ArrayWheelAdapter<String> adapter3 = new ArrayWheelAdapter<String>(
//                    context, data3);
//            wheelView3.setViewAdapter(adapter3);
//            wheelView3.setCurrentItem(data3.length / 2);
//        } else {
//            wheelView3.setVisibility(View.GONE);
//        }
//    }


//    /**
//     * 设置省份和城市
//     *
//     * @param city
//     */
//    ArrayWheelAdapter<String> provinceAdapter;
//    ArrayWheelAdapter<String> cityAdapter;
//
//    public void setData(String[] provinceList, String[] cityList) {
//        if (provinceList != null) {
//            wheelView2.setVisibility(View.VISIBLE);
//            provinceAdapter = new ArrayWheelAdapter<String>(context,
//                    provinceList);
//            wheelView2.setViewAdapter(provinceAdapter);
//            //wheelView2.setCurrentItem(provinceList.length / 2);
//        } else {
//            wheelView2.setVisibility(View.GONE);
//        }
//
//        if (cityList != null) {
//            wheelView4.setVisibility(View.VISIBLE);
//            cityAdapter = new ArrayWheelAdapter<String>(context, cityList);
//            wheelView4.setViewAdapter(cityAdapter);
//        } else {
//            wheelView4.setVisibility(View.GONE);
//        }
//    }

//    public void setOnMenuSelectedListener(OnMenuSelectedListener choiceListener) {
//        this.listener = choiceListener;
//    }
//
//    public void setOnProvinceSelectedListener(
//            onProvinceSelectedListener provinceListener) {
//        this.provinceSelectedListener = provinceListener;
//
//        wheelView2.addChangingListener(new OnWheelChangedListener() {
//            @Override
//            public void onChanged(WheelView wheel, int oldValue, int newValue) {
//                provinceSelectedListener.onProvinceItemSelected(newValue);
//            }
//        });
//    }



//    /**
//     //     * 设置药物名称
//     //     *
//     //     * @param drug
//     //     */
//    public void setData(String[] drug) {
//        if (drug != null) {
//            wheelView1.setVisibility(View.VISIBLE);
//            ArrayWheelAdapter<String> drugAdapter = new ArrayWheelAdapter<String>(
//                    context, drug);
//            wheelView1.setViewAdapter(drugAdapter);
//            wheelView1.setCurrentItem(drug.length / 2);
//        } else {
//            wheelView1.setVisibility(View.GONE);
//        }
//    }



    @Override
    public void onClick(View v) {
        if (listener == null)
            return;
        switch (v.getId()) {
            case R.id.tv_submit:
                int pos1 = wheelView1.getCurrentItem();
                int pos2 = wheelView2.getCurrentItem();
                int pos3 = wheelView3.getCurrentItem();
                int pos4 = wheelView4.getCurrentItem();
                int pos5 = wheelView5.getCurrentItem();
//                int pos6 = wheelView6.getCurrentItem();
                listener.onMenuSelected(pos1, pos2, pos3, pos4, pos5);
                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    public interface OnMenuSelectedListener {
        void onMenuSelected(int item1, int item2, int item3, int item4, int item5);
    }

    public interface onProvinceSelectedListener {
        void onProvinceItemSelected(int position);
    }

    public void setSelectedListener(OnMenuSelectedListener selectedListener){
        this.listener = selectedListener;
    }

    public onProvinceSelectedListener getProvinceSelectedListener() {
        return provinceSelectedListener;
    }

    public void setProvinceSelectedListener(onProvinceSelectedListener provinceSelectedListener) {
        this.provinceSelectedListener = provinceSelectedListener;
    }
}
