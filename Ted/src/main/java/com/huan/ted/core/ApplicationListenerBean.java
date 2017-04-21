/*
 * Copyright Hand China Co.,Ltd.
 */

package com.huan.ted.core;

import java.util.Map;

import javax.servlet.ServletRequestEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * @author huanghuan
 */
public class ApplicationListenerBean implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
            Map<String, AppContextInitListener> beanMap = applicationContext.getBeansOfType(AppContextInitListener.class);
            beanMap.forEach((k, v) -> {
                v.contextInitialized(applicationContext);
            });
        }else  if(event instanceof ServletRequestHandledEvent){
        	//System.out.println(((ServletRequestHandledEvent) event).getDescription());
        }
    }
}
