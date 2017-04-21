/*
 * #{copyright}#
 */
package com.huan.ted.account.mapper;


import com.huan.ted.account.dto.SendRetrieve;
import com.huan.ted.mybatis.common.Mapper;

/**
 * 发送次数限制接口.
 * @author huanghuan
 *
 */
public interface SendRetrieveMapper extends Mapper<SendRetrieve> {
    int insertRecord(SendRetrieve record);

    int query(SendRetrieve sendRetrieve);
}