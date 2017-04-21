/*
 * #{copyright}#
 */

package com.huan.ted.function.mapper;

import java.util.List;

import com.huan.ted.function.dto.Function;
import com.huan.ted.function.dto.Resource;
import com.huan.ted.mybatis.common.Mapper;

/**
 * 资源mapper.
 * 
 * @author wuyichu
 */
public interface ResourceMapper extends Mapper<Resource> {

    Resource selectResourceByUrl(String url);
    
    List<Resource> selectResourcesByFunction(Function function);
}