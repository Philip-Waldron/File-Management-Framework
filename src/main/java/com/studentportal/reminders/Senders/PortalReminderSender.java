package com.studentportal.reminders.Senders;

import java.util.List;

public class PortalReminderSender extends ReminderSender {

    @Override
    public void sendReminder(String title, String body, List<Integer> targetUserIds) {
        System.out.println("Sending Portal Reminder...\n");
    }
}
