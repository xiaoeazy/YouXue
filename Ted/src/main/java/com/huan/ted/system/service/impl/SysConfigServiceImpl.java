package com.huan.ted.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huan.ted.cache.CacheSet;
import com.huan.ted.core.IRequest;
import com.huan.ted.message.IMessagePublisher;
import com.huan.ted.system.dto.GlobalProfile;
import com.huan.ted.system.dto.SysConfig;
import com.huan.ted.system.mapper.SysConfigMapper;
import com.huan.ted.system.service.ISysConfigService;


@Service
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig> implements ISysConfigService { 

    @Autowired
    private SysConfigMapper configMapper;
    
    @Autowired
    private IMessagePublisher messagePublisher;
    
	@Override
    @CacheSet(cache = "config")
    public SysConfig insertSelective(IRequest request, SysConfig config) {
        super.insertSelective(request, config);
        //配置更改时 通知监听者
        messagePublisher.publish("config",
                new GlobalProfile(config.getConfigCode(), config.getConfigValue()));
        return config;
    }

    /*@Override
    @CacheDelete(cache = "config")
    public int deleteByPrimaryKey(Config config) {
        return super.deleteByPrimaryKey(config);
    }*/

    @Override
    @CacheSet(cache = "config")
    public SysConfig updateByPrimaryKeySelective(IRequest request, SysConfig config) {
        super.updateByPrimaryKeySelective(request, config);
        //配置更改时 通知监听者
        messagePublisher.publish("config",
                new GlobalProfile(config.getConfigCode(), config.getConfigValue()));
        return config;
    }

    @Override
    public String getConfigValue(String configCode) { 
        SysConfig config = configMapper.selectByCode(configCode);
        if(config !=null){
            return config.getConfigValue();
        }else return null;
    }
}
