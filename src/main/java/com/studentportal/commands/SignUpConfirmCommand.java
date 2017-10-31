package com.studentportal.commands;

import com.studentportal.security.auth.SignUpConfirmDetails;
import com.studentportal.security.aws.AwsCognitoResult;
import com.studentportal.security.aws.SignUpConfirm;

import javax.ws.rs.WebApplicationException;

public class SignUpConfirmCommand implements Command<Void> {
    private SignUpConfirmDetails confirmDetails;

    public SignUpConfirmCommand(SignUpConfirmDetails confirmDetails) {
        this.confirmDetails = confirmDetails;
    }

    @Override
    public Void execute() {
        String email = confirmDetails.getEmail();
        String tempPassword = confirmDetails.getTempPassword();
        String finalPassword = confirmDetails.getFinalPassword();
        AwsCognitoResult result = SignUpConfirm.doWork(email, tempPassword, finalPassword);
        if (result.isSuccessful()) {
            System.out.println("Account successfully confirmed");
        } else {
            throw new WebApplicationException(result.getReason().name());
        }
        return null;
    }
}
