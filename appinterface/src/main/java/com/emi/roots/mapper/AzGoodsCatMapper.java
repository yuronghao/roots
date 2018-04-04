package com.emi.roots.mapper;

import com.emi.roots.entity.AzGoodsCat;

public interface AzGoodsCatMapper extends SqlMapper {
    int deleteByPrimaryKey(Long term_id);

    int insert(AzGoodsCat record);

    int insertSelective(AzGoodsCat record);

    AzGoodsCat selectByPrimaryKey(Long term_id);

    int updateByPrimaryKeySelective(AzGoodsCat record);

    int updateByPrimaryKeyWithBLOBs(AzGoodsCat record);

    int updateByPrimaryKey(AzGoodsCat record);
}