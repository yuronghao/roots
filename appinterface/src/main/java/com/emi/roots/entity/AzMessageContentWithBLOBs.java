package com.emi.roots.entity;

public class AzMessageContentWithBLOBs extends AzMessageContent {
    private String content;

    private String args;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args == null ? null : args.trim();
    }
}