package com.kwmax.up.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kwmax on 2019/10/8.
 */
@Entity
public class ImportDate {

    @Id(autoincrement = true)
    private Long id;
    private String userid;

    private String date;
    private String reason;

    private String operRemind;//开启提醒

    @Generated(hash = 2144466852)
    public ImportDate(Long id, String userid, String date, String reason,
            String operRemind) {
        this.id = id;
        this.userid = userid;
        this.date = date;
        this.reason = reason;
        this.operRemind = operRemind;
    }

    @Generated(hash = 2004269329)
    public ImportDate() {
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

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOperRemind() {
        return this.operRemind;
    }

    public void setOperRemind(String operRemind) {
        this.operRemind = operRemind;
    }

}
