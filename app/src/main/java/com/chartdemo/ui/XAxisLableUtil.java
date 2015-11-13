package com.chartdemo.ui;

import java.util.ArrayList;

/**
 * @author yhw(email:yuhongwei@anhao.cn)
 * @date 2015-11-09 16:23
 * @package com.chartdemo.ui
 * @description XAxisLableUtil  TODO(界面功能描述)
 * @params TODO(进入界面传参描述)
 */
public class XAxisLableUtil {

    private long mStart;
    private long mEnd;

    /**
     * 传入开始时间,和结束时间的时间戳;转换成需要展示的横轴展示格式返回
     * @param mStart
     * @param mEnd
     */
    public static void getXValues(long mStart, long mEnd){
        if(mStart >= mEnd){
            throw new IllegalArgumentException(" the mStart time must be smaller mEnd time");
        }
        ArrayList<String> arrayList = new ArrayList<>();
        long durTime = mEnd - mStart;
        long dayTime = 60*60*24*1000;
        int dayNum = (int)(durTime/dayTime);
        if(dayNum == 0){//同一天

        }else if(dayNum > 0 && dayNum <= 7){
            //一周以内
        }else if(dayNum > 7 && dayNum <= 30){
            //30天以内
        }else if(dayNum > 30 && dayNum <= 90){

        }else if(dayNum > 90 && dayNum < 300){

        }
    }
} 