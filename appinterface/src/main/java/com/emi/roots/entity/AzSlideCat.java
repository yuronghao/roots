package com.emi.roots.entity;

public class AzSlideCat {
    private Integer cid;

    private String cat_name;

    private String cat_idname;

    private Integer cat_status;

    private String cat_remark;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name == null ? null : cat_name.trim();
    }

    public String getCat_idname() {
        return cat_idname;
    }

    public void setCat_idname(String cat_idname) {
        this.cat_idname = cat_idname == null ? null : cat_idname.trim();
    }

    public Integer getCat_status() {
        return cat_status;
    }

    public void setCat_status(Integer cat_status) {
        this.cat_status = cat_status;
    }

    public String getCat_remark() {
        return cat_remark;
    }

    public void setCat_remark(String cat_remark) {
        this.cat_remark = cat_remark == null ? null : cat_remark.trim();
    }
}