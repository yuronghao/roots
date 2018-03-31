package com.emi.roots.wechat.service;

import com.emi.roots.mapper.WeChatConfMapper;
import com.emi.roots.wechat.bean.WeChatConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by song on 2017/8/25.
 */
@Service
public class WechatBaseServiceImpl implements WechatBaseService {

    @Autowired
    private WeChatConfMapper weChatConfMapper;

    public WeChatConf getWechatconf() {
        return weChatConfMapper.getWechatconf();
    }
}
