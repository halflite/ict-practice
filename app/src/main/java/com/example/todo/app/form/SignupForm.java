package com.example.todo.app.form;

import java.util.Optional;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/** 
 * ユーザー登録用フォーム
 * 
 * @author shingo
 *
 */
public class SignupForm {
    @NotNull
    @Size(min = 4, max = 64)
    @Pattern(regexp = "^\\w+$")
    private String username;

    @Size(max = 32)
    private String dispalyName;

    @NotNull
    @Size(min = 4, max = 72)
    private String password;

    @NotNull
    @Size(min = 4, max = 72)
    private String confirmPassword;

    @AssertTrue
    public boolean isMatchConfirm() {
        if (this.password == null || this.password.isEmpty()) {
            return true;
        }
        if (this.confirmPassword == null || this.confirmPassword.isEmpty()) {
            return true;
        }
        
        return this.password.equals(this.confirmPassword);
    }
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    public String getDispalyName() {
        return dispalyName;
    }

    public void setDispalyName(String dispalyName) {
        this.dispalyName = dispalyName;
    }

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	/** DBに保存するための名前を返します */
	public String getSavingDisplayName() {
	    return Optional.ofNullable(this.dispalyName)
	            .filter(StringUtils::isNotEmpty)
	            .orElse(this.username);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
