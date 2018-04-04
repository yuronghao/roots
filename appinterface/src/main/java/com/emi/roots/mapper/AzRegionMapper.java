package com.emi.roots.mapper;

import com.emi.roots.entity.AzRegion;

public interface AzRegionMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzRegion record);

    int insertSelective(AzRegion record);

    AzRegion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzRegion record);

    int updateByPrimaryKey(AzRegion record);
}