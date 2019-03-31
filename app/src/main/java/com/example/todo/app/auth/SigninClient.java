package com.example.todo.app.auth;

import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.http.client.indirect.FormClient;

public class SigninClient extends FormClient {

    public SigninClient(Authenticator<UsernamePasswordCredentials> usernamePasswordAuthenticator) {
        super("/signin", usernamePasswordAuthenticator);
    }


}
