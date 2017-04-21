package com.huan.ted.account.service;

import java.util.List;

import com.huan.ted.account.dto.Role;
import com.huan.ted.account.dto.User;
import com.huan.ted.account.exception.RoleException;
import com.huan.ted.core.IRequest;
import com.huan.ted.core.ProxySelf;
import com.huan.ted.system.service.IBaseService;

/**
 * @author huanghuan
 */
public interface IRoleService extends IBaseService<Role>, ProxySelf<IRoleService> {

    /**
     * 查询,不属于当前用户角色的数据.
     *
     * @param requestContext
     *            请求上下文
     * @param user
     *            条件,至少包含 userId
     * @param page
     *            起始页
     * @param pagesize
     *            页大小
     * @return 查询结果
     */
    List<Role> selectRoleNotUserRoles(IRequest requestContext, User user, int page, int pagesize);

    /**
     * 查询用户的所有角色.
     *
     * @param requestContext
     *            请求上下文
     * @param user
     *            包含 userId
     * @return 查询结果
     */
    List<Role> selectRolesByUser(IRequest requestContext, User user);

    /**
     * 判断用户角色是否存在.
     *
     * @param userId
     * @param roleId
     * @throws RoleException
     */
    void checkUserRoleExists(Long userId, Long roleId) throws RoleException;

}
