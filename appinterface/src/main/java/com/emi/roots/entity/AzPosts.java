package com.emi.roots.entity;

import java.util.Date;

public class AzPosts {
    private Long id;

    private Long post_author;

    private String post_keywords;

    private String post_source;

    private Date post_date;

    private Integer post_status;

    private Integer comment_status;

    private Date post_modified;

    private Long post_parent;

    private Integer post_type;

    private String post_mime_type;

    private Long comment_count;

    private Integer post_hits;

    private Integer post_like;

    private Boolean istop;

    private Boolean recommended;

    private Date start_date;

    private Date end_date;

    private String price;

    private String original_price;

    private Short number;

    private Byte type;

    private Short listorder;

    private Boolean status;

    private String address;

    private String contacts;

    private Boolean is_show;

    private String commodity_id;

    private Integer review_id;

    private String material_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPost_author() {
        return post_author;
    }

    public void setPost_author(Long post_author) {
        this.post_author = post_author;
    }

    public String getPost_keywords() {
        return post_keywords;
    }

    public void setPost_keywords(String post_keywords) {
        this.post_keywords = post_keywords == null ? null : post_keywords.trim();
    }

    public String getPost_source() {
        return post_source;
    }

    public void setPost_source(String post_source) {
        this.post_source = post_source == null ? null : post_source.trim();
    }

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public Integer getPost_status() {
        return post_status;
    }

    public void setPost_status(Integer post_status) {
        this.post_status = post_status;
    }

    public Integer getComment_status() {
        return comment_status;
    }

    public void setComment_status(Integer comment_status) {
        this.comment_status = comment_status;
    }

    public Date getPost_modified() {
        return post_modified;
    }

    public void setPost_modified(Date post_modified) {
        this.post_modified = post_modified;
    }

    public Long getPost_parent() {
        return post_parent;
    }

    public void setPost_parent(Long post_parent) {
        this.post_parent = post_parent;
    }

    public Integer getPost_type() {
        return post_type;
    }

    public void setPost_type(Integer post_type) {
        this.post_type = post_type;
    }

    public String getPost_mime_type() {
        return post_mime_type;
    }

    public void setPost_mime_type(String post_mime_type) {
        this.post_mime_type = post_mime_type == null ? null : post_mime_type.trim();
    }

    public Long getComment_count() {
        return comment_count;
    }

    public void setComment_count(Long comment_count) {
        this.comment_count = comment_count;
    }

    public Integer getPost_hits() {
        return post_hits;
    }

    public void setPost_hits(Integer post_hits) {
        this.post_hits = post_hits;
    }

    public Integer getPost_like() {
        return post_like;
    }

    public void setPost_like(Integer post_like) {
        this.post_like = post_like;
    }

    public Boolean getIstop() {
        return istop;
    }

    public void setIstop(Boolean istop) {
        this.istop = istop;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price == null ? null : original_price.trim();
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Short getListorder() {
        return listorder;
    }

    public void setListorder(Short listorder) {
        this.listorder = listorder;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public Boolean getIs_show() {
        return is_show;
    }

    public void setIs_show(Boolean is_show) {
        this.is_show = is_show;
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id == null ? null : commodity_id.trim();
    }

    public Integer getReview_id() {
        return review_id;
    }

    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id == null ? null : material_id.trim();
    }
}