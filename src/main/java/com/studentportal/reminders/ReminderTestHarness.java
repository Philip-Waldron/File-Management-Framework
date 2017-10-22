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

        Reminder assignmentSMSReminder = new AssignmentReminder();
        Reminder assignmentEmailReminder = new AssignmentReminder();
        Reminder assignmentPortalReminder = new AssignmentReminder();

        assignmentSMSReminder.setSender(new SMSReminderSender());
        assignmentEmailReminder.setSender(new EmailReminderSender());
        assignmentPortalReminder.setSender(new PortalReminderSender());

        Reminder meetingSMSReminder = new MeetingReminder(new SMSReminderSender());
        Reminder meetingEmailReminder = new MeetingReminder(new EmailReminderSender());
        Reminder meetingPortalReminder = new MeetingReminder(new PortalReminderSender());

        Reminder builtAssignmentReminder = new AssignmentReminder.Builder(new SMSReminderSender())
                                                                 .title("Assignment Due")
                                                                 .message("You have an assignment due soon")
                                                                 .date(new Date())
                                                                 .build();

        System.out.println("Assignment SMS Reminder title : " + assignmentSMSReminder.getTitle());
        System.out.println("Assignment SMS Reminder message : " + assignmentSMSReminder.getMessage());
        assignmentSMSReminder.send();

        System.out.println("Assignment Email Reminder title : " + assignmentEmailReminder.getTitle());
        System.out.println("Assignment Email Reminder message : " + assignmentEmailReminder.getMessage());
        assignmentEmailReminder.send();

        System.out.println("Assignment Portal Reminder title : " + assignmentPortalReminder.getTitle());
        System.out.println("Assignment Portal Reminder message : " + assignmentPortalReminder.getMessage());
        assignmentPortalReminder.send();

        System.out.println("~~~~~~~~~~~~~\nMeeting Reminders\n");

        System.out.println("Meeting SMS Reminder title : " + meetingSMSReminder.getTitle());
        System.out.println("Meeting SMS Reminder message : " + meetingSMSReminder.getMessage());
        meetingSMSReminder.send();

        System.out.println("Meeting Email Reminder title : " + meetingEmailReminder.getTitle());
        System.out.println("Meeting Email Reminder message : " + meetingEmailReminder.getMessage());
        meetingEmailReminder.send();

        System.out.println("Meeting Portal Reminder title : " + meetingPortalReminder.getTitle());
        System.out.println("Meeting Portal Reminder message : " + meetingPortalReminder.getMessage());
        meetingPortalReminder.send();

        System.out.println("Assignment Built Reminder title : " + builtAssignmentReminder.getTitle());
        System.out.println("Assignment Built Reminder message : " + builtAssignmentReminder.getMessage());
        builtAssignmentReminder.send();

        RemindersUI remindersUI = new RemindersUI();
        remindersUI.show();

    }
}
