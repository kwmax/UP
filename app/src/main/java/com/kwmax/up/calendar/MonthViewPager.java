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

public class MonthViewPager extends ViewPager {

    private CalendarDate initDate;
    private OnPagerSelectedListener onPagerSelectedListener;
    private MonthViewPagerAdapter adapter;

    public MonthViewPager(Context context) {
        super(context);
    }

    public MonthViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(int pageCount, int initItemPos, CalendarDate initTime, boolean isHintDot) {
        this.initDate = initTime;
        adapter = new MonthViewPagerAdapter(getContext(), this, pageCount, initItemPos, isHintDot);
        this.setAdapter(adapter);
        setCurrentItem(initItemPos, false);
        addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (onPagerSelectedListener != null) {
                    try {
                        onPagerSelectedListener.onPagerSelected(adapter.getMonthViews().get(position).getSelectedDay());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 月份无边界
     * ViewPage无限滑动处理很麻烦。这里给ViewPager的Adapter设置一个很大的count来实现这个效果
     */
    public void init(CalendarDate initTime, boolean isHintDot) {
        int pageCount = 200 * 12;
        int pos = pageCount / 2;
        init(pageCount, pos, initTime, isHintDot);
    }

    public void setInitDate(CalendarDate initDate) {
        this.initDate = initDate;
    }

    public CalendarDate getInitDate() {
        if (initDate == null) {
            initDate = CalendarUtil.getCurrentTime(getContext());
        }
        return initDate;
    }

    public void setOnPagerSelectedListener(OnPagerSelectedListener onPagerSelectedListener) {
        this.onPagerSelectedListener = onPagerSelectedListener;
    }

    public CalendarDate getSelectedDate() {
        return adapter.getMonthViews().get(getCurrentItem()).getSelectedDay();
    }

    public void setSelectedDate(CalendarDate calendarDate) {
        int currentItem = getCurrentItem();
        CalendarDate currentDate = adapter.getMonthViews().get(currentItem).getSelectedDay();
        if (currentDate.isEquals(calendarDate)) {
            return;
        }
        int monthCount = CalendarUtil.getMonthCount(currentDate, calendarDate);
        adapter.getLastSelected().setDay(calendarDate.getDay());
        adapter.updateDay(adapter.getMonthViews().get(currentItem - 1), calendarDate);
        adapter.updateDay(adapter.getMonthViews().get(currentItem + 1), calendarDate);
        if (monthCount == 0) {
            adapter.getMonthViews().get(currentItem).setSelectedDay(calendarDate);
        } else {
            setCurrentItem(currentItem + monthCount, false);
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
