package com.studentportal.ui;

import com.studentportal.core.UserAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User interface for logging in a user
 */
public class loginUI extends Ui {
    private JLabel UserNumLabel;
    private JLabel passwordLabel;

    private JTextField UserNumField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton registerButton;

    public loginUI() {
        prepareGUI();
    }

    private void prepareGUI() {
        getFrame().setTitle("Login");
        getFrame().setSize(400, 200);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().setLocationRelativeTo(null);
        getFrame().setLayout(new GridLayout(3, 2));

        UserNumLabel = new JLabel("User Number: ");
        UserNumField = new JTextField();

        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!validateInput()) {
                    JOptionPane.showMessageDialog(getFrame(), "Please enter information in all fields", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    String password = new String(passwordField.getPassword());
                    int userNum=new Integer(UserNumField.getText());
                    String userType=UserAction.login(userNum,password);
                    if(userType.equals("Teacher")){
                        TeacherHomePageUI TeacherUI = new TeacherHomePageUI();
                        TeacherUI.show();
                        hide();
                    }
                    else if(userType.equals("Student")){
                        StudentHomePageUI StudentUI = new StudentHomePageUI();
                        //StudentUI.show();
                        hide();
                    }
                    else{

                        JOptionPane.showMessageDialog(getFrame(), "Your Password or User Number is incorrect, Please enter again ", "Error",
                                JOptionPane.ERROR_MESSAGE);

                    }

                }
            }
        });

        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIRegister registerUIWindow = new UIRegister();
                registerUIWindow.show();
                hide();
            }
        });

        getFrame().add(UserNumLabel);
        getFrame().add(UserNumField);

        getFrame().add(passwordLabel);
        getFrame().add(passwordField);

        getFrame().add(registerButton);
        getFrame().add(loginButton);
    }

    private boolean validateInput() {
        char[] password = passwordField.getPassword();
        if (UserNumField.getText().isEmpty()||!UserNumField.getText().matches("[0-9]*")||password.length == 0) {
            return false;
        }
        return true;
    }
}