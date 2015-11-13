package com.chartdemo.ui.markview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.chartdemo.R;

/**
 * @author yhw(email:yuhongwei@anhao.cn)
 * @date 2015-11-13 17:47
 * @package com.chartdemo.ui.markview
 * @description PathView  TODO(界面功能描述)
 * @params TODO(进入界面传参描述)
 */
public class PathView extends View {
    private final Path mPath = new Path();
    private final Matrix matrix = new Matrix();
    private final Bitmap bitmap;
    private Paint mPaint = null;

    // 放大镜的半径
    private static final int RADIUS = 80;
    // 放大倍数

    private static final int FACTOR = 2;
    private int mCurrentX, mCurrentY;

    public PathView(Context context) {
        super(context);
        mPath.addCircle(RADIUS, RADIUS, RADIUS, Path.Direction.CW);
        matrix.setScale(FACTOR, FACTOR);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.show);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath.addCircle(RADIUS, RADIUS, RADIUS, Path.Direction.CW);
        matrix.setScale(FACTOR, FACTOR);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shape_background_circle);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mCurrentX = (int) event.getX();
        mCurrentY = (int) event.getY();
        invalidate();
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 底图
        canvas.drawBitmap(bitmap, 0, 0, null);
        // 剪切
        canvas.translate(mCurrentX - RADIUS, mCurrentY - RADIUS);
        canvas.clipPath(mPath);
        // 画放大后的图
        canvas.translate(RADIUS - mCurrentX * FACTOR, RADIUS - mCurrentY
                * FACTOR);
        canvas.drawBitmap(bitmap, matrix, null);
    }
}