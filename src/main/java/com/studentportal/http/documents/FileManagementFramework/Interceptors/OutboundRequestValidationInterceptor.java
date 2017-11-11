package com.studentportal.http.documents.FileManagementFramework.Interceptors;

import com.studentportal.http.documents.FileManagementFramework.Interceptor;
import com.studentportal.http.documents.FileManagementFramework.OutboundNetworkContext;

public class OutboundRequestValidationInterceptor implements Interceptor<OutboundNetworkContext> {
    @Override
    public void notify(OutboundNetworkContext context) {
        context.getOutboundHttpRequest().setHeader("Validation-Token", getValidationToken());
    }

    private String getValidationToken() {
        return "validationToken123!";
    }
}
