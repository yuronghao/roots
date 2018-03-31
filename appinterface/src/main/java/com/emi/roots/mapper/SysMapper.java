package com.emi.roots.mapper;

import com.emi.roots.entity.Area;
import com.emi.roots.wechat.bean.WeChatConf;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysMapper extends SqlMapper{
	List<Area> getCity(@Param("code") String code);
	public List<Map> getAreaDataList();
	public WeChatConf getWeChatConfByOrgId(@Param("orgid") int orgid);
	public void updateAccesstoken(@Param("accesstoken") String accesstoken, @Param("orgid") int orgid);
	public void updateJSDKTicket(@Param("jsdkticket") String accesstoken, @Param("orgid") int orgid);
	public void addSysLog(@Param("username") String username, @Param("ip") String ip, @Param("type") String type, @Param("msg") String msg);
	public void updateTemplateMsg(@Param("state") int state, @Param("id") int id);



//	List<Member> getMemberList();

	void updateMemberListUnionidByMemberid(@Param("id") Integer id, @Param("unionid") String unionid);
}
