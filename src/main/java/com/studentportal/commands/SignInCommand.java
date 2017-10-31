package com.studentportal.commands;

import com.studentportal.hibernate.UserService;
import com.studentportal.security.auth.SignInCredentials;
import com.studentportal.security.aws.AwsCognitoResult;
import com.studentportal.security.aws.ResultReasons;
import com.studentportal.security.aws.SignIn;
import com.studentportal.user.User;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class SignInCommand implements Command<User> {
    private SignInCredentials credentials;
    private UserService uService;

    public SignInCommand(SignInCredentials credentials, UserService uService) {
        this.credentials = credentials;
        this.uService = uService;
    }

    @Override
    public User execute() {
        AwsCognitoResult result = SignIn.doWork(credentials.getEmail(),
                credentials.getPassword());
        User user = null;
        if (result.isSuccessful()) {
            System.out.println("Successful login attempt");
            user = uService.findByEmail(credentials.getEmail());
            return user;
        } else {
            if (result.getReason().equals(ResultReasons.TEMP_PASSWORD_USED)) {
                throw new WebApplicationException(result.getReason().name(),
                        Response.Status.FOUND);
            }
        }
        return user;
    }
}
