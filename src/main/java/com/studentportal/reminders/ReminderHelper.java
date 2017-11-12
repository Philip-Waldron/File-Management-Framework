package com.studentportal.reminders;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentportal.reminders.ReminderTypes.Reminder;

import java.io.IOException;
import java.util.List;

public class ReminderHelper {

    public static String convertReminderToJson(Reminder r) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(r);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Reminder extractReminderFromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Reminder r = null;
        try {
            r = mapper.readValue(json, Reminder.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static String convertReminderListToJson(List<Reminder> reminders) {
        return null;
    }

    public static Reminder extractReminderListFromJson(String json) {
        return null;
    }
}
