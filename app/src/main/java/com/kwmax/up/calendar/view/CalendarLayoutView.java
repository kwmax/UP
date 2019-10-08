package com.kwmax.up.calendar.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kwmax.up.R;
import com.kwmax.up.calendar.CalendarDate;
import com.kwmax.up.calendar.CalendarHintBean;
import com.kwmax.up.calendar.CalendarUtil;
import com.kwmax.up.calendar.MonthViewPager;
import com.kwmax.up.calendar.WeekViewPager;
import com.kwmax.up.calendar.listener.OnCalendarViewClick;
import com.kwmax.up.calendar.listener.OnCalendarViewSizeChangedListener;
import com.kwmax.up.calendar.listener.OnPagerSelectedListener;

import java.util.HashMap;

/**
 * Created by kwmax on 2018/1/8.
 * 周、月模式的日历view
 */

public class CalendarLayoutView extends LinearLayout implements OnCalendarViewClick, OnPagerSelectedListener {
    // todo：默认是周模式，可切换成月模式
    private static final String SHOW_MODE = "week";

    private Context context;
    private ViewGroup calendarView;
    private ViewGroup pagerContainer;
    private TextView tvShowDate;
    private TextView switchText;
    private ImageView switchImage;
    // 周视图
    private WeekViewPager weekViewPager;
    // 月视图
    private MonthViewPager monthViewPager;
    // 日历的点击事件，传递到CalendarViewModel去
    private OnCalendarViewClick onCalendarViewClick;

    private OnCalendarViewSizeChangedListener onCalendarViewSizeChangedListener;

    private CalendarDate selectedDate;
    // hinttype表单属性有dot和text两种类型，isHintTypeDot表示为dot类型
    private boolean isHintTypeDot;

    // 默认当前日期往前一个月，往后一个月
    private int[] range;

    private CalendarLayoutView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CalendarLayoutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CalendarLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        initView();
        initCalendar();
    }

    /**
     * range属性解析设置相应的范围
     */
    private void initRange(WeekViewPager week, MonthViewPager month) {
        if (range == null || range.length == 0 || range[0] == Integer.MIN_VALUE) {
            week.init(selectedDate, isHintTypeDot);
            month.init(selectedDate, isHintTypeDot);
        } else {
            int monthCount = range[1] - range[0] + 1;
            CalendarDate currentDate = CalendarUtil.getCurrentTime(context);
            CalendarDate firstDate = CalendarUtil.addMonth(currentDate, range[0]);
            firstDate.setDay(1);
            CalendarDate lastDate = CalendarUtil.addMonth(currentDate, range[1]);
            lastDate.setDay(CalendarUtil.getMonthDays(lastDate.getYear(), lastDate.getMonth()));
            int weekCount = CalendarUtil.getWeekCount(firstDate, lastDate) + 1;
            int monthInitItem;
            int weekInitItem;
            if (selectedDate.isBefore(firstDate)) {
                selectedDate = firstDate;
                monthInitItem = 0;
                weekInitItem = 0;
            } else if (selectedDate.isAfter(lastDate)) {
                selectedDate = lastDate;
                monthInitItem = monthCount - 1;
                weekInitItem = weekCount - 1;
            } else {
                monthInitItem = CalendarUtil.getMonthCount(firstDate, selectedDate);
                weekInitItem = CalendarUtil.getWeekCount(firstDate, selectedDate);
            }

            month.init(monthCount, monthInitItem, selectedDate, isHintTypeDot);
            week.init(weekCount, weekInitItem, selectedDate, isHintTypeDot);
        }
    }

    private void initView() {
        calendarView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_calendar, null);
        pagerContainer = calendarView.findViewById(R.id.pagerContainer);
        tvShowDate = calendarView.findViewById(R.id.tv_show_date);
        selectedDate = CalendarUtil.getCurrentTime(context);
        setShowDate();
        weekViewPager = calendarView.findViewById(R.id.weekViewPager);
        monthViewPager = calendarView.findViewById(R.id.monthViewPager);
        initRange(weekViewPager, monthViewPager);
        TextView tvToday = calendarView.findViewById(R.id.tv_today);
        tvToday.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weekViewPager.getVisibility() == VISIBLE) {
                    weekViewPager.toToday();
                } else {
                    monthViewPager.toToday();
                }
                selectedDate = CalendarUtil.getCurrentTime(context);
                setShowDate();
                if (onCalendarViewClick != null) {
                    onCalendarViewClick.onClick(selectedDate);
                }
            }
        });
