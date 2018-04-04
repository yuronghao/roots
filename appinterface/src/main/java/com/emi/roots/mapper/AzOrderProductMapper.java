package com.emi.roots.mapper;

import com.emi.roots.entity.AzOrderProduct;

public interface AzOrderProductMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzOrderProduct record);

    int insertSelective(AzOrderProduct record);

    AzOrderProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzOrderProduct record);

    int updateByPrimaryKeyWithBLOBs(AzOrderProduct record);

    int updateByPrimaryKey(AzOrderProduct record);
}