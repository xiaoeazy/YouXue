package com.huan.ted.system.service;


import com.huan.ted.core.ProxySelf;
import com.huan.ted.system.dto.SysConfig;


public interface ISysConfigService extends IBaseService<SysConfig>, ProxySelf<ISysConfigService> {
    /**
     * 根据ConfigCode获取配置值.
     * 
     * @param configCode
     *            配置代码
     * @return 配置值
     */
            
    String getConfigValue(String configCode);
    
}
