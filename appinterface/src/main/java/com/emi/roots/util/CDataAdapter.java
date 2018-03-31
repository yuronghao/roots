package com.emi.roots.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * xml生产适配器，在元素值前后加上<![CDATA[xxx]]>
 * Created by zhaoyf on 2014/12/24.
 */
public class CDataAdapter extends XmlAdapter<String, String> {
    @Override
    public String marshal(String str) throws Exception {
        return "<![CDATA[" + str + "]]>";
    }

    @Override
    public String unmarshal(String str) throws Exception {
        return str;
    }

}
