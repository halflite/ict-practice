package com.example.todo.app.entity;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import com.example.todo.app.type.ProviderType;

/**
 * アカウント
 */
@Entity
@Table(name = "account")
public class Account {

    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /** 外部認証プロバイダーのアカウントID */
    @Column(name = "provider_id")
    String providerId;

    /** 外部認証プロバイダー種別 */
    @Column(name = "provider_type")
    ProviderType providerType;

    /** 表示名 */
    @Column(name = "name")
    String name;

    /** 状態 */
    @Column(name = "status")
    String status;

    /** 更新日時 */
    @Column(name = "modified")
    LocalDateTime modified;

    /** 登録日時 */
    @Column(name = "created")
    LocalDateTime created;

    /** 
     * Returns the id.
     * 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /** 
     * Sets the id.
     * 
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** 
     * Returns the providerId.
     * 
     * @return the providerId
     */
    public String getProviderId() {
        return providerId;
    }

    /** 
     * Sets the providerId.
     * 
     * @param providerId the providerId
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    /** 
     * Returns the providerType.
     * 
     * @return the providerType
     */
    public ProviderType getProviderType() {
        return providerType;
    }

    /** 
     * Sets the providerType.
     * 
     * @param providerType the providerType
     */
    public void setProviderType(ProviderType providerType) {
        this.providerType = providerType;
    }

    /** 
     * Returns the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /** 
     * Sets the name.
     * 
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * Returns the status.
     * 
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /** 
     * Sets the status.
     * 
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /** 
     * Returns the modified.
     * 
     * @return the modified
     */
    public LocalDateTime getModified() {
        return modified;
    }

    /** 
     * Sets the modified.
     * 
     * @param modified the modified
     */
    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    /** 
     * Returns the created.
     * 
     * @return the created
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /** 
     * Sets the created.
     * 
     * @param created the created
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}