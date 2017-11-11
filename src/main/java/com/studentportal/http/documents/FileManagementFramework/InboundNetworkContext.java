package com.studentportal.http.documents.FileManagementFramework;


import javax.ws.rs.core.HttpHeaders;

public class InboundNetworkContext extends Context {

    private Framework f;

    InboundNetworkContext(Framework f) {
        super(f);
    }

    public HttpHeaders getRequestHeaders() { return f.getInboundHeaders(); }


}
