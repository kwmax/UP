package com.kwmax.self.discipline.model;

/**
 * Created by keweimeng on 2019/10/3.
 */
public class ScheduleTodo {

    private String star;
    private String time;
    private String title;
    private String content;
    private String lastEdittime;

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLastEdittime() {
        return lastEdittime;
    }

    public void setLastEdittime(String lastEdittime) {
        this.lastEdittime = lastEdittime;
    }
}
