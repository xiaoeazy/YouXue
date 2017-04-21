package com.huan.ted.security.service;

/**
 * @author huanghuan
 */
public interface IAESClientService {
    String encrypt(String password);

    String decrypt(String password);
}
