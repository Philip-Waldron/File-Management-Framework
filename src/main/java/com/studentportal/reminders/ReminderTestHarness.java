package com.studentportal.reminders;

import com.studentportal.reminders.ReminderTypes.AssignmentReminder;
import com.studentportal.reminders.ReminderTypes.MeetingReminder;
import com.studentportal.reminders.ReminderTypes.Reminder;
import com.studentportal.reminders.Senders.EmailReminderSender;
import com.studentportal.reminders.Senders.SMSReminderSender;
import com.studentportal.ui.RemindersUI;

import java.util.Date;

public class ReminderTestHarness {

    public static void main(String[] args) {

        Reminder builtAssignmentReminder = new AssignmentReminder.AssignmentReminderBuilder(new SMSReminderSender())
                                                                 .title("Assignment Due")
                                                                 .message("You have an assignment due soon")
                                                                 .date(new Date())
                                                                 .build();

        Reminder builtMeetingReminder = new MeetingReminder.MeetingReminderBuilder(new EmailReminderSender())
                                                           .title("Meeting Due")
                                                           .message("You have a meeting soon")
                                                           .date(new Date())
                                                           .build();

        System.out.println("Assignment Built Reminder title : " + builtAssignmentReminder.getTitle());
        System.out.println("Assignment Built Reminder message : " + builtAssignmentReminder.getMessage());
        builtAssignmentReminder.send();

        System.out.println("Meeting Built Reminder title : " + builtMeetingReminder.getTitle());
        System.out.println("Meeting Built Reminder message : " + builtMeetingReminder.getMessage());
        builtMeetingReminder.send();

        RemindersUI remindersUI = new RemindersUI();
        remindersUI.show();

    }
}
