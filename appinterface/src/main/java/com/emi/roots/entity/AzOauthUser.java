package com.emi.roots.entity;

import java.util.Date;

public class AzOauthUser {
    private Integer id;

    private String from;

    private String name;

    private String head_img;

    private String uid;

    private String last_login_ip;

    private Date last_login_time;

    private Date create_time;

    private Integer login_times;

    private Integer status;

    private String openid;

    private Integer user_status;

    private Byte gag;

    private Integer follow_num;

    private String wechat_user_options;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from == null ? null : from.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img == null ? null : head_img.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip == null ? null : last_login_ip.trim();
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getLogin_times() {
        return login_times;
    }

    public void setLogin_times(Integer login_times) {
        this.login_times = login_times;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getUser_status() {
        return user_status;
    }

    public void setUser_status(Integer user_status) {
        this.user_status = user_status;
    }

    public Byte getGag() {
        return gag;
    }

    public void setGag(Byte gag) {
        this.gag = gag;
    }

    public Integer getFollow_num() {
        return follow_num;
    }

    public void setFollow_num(Integer follow_num) {
        this.follow_num = follow_num;
    }

    public String getWechat_user_options() {
        return wechat_user_options;
    }

    public void setWechat_user_options(String wechat_user_options) {
        this.wechat_user_options = wechat_user_options == null ? null : wechat_user_options.trim();
    }
}