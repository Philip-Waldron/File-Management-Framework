package com.studentportal.core;

import com.studentportal.api.AuthApi;
import com.studentportal.security.auth.AuthHelper;
import com.studentportal.security.auth.SignUpDetails;
import com.studentportal.security.aws.CognitoUser;
import com.studentportal.ui.FileManagementUi;
import com.studentportal.ui.LoginUI;
import com.studentportal.user.UserRole;

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
}