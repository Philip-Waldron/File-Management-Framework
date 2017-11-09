package com.studentportal.core;

import com.studentportal.api.AssignmentApi;
import com.studentportal.api.AuthApi;
import com.studentportal.api.CourseApi;
import com.studentportal.assignments.AssignmentHelper;
import com.studentportal.assignments.CompletedQuiz;
import com.studentportal.courses.Course;
import com.studentportal.courses.CourseHelper;
import com.studentportal.hibernate.UserService;
import com.studentportal.security.auth.AuthHelper;
import com.studentportal.security.auth.SignInCredentials;
import com.studentportal.security.auth.SignUpConfirmDetails;
import com.studentportal.security.auth.SignUpDetails;
import com.studentportal.ui.AssignmentUi;
import com.studentportal.ui.FileManagementUi;
import com.studentportal.ui.LoginUI;
import com.studentportal.user.Admin;
import com.studentportal.user.User;
import com.studentportal.user.UserRole;
import jdk.nashorn.internal.ir.Assignment;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        login();
//        signUpAdmin();
    }

    private static void login() {
        LoginUI ui = new LoginUI();
        ui.show();
    }

    private static void signUpAdmin() {
        String email = "12170321@studentmail.ul.ie";
        String given_name = "David";
        String family_name = "Nkanga";
        UserRole userRole = UserRole.ADMIN;

        SignUpDetails dets = new SignUpDetails(email, given_name, family_name, userRole);
        String json = AuthHelper.convertSignUpDetailsToJson(dets);
        AuthApi api = new AuthApi();
        api.signUp(json);
    }

    private static void assignmentUi() {
//        AssignmentUi ui = new AssignmentUi();
//        ui.show();
    }

    private static void fileManagementUi() {
        FileManagementUi ui = new FileManagementUi();
        ui.show();
    }

}


