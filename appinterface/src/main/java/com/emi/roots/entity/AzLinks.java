package com.emi.roots.entity;

public class AzLinks {
    private Long link_id;

    private String link_url;

    private String link_name;

    private String link_image;

    private String link_target;

    private Integer link_status;

    private Integer link_rating;

    private String link_rel;

    private Integer listorder;

    private String link_description;

    public Long getLink_id() {
        return link_id;
    }

    public void setLink_id(Long link_id) {
        this.link_id = link_id;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url == null ? null : link_url.trim();
    }

    public String getLink_name() {
        return link_name;
    }

    public void setLink_name(String link_name) {
        this.link_name = link_name == null ? null : link_name.trim();
    }

    public String getLink_image() {
        return link_image;
    }

    public void setLink_image(String link_image) {
        this.link_image = link_image == null ? null : link_image.trim();
    }

    public String getLink_target() {
        return link_target;
    }

    public void setLink_target(String link_target) {
        this.link_target = link_target == null ? null : link_target.trim();
    }

    public Integer getLink_status() {
        return link_status;
    }

    public void setLink_status(Integer link_status) {
        this.link_status = link_status;
    }

    public Integer getLink_rating() {
        return link_rating;
    }

    public void setLink_rating(Integer link_rating) {
        this.link_rating = link_rating;
    }

    public String getLink_rel() {
        return link_rel;
    }

    public void setLink_rel(String link_rel) {
        this.link_rel = link_rel == null ? null : link_rel.trim();
    }

    public Integer getListorder() {
        return listorder;
    }

    public void setListorder(Integer listorder) {
        this.listorder = listorder;
    }

    public String getLink_description() {
        return link_description;
    }

    public void setLink_description(String link_description) {
        this.link_description = link_description == null ? null : link_description.trim();
    }
}