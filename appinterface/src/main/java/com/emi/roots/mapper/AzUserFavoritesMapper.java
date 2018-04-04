package com.emi.roots.mapper;

import com.emi.roots.entity.AzUserFavorites;

public interface AzUserFavoritesMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzUserFavorites record);

    int insertSelective(AzUserFavorites record);

    AzUserFavorites selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzUserFavorites record);

    int updateByPrimaryKey(AzUserFavorites record);
}