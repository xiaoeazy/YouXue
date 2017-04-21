/*
 * #{copyright}#
 */
package com.huan.ted.account.service;

import java.util.Date;

import com.huan.ted.account.dto.User;
import com.huan.ted.account.exception.UserException;
import com.huan.ted.core.IRequest;
import com.huan.ted.core.ProxySelf;
import com.huan.ted.system.service.IBaseService;

/**
 * HAP中此接口实现默认都是按照USER类型处理. 其他类型可自行实现此接口逻辑.
 * 
 * @author huanghuan
 *
 *         2016年1月28日
 */
public interface IUserService extends IBaseService<User>, ProxySelf<IUserService> {

    /**
     * do login ,return the user in db.
     * 
     * @param user
     * @return
     */
    User login(User user) throws UserException;

    User selectByUserName(String userName);

    void updatePassword(Long userId, String password);

    User updateUserInfo(IRequest request, User user);
}
