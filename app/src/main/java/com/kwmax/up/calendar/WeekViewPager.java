package com.kwmax.up.calendar;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import com.kwmax.up.calendar.listener.OnCalendarViewClick;
import com.kwmax.up.calendar.listener.OnPagerSelectedListener;
import java.util.HashMap;

/**
 * Created by kwmax on 2018/1/8.
 */

public class WeekViewPager extends ViewPager {

    private CalendarDate initTime;
    private OnPagerSelectedListener onPagerSelectedListener;
    private WeekViewPagerAdapter adapter;

    public WeekViewPager(Context context) {
        super(context);
    }

    public WeekViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(int pageCount, int initItemPos, CalendarDate initTime, boolean isHintDot) {
        this.initTime = initTime;
        adapter = new WeekViewPagerAdapter(getContext(), this, pageCount, initItemPos, isHintDot);
        this.setAdapter(adapter);
        setCurrentItem(initItemPos, false);
        addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (onPagerSelectedListener != null) {
                    try {
                        onPagerSelectedListener.onPagerSelected(adapter.getWeekViews().get(getCurrentItem()).getSelectedDay());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void init(CalendarDate initTime, boolean isHintDot) {
        int pageCount = 200 * 12 * 4;
        int initItemPos = pageCount / 2;
        init(pageCount, initItemPos, initTime, isHintDot);
    }

    public CalendarDate getInitTime() {
        if (initTime == null) {
            initTime = CalendarUtil.getCurrentTime(getContext());
        }
        return initTime;
    }

    public void setOnPagerSelectedListener(OnPagerSelectedListener onPagerSelectedListener) {
        this.onPagerSelectedListener = onPagerSelectedListener;
    }

    public CalendarDate getSelectedDate() {
        return adapter.getWeekViews().get(getCurrentItem()).getSelectedDay();
    }

    /**
     * 跳转到相应的日期。
     */
    public void setSelectedDate(CalendarDate calendarDate) {
        int currentItem = getCurrentItem();
        CalendarDate currentDate = adapter.getWeekViews().get(currentItem).getSelectedDay();
        if (currentDate.isEquals(calendarDate)) {
            return;
        }

        int weekCount = CalendarUtil.getWeekCount(currentDate, calendarDate);
        int selectedItem = CalendarUtil.getWeekOfDay(calendarDate);
        adapter.setLastSelectedItem(selectedItem);
        adapter.updateDay(adapter.getWeekViews().get(currentItem - 1), selectedItem);
        adapter.updateDay(adapter.getWeekViews().get(currentItem + 1), selectedItem);
        if (weekCount == 0) {
            adapter.getWeekViews().get(currentItem).setSelectedDate(calendarDate);
        } else {
            setCurrentItem(currentItem + weekCount, false);
        }
    }

    public void toToday() {
        setSelectedDate(CalendarUtil.getCurrentTime(getContext()));
    }

    public void setOnCalendarViewClick(OnCalendarViewClick onCalendarViewClick) {
        adapter.setOnCalendarViewClick(onCalendarViewClick);
    }

    public void setData(HashMap<String, CalendarHintBean> data) {
        adapter.setData(data);
    }
}
