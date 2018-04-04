package com.emi.roots.entity;

public class AzWeixinOptionsLink {
    private Integer id;

    private Integer uid;

    private Integer mp_id;

    private Byte is_creator;

    private Byte is_use;

    private String addon_status;

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

    public Integer getMp_id() {
        return mp_id;
    }

    public void setMp_id(Integer mp_id) {
        this.mp_id = mp_id;
    }

    public Byte getIs_creator() {
        return is_creator;
    }

    public void setIs_creator(Byte is_creator) {
        this.is_creator = is_creator;
    }

    public Byte getIs_use() {
        return is_use;
    }

    public void setIs_use(Byte is_use) {
        this.is_use = is_use;
    }

    public String getAddon_status() {
        return addon_status;
    }

    public void setAddon_status(String addon_status) {
        this.addon_status = addon_status == null ? null : addon_status.trim();
    }
}