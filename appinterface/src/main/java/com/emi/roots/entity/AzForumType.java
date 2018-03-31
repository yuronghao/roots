package com.emi.roots.entity;

public class AzForumType {
    private Integer id;

    private String name;

    private Byte state;

    private Integer nabled;

    private Integer listorder;

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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getNabled() {
        return nabled;
    }

    public void setNabled(Integer nabled) {
        this.nabled = nabled;
    }

    public Integer getListorder() {
        return listorder;
    }

    public void setListorder(Integer listorder) {
        this.listorder = listorder;
    }
}