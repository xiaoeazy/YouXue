/*
 * #{copyright}#
 */

package com.huan.ted.account.mapper;

import java.util.List;

import com.huan.ted.account.dto.User;
import com.huan.ted.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author huanghuan
 */
public interface UserMapper extends Mapper<User> {

    User selectByUserName(String userName);

    List<User> selectByIdList(List<Long> userIds);

    int updatePassword(@Param("userId") Long userId,@Param("password") String passwordNew);

    int updatePasswordDate(@Param("userId") Long userId);

    int updateUserInfo(User user);
}