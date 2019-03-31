package com.example.todo.app.exception;

public class AuthenticateException extends RuntimeException {

    private static final long serialVersionUID = 9121354509485666776L;

    private final String messageKey;

    public AuthenticateException(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
