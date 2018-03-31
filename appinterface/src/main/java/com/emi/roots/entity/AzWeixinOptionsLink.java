package com.emi.roots.entity;

public class AzWeixinOptionsLink {
    private Integer id;

    private Integer uid;

    private Integer mpId;

    private Byte isCreator;

    private Byte isUse;

    private String addonStatus;

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

    public Integer getMpId() {
        return mpId;
    }

    public void setMpId(Integer mpId) {
        this.mpId = mpId;
    }

    public Byte getIsCreator() {
        return isCreator;
    }

    public void setIsCreator(Byte isCreator) {
        this.isCreator = isCreator;
    }

    public Byte getIsUse() {
        return isUse;
    }

    public void setIsUse(Byte isUse) {
        this.isUse = isUse;
    }

    public String getAddonStatus() {
        return addonStatus;
    }

    public void setAddonStatus(String addonStatus) {
        this.addonStatus = addonStatus == null ? null : addonStatus.trim();
    }
}