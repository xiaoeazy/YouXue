/*
 * #{copyright}#
 */
package com.huan.ted.system.service;

import java.util.List;

import com.huan.ted.core.annotation.StdWho;
import com.huan.ted.core.IRequest;
import com.huan.ted.core.ProxySelf;
import com.huan.ted.system.dto.SysPreferences;

/**
 * 系统首选项接口.
 * 
 * @author huanghuan
 */
public interface ISysPreferencesService extends ProxySelf<ISysPreferencesService> {

    /**
     * 保存首选项.
     * 
     * @param requestContext
     *          系统上下文
     * @param preferences
     *          首选项信息集合
     * @return List<SysPreferences>
     *          返回保存结果
     */
    List<SysPreferences> saveSysPreferences(IRequest requestContext, @StdWho List<SysPreferences> preferences);
    
    /**
     * 查询首选项.
     * 
     * @param requestContext
     *          系统上下文
     * @param preferences
     *          查询参数
     * @return
     *          返回查询得到的首选项集合
     */
    List<SysPreferences> querySysPreferences(IRequest requestContext, SysPreferences preferences);
    
    /**
     * 查询某个首选项.
     * 
     * @param preference 首选项
     * @param userId 用户ID
     * @return
     */
    SysPreferences selectUserPreference(String preference, Long userId);
    
}
