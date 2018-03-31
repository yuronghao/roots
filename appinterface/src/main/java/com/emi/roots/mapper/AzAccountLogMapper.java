package com.emi.roots.mapper;

import com.emi.roots.entity.AzAccountLog;

public interface AzAccountLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(AzAccountLog record);

    int insertSelective(AzAccountLog record);

    AzAccountLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(AzAccountLog record);

    int updateByPrimaryKey(AzAccountLog record);
}