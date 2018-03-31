package com.emi.roots.entity;

public class AzOptions {
    private Long optionId;

    private String optionName;

    private Integer autoload;

    private String optionValue;

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName == null ? null : optionName.trim();
    }

    public Integer getAutoload() {
        return autoload;
    }

    public void setAutoload(Integer autoload) {
        this.autoload = autoload;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue == null ? null : optionValue.trim();
    }
}