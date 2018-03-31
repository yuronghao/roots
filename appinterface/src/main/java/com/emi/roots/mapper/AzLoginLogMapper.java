package com.emi.roots.mapper;

import com.emi.roots.entity.AzLoginLog;

public interface AzLoginLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzLoginLog record);

    int insertSelective(AzLoginLog record);

    AzLoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzLoginLog record);

    int updateByPrimaryKey(AzLoginLog record);
}