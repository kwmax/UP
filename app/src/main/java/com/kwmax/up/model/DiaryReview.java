package com.kwmax.up.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kwmax on 2019/10/8.
 */
@Entity
public class DiaryReview {

    @Id(autoincrement = true)
    private Long id;
    private String userid;
    private String date;
    private String updatetime;
    private String name;
    private String content;
    private String lastEdittime;
    private String done;
    @Generated(hash = 1463937092)
    public DiaryReview(Long id, String userid, String date, String updatetime,
            String name, String content, String lastEdittime, String done) {
        this.id = id;
        this.userid = userid;
        this.date = date;
        this.updatetime = updatetime;
        this.name = name;
        this.content = content;
        this.lastEdittime = lastEdittime;
        this.done = done;
    }
    @Generated(hash = 1769395366)
    public DiaryReview() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getUpdatetime() {
        return this.updatetime;
    }
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getLastEdittime() {
        return this.lastEdittime;
    }
    public void setLastEdittime(String lastEdittime) {
        this.lastEdittime = lastEdittime;
    }
    public String getDone() {
        return this.done;
    }
    public void setDone(String done) {
        this.done = done;
    }
}
