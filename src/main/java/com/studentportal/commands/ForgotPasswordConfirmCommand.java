package com.studentportal.commands;

import com.studentportal.security.auth.ForgotPasswordConfirmDetails;
import com.studentportal.security.aws.AwsCognitoResult;
import com.studentportal.security.aws.ForgotPasswordConfirm;

import javax.ws.rs.WebApplicationException;

public class ForgotPasswordConfirmCommand implements Command<Void> {

    private ForgotPasswordConfirmDetails confirmDetails;

    public ForgotPasswordConfirmCommand(ForgotPasswordConfirmDetails confirmDetails) {
        this.confirmDetails = confirmDetails;
    }

    @Override
    public Void execute() {
        String email = confirmDetails.getEmail();
        String password = confirmDetails.getPassword();
        String verificationCode = confirmDetails.getVerificationCode();
        AwsCognitoResult result = ForgotPasswordConfirm.doWork(email, password,
                verificationCode);

        if (result.isSuccessful()) {
            System.out.println("Password has been reset");
        } else {
            throw new WebApplicationException(result.getReason().name());
        }
        return null;
    }
}
