package com.studentportal.reminders;

import com.studentportal.reminders.ReminderTypes.AssignmentReminder;
import com.studentportal.reminders.ReminderTypes.MeetingReminder;
import com.studentportal.reminders.ReminderTypes.Reminder;
import com.studentportal.reminders.Senders.EmailReminderSender;
import com.studentportal.reminders.Senders.PortalReminderSender;
import com.studentportal.reminders.Senders.SMSReminderSender;
import com.studentportal.ui.RemindersUI;

import java.util.Date;

public class ReminderTestHarness {

    public static void main(String[] args) {

        Reminder builtAssignmentReminder = new AssignmentReminder.AssignmentReminderBuilder(new EmailReminderSender())
                                                                 .title("Assignment Due")
                                                                 .message("You have an assignment due today")
                                                                 .date(new Date())
                                                                 .build();

        System.out.println("Assignment Built Reminder title : " + builtAssignmentReminder.getTitle());
        System.out.println("Assignment Built Reminder message : " + builtAssignmentReminder.getMessage());
        builtAssignmentReminder.send();

        RemindersUI remindersUI = new RemindersUI();
        remindersUI.show();

    }
}
