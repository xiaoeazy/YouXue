package com.huan.ted.system.mapper;

import com.huan.ted.mybatis.common.Mapper;
import com.huan.ted.system.dto.SysConfig;

/**
 * @author huanghuan
 */
public interface SysConfigMapper extends Mapper<SysConfig> {

    SysConfig selectByCode(String configCode);
}
