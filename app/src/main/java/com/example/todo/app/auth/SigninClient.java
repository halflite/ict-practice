package com.example.todo.app.auth;

import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.http.client.direct.DirectFormClient;

public class SigninClient extends DirectFormClient {

    public SigninClient(Authenticator<UsernamePasswordCredentials> signinAuthenticator) {
        super(signinAuthenticator);
    }


}
