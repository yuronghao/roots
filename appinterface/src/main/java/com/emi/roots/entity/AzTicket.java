package com.emi.roots.entity;

import java.math.BigDecimal;

public class AzTicket {
    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer parent_id;

    private Boolean ticket_type;

    private Integer ticket_id;

    private String start_time;

    private String end_time;

    private String sale_start_time;

    private String sale_end_time;

    private Integer start_date;

    private Integer end_date;

    private String week;

    private String pic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Boolean getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(Boolean ticket_type) {
        this.ticket_type = ticket_type;
    }

    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time == null ? null : start_time.trim();
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time == null ? null : end_time.trim();
    }

    public String getSale_start_time() {
        return sale_start_time;
    }

    public void setSale_start_time(String sale_start_time) {
        this.sale_start_time = sale_start_time == null ? null : sale_start_time.trim();
    }

    public String getSale_end_time() {
        return sale_end_time;
    }

    public void setSale_end_time(String sale_end_time) {
        this.sale_end_time = sale_end_time == null ? null : sale_end_time.trim();
    }

    public Integer getStart_date() {
        return start_date;
    }

    public void setStart_date(Integer start_date) {
        this.start_date = start_date;
    }

    public Integer getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Integer end_date) {
        this.end_date = end_date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }
}