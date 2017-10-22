package com.studentportal.reminders.ReminderTypes;

import com.studentportal.reminders.Senders.ReminderSender;

import java.util.Date;

public class AssignmentReminder extends Reminder {

    public AssignmentReminder() {
        super();
        setDefaultValues();
    }

    public AssignmentReminder(Builder builder) {
        this.sender = builder.sender;
        this.title = builder.title;
        this.message = builder.message;
        this.date = builder.date;
    }

    public AssignmentReminder(ReminderSender sender) {
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
    public void send() {
        sender.sendReminder(title, message);
    }

    public static class Builder {
        private final ReminderSender sender;
        private String title;
        private String message;
        private Date date;

        public Builder(ReminderSender sender) {
            this.sender = sender;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Reminder build() {
            return new AssignmentReminder(this);
        }

    }
}
