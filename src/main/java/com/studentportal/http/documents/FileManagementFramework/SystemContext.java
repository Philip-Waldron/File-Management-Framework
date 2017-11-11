package com.studentportal.http.documents.FileManagementFramework;

import com.studentportal.file_management.Document;
import com.studentportal.user.UserRole;
import com.studentportal.user.UserType;

public class SystemContext extends Context {

    SystemContext(Framework f) {
        super(f);
    }

    public int getUserID() { return f.getUserID(); }

    public String getUserEmail() { return f.getUserEmail(); }

    public String getUserName() { return f.getUserName(); }

    public void setUserName(String userName) { f.setUserName(userName); }

    public UserRole getUserType() { return f.getUserRole(); }

    public Document getDocument() { return f.getActiveDocument(); }
}
