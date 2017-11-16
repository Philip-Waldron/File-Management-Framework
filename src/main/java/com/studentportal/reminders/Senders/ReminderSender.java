package com.studentportal.reminders.Senders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reminderSender")

@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailReminderSender.class, name = "EmailReminderSender"),
        @JsonSubTypes.Type(value = SMSReminderSender.class, name = "SMSReminderSender"),
        @JsonSubTypes.Type(value = PortalReminderSender.class, name = "PortalReminderSender")
    })

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
