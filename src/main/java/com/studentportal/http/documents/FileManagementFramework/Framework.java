package com.studentportal.http.documents.FileManagementFramework;

import com.studentportal.file_management.Document;
import com.studentportal.file_management.DocumentHelper;
import com.studentportal.http.HttpRequest;
import com.studentportal.http.RequestAbstractFactory;
import com.studentportal.http.RequestChoice;
import com.studentportal.http.RequestFactoryProducer;
import com.studentportal.http.documents.SaveDocumentRequest;
import com.studentportal.user.LoggedInUserManager;
import com.studentportal.user.User;
import com.studentportal.user.UserRole;

public class Framework {

    private static Framework instance = null;

    private int userID;
    private String userEmail;
    private String userName;
    private UserRole userRole;

    private AdjustableHeaderRequest activeRequest;
    private Document activeDocument;
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

        User loggedInUser = LoggedInUserManager.getInstance().getLoggedInUser();
        if(loggedInUser != null) {
            userID = loggedInUser.getUserNum();
            userEmail = loggedInUser.getUserEmail();
            userName = loggedInUser.getGivenName();
            userRole = loggedInUser.getUserRole();
        }
    }

    public static Framework getInstance() {
        if(instance == null) {
            Framework.instance = new Framework();
        }
        return instance;
    }

    public boolean uploadDocument(Document document) {
        this.activeDocument = document;

        preMarshallOutDispatcher.dispatch(new SystemContext(this));

        String json = DocumentHelper.convertDocToJson(document);
        RequestAbstractFactory docFactory = RequestFactoryProducer.getFactory(RequestChoice.DOCUMENT);
        SaveDocumentRequest request = (SaveDocumentRequest) docFactory.saveRequest();

        this.activeRequest = request;
        postMarshallOutDispatcher.dispatch(new NetworkContext(this));

        request.makeRequest(null, json);



        preMarshallInDispatcher.dispatch(new NetworkContext(this));

        postMarshallInDispatcher.dispatch(new SystemContext(this));
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public AdjustableHeaderRequest getActiveRequest() {
        return activeRequest;
    }

    public void setActiveRequest(AdjustableHeaderRequest activeRequest) {
        this.activeRequest = activeRequest;
    }

    public boolean getRequestValidity() {
        return requestValidity;
    }

    public void setRequestValidity(boolean requestValidity) {
        this.requestValidity = requestValidity;
    }

    public Document getActiveDocument() {
        return activeDocument;
    }
}
