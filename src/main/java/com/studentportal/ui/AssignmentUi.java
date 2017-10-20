package com.studentportal.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignmentUi extends Ui {

    private JPanel pane;

    private JButton setAssignmentBtn;
    private GridBagConstraints c;


    public AssignmentUi() {
        initComponents();
        setComponentsInPane();
        prepareUi();
    }

    private void initComponents() {
        setAssignmentBtn = new JButton("Set Assignment");
        setAssignmentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = getAssignmentChoice();
                if (choice == 0) {
                    // quiz
                    QuizDetailsUi ui = new QuizDetailsUi();
                    ui.show();
                } else if (choice == 1) {
                    // project
                    ProjectDetailsUi ui = new ProjectDetailsUi();
                    ui.show();
                } else {
                    // do nothing
                }
            }
        });
    }

    private int getAssignmentChoice() {
        // project is choice = 1
        // quiz is choice = 0
        int choice = JOptionPane.showOptionDialog(getFrame(),
                "Quiz or Project",
                "Choose an option",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Quiz", "Project"},
                null);
        return choice;
    }

    private void setComponentsInPane() {
        pane = new JPanel();
        pane = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();

        // set assignment button
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0, 150, 0, 150);
        pane.add(setAssignmentBtn, c);
    }

    private void prepareUi() {
        getFrame().setTitle("Assignments");
        getFrame().setSize(500, 300);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().setLocationRelativeTo(null);
        getFrame().setLayout(new GridLayout(3, 1));
        getFrame().setResizable(false);

        getFrame().add(pane);
        getFrame().pack();
    }
}
