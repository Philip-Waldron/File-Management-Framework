package com.studentportal.reminders.ReminderTypes;

import com.studentportal.reminders.Senders.ReminderSender;

import java.util.Date;
import java.util.List;

public class AssignmentReminder extends Reminder {

    private AssignmentReminder() {
        super();
        setDefaultValues();
    }

    public AssignmentReminder(AssignmentReminderBuilder builder) {
        this.sender = builder.sender;
        this.title = builder.title;
        this.message = builder.message;
        this.date = builder.date;
        this.targetUserIds = builder.targetUserIds;
    }

    private AssignmentReminder(ReminderSender sender) {
        super();
        this.sender = sender;
        setDefaultValues();
    }

    private void setDefaultValues() {
        date = new Date();
        title = "Assignment Due";
        message = "You have an assignment due today!";
    }

    @Override
    public void send() {
        sender.sendReminder(title, message, targetUserIds);
    }

    public static class AssignmentReminderBuilder {
        private final ReminderSender sender;
        private String title;
        private String message;
        private Date date;
        private List<Integer> targetUserIds;

        public AssignmentReminderBuilder(ReminderSender sender) {
            this.sender = sender;
        }

        public AssignmentReminderBuilder title(String title) {
            this.title = title;
            return this;
        }

        public AssignmentReminderBuilder message(String message) {
            this.message = message;
            return this;
        }

        public AssignmentReminderBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public AssignmentReminderBuilder targetUserIds(List<Integer> targetUserIds) {
            this.targetUserIds = targetUserIds;
            return this;
        }

        public AssignmentReminder build() {
            if(this.title == null)
                title = "Assignment Due";
            if(this.message == null)
                message = "You have an assignment due today!";
            return new AssignmentReminder(this);
        }

    }
}
