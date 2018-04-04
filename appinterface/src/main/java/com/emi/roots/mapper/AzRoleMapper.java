package com.emi.roots.mapper;

import com.emi.roots.entity.AzRole;

public interface AzRoleMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzRole record);

    int insertSelective(AzRole record);

    AzRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzRole record);

    int updateByPrimaryKey(AzRole record);
}