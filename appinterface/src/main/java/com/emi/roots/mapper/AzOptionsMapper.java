package com.emi.roots.mapper;

import com.emi.roots.entity.AzOptions;

public interface AzOptionsMapper {
    int deleteByPrimaryKey(Long optionId);

    int insert(AzOptions record);

    int insertSelective(AzOptions record);

    AzOptions selectByPrimaryKey(Long optionId);

    int updateByPrimaryKeySelective(AzOptions record);

    int updateByPrimaryKeyWithBLOBs(AzOptions record);

    int updateByPrimaryKey(AzOptions record);
}