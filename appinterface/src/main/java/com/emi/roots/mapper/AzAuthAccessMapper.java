package com.emi.roots.mapper;

import com.emi.roots.entity.AzAuthAccess;

public interface AzAuthAccessMapper extends SqlMapper {
    int insert(AzAuthAccess record);

    int insertSelective(AzAuthAccess record);
}