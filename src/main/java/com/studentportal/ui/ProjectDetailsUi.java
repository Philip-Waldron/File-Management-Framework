package com.studentportal.ui;

import com.studentportal.assignments.AssignmentHelper;
import com.studentportal.assignments.ProjectAssignment;
import com.studentportal.http.*;
import com.studentportal.http.assignments.SaveProjectRequest;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectDetailsUi extends Ui {

    private JPanel pane;

    JLabel nameLbl;
    JTextField nameTxt;

    JLabel lecturerLbl;
    JTextField lecturerTxt;

    JLabel studentLbl;
    JTextField studentTxt;

    JLabel startDateLbl;
    JTextField startDateTxt;

    JLabel endDateLbl;
    JTextField endDateTxt;

    private JLabel specificationLbl;
    private JTextArea specificationTxtArea;

    private JButton cancelBtn;
    private JButton createBtn;

    private ProjectAssignment assignment;
    private RequestHandler createHandler;

    public ProjectDetailsUi() {
        this.createHandler = new RequestHandler() {
            @Override
            public void onSuccess() {
                JOptionPane.showMessageDialog(getFrame(), "Successfully created assignment");
                getFrame().dispose();
            }

            @Override
            public void onFailure(Exception e) {
                if (e.getMessage().contains("409")) {
                    String newName = JOptionPane.showInputDialog(null, e.getMessage());
                    if (StringUtils.isBlank(newName)) {
                        JOptionPane.showMessageDialog(null,
                                "Request not sent because nothing was entered");
                    } else {
                        assignment.setName(newName);
                        String json = AssignmentHelper.convertProjectAssignmentToJson(assignment);

                        RequestAbstractFactory projectFactory = RequestFactoryProducer.getFactory(RequestChoice.PROJECT);
                        SaveProjectRequest request = (SaveProjectRequest) projectFactory.saveRequest();
                        request.makeRequest(createHandler, json);
                    }
                } else {
                    JOptionPane.showMessageDialog(getFrame(), e.getMessage());
                }
            }
        };
        initComponents();
        setComponentsInPane();
        prepareUi();
    }

    private void initComponents() {
        nameLbl = new JLabel("Name:");
        nameTxt = new JTextField();
        lecturerLbl = new JLabel("Lecturer ID");
        lecturerTxt = new JTextField();
        studentLbl = new JLabel("Student ID");
        studentTxt = new JTextField();
        startDateLbl = new JLabel("Start Date(dd/MM/yyyy)");
        startDateTxt = new JTextField();
        endDateLbl = new JLabel("End Date(dd/MM/yyyy)");
        endDateTxt = new JTextField();
        specificationLbl = new JLabel("Specification");
        specificationTxtArea = new JTextArea();
        specificationTxtArea.setLineWrap(true);

        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().dispose();
            }
        });

        createBtn = new JButton("Create");
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTxt.getText();
                String lecturerId = lecturerTxt.getText();
                String studentId = studentTxt.getText();
                String startDateStr = startDateTxt.getText();
                String endDateStr = endDateTxt.getText();
                String specification = specificationTxtArea.getText();

                if (StringUtils.isBlank(name) || StringUtils.isBlank(lecturerId) ||
                        StringUtils.isBlank(studentId) || StringUtils.isBlank(startDateStr) ||
                        StringUtils.isBlank(endDateStr) || StringUtils.isBlank(specification)) {
                    JOptionPane.showMessageDialog(getFrame(), "No fields can be empty");
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date startDate = null;
                    Date endDate = null;
                    try {
                        startDate = sdf.parse(startDateStr);
                        endDate = sdf.parse(endDateStr);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    assignment = new ProjectAssignment(0, name, lecturerId, studentId, startDate, endDate,
                            specification);
                    String json = AssignmentHelper.convertProjectAssignmentToJson(assignment);
                    RequestAbstractFactory projectFactory = RequestFactoryProducer.getFactory(RequestChoice.PROJECT);
                    SaveProjectRequest request = (SaveProjectRequest) projectFactory.saveRequest();
                    request.makeRequest(createHandler, json);
                }
            }
        });
    }

    private void setComponentsInPane() {
        pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        // name
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        pane.add(nameLbl, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        pane.add(nameTxt, c);

        // lecturer
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        pane.add(lecturerLbl, c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.5;
        pane.add(lecturerTxt, c);

        // student
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        pane.add(studentLbl, c);

        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.5;
        pane.add(studentTxt, c);

        // start date
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0.5;
        pane.add(startDateLbl, c);

        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 0.5;
        pane.add(startDateTxt, c);

        // end date
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0.5;
        pane.add(endDateLbl, c);

        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 0.5;
        pane.add(endDateTxt, c);

        // cancel button
        c.gridx = 0;
        c.gridy = 7;
        c.weightx = 0.5;
        pane.add(cancelBtn, c);

        // done button
        c.gridx = 1;
        c.gridy = 7;
        c.weightx = 0.5;
        pane.add(createBtn, c);

        // specification
        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 0.5;
        pane.add(specificationLbl, c);

        c.gridx = 0;
        c.gridy = 6;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.ipady = 20;
        pane.add(specificationTxtArea, c);
    }

    private void prepareUi() {
        getFrame().setTitle("Project Details");
        getFrame().setSize(600, 400);
        getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getFrame().setLocationRelativeTo(null);
        getFrame().setLayout(new BorderLayout());
        getFrame().setResizable(false);

        getFrame().add(pane);
    }
}
