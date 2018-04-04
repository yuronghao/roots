package com.emi.roots.mapper;

import com.emi.roots.entity.AzOptions;

public interface AzOptionsMapper extends SqlMapper {
    int deleteByPrimaryKey(Long option_id);

    int insert(AzOptions record);

    int insertSelective(AzOptions record);

    AzOptions selectByPrimaryKey(Long option_id);

    int updateByPrimaryKeySelective(AzOptions record);

    int updateByPrimaryKeyWithBLOBs(AzOptions record);

    int updateByPrimaryKey(AzOptions record);
}