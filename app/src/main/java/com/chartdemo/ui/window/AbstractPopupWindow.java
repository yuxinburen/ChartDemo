package com.chartdemo.ui.window;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;

import com.chartdemo.R;


public abstract class AbstractPopupWindow extends PopupWindow {
	private Animation enterAnim;
	@SuppressWarnings("unused")
	private Animation exitAnim;

	private boolean isAnim = true;

	public AbstractPopupWindow(Context context) {
		super(context);
		enterAnim = AnimationUtils.loadAnimation(context,
                R.anim.side_bottom_enter);
		exitAnim = AnimationUtils.loadAnimation(context,
                R.anim.side_bottom_exit);
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.MATCH_PARENT);
		ColorDrawable dw = new ColorDrawable(0x34495E);
		setBackgroundDrawable(dw);
		setFocusable(true);
	}

	public void setAnim(boolean isAnim) {
		this.isAnim = isAnim;
	}

	@Override
	public void showAsDropDown(View anchor) {
		super.showAsDropDown(anchor);
		showEnterAnim();
	}

	@Override
	public void showAsDropDown(View anchor, int xoff, int yoff) {
		super.showAsDropDown(anchor, xoff, yoff);
		showEnterAnim();
	}

	@Override
	public void showAtLocation(View parent, int gravity, int x, int y) {
		super.showAtLocation(parent, gravity, x, y);
		showEnterAnim();
	}

	private void showEnterAnim() {
		if (!isAnim) {
			return;
		}
		((ViewGroup) getContentView()).getChildAt(0).startAnimation(enterAnim);
	}

	@Override
	public void setContentView(View contentView) {
		super.setContentView(contentView);
		final ViewGroup root = (ViewGroup) contentView;
		contentView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int height = root.getChildAt(0).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});
	}
}
