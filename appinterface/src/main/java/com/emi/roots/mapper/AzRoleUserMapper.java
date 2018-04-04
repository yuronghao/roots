package com.emi.roots.mapper;

import com.emi.roots.entity.AzRoleUser;

public interface AzRoleUserMapper extends SqlMapper {
    int insert(AzRoleUser record);

    int insertSelective(AzRoleUser record);
}