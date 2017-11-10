package com.studentportal.http.documents.FileManagementFramework.Interceptors;


import com.studentportal.http.documents.FileManagementFramework.Context;
import com.studentportal.http.documents.FileManagementFramework.Interceptor;
import com.studentportal.http.documents.FileManagementFramework.UserContext;

public class UserEncryptionInterceptor implements Interceptor<UserContext> {

    @Override
    public void notify(UserContext context) {
        String userEmail = context.getUserEmail();
        userEmail = encrypt(userEmail);
        context.setUserEmail(userEmail);
    }

    private String encrypt(String userEmail) {
        return "encrypted" + userEmail + "encrypted";
    }
}
