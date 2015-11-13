package com.chartdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.chartdemo.ui.markview.MarkView;
import com.chartdemo.ui.markview.PathView;


public class MarkViewActivity extends ActionBarActivity implements MarkView.OnValueChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathView(this));

//        MarkView markView = ((MarkView) findViewById(R.id.mk_view1));
//        markView.setValueChangeListener(this);
//
//        PathView pathView = new PathView(this);
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(100, 200);
//        pathView.setLayoutParams(params);

    }

    @Override
    public void onValueChange(float value) {
        Toast.makeText(this, " 选择的数值为 : " + value, Toast.LENGTH_SHORT).show();
    }
}
