package com.studentportal.reminders.Senders;

public class PortalReminderSender implements ReminderSender {

    public void sendReminder(String title, String body) {
        System.out.println("Sending Portal Reminder...\n");
    }
}
