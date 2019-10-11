//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kwmax.up.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout.LayoutParams;

/**
 * Function:具体使用方法参照：
 * http://blog.csdn.net/zhaihaohao1/article/details/76619283
 * auth：liming on 2017/8/3 09:25
 * mail：liming@wxchina.com
 */
public class UltimateBar {
    private Activity activity;

    public UltimateBar(Activity activity) {
        this.activity = activity;
    }

    public void setColorBar(@ColorInt int color, int alpha) {
        Window window;
        int alphaColor;
        if (VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window = this.activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            alphaColor = alpha == 0 ? color : this.calculateColor(color, alpha);
            window.setStatusBarColor(alphaColor);
            //  window.setNavigationBarColor(alphaColor);
        }
//        else if (VERSION.SDK_INT >= 19) {
//            window = this.activity.getWindow();
//            window.addFlags(67108864);
//            alphaColor = alpha == 0 ? color : this.calculateColor(color, alpha);
//            ViewGroup decorView = (ViewGroup) window.getDecorView();
//            decorView.addView(this.createStatusBarView(this.activity, alphaColor));
//            if (this.navigationBarExist(this.activity)) {
//                decorView.addView(this.createNavBarView(this.activity, alphaColor));
//                window.addFlags(134217728);
//            }
//
//            this.setRootView(this.activity, true);
//        }

    }

    public void setColorBar(@ColorInt int color) {
        this.setColorBar(color, 0);
    }

    public void setColorBarForDrawer(@ColorInt int color, int alpha) {
        Window window;
        ViewGroup decorView;
        int alphaColor;
        if (VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window = this.activity.getWindow();
            decorView = (ViewGroup) window.getDecorView();
            alphaColor = 1280;
            if (this.navigationBarExist(this.activity)) {
                alphaColor |= 512;
            }

            decorView.setSystemUiVisibility(alphaColor);
            //2017-09-29 对所有的底部虚拟栏取消做处理，保持原来的黑色
            //  window.setNavigationBarColor(0);
            window.setStatusBarColor(0);
            alphaColor = alpha == 0 ? color : this.calculateColor(color, alpha);
            decorView.addView(this.createStatusBarView(this.activity, alphaColor), 0);
            if (this.navigationBarExist(this.activity)) {
                decorView.addView(this.createNavBarView(this.activity, alphaColor), 1);
            }
        }
//        else if (VERSION.SDK_INT >= 19) {
//            window = this.activity.getWindow();
//            window.addFlags(67108864);
//            decorView = (ViewGroup) window.getDecorView();
//            alphaColor = alpha == 0 ? color : this.calculateColor(color, alpha);
//            decorView.addView(this.createStatusBarView(this.activity, alphaColor), 0);
//            if (this.navigationBarExist(this.activity)) {
//                window.addFlags(134217728);
//                decorView.addView(this.createNavBarView(this.activity, alphaColor), 1);
//            }
//        }

    }

    public void setColorBarForDrawer(@ColorInt int color) {
        this.setColorBarForDrawer(color, 0);
    }

    public void setTransparentBar(@ColorInt int color, int alpha) {
        Window window;
        if (VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window = this.activity.getWindow();
            View decorView = window.getDecorView();
            int option = 1792;
            decorView.setSystemUiVisibility(option);
            int finalColor = alpha == 0 ? 0 : Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
            //   window.setNavigationBarColor(finalColor);
            window.setStatusBarColor(finalColor);
        }
//        else if (VERSION.SDK_INT >= 19) {
//            window = this.activity.getWindow();
//            window.addFlags(67108864);
//            ViewGroup decorView = (ViewGroup) window.getDecorView();
//            int finalColor = alpha == 0 ? 0 : Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
//            decorView.addView(this.createStatusBarView(this.activity, finalColor));
//            if (this.navigationBarExist(this.activity)) {
//                window.addFlags(134217728);
//                decorView.addView(this.createNavBarView(this.activity, finalColor));
//            }
//        }

    }

    public void setImmersionBar() {
        this.setTransparentBar(0, 0);
    }

//    public void setHintBar() {
//        if (VERSION.SDK_INT >= 19) {
//            View decorView = this.activity.getWindow().getDecorView();
//            decorView.setSystemUiVisibility(5894);
//        }
//
//    }

    private View createStatusBarView(Context context, @ColorInt int color) {
        View mStatusBarTintView = new View(context);
        LayoutParams params = new LayoutParams(-1, this.getStatusBarHeight(context));
//        params.gravity = 48;
        params.gravity = Gravity.TOP;
        mStatusBarTintView.setLayoutParams(params);
        mStatusBarTintView.setBackgroundColor(color);
        return mStatusBarTintView;
    }

    private View createNavBarView(Context context, @ColorInt int color) {
        View mNavBarTintView = new View(context);
        LayoutParams params = new LayoutParams(-1, this.getNavigationHeight(context));
//        params.gravity = 80;
        params.gravity = Gravity.BOTTOM;
        mNavBarTintView.setLayoutParams(params);
        mNavBarTintView.setBackgroundColor(color);
        return mNavBarTintView;
    }

    private boolean navigationBarExist(Activity activity) {
        WindowManager windowManager = activity.getWindowManager();
        Display d = windowManager.getDefaultDisplay();
        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
            d.getRealMetrics(realDisplayMetrics);

        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getMetrics(displayMetrics);
        int displayHeight = displayMetrics.heightPixels;
        int displayWidth = displayMetrics.widthPixels;
        // return realWidth - displayWidth > 0 || realHeight - displayHeight > 0;
        return false;
    }

    @ColorInt
    private int calculateColor(@ColorInt int color, int alpha) {
        float a = 1.0F - (float) alpha / 255.0F;
        int red = color >> 16 & 255;
        int green = color >> 8 & 255;
        int blue = color & 255;
        red = (int) ((double) ((float) red * a) + 0.5D);
        green = (int) ((double) ((float) green * a) + 0.5D);
        blue = (int) ((double) ((float) blue * a) + 0.5D);
        return -16777216 | red << 16 | green << 8 | blue;
    }

    private void setRootView(Activity activity, boolean fit) {
        ViewGroup parent = activity.findViewById(android.R.id.content);
        int i = 0;

        for (int count = parent.getChildCount(); i < count; ++i) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(fit);
                ((ViewGroup) childView).setClipToPadding(fit);
            }
        }

    }

    private int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    public int getNavigationHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}
