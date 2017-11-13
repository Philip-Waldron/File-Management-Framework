package com.studentportal.reminders.Senders;

import java.util.List;

public class SMSReminderSender extends ReminderSender {

    @Override
    public void sendReminder(String title, String body, List<Integer> targetUserIds) {
        System.out.println("Sending SMS Reminder...\n");
    }
}
