package com.kwmax.up.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kwmax on 2019/10/8.
 */
@Entity
public class EncourageSentence {

    @Id(autoincrement = true)
    private Long id;
    private String userid;

    private String text;
    private String show;
    @Generated(hash = 601148915)
    public EncourageSentence(Long id, String userid, String text, String show) {
        this.id = id;
        this.userid = userid;
        this.text = text;
        this.show = show;
    }
    @Generated(hash = 1855282486)
    public EncourageSentence() {
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
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getShow() {
        return this.show;
    }
    public void setShow(String show) {
        this.show = show;
    }
}
