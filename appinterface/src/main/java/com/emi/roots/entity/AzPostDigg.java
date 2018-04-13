package com.emi.roots.entity;

public class AzPostDigg {
    private Integer id;

    private Integer uid;

    private Integer followId;

    private Long createTime;

    private Integer undigg;

    private Integer fabulous;

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

    public Integer getFollowId() {
        return followId;
    }

    public void setFollowId(Integer followId) {
        this.followId = followId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getUndigg() {
        return undigg;
    }

    public void setUndigg(Integer undigg) {
        this.undigg = undigg;
    }

    public Integer getFabulous() {
        return fabulous;
    }

    public void setFabulous(Integer fabulous) {
        this.fabulous = fabulous;
    }
}