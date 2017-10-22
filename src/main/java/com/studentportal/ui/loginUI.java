package com.studentportal.ui;
import com.studentportal.core.UserAction;
import com.studentportal.core.UserType;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * User interface for logging in a user
 */
public class loginUI extends Ui {
    private JFrame loginFrame;

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
        loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(new GridLayout(3, 2));

        UserNumLabel = new JLabel("User Number: ");
        UserNumField = new JTextField();

        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!validateInput()) {
                    JOptionPane.showMessageDialog(loginFrame, "Please enter information in all fields", "Error",
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

                        JOptionPane.showMessageDialog(loginFrame, "Your Password or User Number is incorrect, Please enter again ", "Error",
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

        loginFrame.add(UserNumLabel);
        loginFrame.add(UserNumField);

        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);

        loginFrame.add(registerButton);
        loginFrame.add(loginButton);
    }

    private boolean validateInput() {
        char[] password = passwordField.getPassword();
        if (UserNumField.getText().isEmpty()||!UserNumField.getText().matches("[0-9]*")||password.length == 0) {
            return false;
        }
        return true;
    }
}