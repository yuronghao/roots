package com.emi.roots.entity;

public class AzSlide {
    private Long slideId;

    private Integer slideCid;

    private String slideName;

    private String slidePic;

    private String slideUrl;

    private String slideDes;

    private Integer slideStatus;

    private Integer listorder;

    private Integer creator;

    private String slideContent;

    public Long getSlideId() {
        return slideId;
    }

    public void setSlideId(Long slideId) {
        this.slideId = slideId;
    }

    public Integer getSlideCid() {
        return slideCid;
    }

    public void setSlideCid(Integer slideCid) {
        this.slideCid = slideCid;
    }

    public String getSlideName() {
        return slideName;
    }

    public void setSlideName(String slideName) {
        this.slideName = slideName == null ? null : slideName.trim();
    }

    public String getSlidePic() {
        return slidePic;
    }

    public void setSlidePic(String slidePic) {
        this.slidePic = slidePic == null ? null : slidePic.trim();
    }

    public String getSlideUrl() {
        return slideUrl;
    }

    public void setSlideUrl(String slideUrl) {
        this.slideUrl = slideUrl == null ? null : slideUrl.trim();
    }

    public String getSlideDes() {
        return slideDes;
    }

    public void setSlideDes(String slideDes) {
        this.slideDes = slideDes == null ? null : slideDes.trim();
    }

    public Integer getSlideStatus() {
        return slideStatus;
    }

    public void setSlideStatus(Integer slideStatus) {
        this.slideStatus = slideStatus;
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

    public String getSlideContent() {
        return slideContent;
    }

    public void setSlideContent(String slideContent) {
        this.slideContent = slideContent == null ? null : slideContent.trim();
    }
}