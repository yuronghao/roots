package com.emi.roots.entity;

public class AzMessage {
    private Integer id;

    private Integer content_id;

    private Integer from_uid;

    private Integer to_uid;

    private Long create_time;

    private Byte isread;

    private Integer read_time;

    private Byte status;

    private Byte isdel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContent_id() {
        return content_id;
    }

    public void setContent_id(Integer content_id) {
        this.content_id = content_id;
    }

    public Integer getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(Integer from_uid) {
        this.from_uid = from_uid;
    }

    public Integer getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(Integer to_uid) {
        this.to_uid = to_uid;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Byte getIsread() {
        return isread;
    }

    public void setIsread(Byte isread) {
        this.isread = isread;
    }

    public Integer getRead_time() {
        return read_time;
    }

    public void setRead_time(Integer read_time) {
        this.read_time = read_time;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsdel() {
        return isdel;
    }

    public void setIsdel(Byte isdel) {
        this.isdel = isdel;
    }
}