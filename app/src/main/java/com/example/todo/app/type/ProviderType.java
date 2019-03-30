package com.example.todo.app.type;

import org.seasar.doma.Domain;

@Domain(valueType = String.class, factoryMethod = "valueOf")
public enum ProviderType {
	GITHUB;
	
    public String getValue() {
        return this.name().toUpperCase();
    }

}
