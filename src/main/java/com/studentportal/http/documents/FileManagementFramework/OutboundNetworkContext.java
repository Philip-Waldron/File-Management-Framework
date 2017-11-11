package com.studentportal.http.documents.FileManagementFramework;

public class OutboundNetworkContext extends Context {

    OutboundNetworkContext(Framework f) {
        super(f);
    }

    public AdjustableHeaderRequest getOutboundHttpRequest() {
        return f.getOutboundRequest();
    }
}
