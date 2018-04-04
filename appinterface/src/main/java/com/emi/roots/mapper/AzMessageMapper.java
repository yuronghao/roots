package com.emi.roots.mapper;

import com.emi.roots.entity.AzMessage;

public interface AzMessageMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzMessage record);

    int insertSelective(AzMessage record);

    AzMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzMessage record);

    int updateByPrimaryKey(AzMessage record);
}