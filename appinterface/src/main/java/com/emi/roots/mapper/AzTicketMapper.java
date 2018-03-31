package com.emi.roots.mapper;

import com.emi.roots.entity.AzTicket;
import com.emi.roots.entity.AzTicketWithBLOBs;

public interface AzTicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzTicketWithBLOBs record);

    int insertSelective(AzTicketWithBLOBs record);

    AzTicketWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzTicketWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AzTicketWithBLOBs record);

    int updateByPrimaryKey(AzTicket record);
}