package com.studentportal.http.documents.FileManagementFramework.Interceptors;

import com.studentportal.http.documents.FileManagementFramework.Interceptor;
import com.studentportal.http.documents.FileManagementFramework.NetworkContext;
import com.sun.net.httpserver.Headers;
import org.apache.http.Header;
import org.apache.http.HttpRequest;


public class InboundRequestValidationInterceptor implements Interceptor<NetworkContext> {
    @Override
    public void notify(NetworkContext context) {
        HttpRequest request = context.getHttpRequest();

        boolean validRequest = false;
        Header[] headers = request.getAllHeaders();
        for(Header header : headers) {
            if(header.getName().matches("Validation-Token")) {
                if(header.getValue().matches("validationToken123!"))
                    validRequest = true;
            }
        }

        if(!validRequest)
            System.out.print("Warning! Payload insecure!");

        context.setRequestValidity(validRequest);
    }
}
