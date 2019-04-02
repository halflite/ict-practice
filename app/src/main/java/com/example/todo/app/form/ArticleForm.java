package com.example.todo.app.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/** 
 * 記事入力フォーム 
 * 
 * @author shingo
 *
 */
public class ArticleForm implements Serializable {

    private static final long serialVersionUID = -6021251837834391695L;

    /** 記事名 */
    @NotNull(message = "{text.input.require}")
    @Size(max = 64, message = "{text.size.max.message}")
    private String name;

    /** 概要 */
    @NotNull
    @Size(max = 255)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
