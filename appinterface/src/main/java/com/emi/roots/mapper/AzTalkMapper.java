package com.emi.roots.mapper;

import com.emi.roots.entity.AzTalk;

public interface AzTalkMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzTalk record);

    int insertSelective(AzTalk record);

    AzTalk selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzTalk record);

    int updateByPrimaryKey(AzTalk record);
}