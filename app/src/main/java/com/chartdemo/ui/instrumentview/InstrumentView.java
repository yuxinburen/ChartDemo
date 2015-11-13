package com.chartdemo.ui.instrumentview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

/**
 * @author yhw(email:yuhongwei@anhao.cn)
 * @date 2015-11-12 16:58
 * @package com.chartdemo.ui.instrumentview
 * @description InstrumentView  TODO(界面功能描述)
 * @params TODO(进入界面传参描述)
 */
public class InstrumentView extends View {

    private static final String TAG = "InstrumentView";

    private static final double PER_ARC = Math.PI / 180;

    private float mDensity;

    private int mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom;
    private int mWidth, mHeight;

    //半径
    private int mRadius= 0;

    //初始偏移角度
    protected float mOffsetAngle = 0.0f;//180;
    protected float mInitOffsetAngle = 0.0f;

    private Paint bgPaint = null;
    private Paint pointPaint = null;
    private Paint linePaint = null;
    private Paint textPaint = null;

    private int mMaxValue = 180;
    private int mValue = 90;

    private float xStart, yStart;
    private float xEnd, yEnd;
    private int TYPE_ALL_LENGHT = 40;
    private int TYPE_HALF_LENGTH = 20;
    private static final int ITEM_TYPE_HALF = 5;
    private static final int ITEM_TYPE_ALL = 10;
    private static final int TEXT_SIZE = 14;
    private static final int TEXT_WIDTH = 20;
    private static final int TEXT_HEIGHT = 20;


    private int mMinVelocity;
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    public InstrumentView(Context context) {
        this(context, null);
    }

    public InstrumentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mDensity = getContext().getResources().getDisplayMetrics().density;
        setBackgroundDrawable(createBackground());
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(Color.argb(0x44, 0xff, 0x66, 0x77));
        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setColor(Color.BLACK);
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.BLACK);
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(TEXT_SIZE);
        mScroller = new Scroller(getContext());
        mMinVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();

    }

    private int mLastX = 0;
    private int mMove = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int xPosition = (int) event.getX();

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        switch (action){
            case MotionEvent.ACTION_DOWN:

                mScroller.forceFinished(true);

                mLastX = xPosition;
                mMove = 0;

                break;
            case MotionEvent.ACTION_MOVE:
                mMove += (mLastX - xPosition);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                countMoveEnd();
                countVelocityTracker(event);
                return false;
//                break;
            default:
                break;
        }
        mLastX = xPosition;
        return super.onTouchEvent(event);
    }

    private void countMoveEnd() {
//        int roundMove = Math.round(mMove / (mLineDivider * mDensity));
//        mValue = mValue + roundMove;
//        mValue = mValue <= 0 ? 0 : mValue;
//        mValue = mValue > mMaxValue ? mMaxValue : mValue;
//
//        mLastX = 0;
//        mMove = 0;
//
//        notifyValueChange();
        postInvalidate();
    }

    private void countVelocityTracker(MotionEvent event) {
        mVelocityTracker.computeCurrentVelocity(1000);
        float xVelocity = mVelocityTracker.getXVelocity();
        if (Math.abs(xVelocity) > mMinVelocity) {
            mScroller.fling(0, 0, (int) xVelocity, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
        }
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mWidth = getWidth();//view 的宽度
        mHeight = getHeight();//view 的高度
        mRadius = mWidth/2;

        super.onLayout(changed, left, top, right, bottom);
    }

    private GradientDrawable createBackground() {
        float strokeWidth = 1 * mDensity; // 边框宽度
        float roundRadius = 3 * mDensity; // 圆角半径
        int strokeColor = Color.parseColor("#00000000");// 边框颜色

        //left top right bottom
        setPadding((int)strokeWidth, 20, (int)strokeWidth, 0);

        int colors[] = { 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF };// 分别为开始颜色，中间夜色，结束颜色
        GradientDrawable bgDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);// 创建drawable
        bgDrawable.setCornerRadius(roundRadius);
        bgDrawable.setStroke((int)strokeWidth, strokeColor);
        return bgDrawable;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        canvas.drawLine(mWidth/2, mHeight, mWidth/4, mHeight/2, linePaint);
    }


    private void drawBg(Canvas canvas) {
        canvas.drawCircle(mWidth/2, mHeight, mRadius, bgPaint);
        canvas.drawCircle(mWidth/2, mHeight, 8, pointPaint);
        for (int i = 0; i <= 90; i++){//左半部分
            xStart = mWidth/2 -(float)( Math.cos(i*PER_ARC) * mRadius) ;
            yStart = mHeight - (float)(Math.sin(i*PER_ARC) * mRadius );
            if(i % ITEM_TYPE_ALL == 0){
                xEnd = mWidth/2 - (float) ( Math.cos(i*PER_ARC) * (mRadius - TYPE_ALL_LENGHT));
                yEnd = mHeight - (float)(Math.sin(i*PER_ARC) * (mRadius - TYPE_ALL_LENGHT ));
            }else {
                xEnd = mWidth/2 - (float) ( Math.cos(i*PER_ARC) * (mRadius - TYPE_HALF_LENGTH));
                yEnd = mHeight - (float)(Math.sin(i*PER_ARC) * (mRadius - TYPE_HALF_LENGTH ));
            }
            canvas.drawLine(xStart, yStart, xEnd , yEnd, pointPaint);

            if(i% 30 == 0){
                canvas.drawText(String.valueOf(i), xEnd, yEnd + TEXT_HEIGHT/2, textPaint);
            }
        }

        //右半部分
        for(int i = 91; i <= 180; i++){
            xStart = mWidth/2 + mRadius * (float)Math.cos((180-i)*PER_ARC);
            yStart = mHeight - mRadius * (float)Math.sin((180-i)*PER_ARC);
            if(i % ITEM_TYPE_ALL == 0){
                xEnd = mWidth/2 + (mRadius-TYPE_ALL_LENGHT)*(float)Math.cos((180-i)*PER_ARC);
                yEnd = mHeight - (mRadius-TYPE_ALL_LENGHT) * (float)Math.sin((180-i)*PER_ARC);
            }else {
                xEnd = mWidth/2 + (mRadius-TYPE_HALF_LENGTH)*(float)Math.cos((180-i)*PER_ARC);
                yEnd = mHeight - (mRadius-TYPE_HALF_LENGTH) * (float)Math.sin((180-i)*PER_ARC);
            }
            canvas.drawLine(xStart, yStart, xEnd , yEnd, pointPaint);

            if(i% 30 == 0){
                canvas.drawText(String.valueOf(i), xEnd - TEXT_WIDTH, yEnd + TEXT_HEIGHT/2, textPaint);
            }
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            if (mScroller.getCurrX() == mScroller.getFinalX()) { // over
                countMoveEnd();
            } else {
                int xPosition = mScroller.getCurrX();
                mMove += (mLastX - xPosition);
//                changeMoveAndValue();
                mLastX = xPosition;
            }
        }
    }
}