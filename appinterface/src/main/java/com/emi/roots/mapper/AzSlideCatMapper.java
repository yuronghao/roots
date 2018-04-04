package com.emi.roots.mapper;

import com.emi.roots.entity.AzSlideCat;

public interface AzSlideCatMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(AzSlideCat record);

    int insertSelective(AzSlideCat record);

    AzSlideCat selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(AzSlideCat record);

    int updateByPrimaryKeyWithBLOBs(AzSlideCat record);

    int updateByPrimaryKey(AzSlideCat record);
}