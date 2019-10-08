package com.kwmax.up.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kwmax on 2019/10/8.
 */
@Entity
public class Target {

    @Id(autoincrement = true)
    private Long id;
    private String userid;

    private String text;
    private String cometrue;
    @Generated(hash = 1977764097)
    public Target(Long id, String userid, String text, String cometrue) {
        this.id = id;
        this.userid = userid;
        this.text = text;
        this.cometrue = cometrue;
    }
    @Generated(hash = 231566653)
    public Target() {
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
    public String getCometrue() {
        return this.cometrue;
    }
    public void setCometrue(String cometrue) {
        this.cometrue = cometrue;
    }
}
