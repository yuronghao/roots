package com.emi.roots.mapper;

import com.emi.roots.entity.AzRoute;

public interface AzRouteMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzRoute record);

    int insertSelective(AzRoute record);

    AzRoute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzRoute record);

    int updateByPrimaryKey(AzRoute record);
}