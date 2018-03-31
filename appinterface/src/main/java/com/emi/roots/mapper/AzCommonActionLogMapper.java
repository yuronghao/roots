package com.emi.roots.mapper;

import com.emi.roots.entity.AzCommonActionLog;

public interface AzCommonActionLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzCommonActionLog record);

    int insertSelective(AzCommonActionLog record);

    AzCommonActionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzCommonActionLog record);

    int updateByPrimaryKey(AzCommonActionLog record);
}