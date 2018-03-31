package com.emi.roots.mapper;

import com.emi.roots.entity.AzOauthUser;

public interface AzOauthUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzOauthUser record);

    int insertSelective(AzOauthUser record);

    AzOauthUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzOauthUser record);

    int updateByPrimaryKeyWithBLOBs(AzOauthUser record);

    int updateByPrimaryKey(AzOauthUser record);
}