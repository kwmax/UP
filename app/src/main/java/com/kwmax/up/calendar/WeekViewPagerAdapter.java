package com.kwmax.up.calendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.kwmax.up.calendar.listener.OnCalendarViewClick;
import com.kwmax.up.calendar.view.WeekCalendarView;

import java.util.HashMap;

/**
 * Created by kwmax on 2018/1/8.
 */

public class WeekViewPagerAdapter extends PagerAdapter {
    private SparseArray<WeekCalendarView> weekCalendarViews;
    private Context context;
    private WeekViewPager weekViewPager;
    private int lastSelectedItem;
    private OnCalendarViewClick onCalendarViewClick;

    private int pageCount;
    private HashMap<String, CalendarHintBean> data;
    private boolean isHintDot;
    private int initItemPos;

    public WeekViewPagerAdapter(Context context, WeekViewPager weekViewPager, int pageCount, int initItemPos, boolean isHintDot) {
        this.context = context;
        this.weekViewPager = weekViewPager;
        this.pageCount = pageCount;
        this.isHintDot = isHintDot;
        this.initItemPos = initItemPos;
        this.weekCalendarViews = new SparseArray<>();
        lastSelectedItem = CalendarUtil.getWeekOfDay(weekViewPager.getInitTime());
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
        WeekCalendarView weekCalendarView = weekCalendarViews.get(position);
        if (weekCalendarView == null) {
            CalendarDate calendarDate = CalendarUtil.addDays(weekViewPager.getInitTime(), (position - initItemPos) * 7);

            final int weekPos = CalendarUtil.getWeekOfDay(calendarDate);
            if (weekPos != lastSelectedItem) {
                calendarDate = CalendarUtil.addDays(calendarDate, lastSelectedItem - weekPos);
            }
            weekCalendarView = new WeekCalendarView(context, calendarDate, weekViewPager.getInitTime());
            weekCalendarView.setData(data);
            weekCalendarView.setHintDot(isHintDot);
            weekCalendarView.setOnCalendarViewClick(new OnCalendarViewClick() {
                @Override
                public void onClick(CalendarDate calendarDate) {
                    lastSelectedItem = CalendarUtil.getWeekOfDay(calendarDate);
                    int pos = weekViewPager.getCurrentItem();
                    WeekCalendarView preView = weekCalendarViews.get(pos - 1);
                    updateDay(preView, lastSelectedItem);

                    WeekCalendarView backView = weekCalendarViews.get(pos + 1);
                    updateDay(backView, lastSelectedItem);

                    if (onCalendarViewClick != null) {
                        onCalendarViewClick.onClick(calendarDate);
                    }

                }
            });
            weekCalendarViews.put(position, weekCalendarView);
        }
        container.addView(weekCalendarView);
        return weekCalendarView;
    }

    public void updateDay(WeekCalendarView week, int selectedItem) {
        if (week == null || week.getSelectedItem() == selectedItem) return;

        CalendarDate[] calendarDates = CalendarUtil.getDaysInWeek(week.getSelectedDay());
        week.setSelectedDate(calendarDates[selectedItem - 1]);
        week.invalidate();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        weekCalendarViews.remove(position);
    }

    public void setLastSelectedItem(int lastSelectedItem) {
        this.lastSelectedItem = lastSelectedItem;
    }

    public void setOnCalendarViewClick(OnCalendarViewClick onCalendarViewClick) {
        this.onCalendarViewClick = onCalendarViewClick;
    }

    public SparseArray<WeekCalendarView> getWeekViews() {
        return weekCalendarViews;
    }

    public void setData(HashMap<String, CalendarHintBean> data) {
        this.data = data;
        int currentPos = weekViewPager.getCurrentItem();
        WeekCalendarView view = weekCalendarViews.get(currentPos);
        WeekCalendarView preView = weekCalendarViews.get(currentPos - 1);
        WeekCalendarView afterView = weekCalendarViews.get(currentPos + 1);
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
