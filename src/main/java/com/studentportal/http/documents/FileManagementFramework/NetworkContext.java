package com.studentportal.http.documents.FileManagementFramework;

import org.apache.http.HttpRequest;

public class NetworkContext extends Context {

    private Framework f;

    NetworkContext(Framework f) {
        super(f);
    }

    public AdjustableHeaderRequest getHttpRequest() {
        return f.getActiveRequest();
    }

    public boolean getRequestValidity() { return f.getRequestValidity(); }

    public void setRequestValidity(boolean validity){ f.setRequestValidity(validity); }
}
