package com.kwmax.self.discipline.model;

/**
 * Created by keweimeng on 2019/10/4.
 */
public class TomatoTodo {

    private String content;
    private String duration;

    public TomatoTodo(String content, String duration) {
        this.content = content;
        this.duration = duration;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
