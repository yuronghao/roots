package com.emi.roots.wechat.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 模板消息推送后形成的响应，返回至Service进行入库更新
 * Created by zhaoyf on 2015-01-13.
 */
@XmlRootElement(name = "xml")
public class TemplateOutMessage extends  OutMessage{
    private Long msgid;//消息id
    private String status;//消息状态


    public long getMsgid() {
        return msgid;
    }

    public void setMsgid(Long msgid) {
        this.msgid = msgid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
