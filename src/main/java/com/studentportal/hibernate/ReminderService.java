package com.studentportal.hibernate;

import com.studentportal.reminders.ReminderTypes.Reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderService {
    private ReminderDAO dao;
    public Reminder findByTitle(String title) {
        List<Reminder> rList = findAll();
        for (Reminder reminder : rList) {
            if (reminder.getTitle().equals(title)) {
                return reminder;
            }
        }
        return null;
    }

    public Reminder findByMessage(String message) {
        List<Reminder> rList = findAll();
        for (Reminder reminder : rList) {
            if (reminder.getMessage().equals(message)) {
                return reminder;
            }
        }
        return null;
    }

    public Reminder findById(int id) {
        dao.openCurrentSession();
        Reminder r = dao.findById(id);
        dao.closeCurrentSession();
        return r;
    }

    public List<Reminder> findAll() {
        dao.openCurrentSession();
        List<Reminder> rList = dao.findAll();
        dao.closeCurrentSession();
        return rList;
    }

    public void save(Reminder r) {
        dao.openCurrentSessionWithTransaction();
        dao.save(r);
        dao.closeCurrentSessionWithTransaction();
    }

    public void update(Reminder r) {
        dao.openCurrentSessionWithTransaction();
        dao.update(r);
        dao.closeCurrentSessionWithTransaction();
    }

    public void delete(int id) {
        dao.openCurrentSessionWithTransaction();
        Reminder r = dao.findById(id);
        dao.delete(r);
        dao.closeCurrentSessionWithTransaction();
    }

    public void deleteAll() {
        dao.openCurrentSessionWithTransaction();
        dao.deleteAll();
        dao.closeCurrentSessionWithTransaction();
    }

    public ReminderDAO getDAO() {
        return dao;
    }
}
