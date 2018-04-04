package com.emi.roots.entity;

public class AzSlide {
    private Long slide_id;

    private Integer slide_cid;

    private String slide_name;

    private String slide_pic;

    private String slide_url;

    private String slide_des;

    private Integer slide_status;

    private Integer listorder;

    private Integer creator;

    private String slide_content;

    public Long getSlide_id() {
        return slide_id;
    }

    public void setSlide_id(Long slide_id) {
        this.slide_id = slide_id;
    }

    public Integer getSlide_cid() {
        return slide_cid;
    }

    public void setSlide_cid(Integer slide_cid) {
        this.slide_cid = slide_cid;
    }

    public String getSlide_name() {
        return slide_name;
    }

    public void setSlide_name(String slide_name) {
        this.slide_name = slide_name == null ? null : slide_name.trim();
    }

    public String getSlide_pic() {
        return slide_pic;
    }

    public void setSlide_pic(String slide_pic) {
        this.slide_pic = slide_pic == null ? null : slide_pic.trim();
    }

    public String getSlide_url() {
        return slide_url;
    }

    public void setSlide_url(String slide_url) {
        this.slide_url = slide_url == null ? null : slide_url.trim();
    }

    public String getSlide_des() {
        return slide_des;
    }

    public void setSlide_des(String slide_des) {
        this.slide_des = slide_des == null ? null : slide_des.trim();
    }

    public Integer getSlide_status() {
        return slide_status;
    }

    public void setSlide_status(Integer slide_status) {
        this.slide_status = slide_status;
    }

    public Integer getListorder() {
        return listorder;
    }

    public void setListorder(Integer listorder) {
        this.listorder = listorder;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getSlide_content() {
        return slide_content;
    }

    public void setSlide_content(String slide_content) {
        this.slide_content = slide_content == null ? null : slide_content.trim();
    }
}