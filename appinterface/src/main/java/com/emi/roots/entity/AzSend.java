package com.emi.roots.entity;

public class AzSend {
    private Short id;

    private String msgtype;

    private String media_id;

    private Integer sendtime;

    private Short group_id;

    private Boolean send_type;

    private Boolean status;

    private String token;

    private String comment;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype == null ? null : msgtype.trim();
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id == null ? null : media_id.trim();
    }

    public Integer getSendtime() {
        return sendtime;
    }

    public void setSendtime(Integer sendtime) {
        this.sendtime = sendtime;
    }

    public Short getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Short group_id) {
        this.group_id = group_id;
    }

    public Boolean getSend_type() {
        return send_type;
    }

    public void setSend_type(Boolean send_type) {
        this.send_type = send_type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}