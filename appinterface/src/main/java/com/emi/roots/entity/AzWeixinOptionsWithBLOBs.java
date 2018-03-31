package com.emi.roots.entity;

public class AzWeixinOptionsWithBLOBs extends AzWeixinOptions {
    private String welcomeConfig;

    private String addonStatus;

    public String getWelcomeConfig() {
        return welcomeConfig;
    }

    public void setWelcomeConfig(String welcomeConfig) {
        this.welcomeConfig = welcomeConfig == null ? null : welcomeConfig.trim();
    }

    public String getAddonStatus() {
        return addonStatus;
    }

    public void setAddonStatus(String addonStatus) {
        this.addonStatus = addonStatus == null ? null : addonStatus.trim();
    }
}