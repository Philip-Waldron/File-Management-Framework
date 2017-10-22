package com.studentportal.core;

public class TeacherFactory implements UserTypeFactory{

    @Override
    public UserType CreateUserTypeFactory() {
        return new Teacher();
    }
}
