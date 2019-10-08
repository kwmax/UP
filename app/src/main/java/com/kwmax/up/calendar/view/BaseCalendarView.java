package com.kwmax.up.calendar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.kwmax.up.R;
import com.kwmax.up.calendar.CalendarDate;
import com.kwmax.up.calendar.CalendarHintBean;
import com.kwmax.up.calendar.DisplayUtil;
import com.kwmax.up.calendar.listener.OnCalendarViewClick;

import java.util.HashMap;

/**
 * Created by kwmax on 2018/1/8.
 * 日历分为周视图（WeekCalendarView）和月视图（MonthCalendarView)，BaseCalendarView是这两个视图的父类
 */

public abstract class BaseCalendarView extends View {

    // 当前所在的日期
    protected CalendarDate curTime;
    // 初始默认日期
    protected CalendarDate initTime;

    protected int columnSize, rowSize;
    protected int circleRadius, hintCircleRadius;
    protected int dateTextHeight, hintTextHeight;

    protected int dateTextColor;// 默认颜色
    protected int selectedColor;// 选中日期的颜色
    protected int outRangeColor;// 超出可选择范围日期的颜色

    protected float dateTextSize;
    protected float contentTextSize;
    protected Paint datePaint;
    protected Paint bgCirclePaint;
    protected Paint hintPaint;

    private GestureDetector gestureDetector;
    protected OnCalendarViewClick onCalendarViewClick;
    protected HashMap<String, CalendarHintBean> data;
    protected boolean isHintDot = false;

    public BaseCalendarView(Context context) {
        this(context, null);
    }

    public BaseCalendarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        dateTextColor = getResources().getColor(R.color.calendar_text_color);
        dateTextSize = DisplayUtil.dip2px(context, 12);
        selectedColor = getResources().getColor(R.color.calendar_selected_color);
        outRangeColor = getResources().getColor(R.color.calendar_outrang_color);
        contentTextSize = 10;
        circleRadius = DisplayUtil.dip2px(getContext(), 20);
        hintCircleRadius = DisplayUtil.dip2px(getContext(), 3);

        datePaint = getPaint(dateTextColor, dateTextSize);
        Paint.FontMetrics fontMetrics = datePaint.getFontMetrics();
        dateTextHeight = (int) (fontMetrics.bottom - fontMetrics.top);

        hintPaint = new Paint();
        hintPaint.setColor(Color.RED);
        hintPaint.setAntiAlias(true);
        hintPaint.setTextSize(DisplayUtil.dip2px(context, 8));
        Paint.FontMetrics hintFontMetrics = hintPaint.getFontMetrics();
        hintTextHeight = (int) (hintFontMetrics.bottom - hintFontMetrics.top);

        bgCirclePaint = new Paint();
        bgCirclePaint.setColor(getResources().getColor(R.color.calendar_circle_color));
        bgCirclePaint.setAntiAlias(true);

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                clickAction((int) e.getX(), (int) e.getY());
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
        });
    }

    private Paint getPaint(int paintColor, float paintSize) {
        Paint paint = new Paint();
        paint.setColor(paintColor);
        paint.setTextSize(paintSize);
        paint.setAntiAlias(true);
        return paint;
    }

    protected void drawHintDot(int centerX, int centerY, Canvas canvas, int color) {
        float dotY = centerY + dateTextHeight / 2 + hintCircleRadius / 2;
        hintPaint.setColor(color);
        datePaint.setColor(color);
        canvas.drawCircle(centerX, dotY, hintCircleRadius, hintPaint);
    }

    protected void drawHintText(String text, int centerX, int centerY, Canvas canvas, int color) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        float textX = centerX - hintPaint.measureText(text) / 2;
        float textY = centerY + (dateTextHeight + hintTextHeight) / 2;
        hintPaint.setColor(color);
        datePaint.setColor(color);
        canvas.drawText(text, textX, textY, hintPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    protected abstract void clickAction(int x, int y);

    public void setOnCalendarViewClick(OnCalendarViewClick onCalendarViewClick) {
        this.onCalendarViewClick = onCalendarViewClick;
    }

    public void setData(HashMap<String, CalendarHintBean> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        this.data = data;
        invalidate();
    }

    public void setHintDot(boolean hintDot) {
        isHintDot = hintDot;
    }
}
