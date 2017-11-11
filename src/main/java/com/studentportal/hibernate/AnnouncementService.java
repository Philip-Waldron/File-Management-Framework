package com.studentportal.hibernate;

import com.studentportal.announcement.announcement;
import java.util.List;

public class AnnouncementService {
    private AnnouncementDAO dao;

    public AnnouncementService() {
        this.dao = new AnnouncementDAO();
    }

    public announcement findById(int id) {
        dao.openCurrentSession();
        announcement a = dao.findById(id);
        dao.closeCurrentSession();
        return a;
    }

    public List<announcement> findAll() {
        dao.openCurrentSession();
        List<announcement> aList = dao.findAll();
        dao.closeCurrentSession();
        return aList;
    }

    public void save(announcement a) {
        dao.openCurrentSessionWithTransaction();
        dao.save(a);
        dao.closeCurrentSessionWithTransaction();
    }

    public void update(announcement a) {
        dao.openCurrentSessionWithTransaction();
        dao.update(a);
        dao.closeCurrentSessionWithTransaction();
    }

    public void delete(int id) {
        dao.openCurrentSessionWithTransaction();
        announcement a = dao.findById(id);
        dao.delete(a);
        dao.closeCurrentSessionWithTransaction();
    }

    public void deleteAll() {
        dao.openCurrentSessionWithTransaction();
        dao.deleteAll();
        dao.closeCurrentSessionWithTransaction();
    }

    public AnnouncementDAO getDAO() {
        return dao;
    }


}
