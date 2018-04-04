package com.emi.roots.entity;

public class AzOptions {
    private Long option_id;

    private String option_name;

    private Integer autoload;

    private String option_value;

    public Long getOption_id() {
        return option_id;
    }

    public void setOption_id(Long option_id) {
        this.option_id = option_id;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name == null ? null : option_name.trim();
    }

    public Integer getAutoload() {
        return autoload;
    }

    public void setAutoload(Integer autoload) {
        this.autoload = autoload;
    }

    public String getOption_value() {
        return option_value;
    }

    public void setOption_value(String option_value) {
        this.option_value = option_value == null ? null : option_value.trim();
    }
}