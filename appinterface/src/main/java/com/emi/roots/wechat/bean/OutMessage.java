package com.emi.roots.wechat.bean;


import com.emi.roots.util.CDataAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Created by zhaoyf on 2014/12/23.
 */
@XmlRootElement(name = "xml")
public class OutMessage {
    @XmlJavaTypeAdapter(CDataAdapter.class)
	private String	ToUserName;
    @XmlJavaTypeAdapter(CDataAdapter.class)
	private String	FromUserName;
	private Long	CreateTime;
	//private int		FuncFlag	= 0;

	public String getToUserName() {
		return ToUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

    @XmlElement(name = "CreateTime")
	public Long getCreateTime() {
		return CreateTime;
	}

 /*   @XmlElement(name = "FuncFlag")
	public int getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}*/
}