package com.studentportal.core;

public class StudentFactory implements UserTypeFactory{

    @Override
    public UserType CreateUserTypeFactory() {
        return new Student();
    }

}
