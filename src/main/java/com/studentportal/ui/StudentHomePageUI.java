package com.studentportal.ui;

import com.studentportal.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentHomePageUI extends HomePageUi {

    private JPanel pane;
    private JButton remindersButton;
    private JButton assignmentsBtn;

    public StudentHomePageUI(User user) {
        super(user);
        initComponents();
        setComponentsInPane();
        prepareGui();
    }

    private void initComponents() {
        remindersButton = new JButton("Reminders");
        remindersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemindersUI remindersUI = new RemindersUI();
                remindersUI.show();
            }
        });

        assignmentsBtn = new JButton("Assignments");
        assignmentsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
    }

    private void setComponentsInPane() {
        pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;

        // profile
        c.gridx = 1;
        c.gridy = 0;
        pane.add(super.getProfileBtn(), c);

        // reminders button
        c.gridx = 1;
        c.gridy = 1;
        pane.add(remindersButton, c);

        // assignments
        c.gridx = 1;
        c.gridy = 2;
        pane.add(assignmentsBtn, c);

        // reset password
        c.gridx = 1;
        c.gridy = 3;
        pane.add(super.getRestPasswordBtn(), c);

        // logout
        c.gridx = 1;
        c.gridy = 4;
        pane.add(super.getLogoutBtn(), c);
    }

    private void prepareGui() {
        super.getFrame().setTitle("Student Home");
        super.getFrame().add(pane);
    }
}
