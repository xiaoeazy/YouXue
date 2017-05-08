package com.huan.ted.yh.cooperativeProject.mapper;

import java.util.List;

import com.huan.ted.mybatis.common.Mapper;
import com.huan.ted.yh.cooperativeProject.dto.Project;

/**
 * @author yuliao.chen@hand-china.com
 */
public interface CooperativeProjectMapper extends Mapper<Project>{
   
    List<Project> queryAll(Project project);
}
