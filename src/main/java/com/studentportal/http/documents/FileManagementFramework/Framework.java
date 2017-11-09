package com.studentportal.http.documents.FileManagementFramework;

import com.studentportal.file_management.Document;
import com.studentportal.file_management.DocumentHelper;
import com.studentportal.http.RequestAbstractFactory;
import com.studentportal.http.RequestChoice;
import com.studentportal.http.RequestFactoryProducer;
import com.studentportal.http.RequestHandler;
import com.studentportal.http.documents.SaveDocumentRequest;
import com.studentportal.user.UserType;

public class Framework {

    private static Framework instance = null;

    private int userID;
    private String userEmail;
    private UserType userType;


    public Dispatcher preOutRequest;
    public Dispatcher postOutRequest;
    public Dispatcher preInRequest;
    public Dispatcher postInRequest;

    private Framework() {
        preOutRequest = new Dispatcher();
        postOutRequest = new Dispatcher();
        preInRequest = new Dispatcher();
        postInRequest = new Dispatcher();
    }

    public boolean uploadDocument(Document document) {
        return true;
    }

    public boolean downloadDocument() {
        return true;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserType getUserType() {
        return userType;
    }
}
