package com.studentportal.core;

import com.studentportal.ui.AssignmentUi;
import com.studentportal.ui.FileManagementUi;

public class Client {

    public static void main(String[] args) {
        assignmentUi();
    }

    private static void assignmentUi() {
        AssignmentUi ui = new AssignmentUi();
        ui.show();
    }

    private static void fileManagementUi() {
        FileManagementUi ui = new FileManagementUi();
        ui.show();
    }

}


