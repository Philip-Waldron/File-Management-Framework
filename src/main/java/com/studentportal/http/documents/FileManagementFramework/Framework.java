package com.studentportal.http.documents.FileManagementFramework;

import com.studentportal.user.UserType;

public class Framework {

    private static Framework instance = null;

    private int userID;
    private String userEmail;
    private UserType userType;


    public Dispatcher pre_out_request;
    public Dispatcher post_out_request;
    public Dispatcher pre_in_request;
    public Dispatcher post_in_request;

    private Framework() {
        pre_out_request = new Dispatcher();
        post_out_request = new Dispatcher();
        pre_in_request = new Dispatcher();
        post_in_request = new Dispatcher();
    }

    public boolean uploadDocument() {
        return true;
    }

    public boolean downloadDocument() {
        return true;
    }
}
