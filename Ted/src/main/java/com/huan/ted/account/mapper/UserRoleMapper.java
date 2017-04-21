/*
 * #{copyright}#
 */

package com.huan.ted.account.mapper;

import com.huan.ted.account.dto.UserRole;
import com.huan.ted.mybatis.common.Mapper;

/**
 * 角色分配功能Mapper.
 * 
 * @author huanghuan
 */
public interface UserRoleMapper extends Mapper<UserRole> {

    int deleteByUserId(Long userId);

    int deleteByRecord(UserRole record);

    int deleteByRoleId(Long roleId);

}