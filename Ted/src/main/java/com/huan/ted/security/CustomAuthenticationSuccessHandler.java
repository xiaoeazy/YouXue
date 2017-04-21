package com.huan.ted.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


/**
 * Created by hailor on 16/6/12.
 */
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private ApplicationContext applicationContext;


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        clearAuthenticationAttributes(request);
        Map<String, IAuthenticationSuccessListener> listeners = applicationContext
                .getBeansOfType(IAuthenticationSuccessListener.class);
        List<IAuthenticationSuccessListener> list = new ArrayList<>();
        list.addAll(listeners.values());
        Collections.sort(list);
        IAuthenticationSuccessListener successListener = null;
        try {
            for (IAuthenticationSuccessListener listener : list) {
                successListener = listener;
                successListener.onAuthenticationSuccess(request, response, authentication);
            }
        } catch (Exception e) {
            logger.error("authentication success, but error occurred in " + successListener, e);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            request.setAttribute("error", true);
            request.setAttribute("exception", e);

            request.getRequestDispatcher("/login").forward(request, response);
            return;
        }
        handle(request, response, authentication);
    }

}
