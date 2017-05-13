/*
 * #{copyright}#
 */
package com.huan.ted.system.service.impl;

import org.springframework.stereotype.Service;

import com.huan.ted.cache.CacheDelete;
import com.huan.ted.cache.CacheSet;
import com.huan.ted.core.IRequest;
import com.huan.ted.system.dto.Prompt;
import com.huan.ted.system.service.IPromptService;

/**
 * @author huanghuan
 */
@Service
public class PromptServiceImpl extends BaseServiceImpl<Prompt> implements IPromptService {

    @Override
    @CacheSet(cache = "prompt")
    public Prompt insertSelective(IRequest request, Prompt prompt) {
        super.insertSelective(request, prompt);
        return prompt;
    }

    @Override
    @CacheDelete(cache = "prompt")
    public int deleteByPrimaryKey(Prompt prompt) {
        return super.deleteByPrimaryKey(prompt);
    }

    @Override
    @CacheSet(cache = "prompt")
    public Prompt updateByPrimaryKeySelective(IRequest request, Prompt prompt) {
        super.updateByPrimaryKeySelective(request, prompt);
        return prompt;
    }
}
