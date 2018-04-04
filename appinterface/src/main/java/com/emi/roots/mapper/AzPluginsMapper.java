package com.emi.roots.mapper;

import com.emi.roots.entity.AzPlugins;
import com.emi.roots.entity.AzPluginsWithBLOBs;

public interface AzPluginsMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzPluginsWithBLOBs record);

    int insertSelective(AzPluginsWithBLOBs record);

    AzPluginsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzPluginsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AzPluginsWithBLOBs record);

    int updateByPrimaryKey(AzPlugins record);
}