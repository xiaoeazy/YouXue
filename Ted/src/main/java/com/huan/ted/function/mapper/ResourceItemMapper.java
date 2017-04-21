/*
 * #{copyright}#
 */

package com.huan.ted.function.mapper;

import java.util.List;

import com.huan.ted.function.dto.Function;
import com.huan.ted.function.dto.Resource;
import com.huan.ted.function.dto.ResourceItem;
import com.huan.ted.mybatis.common.Mapper;

public interface ResourceItemMapper extends Mapper<ResourceItem> {
    
    List<ResourceItem> selectResourceItemsByResourceId(Resource resource);
    
    List<ResourceItem> selectResourceItemsByFunctionId(Function function);
    
    
    ResourceItem selectResourceItemByResourceIdAndItemId(ResourceItem record);
    
}