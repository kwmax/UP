package com.kwmax.self.discipline.calendar;

import android.content.Context;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.Weeks;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by kwmax on 2018/1/8.
 */

public class CalendarUtil {

    /**
     * @param  year 指定年
     * @param month 指定月
     * @return 获取指定月的天数
     */
    public static int getMonthDays(int year, int month) {
        month++;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }

    /**
     * @param year  指定年
     * @param month 指定月
     * @return 获取指定年月的第一天是星期几
     */
    public static int getFirstDayOfWeek(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取指定日期是星期几
     * 从周日开始计数，周日=1 周一=2... 依次类推
     */
    public static int getWeekOfDay(CalendarDate calendarDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendarDate.getYear(), calendarDate.getMonth(), calendarDate.getDay());
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取指定日期所在一周内的日期
     */
    public static CalendarDate[] getDaysInWeek(CalendarDate calendarDate) {
        CalendarDate[] calendarDates = new CalendarDate[7];
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendarDate.getYear(), calendarDate.getMonth(), calendarDate.getDay());
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, -1);
        }
        for (int i = 0; i < 7; i++) {
            CalendarDate tempCalendarDate = new CalendarDate();
            tempCalendarDate.setYear(calendar.get(Calendar.YEAR));
            tempCalendarDate.setMonth(calendar.get(Calendar.MONTH));
            tempCalendarDate.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            calendarDates[i] = tempCalendarDate;
            calendar.add(Calendar.DATE, 1);
        }
        return calendarDates;
    }

    /**
     * 加减月份
     */
    public static CalendarDate addMonth(CalendarDate calendarDate, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendarDate.getYear(), calendarDate.getMonth(), calendarDate.getDay());
        calendar.add(Calendar.MONTH, months);
        return new CalendarDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }


    public static CalendarDate addDays(CalendarDate calendarDate, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendarDate.getYear(), calendarDate.getMonth(), calendarDate.getDay());
        calendar.add(Calendar.DATE, days);
        return new CalendarDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 获取当前的时间，即今天的日期
     */
    public static CalendarDate getCurrentTime(Context context) {
//        long time = DateUtil.getXuanWuTime(context);
        long time = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        CalendarDate calendarDate = new CalendarDate();
        calendarDate.setYear(calendar.get(Calendar.YEAR));
        calendarDate.setMonth(calendar.get(Calendar.MONTH));
        calendarDate.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        return calendarDate;
    }

    /**
     * 获取两个日期之间的月数
     */
    public static int getMonthCount(CalendarDate day1, CalendarDate day2) {
        int yearCount = day2.getYear() - day1.getYear();
        int temp = day2.getMonth() - day1.getMonth();
        return yearCount * 12 + temp;
    }

    /**
     * 获得两个日期距离几周
     */
    public static int getWeekCount(CalendarDate day1, CalendarDate day2) {

        DateTime dateTime1 = new DateTime(day1.getYear(), day1.getMonth() + 1, day1.getDay(), 0, 0, 0);
        DateTime dateTime2 = new DateTime(day2.getYear(), day2.getMonth() + 1, day2.getDay(), 0, 0, 0);

        dateTime1 = getSunFirstDayOfWeek(dateTime1);
        dateTime2 = getSunFirstDayOfWeek(dateTime2);
        return Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
    }

    /**
     * 获得两个日期距离几个月
     *
     * @return
     */
    public static int getIntervalMonths(CalendarDate day1, CalendarDate day2) {
        DateTime dateTime1 = new DateTime(day1.getYear(), day1.getMonth() + 1, day1.getDay(), 0, 0, 0);
        DateTime dateTime2 = new DateTime(day2.getYear(), day2.getMonth() + 1, day2.getDay(), 0, 0, 0);
        dateTime1 = dateTime1.withDayOfMonth(1).withTimeAtStartOfDay();
        dateTime2 = dateTime2.withDayOfMonth(1).withTimeAtStartOfDay();

        return Months.monthsBetween(dateTime1, dateTime2).getMonths();
    }

    //转化一周从周日开始
    private static DateTime getSunFirstDayOfWeek(DateTime dateTime) {
        if (dateTime.dayOfWeek().get() == 7) {
            return dateTime;
        } else {
            return dateTime.minusWeeks(1).withDayOfWeek(7);
        }
    }

    /**
     *返回统一格式的日期（如2017-01-17）
     */
    public static String getFormatDate(CalendarDate calendarDate) {
        int year = calendarDate.getYear();
        int month = calendarDate.getMonth();
        int day = calendarDate.getDay();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(year);
        stringBuilder.append("-");
        month++;
        if (month < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append(month);
        stringBuilder.append("-");
        if (day < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append(day);
        return stringBuilder.toString();
    }


    /**
     * 减去一天
     */
    public static CalendarDate subtractOneDay(CalendarDate calendarDate) {
        int year = calendarDate.getYear();
        int month = calendarDate.getMonth();
        int day = calendarDate.getDay();
        int tempMonth = month;
        int tempDay = day - 1;
        if (tempDay == 0) {
            tempMonth = month - 1;
            if (tempMonth == -1) {
                year--;
                tempMonth = 11;
            }
            tempDay = getMonthDays(year, tempMonth);
        }
        calendarDate.setYear(year);
        calendarDate.setMonth(tempMonth);
        calendarDate.setDay(tempDay);
        return calendarDate;
    }

    /**
     * 加上一天
     */
    public static CalendarDate plusOneDay(CalendarDate calendarDate) {
        int year = calendarDate.getYear();
        int month = calendarDate.getMonth();
        int day = calendarDate.getDay();
        day++;
        if (day > getMonthDays(year, month)) {
            day = 1;
            month++;
            if (month > 11) {
                year++;
                month = 0;
            }
        }
        calendarDate.setYear(year);
        calendarDate.setMonth(month);
        calendarDate.setDay(day);
        return calendarDate;
    }

    public static long toTimeMillis(CalendarDate date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonth(), date.getDay());
        return calendar.getTimeInMillis();
    }

    /**
     * 判断date是否处于d1和d2之间
     */
    public static boolean isBetweenDate(CalendarDate date, CalendarDate d1, CalendarDate d2) {
        Date seedDate = new Date(toTimeMillis(date));
        Date date1 = new Date(toTimeMillis(d1));
        Date date2 = new Date(toTimeMillis(d2));
        return seedDate.equals(date1) || seedDate.equals(date2) || seedDate.after(date1) && seedDate.before(date2);
    }

    public static String getHashStr(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int month = calendar.get(Calendar.MONTH);
        String monthStr;
        if (month < 10) {
            monthStr = "0" + month;
        } else {
            monthStr = String.valueOf(month);
        }

        int day = calendar.get(Calendar.DATE);
        String dayStr;
        if (day < 10) {
            dayStr = "0" + day;
        } else {
            dayStr = String.valueOf(day);
        }
        return calendar.get(Calendar.YEAR) + monthStr + dayStr;
    }

    public static String getHashStr(int year, int month, int day) {
        String monthStr;
        if (month < 10) {
            monthStr = "0" + month;
        } else {
            monthStr = String.valueOf(month);
        }

        String dayStr;
        if (day < 10) {
            dayStr = "0" + day;
        } else {
            dayStr = String.valueOf(day);
        }
        return year + monthStr + dayStr;
    }
}
