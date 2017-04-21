package com.huan.ted.Thread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.core.task.TaskExecutor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.huan.ted.Thread.impl.ContextThread;

public class ContextTaskListener implements ServletContextListener {
	private WebApplicationContext springContext;
	private TaskExecutor taskExecutor;  
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("start===========================================");
		springContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
		if (springContext != null) {
			taskExecutor = (TaskExecutor) springContext.getBean("taskExecutor");
			taskExecutor.execute(new ContextThread());
		} else {
			System.out.println("获取应用程序上下文失败!");
			return;
		}
		// TODO Auto-generated method stub
		
	}
}
