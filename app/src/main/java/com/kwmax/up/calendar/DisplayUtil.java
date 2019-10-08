package com.kwmax.up.calendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by apple on 17/2/14.
 */

public class DisplayUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转换成px
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转换成sp
     */
    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
    /**
     * 返回屏幕的宽度
     *
     * @param context 当前context
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getApplicationContext().getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    /**
     * 返回屏幕的宽度
     *
     * @param context 当前context
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getApplicationContext().getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }

    /**
     * RGBA转ARGB，
     *
     * @param rgbaStr 八位rgba字符串
     */
    public static int rgba2argb(String rgbaStr) {
        if (rgbaStr.length() == 9) {//#F65A544C
            String red = rgbaStr.substring(1, 3);
            String green = rgbaStr.substring(3, 5);
            String blue = rgbaStr.substring(5, 7);
            String alpha = rgbaStr.substring(7);
            return Color.argb(Integer.parseInt(alpha, 16), Integer.parseInt(red, 16), Integer.parseInt(green, 16), Integer.parseInt(blue, 16));
        } else if (rgbaStr.length() == 7) {
            return Color.parseColor(rgbaStr);
        }
        throw new IllegalArgumentException("Unknown color");
    }

    /**
     * 计算指定的 View 在屏幕中的坐标。
     */
    public static RectF calcViewScreenLocation(View view) {
        if(view == null){
            return new RectF(0,0,0,0);
        }
        int[] location = new int[2];
        // 获取控件在屏幕中的位置，返回的数组分别为控件左顶点的 x、y 的值
        view.getLocationOnScreen(location);
        return new RectF(location[0], location[1], location[0] + view.getWidth(),
                location[1] + view.getHeight());
    }
}
