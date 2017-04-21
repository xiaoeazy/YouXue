/*
 * #{copyright}#
 */
package com.huan.ted.adaptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.huan.ted.account.dto.Role;
import com.huan.ted.account.dto.User;
import com.huan.ted.account.exception.RoleException;
import com.huan.ted.core.exception.BaseException;
import com.huan.ted.system.dto.ResponseData;

/**
 * 登陆代理接口类.
 * 
 * @author huanghuan
 */
public interface ILoginAdaptor {

    /**
     * 超时登陆逻辑.
     * 
     * @param account
     *            登陆账号对象
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ResponseData
     * @throws BaseException
     */
    ResponseData sessionExpiredLogin(User account, HttpServletRequest request, HttpServletResponse response)
            throws RoleException;

    /**
     * 
     * 角色选择逻辑.
     * 
     * @param role
     *            角色对象
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return view
     * @throws RoleException
     */
    ModelAndView doSelectRole(Role role, HttpServletRequest request, HttpServletResponse response) throws RoleException;

    /**
     * 显示主界面.
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return view
     */
    ModelAndView indexView(HttpServletRequest request, HttpServletResponse response);

    /**
     * 登陆界面.
     * 
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return view
     */
    ModelAndView loginView(HttpServletRequest request, HttpServletResponse response);

    /**
     * 显示角色选择界面.
     * 
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return view viewModel
     * 
     * @throws BaseException
     *             BaseException
     */
    ModelAndView roleView(HttpServletRequest request, HttpServletResponse response) throws BaseException;
}
