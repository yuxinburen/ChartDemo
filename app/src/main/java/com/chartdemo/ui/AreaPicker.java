package com.chartdemo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chartdemo.R;
import com.chartdemo.ui.wheelview.ArrayWheelAdapter;
import com.chartdemo.ui.wheelview.WheelView;
import com.chartdemo.ui.window.AbstractPopupWindow;

/**
 * @author yhw(email:yuhongwei@anhao.cn)
 * @date 2015-11-11 16:36
 * @package com.chartdemo.ui
 * @description AreaPicker  TODO(界面功能描述)
 * @params TODO(进入界面传参描述)
 */
public class AreaPicker extends AbstractPopupWindow implements View.OnClickListener {

    private Context context;
    private WheelView wheelview1;
    private WheelView wheelview2;
    private WheelView wheelView3;

    private ArrayWheelAdapter<String> adapter1;
    private ArrayWheelAdapter<String> adapter2;
    private ArrayWheelAdapter<String> adapter3;

    private TextView cancel;
    private TextView submit;

    private String [] provinceArray;
    private String [] cityArray;
    private String [] countyArray;

    private int currentProvince = 0;
    private int currentCity = 0;
    private int currentCounty = 0;

    private OnAreaSelected setOnClickListener;

    public AreaPicker(Context context) {
        super(context);
        this.context = context;
        View root = LayoutInflater.from(context).inflate(
        R.layout.layout_common_menu, null);
        cancel = (TextView) root.findViewById(R.id.tv_cancel);
        submit = (TextView) root.findViewById(R.id.tv_submit);
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);
        wheelview1 = (WheelView) root.findViewById(R.id.layout_common_menu_wheelView1);
        wheelview1.setVisibility(View.VISIBLE);
        wheelview2 = (WheelView) root.findViewById(R.id.layout_common_menu_wheelView2);
        wheelview2.setVisibility(View.VISIBLE);
        wheelView3 = (WheelView) root.findViewById(R.id.layout_common_menu_wheelView3);
        wheelView3.setVisibility(View.VISIBLE);
        setContentView(root);
        genericData();
    }
    private void genericData(){
        provinceArray = new String[]{"北京", "天津", "山东", "河北", "河南", "黑龙江", "陕西", "安徽"};
        cityArray = new String[]{"海淀区", "朝阳区", "密云县", "丰台区"};
        countyArray = new String[]{ "门头沟", "大庆"};
        setData(provinceArray, cityArray, countyArray);
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
        }
    }

    public void setData(final String [] data1, String [] data2, String [] data3){
        if (data1 != null) {
            wheelview1.setVisibility(View.VISIBLE);
            adapter1 = new ArrayWheelAdapter<String>(
            context, data1);
            wheelview1.setViewAdapter(adapter1);
            wheelview1.addChangingListener(new WheelView.OnWheelChangedListener() {
                @Override
                public void onChanged(WheelView wheel, int oldValue,
                    int newValue) {

                    currentProvince = newValue;

                    cityArray = new String[]{ "海淀", "朝阳", "丰台"};
                    adapter3 = new ArrayWheelAdapter<String>(context, cityArray);
                    wheelView3.setViewAdapter(adapter3);
                }
            });
            wheelview1.setCurrentItem(0);
        } else {
            wheelview1.setVisibility(View.GONE);
        }

        if (data2 != null) {
            wheelview2.setVisibility(View.VISIBLE);
            adapter2 = new ArrayWheelAdapter<String>(
            context, data2);
            wheelview2.setViewAdapter(adapter2);
            wheelview2.addChangingListener(new WheelView.OnWheelChangedListener() {
                @Override
                public void onChanged(WheelView wheel, int oldValue,
                            int newValue) {
                    currentCity = newValue;//下标
                    countyArray = new String[]{ "平阴", "长清", "历下", "历城"};
                    adapter3 = new ArrayWheelAdapter<String>(context, countyArray);
                    wheelView3.setViewAdapter(adapter3);
                }
            });
        } else {
            wheelview2.setVisibility(View.GONE);
        }

        if (data3 != null) {
            wheelView3.setVisibility(View.VISIBLE);
            adapter3 = new ArrayWheelAdapter<String>(context, data3);
            wheelView3.setViewAdapter(adapter3);
            wheelView3.addChangingListener(new WheelView.OnWheelChangedListener() {
                @Override
                public void onChanged(WheelView wheel, int oldValue,
                    int newValue) {
                    currentCounty = newValue;
                }
            });
        } else {
                wheelView3.setVisibility(View.GONE);
        }
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.tv_submit:
                int pos1 = wheelview1.getCurrentItem();
                int pos2 = wheelview2.getCurrentItem();
                int pos3 = wheelView3.getCurrentItem();
                setOnClickListener.onAreaSelected(pos1, pos2, pos3);
                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    public interface OnAreaSelected{
        public void onAreaSelected(int province,int city, int county);
    }
}