package com.studentportal.reminders.ReminderTypes;

import com.studentportal.reminders.Senders.ReminderSender;

import java.util.Date;

public abstract class Reminder {
    ReminderSender sender;
    String title;
    String message;
    Date date;

    public Reminder() {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ReminderSender getSender() {
        return sender;
    }

    public void setSender(ReminderSender sender) {
        this.sender = sender;
    }

    public abstract void send();


}
