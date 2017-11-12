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
        message = "You have an assignment due " + date.toString() + ".";
    }

    @Override
    public void send(List<Integer> targetUserIds) {
        sender.sendReminder(title, message);
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

        public AssignmentReminderBuilder ownerId(List<Integer> targetUserIds) {
            this.targetUserIds = targetUserIds;
            return this;
        }

        public Reminder build() {
            return new AssignmentReminder(this);
        }

    }
}
