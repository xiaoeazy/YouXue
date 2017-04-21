/*
 * #{copyright}#
 */

package com.huan.ted.security.service;

import com.huan.ted.security.dto.User;

/**
 * 权限安全认证服务接口.
 * 
 * @author wuyichu
 * @deprecated
 */
public interface ISecurityService {

    boolean setUser(User user);

    boolean verifyUser(String url, String userName);
}
