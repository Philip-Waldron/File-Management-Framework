package com.studentportal.reminders.Senders;

public class SMSReminderSender implements ReminderSender {

    public void sendReminder(String title, String body) {
        System.out.println("Sending SMS Reminder...\n");
    }
}
