package com.studentportal.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.omg.CORBA.PUBLIC_MEMBER;
import com.studentportal.core.Student;
import com.studentportal.core.Teacher;
import com.studentportal.core.UserAction;
import com.studentportal.core.UserType;

public class UIRegister extends Ui {
    private JFrame registerFrame;

    private JLabel NameLabel;
    private JTextField NameField;

    private JLabel PasswordLabel;
    private JPasswordField PasswordField;

    private JLabel EmailLabel;
    private JTextField EmailField;
    private JLabel UserTypeLabel;
    private JComboBox cmbMessageList;
    private JButton submitButton;
    private String msg="";
    public UIRegister()
    {
        prepareGUI();
    }

    private void prepareGUI()
    {
        registerFrame = new JFrame("Register");
        registerFrame.setSize(400,200);
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setLayout(new GridLayout(5, 2));

        NameLabel = new JLabel("User Name");
        NameField = new JTextField();

        PasswordLabel = new JLabel("Password");
        PasswordField = new JPasswordField();

        EmailLabel = new JLabel("Email");
        EmailField = new JTextField();

        UserTypeLabel = new JLabel("User Type:");
        String [] messageStrings={"Teacher","Student"};
        cmbMessageList= new JComboBox(messageStrings);

        cmbMessageList.setSelectedIndex(0);
        cmbMessageList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== cmbMessageList){
                    JComboBox cb=(JComboBox)e.getSource();
                    msg=(String)cb.getSelectedItem();
                }
            }
        });

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateInput();

            }
        });

        registerFrame.add(NameLabel);
        registerFrame.add(NameField);

        registerFrame.add(PasswordLabel);
        registerFrame.add(PasswordField);

        registerFrame.add(EmailLabel);
        registerFrame.add(EmailField);
        registerFrame.add(UserTypeLabel);
        registerFrame.add(cmbMessageList);
        registerFrame.add(submitButton);

    }

    private void validateInput()
    {
        String userName =NameField.getText();
        String email = EmailField.getText();
        char[]Password=PasswordField.getPassword();

        if(userName.equals("") || Password.length == 0 ||email.equals("")||msg.equals(""))
        {
            JOptionPane.showMessageDialog(registerFrame, "Please enter information in all fields",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            String password = new String(PasswordField.getPassword());
            UserAction userAct=new UserAction();
            int userNumber = userAct.Register(msg,userName,password,email);
            registerFrame.dispatchEvent(new WindowEvent(registerFrame, WindowEvent.WINDOW_CLOSING));
            Object[] options = {"Cancel", "Go To Profile"};
            int successResult = JOptionPane.showOptionDialog(registerFrame,
                    "Success! You are now registered on our system. Your UserNumber is: "+ userNumber + " Would you like to go to your profile?",
                    "Registration Successful",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);

            if(successResult != 0) {
                TeacherHomePageUI TeacherUI = new TeacherHomePageUI();
                TeacherUI.show();
                hide();
            }
        }

    }

}