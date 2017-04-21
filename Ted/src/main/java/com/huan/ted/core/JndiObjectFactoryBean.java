/*
 * Copyright Hand China Co.,Ltd.
 */

package com.huan.ted.core;

import java.io.IOException;
import java.util.Properties;

/**
 * @author huanghuan
 */
public class JndiObjectFactoryBean extends org.springframework.jndi.JndiObjectFactoryBean {

    @Override
    public void setJndiName(String jndiName) {
        if (jndiName.startsWith("${")) {
            String rawKey = jndiName.substring(2, jndiName.length() - 1);
            Properties properties = new Properties();
            try {
                properties.load(JndiObjectFactoryBean.class.getResourceAsStream("/config.properties"));
                jndiName = properties.getProperty(rawKey);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.setJndiName(jndiName);
    }

}
