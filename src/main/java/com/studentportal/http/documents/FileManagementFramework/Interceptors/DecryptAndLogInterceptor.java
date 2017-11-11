package com.studentportal.http.documents.FileManagementFramework.Interceptors;

import com.studentportal.http.documents.FileManagementFramework.Interceptor;
import com.studentportal.http.documents.FileManagementFramework.SystemContext;


public class DecryptAndLogInterceptor implements Interceptor<SystemContext> {

    public void notify(SystemContext context) {
        System.out.print("File " + context.getDocument().getFileName() + "was uploaded by user " + decrypt(context.getUserName()));
    }

    private String decrypt(String string) {
        return string.replace("encrypted", "");
    }
}
