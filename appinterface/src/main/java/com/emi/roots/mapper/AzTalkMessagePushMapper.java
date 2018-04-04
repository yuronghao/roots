package com.emi.roots.mapper;

import com.emi.roots.entity.AzTalkMessagePush;

public interface AzTalkMessagePushMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzTalkMessagePush record);

    int insertSelective(AzTalkMessagePush record);

    AzTalkMessagePush selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzTalkMessagePush record);

    int updateByPrimaryKey(AzTalkMessagePush record);
}