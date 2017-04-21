/*
 * #{copyright}#
 */
package com.huan.ted.function.service;

import java.util.List;

import com.huan.ted.core.annotation.StdWho;
import com.huan.ted.core.IRequest;
import com.huan.ted.core.ProxySelf;
import com.huan.ted.function.dto.RoleFunction;
import com.huan.ted.system.service.IBaseService;

/**
 * @author liuxiawang
 * @author njq.niu@hand-china.com
 */
public interface IRoleFunctionService extends IBaseService<RoleFunction>, ProxySelf<IRoleFunctionService> {

    /**
     * 保存角色分配的功能.
     * 
     * @param requestContext requestContext
     * @param rolefunctions functions
     * @return list
     */
    List<RoleFunction> batchUpdate(IRequest requestContext, @StdWho List<RoleFunction> rolefunctions);

    /**
     * 从缓存中查询角色的所有功能ID的集合. 
     * @param roleId 角色id
     * @return roleFunction
     */
    Long[] getRoleFunctionById(Long roleId);
    
    
    /**
     * 清空角色功能.
     * 
     * @param roleId 角色id
     */
    void clearRoleFunctionByRoleId(Long roleId);
    
    /**
     * 重新加载角色资源缓存.
     * 
     * @param roleId 角色id
     */
    void reloadRoleResourceCache(Long roleId);
}
