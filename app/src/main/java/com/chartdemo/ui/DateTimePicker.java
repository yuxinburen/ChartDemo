package com.chartdemo.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chartdemo.R;
import com.chartdemo.ui.wheelview.ArrayWheelAdapter;
import com.chartdemo.ui.wheelview.WheelView;
import com.chartdemo.ui.window.AbstractPopupWindow;

import java.util.Calendar;

/**
 * @author yhw(email:yuhongwei@anhao.cn)
 * @date 2015-11-11 11:41
 * @package com.chartdemo.ui
 * @description DataTimePicker  TODO(界面功能描述)
 * @params TODO(进入界面传参描述)
 */
public class DateTimePicker extends AbstractPopupWindow implements View.OnClickListener {

    private Context context;
    private WheelView wheelview1;
    private WheelView wheelview2;
    private WheelView wheelView3;
    private WheelView wheelView4;
    private WheelView wheelView5;

    private ArrayWheelAdapter<String> adapter2;
    private ArrayWheelAdapter<String> adapter3;

    private TextView cancel;
    private TextView submit;

    private String [] yearArray;
    private String [] monthArray;
    private String [] dayArray;
    private String [] hourArray;
    private String [] minArray;


    private Calendar calendar = Calendar.getInstance();
    private int monthMaxDay = 31;
    private int currentYear = 1970;
    private int currentMonth = 1;
    private int currentDay = 1;


    private OnDateTimeSelected setOnClickListener;

    public DateTimePicker(Context context) {
        super(context);
        this.context = context;
        View root = LayoutInflater.from(context).inflate(
                R.layout.layout_common_menu, null);
        cancel = (TextView) root.findViewById(R.id.tv_cancel);
        submit = (TextView) root.findViewById(R.id.tv_submit);
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);
        wheelview1 = (WheelView) root
                .findViewById(R.id.layout_common_menu_wheelView1);
        wheelview1.setVisibility(View.VISIBLE);
        wheelview2 = (WheelView) root
                .findViewById(R.id.layout_common_menu_wheelView2);
        wheelview2.setVisibility(View.VISIBLE);
        wheelView3 = (WheelView) root
                .findViewById(R.id.layout_common_menu_wheelView3);
        wheelView3.setVisibility(View.VISIBLE);
        wheelView4 = (WheelView) root
                .findViewById(R.id.layout_common_menu_wheelView4);
        wheelView4.setVisibility(View.VISIBLE);
        wheelView5 = (WheelView) root
                .findViewById(R.id.layout_common_menu_wheelView5);
        wheelView5.setVisibility(View.VISIBLE);
        setContentView(root);

