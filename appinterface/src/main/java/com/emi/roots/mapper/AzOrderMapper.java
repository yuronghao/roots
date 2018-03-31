package com.emi.roots.mapper;

import com.emi.roots.entity.AzOrder;

public interface AzOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzOrder record);

    int insertSelective(AzOrder record);

    AzOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzOrder record);

    int updateByPrimaryKeyWithBLOBs(AzOrder record);

    int updateByPrimaryKey(AzOrder record);
}