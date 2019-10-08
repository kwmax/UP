package com.kwmax.up.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kwmax on 2019/10/8.
 */
@Entity
public class UpTemplet {

    @Id(autoincrement = true)
    private Long id;
    private String userid;

    private String typenum;//模板号
    private String star;
    private String date;
    private String time;
    private String name;
    private String content;
    private String lastEdittime;
    private String done;
    @Generated(hash = 108217000)
    public UpTemplet(Long id, String userid, String typenum, String star,
            String date, String time, String name, String content,
            String lastEdittime, String done) {
        this.id = id;
        this.userid = userid;
        this.typenum = typenum;
        this.star = star;
        this.date = date;
        this.time = time;
        this.name = name;
        this.content = content;
        this.lastEdittime = lastEdittime;
        this.done = done;
    }
    @Generated(hash = 2015735027)
    public UpTemplet() {
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
    public String getTypenum() {
        return this.typenum;
    }
    public void setTypenum(String typenum) {
        this.typenum = typenum;
    }
    public String getStar() {
        return this.star;
    }
    public void setStar(String star) {
        this.star = star;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
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
