package com.emi.roots.entity;

public class AzWeixinOptionsWithBLOBs extends AzWeixinOptions {
    private String welcome_config;

    private String addon_status;

    public String getWelcome_config() {
        return welcome_config;
    }

    public void setWelcome_config(String welcome_config) {
        this.welcome_config = welcome_config == null ? null : welcome_config.trim();
    }

    public String getAddon_status() {
        return addon_status;
    }

    public void setAddon_status(String addon_status) {
        this.addon_status = addon_status == null ? null : addon_status.trim();
    }
}