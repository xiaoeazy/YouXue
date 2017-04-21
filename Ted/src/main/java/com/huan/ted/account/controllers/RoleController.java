/*
 * #{copyright}#
 */

package com.huan.ted.account.controllers;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huan.ted.account.dto.Role;
import com.huan.ted.account.dto.User;
import com.huan.ted.account.service.IRoleService;
import com.huan.ted.core.IRequest;
import com.huan.ted.core.exception.BaseException;
import com.huan.ted.system.controllers.BaseController;
import com.huan.ted.system.dto.ResponseData;

/**
 * 角色控制器.
 * 
 * @author huanghuan
 */
@Controller
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    /**
     * 查询被角色分配的功能以外的所有功能.
     * 
     * @param request
     *            HttpServletRequest
     * @param user
     *            user with id
     * @param page
     *            page
     * @param pagesize
     *            pagesize
     * @return ResponseData
     */
    @RequestMapping(value = "/sys/role/queryRoleNotUserRole")
    @ResponseBody
    public ResponseData getRoleNotUserRoles(HttpServletRequest request, User user,
                                            @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
    	roleService.select(anyObject(),anyObject(),anyInt(),anyInt());
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(roleService.selectRoleNotUserRoles(requestContext, user, page, pagesize));
    }

    /**
     * 角色查询.
     * 
     * @param role
     *            角色对象
     * @param page
     *            起始页
     * @param pagesize
     *            分页大小
     * @param request
     *            HttpServletRequest
     * @return ResponseData
     */
    @RequestMapping(value = "/sys/role/query")
    @ResponseBody
    public ResponseData getRoles(Role role, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(roleService.select(requestContext, role, page, pagesize));
    }

    /**
     * 保存角色.
     * 
     * @param roles
     *            roles
     * @param result
     *            BindingResult
     * @param request
     *            HttpServletRequest
     * @return ResponseData ResponseData
     * @throws BaseException
     *             BaseException
     */
    @RequestMapping(value = "/sys/role/submit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData submitRole(@RequestBody List<Role> roles, BindingResult result, HttpServletRequest request)
            throws BaseException {
        getValidator().validate(roles, result);
        if (result.hasErrors()) {
            ResponseData rd = new ResponseData(false);
            rd.setMessage(getErrorMessage(result, request));
            return rd;
        }
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(roleService.batchUpdate(requestContext, roles));
    }

    /**
     * 批量删除角色.
     * 
     * @param roles
     *            需要删除的角色
     * @param result
     *            BindingResult
     * @param request
     *            HttpServletRequest
     * @return 返回响应信息
     * @throws BaseException
     *             抛出业务异常
     */
    @RequestMapping(value = "/sys/role/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData deleteRole(@RequestBody List<Role> roles, BindingResult result, HttpServletRequest request)
            throws BaseException {
        getValidator().validate(roles, result);
        if (result.hasErrors()) {
            ResponseData rd = new ResponseData(false);
            rd.setMessage(getErrorMessage(result, request));
            return rd;
        }
        // IRequest requestContext = createRequestContext(request);
        roleService.batchDelete(roles);
        return new ResponseData();
    }

}
