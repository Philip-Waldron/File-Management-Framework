package com.studentportal.reminders.Senders;

import com.studentportal.hibernate.UserService;
import com.studentportal.user.User;

import java.util.List;
import java.util.Properties;
import java.util.*;
import javax.activation.*;

public class EmailReminderSender extends ReminderSender {
    UserService userService;

    public EmailReminderSender() {
        this.setSenderType(SenderType.EMAIL);
    }

    @Override
    public void sendReminder(String title, String body, List<Integer> targetUserIds) {
        userService = new UserService();

        for(int userId : targetUserIds) {
            sendEmail(userService.findById(userId).getUserEmail());
        }
    }

    private void sendEmail(String userEmail) {

    }
}
