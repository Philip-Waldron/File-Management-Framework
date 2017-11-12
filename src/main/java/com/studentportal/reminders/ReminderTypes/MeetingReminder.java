package com.studentportal.reminders.ReminderTypes;

import com.studentportal.reminders.Senders.ReminderSender;

import java.util.Date;
import java.util.List;

public class MeetingReminder extends Reminder {

    private MeetingReminder() {
        super();
        setDefaultValues();
    }

    public MeetingReminder(MeetingReminderBuilder builder) {
        this.sender = builder.sender;
        this.title = builder.title;
        this.message = builder.message;
        this.date = builder.date;
        this.targetUserIds = builder.targetUserIds;
    }

    private MeetingReminder(ReminderSender sender) {
        super();
        this.sender = sender;
        setDefaultValues();
    }

    private void setDefaultValues() {
        date = new Date();
        title = "Meeting Due";
        message = "You have a meeting " + date + ".";
    }

    @Override
    public void send(List<Integer> targetUserIds) {
        sender.sendReminder(title, message);
    }

    public static class MeetingReminderBuilder {
        private final ReminderSender sender;
        private String title;
        private String message;
        private Date date;
        private List<Integer> targetUserIds;

        public MeetingReminderBuilder(ReminderSender sender) {
            this.sender = sender;
        }

        public MeetingReminderBuilder title(String title) {
            this.title = title;
            return this;
        }

        public MeetingReminderBuilder message(String message) {
            this.message = message;
            return this;
        }

        public MeetingReminderBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public MeetingReminderBuilder ownerId(List<Integer> targetUserIds) {
            this.targetUserIds = targetUserIds;
            return this;
        }

        public Reminder build() {
            return new MeetingReminder(this);
        }

    }
}
