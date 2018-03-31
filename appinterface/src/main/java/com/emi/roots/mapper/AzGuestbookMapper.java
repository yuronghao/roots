package com.emi.roots.mapper;

import com.emi.roots.entity.AzGuestbook;

public interface AzGuestbookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzGuestbook record);

    int insertSelective(AzGuestbook record);

    AzGuestbook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzGuestbook record);

    int updateByPrimaryKeyWithBLOBs(AzGuestbook record);

    int updateByPrimaryKey(AzGuestbook record);
}