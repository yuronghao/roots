package com.emi.roots.mapper;

import com.emi.roots.entity.AzAddress;

public interface AzAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzAddress record);

    int insertSelective(AzAddress record);

    AzAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzAddress record);

    int updateByPrimaryKeyWithBLOBs(AzAddress record);

    int updateByPrimaryKey(AzAddress record);
}