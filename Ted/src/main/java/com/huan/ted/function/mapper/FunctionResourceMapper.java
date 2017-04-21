/*
 * #{copyright}#
 */

package com.huan.ted.function.mapper;

import org.apache.ibatis.annotations.Param;

import com.huan.ted.function.dto.FunctionResource;
import com.huan.ted.function.dto.Resource;
import com.huan.ted.mybatis.common.Mapper;

/**
 * 功能资源mapper.
 * 
 * @author wuyichu
 */
public interface FunctionResourceMapper extends Mapper<FunctionResource> {

    int deleteByResource(Resource resource);

    int deleteByFunctionId(Long functionId);

    int deleteFunctionResource(@Param(value = "functionId") Long functionId,
            @Param(value = "resourceId") Long resourceId);
}