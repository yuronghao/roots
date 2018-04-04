package com.emi.roots.mapper;

import com.emi.roots.entity.AzNav;

public interface AzNavMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzNav record);

    int insertSelective(AzNav record);

    AzNav selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzNav record);

    int updateByPrimaryKey(AzNav record);
}