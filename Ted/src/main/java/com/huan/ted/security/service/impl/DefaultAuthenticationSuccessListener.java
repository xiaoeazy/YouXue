package com.huan.ted.security.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import com.huan.ted.account.dto.Role;
import com.huan.ted.account.dto.User;
import com.huan.ted.account.exception.RoleException;
import com.huan.ted.account.service.IRoleService;
import com.huan.ted.account.service.IUserService;
import com.huan.ted.core.BaseConstants;
import com.huan.ted.core.IRequest;
import com.huan.ted.core.impl.RequestHelper;
import com.huan.ted.core.util.TimeZoneUtil;
import com.huan.ted.message.components.CaptchaConfig;
import com.huan.ted.security.CustomUserDetails;
import com.huan.ted.security.IAuthenticationSuccessListener;
import com.huan.ted.security.PasswordManager;
import com.huan.ted.security.TokenUtils;
import com.huan.ted.system.dto.SysPreferences;
import com.huan.ted.system.service.ISysPreferencesService;

/**
 * @author huanghuan
 */
@Component
public class DefaultAuthenticationSuccessListener implements IAuthenticationSuccessListener {
    @Autowired
    IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    ISysPreferencesService preferencesService;

    @Autowired
    CaptchaConfig captchaConfig;
    
    @Autowired
    PasswordManager passwordManager;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        Locale locale = RequestContextUtils.getLocale(request);
 /*       CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();*/
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        User user = userService.selectByUserName(userDetails.getUsername());
        HttpSession session = request.getSession(true);
        session.setAttribute(User.FIELD_USER_ID, user.getUserId());
        session.setAttribute(User.FIELD_USER_NAME, user.getUserName());
        session.setAttribute(IRequest.FIELD_LOCALE, locale.toString());
        setTimeZoneFromPreference(session, user.getUserId());
        setLocalePreference(request, user.getUserId());
        setRoleInfo(request, session, user);
        setPwdExpireInfo(request,session, user);
        generateSecurityKey(session);
        captchaConfig.resetLoginFailureInfo(request,response);
    }

    private void setTimeZoneFromPreference(HttpSession session, Long accountId) {
        SysPreferences pref = preferencesService.selectUserPreference(BaseConstants.PREFERENCE_TIME_ZONE, accountId);
        String tz = pref == null ? null : pref.getPreferencesValue();
        // String tz = "GMT+0800";
        if (StringUtils.isBlank(tz)) {
            tz = TimeZoneUtil.toGMTFormat(TimeZone.getDefault());
        }
        session.setAttribute(BaseConstants.PREFERENCE_TIME_ZONE, tz);
    }

    private void setLocalePreference(HttpServletRequest request, Long accountId) {
        SysPreferences pref = preferencesService.selectUserPreference(BaseConstants.PREFERENCE_LOCALE, accountId);
        if (pref != null) {
            WebUtils.setSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
                    org.springframework.util.StringUtils.parseLocaleString(pref.getPreferencesValue()));
        }
    }

    private void setRoleInfo(HttpServletRequest request,HttpSession session ,User user) {
        List<Role> roles = roleService.selectRolesByUser(RequestHelper.createServiceRequest(request), user);
        if (roles.isEmpty()) {
            request.setAttribute("code", "NO_ROLE");
            throw new RuntimeException(new RoleException(null, RoleException.MSG_NO_USER_ROLE, null));
        }
        List<Long> roleIds = new ArrayList<Long>();
        for (Role role : roles) {
            roleIds.add(role.getRoleId());
        }
        Long[] ids = roleIds.toArray(new Long[roleIds.size()]);

        session.setAttribute(Role.FIELD_ALL_ROLE_ID, ids);
        session.setAttribute(Role.FIELD_ROLE_ID, roles.get(0).getRoleId());
    }
    
    private void  setPwdExpireInfo(HttpServletRequest request,HttpSession session ,User user){
        // 判断密码是否失效
        if(user.getLastPasswordUpdateDate() !=null && passwordManager.getPasswordInvalidTime()>0 &&
                daysBetween(user.getLastPasswordUpdateDate(), new Date()) >= passwordManager.getPasswordInvalidTime() ){
            session.setAttribute(User.PASSWORD_EXPIRE_VERIFY, "EXPIRE");
        }
    }

    public int daysBetween(Date smdate,Date bdate){    
       int between_days=(int) ((bdate.getTime()-smdate.getTime())/(1000*3600*24));  
       return between_days;           
    } 
    
    private String generateSecurityKey(HttpSession session) {
        return TokenUtils.setSecurityKey(session);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
