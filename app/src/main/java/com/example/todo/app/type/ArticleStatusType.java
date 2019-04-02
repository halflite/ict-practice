package com.example.todo.app.type;

import org.seasar.doma.Domain;

@Domain(valueType = String.class, factoryMethod = "valueOf")
public enum ArticleStatusType {
	/** 進行中 */
	OPENED,
	/** 完了 */
	COMPLETED,
	/** 削除 */
	DELETED;
	
    public String getValue() {
        return this.name().toUpperCase();
    }
    
    public boolean isCompleted() {
        return this == COMPLETED;
    }
}
