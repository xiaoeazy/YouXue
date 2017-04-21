/*
 * #{copyright}#
 */

package com.huan.ted.system.mapper;

import java.util.List;
import java.util.Map;

import com.huan.ted.system.dto.MultiLanguageField;

/**
 * 
 * @author huanghuan
 *
 *         
 */
public interface MultiLanguageMapper {

    List<MultiLanguageField> select(Map<String, String> map);
}