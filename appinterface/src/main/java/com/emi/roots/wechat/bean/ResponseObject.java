package com.emi.roots.wechat.bean;

/**
 * 处理请求返回service处理类
 * Created by zhaoyf on 2015-01-13.
 */
public class ResponseObject {
    private String type=null;
    private Object object;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
