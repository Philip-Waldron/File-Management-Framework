package com.studentportal.http.documents.FileManagementFramework;


public interface Interceptor<C extends Context> {

    public void notify(C context);
}
