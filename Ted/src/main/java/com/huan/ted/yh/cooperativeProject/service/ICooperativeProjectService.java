package com.huan.ted.yh.cooperativeProject.service;

import java.util.List;

import com.huan.ted.core.IRequest;
import com.huan.ted.core.ProxySelf;
import com.huan.ted.system.service.IBaseService;
import com.huan.ted.yh.cooperativeProject.dto.Project;

/**
 * @author yuliao.chen@hand-china.com
 */
public interface ICooperativeProjectService extends IBaseService<Project>,ProxySelf<ICooperativeProjectService>{
	
	public List<Project> queryAll(IRequest requestContext,Project project, int page, int pagesize);
}
