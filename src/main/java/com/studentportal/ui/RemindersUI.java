package com.studentportal.ui;


import com.studentportal.reminders.ReminderTypes.AssignmentReminder;
import com.studentportal.reminders.ReminderTypes.MeetingReminder;
import com.studentportal.reminders.ReminderTypes.Reminder;
import com.studentportal.reminders.Senders.EmailReminderSender;
import com.studentportal.reminders.Senders.SMSReminderSender;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sun.deploy.trace.TraceLevel.UI;

public class RemindersUI extends Ui {
    private JFrame viewOrdersFrame;

    private JButton backButton;
    private JButton fullDetailsButton;
    private JTable reminderTable;
    private ArrayList<Reminder> reminders;

    public RemindersUI() {
        populateReminders();
        prepareGUI();
    }

    private void populateReminders() {
        reminders = new ArrayList<Reminder>();
        reminders.add(new AssignmentReminder.Builder(new SMSReminderSender())
                                            .date(new Date())
                                            .title("Assignment Due")
                                            .message("Assignment Due soon")
                                            .build());

        reminders.add(new MeetingReminder.Builder(new EmailReminderSender())
                .date(new Date())
                .title("Meeting")
                .message("Meeting Due soon")
                .build());

    }

    private void prepareGUI() {
        viewOrdersFrame = new JFrame("View Orders");
        viewOrdersFrame.setSize(1000, 400);
        viewOrdersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewOrdersFrame.setLocationRelativeTo(null);
        viewOrdersFrame.setLayout(new GridLayout(5, 1));

        reminderTable = new JTable();
        List<Object[]> list = new ArrayList<>();
        for(Reminder o : reminders) {
            list.add(new Object[] {
                    o.getTitle(),
                    o.getMessage(),
                    o.getDate().toString(),
            });
        }

        @SuppressWarnings("serial")
        TableModel orderModel = new DefaultTableModel(list.toArray(new Object[][] {}), new String[] {"Title", "Message", "Date"}) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        reminderTable.setModel(orderModel);
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(reminderTable.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(reminderTable, BorderLayout.CENTER);

        backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToHome();
            }
        });

        fullDetailsButton = new JButton("Full Details");
        fullDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = reminderTable.getSelectedRow();
                if(row >= 0) {
                    Reminder reminder = reminders.get(row);

                    String data = reminder.getTitle() + "\n" + reminder.getMessage() + "\n" + reminder.getDate().toString();
                    JOptionPane.showMessageDialog(viewOrdersFrame, data);
                }
                else JOptionPane.showMessageDialog(viewOrdersFrame, "Please select a row to see details");

            }
        });

        viewOrdersFrame.add(tablePanel);
        viewOrdersFrame.add(fullDetailsButton);
        viewOrdersFrame.add(backButton);

    }

    @Override
    public void show() {
        if(reminders.size() == 0) {
            JOptionPane.showMessageDialog(null, "You have no reminders at this time");
            returnToHome();
        } else {
            viewOrdersFrame.setVisible(true);
        }
    }

    @Override
    public void hide() {
        viewOrdersFrame.setVisible(false);
    }

    private void returnToHome() {
        //TODO: add return home action
        hide();
    }
}
