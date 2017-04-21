package com.huan.ted.security.service.impl;

import com.huan.ted.security.service.IAESClientService;
import org.springframework.stereotype.Service;

/**
 * @author huanghuan
 */
@Service
public class AESClientServiceImpl implements IAESClientService {
    @Override
    public String encrypt(String password) {
        return password;
    }

    @Override
    public String decrypt(String password) {
        return password;
    }
}
