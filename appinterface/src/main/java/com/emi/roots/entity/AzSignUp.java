package com.emi.roots.entity;

import java.util.Date;

public class AzSignUp {
    private Integer id;

    private Integer uid;

    private Integer object_id;

    private Boolean sign_status;

    private Date create_time;

    private Date check_time;

    private Boolean audit_status;

    private Boolean pay_status;

    private String order_id;

    private String comment;

    private String opus;

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

    public Integer getObject_id() {
        return object_id;
    }

    public void setObject_id(Integer object_id) {
        this.object_id = object_id;
    }

    public Boolean getSign_status() {
        return sign_status;
    }

    public void setSign_status(Boolean sign_status) {
        this.sign_status = sign_status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getCheck_time() {
        return check_time;
    }

    public void setCheck_time(Date check_time) {
        this.check_time = check_time;
    }

    public Boolean getAudit_status() {
        return audit_status;
    }

    public void setAudit_status(Boolean audit_status) {
        this.audit_status = audit_status;
    }

    public Boolean getPay_status() {
        return pay_status;
    }

    public void setPay_status(Boolean pay_status) {
        this.pay_status = pay_status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id == null ? null : order_id.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getOpus() {
        return opus;
    }

    public void setOpus(String opus) {
        this.opus = opus == null ? null : opus.trim();
    }
}