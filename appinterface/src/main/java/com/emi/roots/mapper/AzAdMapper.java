package com.emi.roots.mapper;

import com.emi.roots.entity.AzAd;

public interface AzAdMapper extends SqlMapper {
    int deleteByPrimaryKey(Long ad_id);

    int insert(AzAd record);

    int insertSelective(AzAd record);

    AzAd selectByPrimaryKey(Long ad_id);

    int updateByPrimaryKeySelective(AzAd record);

    int updateByPrimaryKeyWithBLOBs(AzAd record);

    int updateByPrimaryKey(AzAd record);
}