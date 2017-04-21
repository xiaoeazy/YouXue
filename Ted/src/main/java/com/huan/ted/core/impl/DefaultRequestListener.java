/*
 * #{copyright}#
 */

package com.huan.ted.core.impl;

import javax.servlet.http.HttpServletRequest;

import com.huan.ted.core.IRequest;
import com.huan.ted.core.IRequestListener;

/**
 * @author huanghuan
 */
public class DefaultRequestListener implements IRequestListener {
    
    @Override
    public IRequest newInstance() {
        return new ServiceRequest();
    }

    @Override
    public void afterInitialize(HttpServletRequest httpServletRequest, IRequest request) {

    }
}
