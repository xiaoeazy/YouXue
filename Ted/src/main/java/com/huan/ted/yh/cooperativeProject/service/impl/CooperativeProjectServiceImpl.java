package com.huan.ted.yh.cooperativeProject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.huan.ted.core.IRequest;
import com.huan.ted.message.IMessagePublisher;
import com.huan.ted.system.service.impl.BaseServiceImpl;
import com.huan.ted.yh.cooperativeProject.dto.Project;
import com.huan.ted.yh.cooperativeProject.mapper.CooperativeProjectMapper;
import com.huan.ted.yh.cooperativeProject.service.ICooperativeProjectService;

/**
 * @author huanghuan
 */
@Service
public class CooperativeProjectServiceImpl extends BaseServiceImpl<Project> implements ICooperativeProjectService {

	@Autowired
	CooperativeProjectMapper cooperativeProjectMapper;
	
    @Autowired
    private IMessagePublisher messagePublisher;
    

    @Override
    public List<Project> batchUpdate(IRequest request, List<Project> list) {
        super.batchUpdate(request, list);
        for (Project e : list) {
            messagePublisher.publish("employee.change", e);
        }
        return list;
    }


	@Override
	public List<Project> queryAll(IRequest requestContext, Project project, int page, int pagesize) {
		PageHelper.startPage(page, pagesize);
		return cooperativeProjectMapper.queryAll(project);
	}
}
