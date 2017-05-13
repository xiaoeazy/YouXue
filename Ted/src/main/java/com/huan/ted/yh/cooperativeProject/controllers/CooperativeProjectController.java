/*
 * #{copyright}#
 */

package com.huan.ted.yh.cooperativeProject.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huan.ted.attachment.exception.StoragePathNotExsitException;
import com.huan.ted.attachment.exception.UniqueFileMutiException;
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
//		 	getValidator().validate(projects, result);
//	        if (result.hasErrors()) {
//	            ResponseData responseData = new ResponseData(false);
//	            responseData.setMessage(getErrorMessage(result, request));
//	            return responseData;
//	        }
	        IRequest requestContext = createRequestContext(request);
	        return new ResponseData(cooperativeProjectService.batchUpdate(requestContext, projects));
	}
	
	
    /**
     * 缩略图提交页面.
     * 
     * @param request
     *            HttpServletRequest
     * @return Map 返回结果对象
     * @throws StoragePathNotExsitException
     *             存储路径不存在异常
     * @throws UniqueFileMutiException
     *             附件不唯一异常
     * @throws IOException
     * @throws FileUploadException
     */
    @RequestMapping(value = "/yh/cooperativeProject/thumbnail", method = RequestMethod.POST, produces = "text/html")
    public String uploadThumbnail(HttpServletRequest request)
            throws StoragePathNotExsitException, UniqueFileMutiException, IOException, FileUploadException {
        String file_path = request.getServletContext().getRealPath("/") + "/resources/upload";
        String fileName = "";
        File dir=new File(file_path);
        if(!dir.exists())
            dir.mkdir();
        
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);

        for (FileItem fi : items) {
            if (fi.isFormField()) {
                fi.getFieldName();
                fi.getString();
            } else {
                String imgName = fi.getName();//
                fileName= imgName;
                if (imgName == null) {
                    return "<script>window.parent.showUploadError('NO_FILE')</script>";
                } else {
                    int idx = imgName.lastIndexOf(".");
                    if (idx != -1) {
                        String ext = imgName.substring(idx + 1).toUpperCase();
                        ext = ext.toLowerCase();
                        if (!ext.equals("jpg") && !ext.equals("png") && !ext.equals("jpeg") && !ext.equals("gif")) {
                            // 错误信息
                            return "<script>window.parent.showUploadError('FILE_TYPE_ERROR')</script>";
                        }
                    } else {
                        // 文件类型错误
                        return "<script>window.parent.showUploadError('FILE_NO_SUFFIX')</script>";
                    }
                }
                File tempFile = new File(file_path+'/'+fileName);
                try (InputStream is = fi.getInputStream(); OutputStream os = new FileOutputStream(tempFile)) {
                    IOUtils.copyLarge(is, os);
                }

            }
        }
        String filePath = "/resources/upload/"+fileName;
        return "<script>window.parent.showUploadThumbnailSuccess('"+filePath+"')</script>";
    }
    
    /**
     * banner提交页面.
     * 
     * @param request
     *            HttpServletRequest
     * @return Map 返回结果对象
     * @throws StoragePathNotExsitException
     *             存储路径不存在异常
     * @throws UniqueFileMutiException
     *             附件不唯一异常
     * @throws IOException
     * @throws FileUploadException
     */
    @RequestMapping(value = "/yh/cooperativeProject/banner", method = RequestMethod.POST, produces = "text/html")
    public String uploadBanner(HttpServletRequest request)
            throws StoragePathNotExsitException, UniqueFileMutiException, IOException, FileUploadException {
        String file_path = request.getServletContext().getRealPath("/") + "/resources/upload";
        String fileName = "";
        File dir=new File(file_path);
        if(!dir.exists())
            dir.mkdir();
        
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);

        for (FileItem fi : items) {
            if (fi.isFormField()) {
                fi.getFieldName();
                fi.getString();
            } else {
                String imgName = fi.getName();//
                fileName= imgName;
                if (imgName == null) {
                    return "<script>window.parent.showUploadError('NO_FILE')</script>";
                } else {
                    int idx = imgName.lastIndexOf(".");
                    if (idx != -1) {
                        String ext = imgName.substring(idx + 1).toUpperCase();
                        ext = ext.toLowerCase();
                        if (!ext.equals("jpg") && !ext.equals("png") && !ext.equals("jpeg") && !ext.equals("gif")) {
                            // 错误信息
                            return "<script>window.parent.showUploadError('FILE_TYPE_ERROR')</script>";
                        }
                    } else {
                        // 文件类型错误
                        return "<script>window.parent.showUploadError('FILE_NO_SUFFIX')</script>";
                    }
                }
                File tempFile = new File(file_path+'/'+fileName);
                try (InputStream is = fi.getInputStream(); OutputStream os = new FileOutputStream(tempFile)) {
                    IOUtils.copyLarge(is, os);
                }

            }
        }
        String filePath = "/resources/upload/"+fileName;
        return "<script>window.parent.showUploadBannerSuccess('"+filePath+"')</script>";
    }
}
