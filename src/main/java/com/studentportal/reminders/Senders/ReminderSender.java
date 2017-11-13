package com.studentportal.reminders.Senders;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reminders")
public abstract class ReminderSender {

    @Enumerated(EnumType.STRING)
    @Column(name = "sender_type")
    private SenderType senderType;

    public abstract void sendReminder(String title, String body, List<Integer> targetUserIds);

    public SenderType getSenderType() {
        return senderType;
    }

    public void setSenderType(SenderType senderType) {
        this.senderType = senderType;
    }
}
