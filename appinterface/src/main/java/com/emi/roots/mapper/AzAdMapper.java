package com.emi.roots.mapper;

import com.emi.roots.entity.AzAd;

public interface AzAdMapper {
    int deleteByPrimaryKey(Long adId);

    int insert(AzAd record);

    int insertSelective(AzAd record);

    AzAd selectByPrimaryKey(Long adId);

    int updateByPrimaryKeySelective(AzAd record);

    int updateByPrimaryKeyWithBLOBs(AzAd record);

    int updateByPrimaryKey(AzAd record);
}