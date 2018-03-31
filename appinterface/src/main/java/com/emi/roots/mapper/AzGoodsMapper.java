package com.emi.roots.mapper;

import com.emi.roots.entity.AzGoods;
import com.emi.roots.entity.AzGoodsWithBLOBs;

public interface AzGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzGoodsWithBLOBs record);

    int insertSelective(AzGoodsWithBLOBs record);

    AzGoodsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzGoodsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AzGoodsWithBLOBs record);

    int updateByPrimaryKey(AzGoods record);
}