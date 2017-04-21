package com.huan.ted.core.components;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.huan.ted.message.profile.SystemConfigListener;

/**
 * 系统配置
 *
 * @author huanghuan
 */
@Component
public class SysConfigManager implements SystemConfigListener{

    //密码失效时间 默认0 不失效
    private String sysTitle = "Hand Application Platform";
    


	public String getSysTitle() {
		return sysTitle;
	}
    
    @Override
    public List<String> getAcceptedProfiles() {
        return Arrays.asList("SYS_TITLE");
    }

    @Override
    public void updateProfile(String profileName, String profileValue) {
        if ("SYS_TITLE".equalsIgnoreCase(profileName)){
            this.sysTitle = profileValue;
        }
    }

}
