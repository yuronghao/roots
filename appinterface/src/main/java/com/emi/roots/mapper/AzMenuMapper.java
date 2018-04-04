package com.emi.roots.mapper;

import com.emi.roots.entity.AzMenu;

public interface AzMenuMapper extends SqlMapper {
    int deleteByPrimaryKey(Short id);

    int insert(AzMenu record);

    int insertSelective(AzMenu record);

    AzMenu selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(AzMenu record);

    int updateByPrimaryKey(AzMenu record);
}