//        ImageView imgSwitch = (ImageView) calendarView.findViewById(R.id.img_switch);
        LinearLayout switchLayout = calendarView.findViewById(R.id.mode_switch);
        switchText = calendarView.findViewById(R.id.switch_text);
        switchImage = calendarView.findViewById(R.id.switch_image);

        switchLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weekViewPager.getVisibility() == VISIBLE) {
                    //切换成月模式
                    switchText.setText("收起日历");
                    switchImage.setImageDrawable(getResources().getDrawable(R.drawable.switch_week));
                    weekViewPager.setVisibility(GONE);
                    monthViewPager.setVisibility(VISIBLE);
                    monthViewPager.setSelectedDate(selectedDate);

                    pagerContainer.addView(monthViewPager);
                    pagerContainer.removeView(weekViewPager);

                } else {
                    //切换成周模式
                    switchText.setText("展开日历");
                    switchImage.setImageDrawable(getResources().getDrawable(R.drawable.switch_month));
                    weekViewPager.setVisibility(VISIBLE);
                    monthViewPager.setVisibility(GONE);
                    weekViewPager.setSelectedDate(selectedDate);

                    pagerContainer.addView(weekViewPager);
                    pagerContainer.removeView(monthViewPager);
                }


                if (onCalendarViewSizeChangedListener != null) {
                    onCalendarViewSizeChangedListener.onCalendarViewSizeChangedListener();
                }
            }
        });

        addView(calendarView);
    }

    private void initCalendar() {
        calendarView.post(new Runnable() {
            @Override
            public void run() {
                if ("WEEK".equalsIgnoreCase(SHOW_MODE)) {
                    weekViewPager.setVisibility(VISIBLE);
                    monthViewPager.setVisibility(GONE);

                    pagerContainer.removeView(monthViewPager);
                    switchText.setText("展开日历");
                    switchImage.setImageDrawable(getResources().getDrawable(R.drawable.switch_month));
                } else {
                    monthViewPager.setVisibility(VISIBLE);
                    weekViewPager.setVisibility(GONE);

                    pagerContainer.removeView(weekViewPager);
                    switchText.setText("收起日历");
                    switchImage.setImageDrawable(getResources().getDrawable(R.drawable.switch_week));
                }

                if (onCalendarViewSizeChangedListener != null) {
                    onCalendarViewSizeChangedListener.onCalendarViewSizeChangedListener();
                }
            }
        });

        weekViewPager.setOnPagerSelectedListener(this);
        monthViewPager.setOnPagerSelectedListener(this);
        weekViewPager.setOnCalendarViewClick(this);
        monthViewPager.setOnCalendarViewClick(this);
    }

    /**
     * 显示相应的年和月
     */
    private void setShowDate() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(selectedDate.getYear());
        stringBuilder.append("-");
        int month = selectedDate.getMonth() + 1;
        if (month < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(month);
        tvShowDate.setText(stringBuilder);
//        if (viewObservable != null){
//            try {
//                viewObservable.onViewDataChange(new FormViewData(String.valueOf(DateUtil.date2Stamp(CalendarUtil.getFormatDate(selectedDate),DATE_FORMAT))));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void setData(HashMap<String, CalendarHintBean> data) {
        monthViewPager.setData(data);
        weekViewPager.setData(data);
    }

    public void setMonthViewDate(CalendarDate selectedDate) {
        this.selectedDate = selectedDate;
        monthViewPager.setSelectedDate(selectedDate);
    }

    public void setMonthViewData(HashMap<String, CalendarHintBean> data) {
        monthViewPager.setData(data);
    }

    @Override
    public void onClick(CalendarDate calendarDate) {
        selectedDate = calendarDate;
        setShowDate();
        if (onCalendarViewClick != null) {
            onCalendarViewClick.onClick(calendarDate);
        }
    }

    @Override
    public void onPagerSelected(CalendarDate calendarDate) {
        selectedDate = calendarDate;
        setShowDate();
    }
}
