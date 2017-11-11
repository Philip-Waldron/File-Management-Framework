package com.studentportal.http.documents.FileManagementFramework.Interceptors;


import com.studentportal.http.documents.FileManagementFramework.Interceptor;
import com.studentportal.http.documents.FileManagementFramework.SystemContext;

public class UserEncryptionInterceptor implements Interceptor<SystemContext> {

    @Override
    public void notify(SystemContext context) {
        String userName = context.getUserName();
        userName = encrypt(userName);
        context.setUserName(userName);
    }

    private String encrypt(String userEmail) {
        return "encrypted" + userEmail + "encrypted";
    }
}
