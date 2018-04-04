package com.emi.roots.mapper;

import com.emi.roots.entity.AzWeixinOptions;
import com.emi.roots.entity.AzWeixinOptionsWithBLOBs;

public interface AzWeixinOptionsMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzWeixinOptionsWithBLOBs record);

    int insertSelective(AzWeixinOptionsWithBLOBs record);

    AzWeixinOptionsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzWeixinOptionsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AzWeixinOptionsWithBLOBs record);

    int updateByPrimaryKey(AzWeixinOptions record);
}