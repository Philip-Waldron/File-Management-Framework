package com.studentportal.http.documents.FileManagementFramework;


public abstract interface Interceptor<C extends Context> {

    public abstract void notify(C context);
}
