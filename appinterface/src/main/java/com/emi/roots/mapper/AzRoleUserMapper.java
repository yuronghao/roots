package com.emi.roots.mapper;

import com.emi.roots.entity.AzRoleUser;

public interface AzRoleUserMapper {
    int insert(AzRoleUser record);

    int insertSelective(AzRoleUser record);
}