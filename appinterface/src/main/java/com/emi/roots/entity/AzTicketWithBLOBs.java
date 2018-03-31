package com.emi.roots.entity;

public class AzTicketWithBLOBs extends AzTicket {
    private String comment;

    private String description;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}