package com.studentportal.http.documents;

public interface Interceptable {
    public void preMarshall();
    public void postMarshall();
}
