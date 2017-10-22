package com.studentportal.core;

/**
 * This is the base class for all users in the system
 */
public abstract class User {
    private String userName;

    private String userEmail;

    private String userPassword;

    private int userNum;

    private String userType;

    public User() {
        super();
    }

    public User(int userNum, String userType,String userName,String userPassword, String userEmail) {
        super();
        this.userNum = userNum;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userType=userType;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User [userNum=" + userNum + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
                + userPassword + "]";
    }

}
