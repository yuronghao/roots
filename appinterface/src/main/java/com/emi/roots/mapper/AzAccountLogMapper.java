package com.emi.roots.mapper;

import com.emi.roots.entity.AzAccountLog;

public interface AzAccountLogMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer log_id);

    int insert(AzAccountLog record);

    int insertSelective(AzAccountLog record);

    AzAccountLog selectByPrimaryKey(Integer log_id);

    int updateByPrimaryKeySelective(AzAccountLog record);

    int updateByPrimaryKey(AzAccountLog record);
}