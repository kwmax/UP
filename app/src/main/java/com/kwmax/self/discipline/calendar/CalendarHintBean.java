package com.kwmax.self.discipline.calendar;

/**
 * Created by zcbin on 2018/1/13.
 * 日历的数据显示
 */

public class CalendarHintBean {
    private long time;
    private String hintColor;

    public CalendarHintBean() {
    }

    public CalendarHintBean(long time, String hintColor, String hintText) {
        this.time = time;
        this.hintColor = hintColor;
        this.hintText = hintText;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getHintColor() {
        return hintColor;
    }

    public void setHintColor(String hintColor) {
        this.hintColor = hintColor;
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    private String hintText;
}
