package com.emi.roots.entity;

public class Area {
    private Integer id;

    private String name;

    private String shortname;

    private Integer level;

    private String parentcode;

    private String code;

    private Integer isprovincecity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode == null ? null : parentcode.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getIsprovincecity() {
        return isprovincecity;
    }

    public void setIsprovincecity(Integer isprovincecity) {
        this.isprovincecity = isprovincecity;
    }
}