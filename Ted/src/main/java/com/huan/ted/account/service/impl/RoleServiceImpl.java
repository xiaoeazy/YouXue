package com.huan.ted.account.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.huan.ted.account.dto.Role;
import com.huan.ted.account.dto.User;
import com.huan.ted.account.exception.RoleException;
import com.huan.ted.account.mapper.RoleMapper;
import com.huan.ted.account.mapper.UserRoleMapper;
import com.huan.ted.account.service.IRoleService;
import com.huan.ted.core.IRequest;
import com.huan.ted.system.service.impl.BaseServiceImpl;

/**
 * @author huanghuan
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    /**
     * 查询被角色分配的功能以外的所有功能.D
     *
     * @author huanghuan
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> selectRoleNotUserRoles(IRequest request, User user, int page, int pagesize) {
        PageHelper.startPage(page, pagesize);
        return roleMapper.selectRoleNotUserRoles(user.getUserId());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> selectRolesByUser(IRequest requestContext, User user) {
        List<Role> rootRoles = roleMapper.selectByUser(user);
        return rootRoles;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void checkUserRoleExists(Long userId, Long roleId) throws RoleException {
        if (roleMapper.selectUserRoleCount(userId, roleId) != 1) {
            throw new RoleException(RoleException.MSG_INVALID_USER_ROLE, RoleException.MSG_INVALID_USER_ROLE, null);
        }
    }

    @Override
    protected boolean useSelectiveUpdate() {
        return false;
    }

    @Override
    public int deleteByPrimaryKey(Role record) {
        int ret = super.deleteByPrimaryKey(record);
        userRoleMapper.deleteByRoleId(record.getRoleId());
        return ret;
    }
}
