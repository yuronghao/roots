package com.emi.roots.mapper;

import com.emi.roots.entity.AzReply;

public interface AzReplyMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzReply record);

    int insertSelective(AzReply record);

    AzReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzReply record);

    int updateByPrimaryKey(AzReply record);
}