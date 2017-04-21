/*
 * #{copyright}#
 */

package com.huan.ted.system.mapper;

import com.huan.ted.system.dto.DocSequence;

/**
 * @author huanghuan
 */
public interface DocSequenceMapper {

    DocSequence lockDocSequence(DocSequence docSequence);

    int insert(DocSequence docSequence);

    int update(DocSequence docSequence);
}