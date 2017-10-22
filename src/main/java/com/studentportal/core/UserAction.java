package com.studentportal.core;

import javax.swing.JOptionPane;

public class UserAction {
    public static String login(int UserNum, String UserPassword) {
         /*choose a all user ID .equal to(UserNum) */
        String userType;
        User user=new Student(20170203,"Student","Nike","abc","Nike@outlook.com");
        if(user.getUserNum()==UserNum&&user.getUserPassword().equals(UserPassword)){
            userType=user.getUserType();
        }
        else{
            userType="";
        }
        return userType;
    }

    public int Register(String userType, String userName, String password, String email){
        int userNumber;
        if(userType.equals("Teacher")){
            UserType user1=new TeacherFactory().CreateUserTypeFactory();
            userNumber=user1.UserNumberGeneration();
	       /*Sore all user information into data base*/
        }
        else {
            UserType user2=new StudentFactory().CreateUserTypeFactory();
            userNumber=user2.UserNumberGeneration();
		    /*Sore all user information into data base*/
        }
        return userNumber;

    }
}