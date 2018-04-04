package com.emi.roots.entity;

public class AzTalkMessagePush {
    private Integer id;

    private Integer uid;

    private Integer create_time;

    private Byte status;

    private Integer source_id;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getSource_id() {
        return source_id;
    }

    public void setSource_id(Integer source_id) {
        this.source_id = source_id;
    }

    public Integer getTalk_id() {
        return talk_id;
    }

    public void setTalk_id(Integer talk_id) {
        this.talk_id = talk_id;
    }
}