package com.studentportal.http.documents.FileManagementFramework;


import org.apache.http.Header;

public interface AdjustableHeaderRequest {

    public void setHeader(String name, String value);

    public Header[] getHeaders();
}
