package com.studentportal.commands;

import com.studentportal.security.aws.AwsCognito;
import com.studentportal.security.aws.AwsCognitoResult;
import com.studentportal.security.aws.ForgotPassword;

import javax.ws.rs.WebApplicationException;

public class ForgotPasswordCommand implements Command<Void> {
    private String email;

    public ForgotPasswordCommand(String email) {
        this.email = email;
    }

    @Override
    public Void execute() {
        AwsCognitoResult result = ForgotPassword.doWork(email);
        if (result.isSuccessful()) {
            System.out.println("Verification code sent to email");
        } else {
            throw new WebApplicationException(result.getReason().name());
        }
        return null;
    }
}
