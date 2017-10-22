package com.studentportal.reminders.Senders;

public class EmailReminderSender implements ReminderSender {

    public void sendReminder(String title, String body) {
        System.out.println("Sending Email Reminder...\n");
    }
}
