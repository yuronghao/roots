package com.emi.roots.entity;

public class AzGoodsCat {
    private Long term_id;

    private String name;

    private String slug;

    private String taxonomy;

    private Long parent;

    private Long count;

    private String path;

    private String seo_title;

    private String seo_keywords;

    private String seo_description;

    private String list_tpl;

    private String one_tpl;

    private Integer listorder;

    private Integer status;

    private String description;

    public Long getTerm_id() {
        return term_id;
    }

    public void setTerm_id(Long term_id) {
        this.term_id = term_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy == null ? null : taxonomy.trim();
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getSeo_title() {
        return seo_title;
    }

    public void setSeo_title(String seo_title) {
        this.seo_title = seo_title == null ? null : seo_title.trim();
    }

    public String getSeo_keywords() {
        return seo_keywords;
    }

    public void setSeo_keywords(String seo_keywords) {
        this.seo_keywords = seo_keywords == null ? null : seo_keywords.trim();
    }

    public String getSeo_description() {
        return seo_description;
    }

    public void setSeo_description(String seo_description) {
        this.seo_description = seo_description == null ? null : seo_description.trim();
    }

    public String getList_tpl() {
        return list_tpl;
    }

    public void setList_tpl(String list_tpl) {
        this.list_tpl = list_tpl == null ? null : list_tpl.trim();
    }

    public String getOne_tpl() {
        return one_tpl;
    }

    public void setOne_tpl(String one_tpl) {
        this.one_tpl = one_tpl == null ? null : one_tpl.trim();
    }

    public Integer getListorder() {
        return listorder;
    }

    public void setListorder(Integer listorder) {
        this.listorder = listorder;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}