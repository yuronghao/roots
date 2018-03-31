package com.emi.roots.entity;

public class AzLinks {
    private Long linkId;

    private String linkUrl;

    private String linkName;

    private String linkImage;

    private String linkTarget;

    private Integer linkStatus;

    private Integer linkRating;

    private String linkRel;

    private Integer listorder;

    private String linkDescription;

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage == null ? null : linkImage.trim();
    }

    public String getLinkTarget() {
        return linkTarget;
    }

    public void setLinkTarget(String linkTarget) {
        this.linkTarget = linkTarget == null ? null : linkTarget.trim();
    }

    public Integer getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(Integer linkStatus) {
        this.linkStatus = linkStatus;
    }

    public Integer getLinkRating() {
        return linkRating;
    }

    public void setLinkRating(Integer linkRating) {
        this.linkRating = linkRating;
    }

    public String getLinkRel() {
        return linkRel;
    }

    public void setLinkRel(String linkRel) {
        this.linkRel = linkRel == null ? null : linkRel.trim();
    }

    public Integer getListorder() {
        return listorder;
    }

    public void setListorder(Integer listorder) {
        this.listorder = listorder;
    }

    public String getLinkDescription() {
        return linkDescription;
    }

    public void setLinkDescription(String linkDescription) {
        this.linkDescription = linkDescription == null ? null : linkDescription.trim();
    }
}