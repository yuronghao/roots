package com.emi.roots.entity;

public class AzSlideCat {
    private Integer cid;

    private String catName;

    private String catIdname;

    private Integer catStatus;

    private String catRemark;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    public String getCatIdname() {
        return catIdname;
    }

    public void setCatIdname(String catIdname) {
        this.catIdname = catIdname == null ? null : catIdname.trim();
    }

    public Integer getCatStatus() {
        return catStatus;
    }

    public void setCatStatus(Integer catStatus) {
        this.catStatus = catStatus;
    }

    public String getCatRemark() {
        return catRemark;
    }

    public void setCatRemark(String catRemark) {
        this.catRemark = catRemark == null ? null : catRemark.trim();
    }
}