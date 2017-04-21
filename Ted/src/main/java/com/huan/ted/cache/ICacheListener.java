/*
 * #{copyright}#
 */

package com.huan.ted.cache;

/**
 * ICacheListener.
 *
 * @author huanghuan Cache监听器 2016-01-08
 */
public interface ICacheListener {

    /**
     * 当cache init 是调用.
     * 
     * @param cache
     *            当前 cache
     */
    void cacheInit(Cache cache);

}
