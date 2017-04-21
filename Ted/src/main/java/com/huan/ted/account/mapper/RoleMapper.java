package com.huan.ted.account.mapper;

import java.util.List;

import com.huan.ted.account.dto.Role;
import com.huan.ted.account.dto.User;
import com.huan.ted.mybatis.common.Mapper;

/**
 * @author huanghuan
 */
public interface RoleMapper extends Mapper<Role> {

    List<Role> selectUserRoles(Long userId);

    List<Role> selectRoleNotUserRoles(Long userId);

    List<Role> selectByUser(User user);

    List<Role> selectRolesByUserWithoutLang(User user);

    int selectUserRoleCount(Long userId, Long roleId);
}
