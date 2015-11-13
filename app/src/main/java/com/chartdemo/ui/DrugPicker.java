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
 * @date 2015-11-13 17:21
 * @package com.chartdemo.ui.window
 * @description DrugPicker  TODO(界面功能描述)
 * @params TODO(进入界面传参描述)
 */
public class DrugPicker extends AbstractPopupWindow implements View.OnClickListener{

    private Context context;
    private WheelView wheelview1;
    private ArrayWheelAdapter<String> adapter1;

    private TextView cancel;
    private TextView submit;

    private String [] drugArray;

    private int currentDrug;

    private OnDrugSelected setOnClickListener;

    public DrugPicker(Context context) {
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
        setContentView(root);
        genericData();
    }
    private void genericData(){
        drugArray = new String[]{"高血压", "胰岛素", "尿嘧啶", "吗丁啉", "伟泰感冒灵", "糖浆", "降压药", "止咳散"};
        setData(drugArray);
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
        }
    }

    public void setData(final String [] data1){
        if (data1 != null) {
            wheelview1.setVisibility(View.VISIBLE);
            adapter1 = new ArrayWheelAdapter<String>(
                    context, data1);
            wheelview1.setViewAdapter(adapter1);
            wheelview1.addChangingListener(new WheelView.OnWheelChangedListener() {
                @Override
                public void onChanged(WheelView wheel, int oldValue,
                                      int newValue) {
                    currentDrug = newValue;
                }
            });
            wheelview1.setCurrentItem(0);
        } else {
            wheelview1.setVisibility(View.GONE);
        }
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.tv_submit:
                int pos1 = wheelview1.getCurrentItem();
                setOnClickListener.onDrugSelected(drugArray[pos1]);
                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    public interface OnDrugSelected{
        public void onDrugSelected(String drug);
    }
}