        genericData();
    }

    public static final int YEAR_NUM = 60;
    public static final int YEAR_START = 1970;
    private void genericData(){

        yearArray = new String[YEAR_NUM];
        for (int n = 0, i = YEAR_START; i < YEAR_START + YEAR_NUM; i++, n++){
            yearArray[n] = String.valueOf(i);
        }

        monthArray = new String[12];
        for (int i = 1; i <= 12; i++){
            monthArray[i-1] = String.valueOf(i);
        }

        hourArray = new String[24];
        for (int i = 0; i<24; i++){
            if(i<10){
                hourArray[i] = "0"+i;
            }else {
                hourArray[i] = String.valueOf(i);
            }
        }

        minArray = new String[60];
        for (int i = 0; i<60;i++){
            if(i<10){
                minArray[i] = "0"+i;
            }else {
                minArray[i] = String.valueOf(i);
            }
        }

        int monthMaxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        dayArray = new String[monthMaxDay];
        for (int i = 1; i <= monthMaxDay; i++){
            dayArray[i-1] = String.valueOf(i);
        }
        setData(yearArray, monthArray, dayArray, hourArray, minArray);
    }

    /**
     * 设置wheelView列是否可见
     * @param num
     * @param flag
     */
    public void setVisibility(int num, boolean flag){
        switch (num){
            case 1:
                wheelview1.setVisibility(View.GONE);
                break;
            case 2:
                wheelview2.setVisibility(View.GONE);
                break;
            case 3:
                wheelView3.setVisibility(View.GONE);
                break;
            case 4:
                wheelView4.setVisibility(View.GONE);
                break;
            case 5:
                wheelView5.setVisibility(View.GONE);
                break;
        }
    }

    public void setData(final String [] data1, String [] data2, String [] data3, String [] data4,
                        String [] data5){

        int yearIndex = calendar.get(Calendar.YEAR);
        int monthIndex = calendar.get(Calendar.MONTH);
        int dayIndex = calendar.get(Calendar.DAY_OF_MONTH);
        int hourIndex = calendar.get(Calendar.HOUR_OF_DAY);
        int minIndex = calendar.get(Calendar.MINUTE);
        int index = -1;

        if (data1 != null) {
            wheelview1.setVisibility(View.VISIBLE);
            ArrayWheelAdapter<String> adapter1 = new ArrayWheelAdapter<String>(
                    context, data1);
            wheelview1.setViewAdapter(adapter1);
            index = indexOf(data1, yearIndex+"");
            wheelview1.addChangingListener(new WheelView.OnWheelChangedListener() {
                @Override
                public void onChanged(WheelView wheel, int oldValue,
                                      int newValue) {

                    currentYear = newValue;
                }
            });
            wheelview1.setCurrentItem(index == -1 ? data1.length/2 : index);
        } else {
            wheelview1.setVisibility(View.GONE);
        }

        if (data2 != null) {
            wheelview2.setVisibility(View.VISIBLE);
            adapter2 = new ArrayWheelAdapter<String>(
                    context, data2);
            wheelview2.setViewAdapter(adapter2);
            index = indexOf(data2, monthIndex+"");
            wheelview2.setCurrentItem(index == -1 ? data2.length/2 : index);
            wheelview2.addChangingListener(new WheelView.OnWheelChangedListener() {
                @Override
                public void onChanged(WheelView wheel, int oldValue,
                                      int newValue) {

                    currentMonth = newValue;//下标

                    calendar.set(Calendar.YEAR, Integer.parseInt(yearArray[currentYear]));
                    calendar.set(Calendar.MONTH, currentMonth);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    monthMaxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                    Log.i("TAG", currentMonth + " , " + monthMaxDay);


                    dayArray = new String[monthMaxDay];
                    for (int i = 1; i <= monthMaxDay; i++) {
                        dayArray[i - 1] = String.valueOf(i);
                    }
                    adapter3 = new ArrayWheelAdapter<String>(context, dayArray);
                    wheelView3.setViewAdapter(adapter3);
                }
            });
        } else {
            wheelview2.setVisibility(View.GONE);
        }

        if (data3 != null) {
            wheelView3.setVisibility(View.VISIBLE);
            adapter3 = new ArrayWheelAdapter<String>(
                    context, data3);
            wheelView3.setViewAdapter(adapter3);
            index = indexOf(data3, dayIndex+"");
            wheelView3.setCurrentItem(index == -1 ? data3.length/2 : index);
            wheelView3.addChangingListener(new WheelView.OnWheelChangedListener() {
                @Override
                public void onChanged(WheelView wheel, int oldValue,
                                      int newValue) {
                    currentDay = newValue;
                }
            });
        } else {
            wheelView3.setVisibility(View.GONE);
        }

        if (data4 != null) {
            wheelView4.setVisibility(View.VISIBLE);
            ArrayWheelAdapter<String> adapter4 = new ArrayWheelAdapter<String>(
                    context, data4);
            wheelView4.setViewAdapter(adapter4);
            index = indexOf(data4, hourIndex+"");
            wheelView4.setCurrentItem(index == -1 ? data4.length/2 : index);
        } else {
            wheelView4.setVisibility(View.GONE);
        }

        if (data5 != null) {
            wheelView5.setVisibility(View.VISIBLE);
            ArrayWheelAdapter<String> adapter5 = new ArrayWheelAdapter<String>(
                    context, data5);
            wheelView5.setViewAdapter(adapter5);
            index = indexOf(data5, minIndex+"");
            wheelView5.setCurrentItem(index == -1 ? data5.length/2 : index);
        } else {
            wheelView5.setVisibility(View.GONE);
        }
    }

    private int indexOf(String [] data, String key){
        for (int i = 0; i < data.length; i++){
            if(data[i].equals(key)){
                return i;
            }
            continue;
        }
        return -1;
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.tv_submit:
                int pos1 = wheelview1.getCurrentItem();
                int pos2 = wheelview2.getCurrentItem();
                int pos3 = wheelView3.getCurrentItem();
                setOnClickListener.onDateTimeSelected(pos1, pos2, pos3);
                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    public interface OnDateTimeSelected{
        public void onDateTimeSelected(int year,int month, int day);
    }
}