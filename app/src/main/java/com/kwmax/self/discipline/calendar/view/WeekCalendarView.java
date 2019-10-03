package com.kwmax.self.discipline.calendar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;

import com.kwmax.self.discipline.R;
import com.kwmax.self.discipline.calendar.CalendarDate;
import com.kwmax.self.discipline.calendar.CalendarHintBean;
import com.kwmax.self.discipline.calendar.CalendarUtil;

/**
 * Created by kwmax on 2018/1/8.
 */

public class WeekCalendarView extends BaseCalendarView {

    private int selectedItem;
    private CalendarDate[] calendarDates;
    private float columnWidth;
    private CalendarDate currentDate;
    private CalendarDate selectedDate;

    public WeekCalendarView(Context context, CalendarDate calendarDate, CalendarDate currentDate) {
        super(context);
        this.initTime = calendarDate;
        this.curTime = initTime;
        this.currentDate = currentDate;
        selectedItem = CalendarUtil.getWeekOfDay(curTime);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);

        columnWidth = widthSize / 7;
        columnSize = widthSize / 7;
        rowSize = heightSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calendarDates = CalendarUtil.getDaysInWeek(curTime);
        int startY = rowSize / 2;
        for (int i = 0; i < 7; i++) {
            String dayStr = String.valueOf(calendarDates[i].getDay());
            int startX = (int) (columnSize * i + (columnSize - datePaint.measureText("30")) / 2);
            boolean outRange = judgeDayOutOfRange(calendarDates[i]);
            boolean isSelected = (selectedDate != null && calendarDates[i].isEquals(selectedDate)) || (selectedDate == null && calendarDates[i].isEquals(currentDate));
            if (isSelected && !outRange) {
                int circleX = columnSize * i + columnSize / 2;
                canvas.drawCircle(circleX, rowSize / 2, circleRadius, bgCirclePaint);
                datePaint.setColor(selectedColor);
                drawHint(columnSize * i + columnSize / 2, rowSize / 2, canvas, calendarDates[i], true);
            } else {
                if (outRange) {//超出可点击范围
                    datePaint.setColor(outRangeColor);
                    Paint.FontMetricsInt fm = datePaint.getFontMetricsInt();
                    int top = startY - rowSize / 4 + (fm.top - fm.bottom) / 2;
                    int bottom = startY + rowSize / 4;
                    int right = (int) (columnWidth * (i + 1) + 0.5f);
                    int left = (int) (columnWidth * i + 0.5f);
                    int outRangeSize = (int) (columnSize * i + columnSize / 2 - (datePaint.measureText("30") / 2) + columnWidth + 0.5f);
                    boolean preOutRangeLastIndex = false;
                    RectF rect;
                    if (i == 6 || preOutRangeLastIndex) {
                        rect = new RectF(left, top, right, bottom);
                    } else {
                        rect = new RectF(left, top, outRangeSize, bottom);
                    }

                    datePaint.setColor(getResources().getColor(R.color.calendar_outrang_bg));
                    canvas.drawRoundRect(rect, 35, 35, datePaint);
                    datePaint.setColor(outRangeColor);
                } else {
                    datePaint.setColor(dateTextColor);
                }
                drawHint(columnSize * i + columnSize / 2, rowSize / 2, canvas, calendarDates[i], false);
            }
            canvas.drawText(dayStr, startX, startY, datePaint);
        }
    }

    /**
     * 判断指定日期是否在可点击范围之内
     *
     * @return
     */
    public boolean judgeDayOutOfRange(CalendarDate date) {
        boolean out = false;
        return out;
    }

    private void drawHint(int centerX, int centerY, Canvas canvas, CalendarDate calendarDate, boolean isSelected) {
        if (data == null || data.size() == 0) {
            return;
        }
        CalendarHintBean bean = data.get(CalendarUtil.getHashStr(calendarDate.getYear(), calendarDate.getMonth(), calendarDate.getDay()));
        if (bean == null) return;
        int color = getResources().getColor(R.color.calendar_circle_color);
        if (isSelected) {
            color = getResources().getColor(R.color.calendar_selected_color);
        } else {
            try {
                if (!TextUtils.isEmpty(bean.getHintColor())) {
                    color = Color.parseColor(bean.getHintColor());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!isHintDot) {
            drawHintText(bean.getHintText(), centerX, centerY, canvas, color);
        } else {
            drawHintDot(centerX, centerY, canvas, color);
        }
    }


    @Override
    protected void clickAction(int x, int y) {
        if (y > getHeight()) return;

        // 如果选中，不响应点击事件
//        if (selectedItem == (x / columnSize + 1)) {
//            return;
//        }
        selectedItem = (x / columnSize + 1);
        curTime = calendarDates[selectedItem - 1];
        selectedDate = curTime;
        boolean clickable = true;
        if (onCalendarViewClick != null && !judgeDayOutOfRange(selectedDate)) {
            onCalendarViewClick.onClick(selectedDate);
        }
        if (!clickable) {
//            Toast.makeText(getContext(),"超出可选择时间范围",Toast.LENGTH_SHORT).show();
            return;
        }
        invalidate();
    }

    public CalendarDate getSelectedDay() {
        return curTime;
    }

    public void setSelectedDate(CalendarDate calendarDate) {
        this.curTime = calendarDate;
        selectedDate = curTime;
        this.selectedItem = CalendarUtil.getWeekOfDay(curTime);
        invalidate();
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(int item) {
        this.selectedItem = item;
        this.curTime = calendarDates[selectedItem - 1];
        invalidate();
    }
}
