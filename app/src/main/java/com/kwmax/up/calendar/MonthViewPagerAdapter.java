package com.kwmax.up.calendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.kwmax.up.calendar.listener.OnCalendarViewClick;
import com.kwmax.up.calendar.view.MonthCalendarView;

import java.util.HashMap;

/**
 * Created by kwmax on 2018/1/8.
 */

public class MonthViewPagerAdapter extends PagerAdapter {
    private SparseArray<MonthCalendarView> monthCalendarViews;
    private Context context;
    private MonthViewPager monthViewPager;
    private CalendarDate lastSelected;
    private OnCalendarViewClick onCalendarViewClick;
    private int pageCount;
    private HashMap<String, CalendarHintBean> data;
    private boolean isHintDot;
    private int initItemPos;

    public MonthViewPagerAdapter(Context context, MonthViewPager monthViewPager, int pageCount, int initItemPos, boolean isHintDot) {
        this.context = context;
        this.monthViewPager = monthViewPager;
        this.pageCount = pageCount;
        this.isHintDot = isHintDot;
        this.initItemPos = initItemPos;
        monthCalendarViews = new SparseArray<>();
        lastSelected = monthViewPager.getInitDate().copy();
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        MonthCalendarView monthCalendarView = monthCalendarViews.get(position);
        if (monthCalendarView == null) {
            CalendarDate calendarDate = CalendarUtil.addMonth(monthViewPager.getInitDate(), position - initItemPos);

            int day = lastSelected.getDay();
            if (day != calendarDate.getDay()) {
                int maxDay = CalendarUtil.getMonthDays(calendarDate.getYear(), calendarDate.getMonth());
                if (day <= maxDay) {
                    calendarDate.setDay(day);
                } else {
                    calendarDate.setDay(maxDay);
                }
            }
            monthCalendarView = new MonthCalendarView(context, calendarDate, monthViewPager.getInitDate(), lastSelected);
            monthCalendarView.setData(data);
            monthCalendarView.setHintDot(isHintDot);
            monthCalendarView.setOnCalendarViewClick(new OnCalendarViewClick() {
                @Override
                public void onClick(CalendarDate calendarDate) {
                    lastSelected = calendarDate;
                    int pos = monthViewPager.getCurrentItem();
                    MonthCalendarView preView = monthCalendarViews.get(pos - 1);
                    MonthCalendarView backView = monthCalendarViews.get(pos + 1);
//                    int day = lastSelected.getDay();
                    updateDay(preView, lastSelected);
                    updateDay(backView, lastSelected);

                    if (onCalendarViewClick != null) {
                        onCalendarViewClick.onClick(calendarDate);
                    }
                }
            });
            monthCalendarViews.put(position, monthCalendarView);
        }
        container.addView(monthCalendarView);
        return monthCalendarView;
    }

    /**
     * 选中的天会改变，invalidate一下monthView
     */
    public void updateDay(MonthCalendarView month, CalendarDate wholeSelected) {
        if (month == null) return;

//        CalendarDate calendarDate = month.getSelectedDay();
//        if (newDay != calendarDate.getDay()) {
//            int maxDay = CalendarUtil.getMonthDays(calendarDate.getYear(), calendarDate.getMonth());
//            calendarDate.setDay(newDay > maxDay ? maxDay : newDay);
//            month.invalidate();
//        }
        month.setSelectedDate(wholeSelected);
        month.invalidate();

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        monthCalendarViews.remove(position);
    }


    public void setOnCalendarViewClick(OnCalendarViewClick onCalendarViewClick) {
        this.onCalendarViewClick = onCalendarViewClick;
    }

    public SparseArray<MonthCalendarView> getMonthViews() {
        return monthCalendarViews;
    }

    public CalendarDate getLastSelected() {
        return lastSelected;
    }

    public void setData(HashMap<String, CalendarHintBean> data) {
        this.data = data;
        int currentPos = monthViewPager.getCurrentItem();
        MonthCalendarView view = monthCalendarViews.get(currentPos);
        MonthCalendarView preView = monthCalendarViews.get(currentPos - 1);
        MonthCalendarView afterView = monthCalendarViews.get(currentPos + 1);
        if (view != null) {
            view.setData(data);
        }
        if (preView != null) {
            preView.setData(data);
        }
        if (afterView != null) {
            afterView.setData(data);
        }
    }
}
