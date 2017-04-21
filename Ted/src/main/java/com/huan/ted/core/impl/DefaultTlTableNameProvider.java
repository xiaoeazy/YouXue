/*
 * #{copyright}#
 */

package com.huan.ted.core.impl;

import com.huan.ted.core.ITlTableNameProvider;

/**
 * 去除_B 后缀,添加_TL 后缀.
 * <p>
 * 例如:SYS_ROLE_B --&gt; SYS_ROLE_TL
 *
 * @author huanghuan
 */
public final class DefaultTlTableNameProvider implements ITlTableNameProvider {
    private static DefaultTlTableNameProvider ourInstance = new DefaultTlTableNameProvider();
    private static final String SUFFIX = "_TL";

    public static DefaultTlTableNameProvider getInstance() {
        return ourInstance;
    }

    private DefaultTlTableNameProvider() {
    }

    @Override
    public String getTlTableName(String baseTableName) {
        int len = baseTableName.length();
        return new StringBuilder(baseTableName).delete(len - 2, len).append(SUFFIX).toString();
    }
}
