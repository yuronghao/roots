package com.emi.roots.mapper;

import com.emi.roots.entity.AzTalkPush;

public interface AzTalkPushMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzTalkPush record);

    int insertSelective(AzTalkPush record);

    AzTalkPush selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzTalkPush record);

    int updateByPrimaryKey(AzTalkPush record);
}