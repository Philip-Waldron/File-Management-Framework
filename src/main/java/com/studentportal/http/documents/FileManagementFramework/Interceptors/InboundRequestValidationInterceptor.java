package com.studentportal.http.documents.FileManagementFramework.Interceptors;

import com.studentportal.http.documents.FileManagementFramework.AdjustableHeaderRequest;
import com.studentportal.http.documents.FileManagementFramework.InboundNetworkContext;
import com.studentportal.http.documents.FileManagementFramework.Interceptor;
import com.studentportal.http.documents.FileManagementFramework.OutboundNetworkContext;

import javax.ws.rs.core.HttpHeaders;


public class InboundRequestValidationInterceptor implements Interceptor<InboundNetworkContext> {
    @Override
    public void notify(InboundNetworkContext context) {
        boolean validRequest = false;
        HttpHeaders headers = context.getRequestHeaders();

        if(headers.getRequestHeader("Validation-Token").get(0) != null) {
            if(headers.getRequestHeader("Validation-Token").get(0).matches("validationToken123!"))
                validRequest = true;
        }

        if(!validRequest)
            System.out.print("Payload validated validation token");

        else System.out.print("Warning! Payload insecure!");

    }
}
