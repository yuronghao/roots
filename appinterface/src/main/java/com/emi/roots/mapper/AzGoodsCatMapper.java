package com.emi.roots.mapper;

import com.emi.roots.entity.AzGoodsCat;

public interface AzGoodsCatMapper {
    int deleteByPrimaryKey(Long termId);

    int insert(AzGoodsCat record);

    int insertSelective(AzGoodsCat record);

    AzGoodsCat selectByPrimaryKey(Long termId);

    int updateByPrimaryKeySelective(AzGoodsCat record);

    int updateByPrimaryKeyWithBLOBs(AzGoodsCat record);

    int updateByPrimaryKey(AzGoodsCat record);
}