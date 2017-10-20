package com.studentportal.http;

public interface HttpRequest<T> {
    public T makeRequest(RequestHandler callback);
}
