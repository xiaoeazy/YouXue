/*
 * #{copyright}#
 */
package com.huan.ted.attachment.exception;

import com.huan.ted.core.exception.BaseException;

/**
 * @author huanghuan
 *
 *         2016年3月03日
 */
public class StoragePathNotExsitException extends BaseException {

    private static final long serialVersionUID = 9046687211507280533L;
    
    /**
     * 文件存储路径不存在.
     */
    private static final String STORAGE_PATH_NOT_EXSIT = "msg.error.system.storagepath.not_exsit";

    public StoragePathNotExsitException() {
        super(STORAGE_PATH_NOT_EXSIT, STORAGE_PATH_NOT_EXSIT, new Object[0]);
    }
}
