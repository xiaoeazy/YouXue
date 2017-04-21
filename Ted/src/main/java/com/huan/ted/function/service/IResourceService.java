/*
 * #{copyright}#
 */
package com.huan.ted.function.service;

import java.util.List;

import com.huan.ted.core.annotation.StdWho;
import com.huan.ted.core.IRequest;
import com.huan.ted.core.ProxySelf;
import com.huan.ted.function.dto.Resource;
import com.huan.ted.system.service.IBaseService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wuyichu
 */
public interface IResourceService extends IBaseService<Resource>, ProxySelf<IResourceService> {

   /**
    * 根据资源的url查询资源数据.
    * @param url url
    * @return Resource
    */
    Resource selectResourceByUrl(String url);

    @Transactional(propagation = Propagation.SUPPORTS)
    Resource selectResourceById(Long id);

    /**
     * 批量修改或新增资源记录.
     * @param requestContext requestContext
     * @param resources resources
     * @return Resource
     */
    List<Resource> batchUpdate(IRequest requestContext, @StdWho List<Resource> resources);

    /**
     * 批量删除资源记录.
     * 
     * @param requestContext requestContext
     * @param resources resources
     */
    void batchDelete(IRequest requestContext, List<Resource> resources);

}
