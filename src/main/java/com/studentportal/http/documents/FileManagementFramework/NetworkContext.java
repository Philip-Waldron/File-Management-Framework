package com.studentportal.http.documents.FileManagementFramework;

import org.apache.http.HttpRequest;

public class NetworkContext extends Context {

    Framework f;

    NetworkContext(Framework f) {
        super(f);
    }

    public HttpRequest getHttpRequest() {
        return f.getActiveRequest();
    }

    public boolean getRequestValidity() { return f.getRequestValidity(); }

    public void setRequestValidity(boolean validity){ f.setRequestValidity(validity); }
}
