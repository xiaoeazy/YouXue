/*
 * #{copyright}#
 */

package com.huan.ted.message.impl;

import com.huan.ted.message.IMessageConsumer;

/**
 * 用于测试.
 * <p>
 * 直接打印收到的消息
 * 
 * @author huanghuan
 */
public class SimpleMessageConsumer implements IMessageConsumer<String> {
    @Override
    public void onMessage(String message, String pattern) {

    }
}
