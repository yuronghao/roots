package com.emi.roots.mapper;

import com.emi.roots.entity.AzCharging;

public interface AzChargingMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzCharging record);

    int insertSelective(AzCharging record);

    AzCharging selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzCharging record);

    int updateByPrimaryKey(AzCharging record);
}