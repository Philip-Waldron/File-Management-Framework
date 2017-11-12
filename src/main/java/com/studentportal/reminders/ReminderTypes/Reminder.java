package com.studentportal.reminders.ReminderTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.studentportal.reminders.Senders.ReminderSender;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reminders")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Reminder {
    int reminderNum;
    ReminderSender sender;
    String title;
    String message;
    Date date;
    List<Integer> targetUserIds;

    public Reminder() {
        this.sender = sender;
    }

    public int getReminderNum() {
        return reminderNum;
    }

    public void setReminderNum(int reminderNum) {
        this.reminderNum = reminderNum;
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

    public List<Integer> getTargetUserIds() {
        return targetUserIds;
    }

    public void setTargetUserIds(List<Integer> targetUserIds) {
        this.targetUserIds = targetUserIds;
    }

    public ReminderSender getSender() {
        return sender;
    }

    public void setSender(ReminderSender sender) {
        this.sender = sender;
    }

    public abstract void send(List<Integer> targetUserIds);


}
