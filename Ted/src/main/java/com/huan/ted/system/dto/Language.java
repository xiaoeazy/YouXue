/*
 * #{copyright}#
 */
package com.huan.ted.system.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.huan.ted.mybatis.annotation.Condition;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 语言DTO.
 * 
 * @author huanghuan
 */
@Table(name = "sys_lang_b")
public class Language extends BaseDTO {

    private static final long serialVersionUID = -2978619661646886631L;

    @Id
    @NotEmpty
    private String langCode;

    @Condition(exclude = true)
    private String baseLang;

    @Condition(operator = LIKE)
    private String description;

    public String getBaseLang() {
        return baseLang;
    }

    public String getDescription() {
        return description;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setBaseLang(String baseLang) {
        this.baseLang = baseLang == null ? null : baseLang.trim();
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode == null ? null : langCode.trim();
    }

}