/*
 * #{copyright}#
 */

package com.huan.ted.yh.cooperativeProject.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huan.ted.core.IRequest;
import com.huan.ted.system.controllers.BaseController;
import com.huan.ted.system.dto.ResponseData;
import com.huan.ted.yh.cooperativeProject.dto.Project;
import com.huan.ted.yh.cooperativeProject.service.ICooperativeProjectService;

/**
 * 项目控制器.
 * 
 * @author huanghuan
 */
@Controller
public class CooperativeProjectController extends BaseController {
	
	@Autowired
	private ICooperativeProjectService cooperativeProjectService;

	@RequestMapping(value = "/yh/cooperativeProject/query")
	@ResponseBody
	public ResponseData query(final Project project, @RequestParam(defaultValue = DEFAULT_PAGE) final int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) final int pagesize, final HttpServletRequest request){
		IRequest requestContext = createRequestContext(request);
		return new ResponseData(cooperativeProjectService.select(requestContext, project, page, pagesize));
	}
	
	@RequestMapping(value = "/yh/cooperativeProject/submit")
	@ResponseBody
	public ResponseData submit(@RequestBody final List<Project> projects, final BindingResult result,
            final HttpServletRequest request){
		 	getValidator().validate(projects, result);
	        if (result.hasErrors()) {
	            ResponseData responseData = new ResponseData(false);
	            responseData.setMessage(getErrorMessage(result, request));
	            return responseData;
	        }
	        IRequest requestContext = createRequestContext(request);
	        return new ResponseData(cooperativeProjectService.batchUpdate(requestContext, projects));
	}
}
