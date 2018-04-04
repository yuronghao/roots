package com.emi.roots.entity;

public class AzPostsWithBLOBs extends AzPosts {
    private String post_content;

    private String post_title;

    private String post_link;

    private String post_excerpt;

    private String post_content_filtered;

    private String smeta;

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content == null ? null : post_content.trim();
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title == null ? null : post_title.trim();
    }

    public String getPost_link() {
        return post_link;
    }

    public void setPost_link(String post_link) {
        this.post_link = post_link == null ? null : post_link.trim();
    }

    public String getPost_excerpt() {
        return post_excerpt;
    }

    public void setPost_excerpt(String post_excerpt) {
        this.post_excerpt = post_excerpt == null ? null : post_excerpt.trim();
    }

    public String getPost_content_filtered() {
        return post_content_filtered;
    }

    public void setPost_content_filtered(String post_content_filtered) {
        this.post_content_filtered = post_content_filtered == null ? null : post_content_filtered.trim();
    }

    public String getSmeta() {
        return smeta;
    }

    public void setSmeta(String smeta) {
        this.smeta = smeta == null ? null : smeta.trim();
    }
}