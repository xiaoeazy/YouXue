package com.huan.ted.security;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.huan.ted.cache.CacheManager;

/**
 * Created by hailor on 16/6/12.
 */
public class PermissionVoter implements AccessDecisionVoter<FilterInvocation> {
    private static final Logger logger = LoggerFactory.getLogger(PermissionVoter.class);

    private static String APP_SERVLET_CONTEXT_KEY = FrameworkServlet.SERVLET_CONTEXT_PREFIX + "appServlet";


    @Autowired
    private CacheManager cacheManager;


    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    private void initRequestMappingHandlerMapping(HttpServletRequest request) {
        if (requestMappingHandlerMapping != null) {
            return;
        }
        ServletContext servletContext = request.getServletContext();

        WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext,
                APP_SERVLET_CONTEXT_KEY);
        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext,
                HandlerMapping.class, true, false);
        for (HandlerMapping handlerMapping : allRequestMappings.values()) {
            if (handlerMapping instanceof RequestMappingHandlerMapping) {
                requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                break;
            }
        }

    }

    private String getBestMatchPattern(HttpServletRequest request, String uri) {
        initRequestMappingHandlerMapping(request);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            RequestMappingInfo requestMappingInfo = entry.getKey();
            List<String> patterns = requestMappingInfo.getPatternsCondition().getMatchingPatterns(uri);
            if (patterns.size() > 0) {
                return patterns.get(0);
            }
        }
        return uri;
    }

  

    @Override
    public int vote(Authentication authentication, FilterInvocation fi, Collection<ConfigAttribute> attributes) {
//        int result = ACCESS_ABSTAIN;
//        assert authentication != null;
//        assert fi != null;
//        assert attributes != null;
//
//        // 已经 permitAll 的 url 不再过滤(主要是一些资源类 url,通用 url)
//        for (ConfigAttribute attribute : attributes) {
//            if ("permitAll".equals(attribute.toString())) {
//                return result;
//            }
//        }
//
//        HttpServletRequest request = fi.getRequest();
//        String uri = StringUtils.substringAfter(request.getRequestURI(), request.getContextPath());
//        String uri_ori = uri;
//
//        if (uri.startsWith("/")) {
//            uri = uri.substring(1);
//        }
//        if ("".equals(uri)) {
//            return ACCESS_ABSTAIN;
//        }
//        Resource resource = getResourceOfUri(request, uri_ori, uri);
//        if (resource == null) {
//            return ACCESS_ABSTAIN;
//            // return ACCESS_DENIED;
//        }
//        if (!BaseConstants.YES.equalsIgnoreCase(resource.getLoginRequire())) {
//            if (logger.isDebugEnabled()) {
//                logger.debug("url :'{}' does not require login.", uri);
//            }
//            return ACCESS_ABSTAIN;
//        }
//        if (!BaseConstants.YES.equalsIgnoreCase(resource.getAccessCheck())) {
//            if (logger.isDebugEnabled()) {
//                logger.debug("url :'{}' need no access control.", uri);
//            }
//            return ACCESS_ABSTAIN;
//        }
//
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return ACCESS_DENIED;
//        }
//        Long roleId = (Long) session.getAttribute(Role.FIELD_ROLE_ID);
//        if (roleId == null) {
//            return ACCESS_DENIED;
//        }
//        //改为判断当前用户下的所有角色
//       /* Long[] accessible = roleResourceCache.getValue(String.valueOf(roleId));
//        if (accessible == null || !Arrays.asList(accessible).contains(resource.getResourceId())) {
//            logger.warn("access to uri :'{}' denied.", uri);
//            return ACCESS_DENIED;
//        }
//         result = ACCESS_DENIED;
//
//         return result; */
//        Long [] roleIds = RequestHelper.createServiceRequest(request).getAllRoleId();
//        for(Long rid : roleIds){
//            Long [] ids = roleResourceCache.getValue(String.valueOf(rid));
//            if(ids !=null && Arrays.asList(ids).contains(resource.getResourceId())){
//                return ACCESS_ABSTAIN;
//            }
//        }
//        logger.warn("access to uri :'{}' denied.", uri);
        return ACCESS_DENIED;
    }
}
