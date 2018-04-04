package com.emi.roots.entity;

public class AzTalkMessage {
    private Integer id;

    private Integer uid;

    private Integer create_time;

    private String content;

    private Integer talk_id;

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

    public Integer getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Integer create_time) {
        this.create_time = create_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getTalk_id() {
        return talk_id;
    }

    public void setTalk_id(Integer talk_id) {
        this.talk_id = talk_id;
    }
}