package com.emi.roots.mapper;

import com.emi.roots.entity.AzSof;

public interface AzSofMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzSof record);

    int insertSelective(AzSof record);

    AzSof selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzSof record);

    int updateByPrimaryKey(AzSof record);
}