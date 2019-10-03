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

public class MonthCalendarView extends BaseCalendarView {

    private int[][] days = new int[6][7];

    private float columnWidth;
    private CalendarDate currentDate;
    private CalendarDate selectedDate;

    public MonthCalendarView(Context context, CalendarDate initTime, CalendarDate currentDate,
                             CalendarDate selectedDate) {
        super(context);
        this.initTime = initTime;
        this.curTime = initTime;
        this.currentDate = currentDate;
        this.selectedDate = selectedDate;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);

        columnWidth = widthSize / 7;
        columnSize = widthSize / 7;

        int monthDays = CalendarUtil.getMonthDays(curTime.getYear(), curTime.getMonth());
        int firstDayOfWeek = CalendarUtil.getFirstDayOfWeek(curTime.getYear(), curTime.getMonth());
        double temp = monthDays + firstDayOfWeek - 1;
        int row = (int) Math.ceil(temp / 7);
        rowSize = heightSize / row;  //计算行的高度
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String dayStr;
        int monthDays = CalendarUtil.getMonthDays(curTime.getYear(), curTime.getMonth());
        int firstDayOfWeek = CalendarUtil.getFirstDayOfWeek(curTime.getYear(), curTime.getMonth());
        //todo：先算出超出范围的前面最后一个
        int preOutRangeLastIndex = 0;

        for (int day = 0; day < monthDays; day++) {
            CalendarDate calendarDate = new CalendarDate(curTime.getYear(), curTime.getMonth(), day + 1);
            dayStr = String.valueOf(day + 1);
            int column = (day + firstDayOfWeek - 1) % 7;
            int row = (day + firstDayOfWeek - 1) / 7;

            days[row][column] = day + 1;
            int startX = (int) (columnSize * column + columnSize / 2 - (datePaint.measureText("30") / 2));
            //为保证对齐，要测量两位数的字框，0.5f四舍五入
            int outRangeSize = (int)(columnSize * column + columnSize / 2 - (datePaint.measureText("30") / 2) + columnWidth + 0.5f);
            int startY = rowSize * row + rowSize / 2;

            boolean outRange = judgeDayOutOfRange(calendarDate);
            boolean isSelected = (selectedDate != null && calendarDate.isEquals(selectedDate)) ||
                    (selectedDate == null && calendarDate.isEquals(currentDate));
            if (isSelected && !outRange) {
                int circleX = columnSize * column + columnSize / 2;
                int circleY = rowSize * row + rowSize / 2;
                canvas.drawCircle(circleX, circleY, circleRadius, bgCirclePaint);
                datePaint.setColor(selectedColor);
                drawHint(day + 1, columnSize * column + columnSize / 2, rowSize * row + rowSize / 2, canvas, true, false);
            } else {
                if (outRange) {//超出可点击范围
                    drawHint(day + 1, columnSize * column + columnSize / 2, rowSize * row + rowSize / 2, canvas, false, true);
                    Paint.FontMetricsInt fm = datePaint.getFontMetricsInt();
                    int top = startY - rowSize / 4 + (fm.top - fm.bottom) / 2;
                    int bottom = startY + rowSize / 4 ;
                    int left = (int) (columnWidth * column + 0.5f);
                    int right = (int) (columnWidth * (column + 1) + 0.5f);
                    RectF rect = new RectF(left, top, outRangeSize, bottom);
                    if (day + 1 == preOutRangeLastIndex || day + 1 == monthDays || column == 6) {
                        rect = new RectF(left, top, right, bottom);
                    }
                    datePaint.setColor(getResources().getColor(R.color.calendar_outrang_bg));
                    canvas.drawRoundRect(rect,35,35, datePaint);
                    datePaint.setColor(outRangeColor);
                } else {
                    datePaint.setColor(dateTextColor);
                    drawHint(day + 1, columnSize * column + columnSize / 2, rowSize * row + rowSize / 2, canvas, false, false);
                }
            }
            canvas.drawText(dayStr, startX, startY, datePaint);
        }
    }

    /**
     * 判断指定日期是否在可点击范围之内
     * @return
     */
    public boolean judgeDayOutOfRange(CalendarDate date) {
        boolean out = false;
        return out;
    }

    private void drawHint(int day, int centerX, int centerY, Canvas canvas, boolean isSelected, boolean isoutrange) {
        if (data == null || data.size() == 0) return;
        CalendarHintBean bean = data.get(CalendarUtil.getHashStr(curTime.getYear(), curTime.getMonth(), day));
        if (bean == null) return;
        int color = getResources().getColor(R.color.calendar_circle_color);
        if (isoutrange) color = outRangeColor;
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

    /**
     * 改变当前展示的日期
     *
     * @param calendarDate
     */
    public void changDate(CalendarDate calendarDate) {
        this.curTime = calendarDate;
        invalidate();
    }

    /**
     * 处理点击事件
     */
    @Override
    protected void clickAction(int x, int y) {
        if (y > getHeight()) {
            return;
        }

        int row = y / rowSize;
        int column = Math.min(x / columnSize, 6);
        int day = days[row][column];
        if (day == 0) return;

        // 点击同一天不响应
//        if (curTime.getDay() == day) {
//            return;
//        }
        curTime.setDay(day);
        boolean clickable = true;
        selectedDate = curTime;
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

    public CalendarDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(CalendarDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    public void setSelectedDay(CalendarDate calendarDate) {
        this.curTime = calendarDate;
        selectedDate = curTime;
        invalidate();
    }
}
