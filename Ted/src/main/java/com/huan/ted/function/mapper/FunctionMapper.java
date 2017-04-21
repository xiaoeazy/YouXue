/*
 * #{copyright}#
 */

package com.huan.ted.function.mapper;

import java.util.List;
import java.util.Map;

import com.huan.ted.function.dto.Function;
import com.huan.ted.function.dto.FunctionDisplay;
import com.huan.ted.function.dto.Resource;
import com.huan.ted.mybatis.common.Mapper;

/**
 * @author wuyichu
 */
public interface FunctionMapper extends Mapper<Function> {

    List<Resource> selectExistsResourcesByFunction(Map<String, Object> params);

    List<Resource> selectNotExistsResourcesByFunction(Map<String, Object> params);

    List<FunctionDisplay> selectFunctions(Function function);
}