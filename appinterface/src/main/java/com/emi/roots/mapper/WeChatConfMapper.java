package com.emi.roots.mapper;

import com.emi.roots.wechat.bean.WeChatConf;
import org.apache.ibatis.annotations.Param;

public interface WeChatConfMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeChatConf record);

    int insertSelective(WeChatConf record);

    WeChatConf selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeChatConf record);

    int updateByPrimaryKey(WeChatConf record);

    public WeChatConf getWechatconf();

    public void updateJSDKTicket(@Param("jsdkticket") String sss, @Param("id") int id);

    public void updateAccesstoken(@Param("accesstoken") String accesstoken, @Param("id") int id);

    public void updateApiTicket(@Param("apiticket") String apiticket, @Param("id") int id);
}