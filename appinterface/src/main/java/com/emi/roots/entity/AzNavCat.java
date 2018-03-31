package com.emi.roots.entity;

public class AzNavCat {
    private Integer navcid;

    private String name;

    private Integer active;

    private String remark;

    public Integer getNavcid() {
        return navcid;
    }

    public void setNavcid(Integer navcid) {
        this.navcid = navcid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}