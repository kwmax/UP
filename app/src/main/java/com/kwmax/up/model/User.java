package com.kwmax.up.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kwmax on 2019/10/8.
 */
@Entity
public class User {

    @Id(autoincrement = true)
    private Long id;

    private String name;
    private String password;
    private String phonenum;
    @Generated(hash = 367932995)
    public User(Long id, String name, String password, String phonenum) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phonenum = phonenum;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhonenum() {
        return this.phonenum;
    }
    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

}
