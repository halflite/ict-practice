package com.example.todo.app.type;

import org.seasar.doma.Domain;

@Domain(valueType = String.class, factoryMethod = "valueOf")
public enum AccountStatusType {
	ENABLED, DISABLED;
	
    public String getValue() {
        return this.name().toUpperCase();
    }

    /** @return ログイン可能の時 {@code true} */
    public boolean isLoginable() {
        return this == ENABLED;
    }
}
