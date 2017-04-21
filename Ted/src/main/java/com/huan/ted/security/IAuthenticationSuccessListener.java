package com.huan.ted.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

/**
 * @author huanghuan
 */
public interface IAuthenticationSuccessListener extends Comparable<IAuthenticationSuccessListener> {

    void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication);

    default int getOrder() {return 10;}

    @Override
    default int compareTo(IAuthenticationSuccessListener o) {
        return getOrder() - o.getOrder();
    }
}
