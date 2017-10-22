package com.studentportal.core;

public class Teacher extends User implements UserType{
    private int i=20170100;

    public Teacher(){}
    public Teacher(int userNum, String userType,String userName,String userPassword, String userEmail) {
        super(userNum,userType,userName,userPassword,userEmail);
    }

    @Override
    public int UserNumberGeneration(){
        int TeacherNum;
        TeacherNum=i+1;
		  /*get the biggest Teacher number from data base, then add 1 to be next TeacherID */
        return TeacherNum;
    }
}