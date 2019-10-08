package com.kwmax.up.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kwmax on 2019/10/4.
 */
@Entity
public class TomatoTodo {

    @Id(autoincrement = true)
    private Long id;

    private String userid;
    private String content;
    private String duration;
    @Generated(hash = 1615951699)
    public TomatoTodo(Long id, String userid, String content, String duration) {
        this.id = id;
        this.userid = userid;
        this.content = content;
        this.duration = duration;
    }
    @Generated(hash = 652839444)
    public TomatoTodo() {
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
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDuration() {
        return this.duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
}
