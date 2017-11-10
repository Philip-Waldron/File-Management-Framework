package com.studentportal.http.documents.FileManagementFramework.Interceptors;

import com.studentportal.http.documents.FileManagementFramework.Context;
import com.studentportal.http.documents.FileManagementFramework.Interceptor;
import com.studentportal.http.documents.FileManagementFramework.NetworkContext;
import org.apache.http.HttpRequest;

public class OutboundRequestValidationInterceptor implements Interceptor<NetworkContext> {
    @Override
    public void notify(NetworkContext context) {
        context.getHttpRequest().setHeader("Validation-Token", getValidationToken());
    }

    private String getValidationToken() {
        return "validationToken123!";
    }
}
