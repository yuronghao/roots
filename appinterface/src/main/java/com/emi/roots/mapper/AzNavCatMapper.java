package com.emi.roots.mapper;

import com.emi.roots.entity.AzNavCat;

public interface AzNavCatMapper {
    int deleteByPrimaryKey(Integer navcid);

    int insert(AzNavCat record);

    int insertSelective(AzNavCat record);

    AzNavCat selectByPrimaryKey(Integer navcid);

    int updateByPrimaryKeySelective(AzNavCat record);

    int updateByPrimaryKeyWithBLOBs(AzNavCat record);

    int updateByPrimaryKey(AzNavCat record);
}