package com.huan.ted.yh.cooperativeProject.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.huan.ted.mybatis.annotation.ExtensionAttribute;
import com.huan.ted.system.dto.BaseDTO;

@Table(name = "yh_cooperative_project")
@ExtensionAttribute(disable = true)
public class Project extends BaseDTO{
	 	@Id
	    @GeneratedValue
		private Long id;
	 	
	 	@Column
	 	@NotEmpty
		private String projectname;
		
		@Column(name="projectdate_s")
		private Date projectdateS;
		
		@Column(name="projectdate_e")
		private Date projectdateE;
		
		@Column(name="project_site")
		@NotEmpty
		private String projectSite;
		
		private String thumbnail;
		private String banner;
		
		@Column(name="project_intro")
		@NotEmpty
		private String projectIntro;
		
		@Column(name="project_content")
		@NotEmpty
		private String projectContent;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getProjectname() {
			return projectname;
		}
		public void setProjectname(String projectname) {
			this.projectname = projectname;
		}
		public Date getProjectdateS() {
			return projectdateS;
		}
		public void setProjectdateS(Date projectdateS) {
			this.projectdateS = projectdateS;
		}
		public Date getProjectdateE() {
			return projectdateE;
		}
		public void setProjectdateE(Date projectdateE) {
			this.projectdateE = projectdateE;
		}
		public String getProjectSite() {
			return projectSite;
		}
		public void setProjectSite(String projectSite) {
			this.projectSite = projectSite;
		}
		public String getThumbnail() {
			return thumbnail;
		}
		public void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}
		public String getBanner() {
			return banner;
		}
		public void setBanner(String banner) {
			this.banner = banner;
		}
		public String getProjectIntro() {
			return projectIntro;
		}
		public void setProjectIntro(String projectIntro) {
			this.projectIntro = projectIntro;
		}
		public String getProjectContent() {
			return projectContent;
		}
		public void setProjectContent(String projectContent) {
			this.projectContent = projectContent;
		}
		
		
		
}
