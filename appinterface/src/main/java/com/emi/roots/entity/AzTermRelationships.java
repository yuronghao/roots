package com.emi.roots.entity;

public class AzTermRelationships {
    private Long tid;

    private Long object_id;

    private Long term_id;

    private Integer listorder;

    private Integer status;

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getObject_id() {
        return object_id;
    }

    public void setObject_id(Long object_id) {
        this.object_id = object_id;
    }

    public Long getTerm_id() {
        return term_id;
    }

    public void setTerm_id(Long term_id) {
        this.term_id = term_id;
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
}