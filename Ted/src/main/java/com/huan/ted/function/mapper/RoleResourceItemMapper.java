/*
 * #{copyright}#
 */
package com.huan.ted.function.mapper;


import com.huan.ted.function.dto.RoleResourceItem;

import java.util.List;

/**
 * 
 * @author njq.niu@hand-china.com
 *
 * 2016年4月8日
 */
public interface RoleResourceItemMapper {
    
    int deleteByResourceItemId(Long itemId);
    
    int deleteByRoleIdAndFunctionId(Long roleId, Long functionId);

    int insert(RoleResourceItem record);
    
    List<RoleResourceItem> selectResourceItemsByRole(Long roleId);
    
    
    RoleResourceItem selectByRoleIdAndResourceItemId(Long roleId, Long resourceItemId);
}