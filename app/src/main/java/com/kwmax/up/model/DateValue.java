package com.kwmax.up.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kwmax on 2017/3/25.
 */

public class DateValue implements Cloneable {

    // 完整的日期格式
    public static final String DefaultShowMinuteFormat = "yyyy-MM-dd HH:mm";

    private String showFormat = DefaultShowMinuteFormat;
    private Date value;

    public DateValue(Date date) {
        this.value = date;
    }

    public DateValue(Calendar calendar) {
        this.value = calendar.getTime();
    }

    public void setValue(Date value) {
        this.value = value;
    }

    public String getText() {
        if (this.value == null) {
            return "";
        }
        return new SimpleDateFormat(showFormat).format(this.value);
    }

    public String getTextValue(String format){
        if (this.value == null) {
            return "";
        }
        return new SimpleDateFormat(format).format(this.value);
    }

    public long getTime() {
        if (this.value == null) {
            return 0;
        }
        return value.getTime();
    }

    /**
     * 获取时间戳
     * @return
     */
    public String get() {
        if (this.value == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(showFormat);//小写的mm表示的是分钟
        String dstr = getText();
        Date date = null;
        try {
            date = sdf.parse(dstr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "" + date.getTime();
    }


    public Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);
        return calendar;
    }

    @Override
    public Object clone() {
        DateValue dateValue = null;
        try {
            dateValue = (DateValue)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return dateValue;
    }

    /**
     * 比较两个时间的间隔
     * @param dateValue2
     * @param unit
     * @return
     */
    public int getDateInterval(DateValue dateValue2, String unit){
        int interval = 0;
        int minunit = 60, hourunit = 60, dayunit = 24, monthunit = 30, yearunit = 12;
        int inter = (int) (getTime() - dateValue2.getTime()) / 1000;//时间相差毫秒数
        switch (unit) {
            case "minute":
                interval = inter / minunit;
                break;
            case "hour":
                interval = inter / (minunit * hourunit);
                break;
            case "day":
                interval = inter / (minunit * hourunit * dayunit);
                break;
            case "month":
                interval = inter / (minunit * hourunit * dayunit * monthunit);
                break;
            case "year":
                interval = inter / (minunit * hourunit * dayunit * monthunit * yearunit);
                break;
            default:
                interval = inter / (minunit * hourunit * dayunit);
                break;
        }
        return interval;
    }

    public DateValue generateIntervalDateVale(String unit, int interval){
        int field = Calendar.DATE;
        switch (unit) {
            case "minute":
                field = Calendar.MINUTE;
                break;
            case "hour":
                field = Calendar.HOUR_OF_DAY;
                break;
            case "day":
                field = Calendar.DATE;
                break;
            case "month":
                field = Calendar.MONTH;
                break;
            case "year":
                field = Calendar.YEAR;
                break;
            default:
                field = Calendar.DATE;
                break;
        }
        Calendar calendar = getCalendar();
        calendar.add(field,interval);
        DateValue dateValue2 = new DateValue(calendar);
        return dateValue2;
    }

    /**
     * 当前日期是不是在dateValue之前
     * 只比较到日。
     */
    public boolean before(DateValue dateValue) {
        Calendar calendar1 = getCalendar();
        Calendar calendar2 = dateValue.getCalendar();
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        if (year1 < year2) {
            return true;
        } else if (year1 > year2) {
            return false;
        }
//        if (showFormat.equals(DateValue.DefaultShowYearFormat)) {
//            return true;
//        }

        int month1 = calendar1.get(Calendar.MONTH);
        int month2 = calendar2.get(Calendar.MONTH);
        if (month1 < month2) {
            return true;
        } else if (month1 > month2) {
            return false;
        }
//        if (showFormat.equals(DateValue.DefaultShowMonthFormat)){
//            return true;
//        }

        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        if (day1 < day2){
            return true;
        }else if (day1 > day2){
            return false;
        }
//        if (showFormat.equals(DateValue.DefaultShowFormat)){
//            return true;
//        }

        int hour1 = calendar1.get(Calendar.HOUR_OF_DAY);
        int hour2 = calendar2.get(Calendar.HOUR_OF_DAY);
        if (hour1 < hour2){
            return true;
        }else if (hour1 > hour2) {
            return false;
        }
//        if (showFormat.equals(DateValue.DefaultShowHourFormat)){
//            return true;
//        }

        int min1 = calendar1.get(Calendar.MINUTE);
        int min2 = calendar2.get(Calendar.MINUTE);
        /**
         * 考虑到单位为年的时候，判断分钟的时候，选择结束时间的分钟数可能超过最大时间的分钟数，因此这样处理，
         * 原因：最小时间和开始时间，选择最小开始时间作为开始时间，总会大于或等于最小时间，
         * 选择最大时间作为结束时间，总会小于或等于最大时间，目前只考虑到分钟情况
         */
        if (min1 > min2){
            return false;
        }else {
            return true;
        }
    }

    // 判断开始时间和结束时间是否相等
    public boolean equalsDateValue(DateValue dateValue) {
        Calendar calendar1 = getCalendar();
        Calendar calendar2 = dateValue.getCalendar();
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        if (year1 == year2) {
//            if (showFormat.equals(DateValue.DefaultShowYearFormat)) {
//                return true;
//            }
            int month1 = calendar1.get(Calendar.MONTH);
            int month2 = calendar2.get(Calendar.MONTH);
            if (month1 == month2) {
//                if (showFormat.equals(DateValue.DefaultShowMonthFormat)) {
//                    return true;
//                }
                int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
                if (day1 == day2) {
//                    if (showFormat.equals(DateValue.DefaultShowFormat)) {
//                        return true;
//                    }
                    int hour1 = calendar1.get(Calendar.HOUR_OF_DAY);
                    int hour2 = calendar2.get(Calendar.HOUR_OF_DAY);
                    if (hour1 == hour2) {
//                        if (showFormat.equals(DateValue.DefaultShowHourFormat)) {
//                            return true;
//                        }
                        int min1 = calendar1.get(Calendar.MINUTE);
                        int min2 = calendar2.get(Calendar.MINUTE);
                        if (min1 == min2) {
                            return true;
                        }
                        else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
