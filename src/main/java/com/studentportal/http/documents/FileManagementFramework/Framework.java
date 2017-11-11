package com.studentportal.http.documents.FileManagementFramework;

import com.studentportal.file_management.Document;
import com.studentportal.file_management.DocumentHelper;
import com.studentportal.http.RequestAbstractFactory;
import com.studentportal.http.RequestChoice;
import com.studentportal.http.RequestFactoryProducer;
import com.studentportal.http.RequestHandler;
import com.studentportal.http.documents.FileManagementFramework.Interceptors.DecryptAndLogInterceptor;
import com.studentportal.http.documents.FileManagementFramework.Interceptors.InboundRequestValidationInterceptor;
import com.studentportal.http.documents.FileManagementFramework.Interceptors.OutboundRequestValidationInterceptor;
import com.studentportal.http.documents.FileManagementFramework.Interceptors.UserEncryptionInterceptor;
import com.studentportal.http.documents.SaveDocumentRequest;
import com.studentportal.user.LoggedInUserManager;
import com.studentportal.user.User;
import com.studentportal.user.UserRole;

import javax.ws.rs.core.HttpHeaders;

public class Framework {

    private static Framework instance = null;

    private int userID;
    private String userEmail;
    private String userName;
    private UserRole userRole;

    private AdjustableHeaderRequest outboundRequest;
    private HttpHeaders inboundHeaders;
    private Document activeDocument;

    public Dispatcher preMarshallOutDispatcher;
    public Dispatcher postMarshallOutDispatcher;
    public Dispatcher preMarshallInDispatcher;
    public Dispatcher postMarshallInDispatcher;

    private Framework() {
        preMarshallOutDispatcher = new Dispatcher();
        postMarshallOutDispatcher = new Dispatcher();
        preMarshallInDispatcher = new Dispatcher();
        postMarshallInDispatcher = new Dispatcher();

        if(LoggedInUserManager.getInstance().getLoggedInUser() != null) {
            User loggedInUser = LoggedInUserManager.getInstance().getLoggedInUser();
            userID = loggedInUser.getUserNum();
            userEmail = loggedInUser.getUserEmail();
            userName = loggedInUser.getGivenName();
            userRole = loggedInUser.getUserRole();
        }
        //dummy user in case there are no users logged in
        else {
            userID = 1234;
            userEmail = "test@email.com";
            userName = "John Smith";
            userRole = UserRole.STUDENT;
        }
    }

    public static Framework getInstance() {
        if(instance == null) {
            Framework.instance = new Framework();
        }
        return instance;
    }

    public void uploadDocument(Document document) {
        this.activeDocument = document;

        preMarshallOutDispatcher.dispatch(new SystemContext(this));

        String json = DocumentHelper.convertDocToJson(document);
        RequestAbstractFactory docFactory = RequestFactoryProducer.getFactory(RequestChoice.DOCUMENT);
        SaveDocumentRequest request = (SaveDocumentRequest) docFactory.saveRequest();
        //For validating response
        RequestHandler callback = new RequestHandler() {
            @Override
            public void onSuccess() {
                System.out.print("Callback success");
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        };

        this.outboundRequest = request;

        postMarshallOutDispatcher.dispatch(new OutboundNetworkContext(this));

        request.makeRequest(callback, json);
    }

    public void serverReachedPreMarshall(HttpHeaders headers) {
        this.inboundHeaders = headers;
        preMarshallInDispatcher.dispatch(new InboundNetworkContext(this));
    }

    public void serverReachedPostMarshall(Document document) {
        this.activeDocument = document;
        postMarshallInDispatcher.dispatch(new SystemContext(this));
    }

    int getUserID() {
        return userID;
    }

    String getUserEmail() {
        return userEmail;
    }

    String getUserName() {
        return userName;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    UserRole getUserRole() {
        return userRole;
    }

    AdjustableHeaderRequest getOutboundRequest() {
        return outboundRequest;
    }

    HttpHeaders getInboundHeaders() {
        return inboundHeaders;
    }

    Document getActiveDocument() {
        return activeDocument;
    }
}
