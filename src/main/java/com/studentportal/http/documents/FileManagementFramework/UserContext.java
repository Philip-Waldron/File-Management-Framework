package com.studentportal.http.documents.FileManagementFramework;


import com.studentportal.user.UserType;

public class UserContext extends Context {

    UserContext(Framework f) {
        super(f);

    }

    public int getUserID() { return f.getUserID(); }

    public String getUserEmail() { return f.getUserEmail(); }

    public UserType getUserType() { return f.getUserType(); }
}
