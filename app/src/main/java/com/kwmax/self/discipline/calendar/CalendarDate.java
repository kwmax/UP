package com.kwmax.self.discipline.calendar;

/**
 * Created by kwmax on 2018/1/8.
 * month从0开始计
 * 仅仅是为了方便处理日期
 */

public class CalendarDate {
    private int day;
    private int month;
    private int year;

    public CalendarDate() {
    }

    public CalendarDate(int year, int month, int day) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public CalendarDate(int year, int month, int day, String content) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public CalendarDate copy() {
        CalendarDate newCalendarDate = new CalendarDate();
        newCalendarDate.setYear(this.getYear());
        newCalendarDate.setMonth(this.getMonth());
        newCalendarDate.setDay(this.getDay());
        return newCalendarDate;
    }

    /**
     * 判断两个CalendarDate的日期是否相等
     */
    public boolean isEquals(CalendarDate calendarDate) {
        return calendarDate != null && calendarDate.getYear() == this.getYear() && calendarDate.getMonth() == this.getMonth() && calendarDate.getDay() == this.getDay();
    }

    public boolean isAfter(CalendarDate calendarDate) {
        return year > calendarDate.getYear() || year == calendarDate.getYear() && (month > calendarDate.getMonth() || month == calendarDate.getMonth() && day > calendarDate.getDay());
    }

    public boolean isBefore(CalendarDate calendarDate) {
        return year < calendarDate.getYear() || year <= calendarDate.getYear() && (month < calendarDate.getMonth() || month <= calendarDate.getMonth() && day < calendarDate.getDay());
    }

    public String getString() {
        return year + String.valueOf(month) + day;
    }
}
