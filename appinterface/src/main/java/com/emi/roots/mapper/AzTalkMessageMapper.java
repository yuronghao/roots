package com.emi.roots.mapper;

import com.emi.roots.entity.AzTalkMessage;

public interface AzTalkMessageMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzTalkMessage record);

    int insertSelective(AzTalkMessage record);

    AzTalkMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzTalkMessage record);

    int updateByPrimaryKey(AzTalkMessage record);
}