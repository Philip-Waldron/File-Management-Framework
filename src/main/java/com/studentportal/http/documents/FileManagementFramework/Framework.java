package com.studentportal.http.documents.FileManagementFramework;

import com.studentportal.file_management.Document;
import com.studentportal.user.UserType;
import org.apache.http.HttpRequest;

public class Framework {

    private static Framework instance = null;

    private int userID;
    private String userEmail;
    private UserType userType;

    private HttpRequest activeRequest;
    private boolean requestValidity;


    public Dispatcher preMarshallOutDispatcher;
    public Dispatcher postMarshallOutDispatcher;
    public Dispatcher preMarshallInDispatcher;
    public Dispatcher postMarshallInDispatcher;

    private Framework() {
        preMarshallOutDispatcher = new Dispatcher();
        postMarshallOutDispatcher = new Dispatcher();
        preMarshallInDispatcher = new Dispatcher();
        postMarshallInDispatcher = new Dispatcher();
    }

    public static Framework getInstance() {
        if(instance == null) {
            Framework.instance = new Framework();
        }
        return instance;
    }

    public boolean uploadDocument(Document document) {
        preMarshallOutDispatcher.dispatch(new UserContext(this));

        postMarshallOutDispatcher.dispatch(new NetworkContext(this));
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

    public void setUserEmail(String userEmail) { this.userEmail = userEmail;}

    public UserType getUserType() {
        return userType;
    }

    public HttpRequest getActiveRequest() {
        return activeRequest;
    }

    public void setActiveRequest(HttpRequest activeRequest) {
        this.activeRequest = activeRequest;
    }

    public boolean getRequestValidity() {
        return requestValidity;
    }

    public void setRequestValidity(boolean requestValidity) {
        this.requestValidity = requestValidity;
    }
}
