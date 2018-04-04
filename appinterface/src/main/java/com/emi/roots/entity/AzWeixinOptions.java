package com.emi.roots.entity;

public class AzWeixinOptions {
    private Integer id;

    private Integer uid;

    private String app_name;

    private String app_user;

    private String app_wxid;

    private String interface_url;

    private String headface_url;

    private String area;

    private String token;

    private Byte is_use;

    private String type;

    private String app_id;

    private String app_secret;

    private String app_token;

    private String app_key;

    private Integer group_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name == null ? null : app_name.trim();
    }

    public String getApp_user() {
        return app_user;
    }

    public void setApp_user(String app_user) {
        this.app_user = app_user == null ? null : app_user.trim();
    }

    public String getApp_wxid() {
        return app_wxid;
    }

    public void setApp_wxid(String app_wxid) {
        this.app_wxid = app_wxid == null ? null : app_wxid.trim();
    }

    public String getInterface_url() {
        return interface_url;
    }

    public void setInterface_url(String interface_url) {
        this.interface_url = interface_url == null ? null : interface_url.trim();
    }

    public String getHeadface_url() {
        return headface_url;
    }

    public void setHeadface_url(String headface_url) {
        this.headface_url = headface_url == null ? null : headface_url.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Byte getIs_use() {
        return is_use;
    }

    public void setIs_use(Byte is_use) {
        this.is_use = is_use;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id == null ? null : app_id.trim();
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret == null ? null : app_secret.trim();
    }

    public String getApp_token() {
        return app_token;
    }

    public void setApp_token(String app_token) {
        this.app_token = app_token == null ? null : app_token.trim();
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key == null ? null : app_key.trim();
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }
}