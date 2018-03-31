package com.emi.roots.mapper;

import com.emi.roots.entity.AzWeixinReplyText;

public interface AzWeixinReplyTextMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzWeixinReplyText record);

    int insertSelective(AzWeixinReplyText record);

    AzWeixinReplyText selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzWeixinReplyText record);

    int updateByPrimaryKeyWithBLOBs(AzWeixinReplyText record);

    int updateByPrimaryKey(AzWeixinReplyText record);
}