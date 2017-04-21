/*
 * Copyright Hand China Co.,Ltd.
 */

package com.huan.ted.core;

import org.springframework.context.ApplicationContext;

/**
 * @author huanghuan
 */
public interface AppContextInitListener {
    void contextInitialized(ApplicationContext applicationContext);
}
