package com.emi.roots.entity;

public class AzKeyword {
    private Integer id;

    private String keyword;

    private String token;

    private String addon;

    private Integer aim_id;

    private Integer cTime;

    private Integer keyword_length;

    private Byte keyword_type;

    private Integer extra_int;

    private Integer request_count;

    private String extra_text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getAddon() {
        return addon;
    }

    public void setAddon(String addon) {
        this.addon = addon == null ? null : addon.trim();
    }

    public Integer getAim_id() {
        return aim_id;
    }

    public void setAim_id(Integer aim_id) {
        this.aim_id = aim_id;
    }

    public Integer getcTime() {
        return cTime;
    }

    public void setcTime(Integer cTime) {
        this.cTime = cTime;
    }

    public Integer getKeyword_length() {
        return keyword_length;
    }

    public void setKeyword_length(Integer keyword_length) {
        this.keyword_length = keyword_length;
    }

    public Byte getKeyword_type() {
        return keyword_type;
    }

    public void setKeyword_type(Byte keyword_type) {
        this.keyword_type = keyword_type;
    }

    public Integer getExtra_int() {
        return extra_int;
    }

    public void setExtra_int(Integer extra_int) {
        this.extra_int = extra_int;
    }

    public Integer getRequest_count() {
        return request_count;
    }

    public void setRequest_count(Integer request_count) {
        this.request_count = request_count;
    }

    public String getExtra_text() {
        return extra_text;
    }

    public void setExtra_text(String extra_text) {
        this.extra_text = extra_text == null ? null : extra_text.trim();
    }
}