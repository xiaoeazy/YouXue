/*
 * #{copyright}#
 */
package com.huan.ted.function.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huan.ted.core.IRequest;
import com.huan.ted.function.dto.Resource;
import com.huan.ted.function.dto.ResourceItem;
import com.huan.ted.function.mapper.ResourceItemMapper;
import com.huan.ted.function.mapper.RoleResourceItemMapper;
import com.huan.ted.function.service.IResourceItemService;
import com.huan.ted.system.service.impl.BaseServiceImpl;

/**
 * @author njq.niu@hand-china.com
 *
 * 2016年4月7日
 * 
 * TODO:缓存处理
 */
@Transactional
@Service
public class ResourceItemServiceImpl extends BaseServiceImpl<ResourceItem> implements IResourceItemService {

    @Autowired
    private ResourceItemMapper resourceItemMapper;
    
    @Autowired
    private RoleResourceItemMapper roleResourceItemMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ResourceItem> selectResourceItems(IRequest requestContext, Resource resource) {
        return resourceItemMapper.selectResourceItemsByResourceId(resource);
    }

    @Override
    public List<ResourceItem> batchUpdate(IRequest requestContext, List<ResourceItem> resourceItems)  {
        for (ResourceItem resourceItem : resourceItems) {
            if (resourceItem.getResourceItemId() != null) {
                resourceItemMapper.updateByPrimaryKeySelective(resourceItem);
            } else {
                resourceItemMapper.insertSelective(resourceItem);
            }
        }
        return resourceItems;
    }


    private void delete(ResourceItem resourceItem) {
        if (resourceItem == null || resourceItem.getResourceItemId() == null) {
            return;
        }
        resourceItemMapper.deleteByPrimaryKey(resourceItem.getResourceItemId());
        roleResourceItemMapper.deleteByResourceItemId(resourceItem.getResourceItemId());
    }

    @Override
    public void batchDelete(IRequest requestContext, List<ResourceItem> resourceItems)  {
        if (resourceItems == null || resourceItems.isEmpty()) {
            return;
        }
        for (ResourceItem resourceItem : resourceItems) {
            delete(resourceItem);
        }
    }
    
    @Override
    public ResourceItem selectResourceItemByResourceIdAndItemId(ResourceItem resourceItem) {
        return resourceItemMapper.selectResourceItemByResourceIdAndItemId(resourceItem);
    }

}
