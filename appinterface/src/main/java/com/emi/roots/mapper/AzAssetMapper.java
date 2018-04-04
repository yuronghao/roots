package com.emi.roots.mapper;

import com.emi.roots.entity.AzAsset;

public interface AzAssetMapper extends SqlMapper {
    int deleteByPrimaryKey(Long aid);

    int insert(AzAsset record);

    int insertSelective(AzAsset record);

    AzAsset selectByPrimaryKey(Long aid);

    int updateByPrimaryKeySelective(AzAsset record);

    int updateByPrimaryKeyWithBLOBs(AzAsset record);

    int updateByPrimaryKey(AzAsset record);
}