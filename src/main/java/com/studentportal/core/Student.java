package com.studentportal.core;

public class Student extends User implements UserType {
    private  int i=20170200;
    public Student(){}
    public Student(int userNum, String userType,String userName,String userPassword, String userEmail) {
        super(userNum,userType,userName,userPassword,userEmail);
    }


    @Override
    public int UserNumberGeneration() {
        int StudentNum;
        StudentNum=i+1;
		   /*get the biggest Student number from data base, then add 1 to be next studentID */
        return StudentNum;

    }


}
