package com.example.todo.app.entity;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import com.example.todo.app.type.ArticleStatusType;

@Entity
public class DisplayArticle {
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private ArticleStatusType status;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "account_display_name")
    private String accountDisplayName;
    @Column
    private LocalDateTime modified;
    @Column
    private LocalDateTime created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ArticleStatusType getStatus() {
        return status;
    }

    public void setStatus(ArticleStatusType status) {
        this.status = status;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountDisplayName() {
        return accountDisplayName;
    }

    public void setAccountDisplayName(String accountDisplayName) {
        this.accountDisplayName = accountDisplayName;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
