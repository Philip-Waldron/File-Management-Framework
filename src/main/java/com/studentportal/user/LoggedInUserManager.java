package com.studentportal.user;

public class LoggedInUserManager {

    private static LoggedInUserManager instance;
    private User loggedInUser;

    private LoggedInUserManager() {

    }

    public static LoggedInUserManager getInstance() {
        return instance;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User user) {
        loggedInUser = user;
    }
    public void logOutUser() {
        loggedInUser = null;
    }
}
