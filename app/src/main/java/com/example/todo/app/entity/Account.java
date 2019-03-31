package com.example.todo.app.entity;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.pac4j.core.context.Pac4jConstants;
import org.pac4j.core.profile.CommonProfile;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import com.example.todo.app.type.AccountStatusType;

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

    /** ログイン用ユーザーネーム */
    @Column(name = "username")
    String username;

    /** ハッシュ化されたパスワード */
    @Column(name = "hashed_password")
    String hashedPassword;

    /** 表示名 */
    @Column(name = "display_name")
    String displayName;

    /** 状態 */
    @Column(name = "status")
    AccountStatusType status;

    /** 更新日時 */
    @Column(name = "modified")
    LocalDateTime modified;

    /** 登録日時 */
    @Column(name = "created")
    LocalDateTime created;

    public Account() {
    }

    public Account(String username, String hashedPassword, String name, LocalDateTime now) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.displayName = name;
        this.status = AccountStatusType.ENABLED;
        this.modified = now;
        this.created = now;
    }

    public CommonProfile toProfile() {
        final CommonProfile profile = new CommonProfile();
        profile.setId(this.id.toString());
        profile.addAttribute(Pac4jConstants.USERNAME, this.displayName);
        return profile;
    }
    
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
     * Returns the username.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /** 
     * Sets the username.
     * 
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /** 
     * Returns the hashedPassword.
     * 
     * @return the hashedPassword
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /** 
     * Sets the hashedPassword.
     * 
     * @param hashedPassword the hashedPassword
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    /** 
     * Returns the displayName.
     * 
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /** 
     * Sets the displayName.
     * 
     * @param the displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /** 
     * Returns the status.
     * 
     * @return the status
     */
    public AccountStatusType getStatus() {
        return status;
    }

    /** 
     * Sets the status.
     * 
     * @param status the status
     */
    public void setStatus(AccountStatusType status) {
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
        return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
    }
}