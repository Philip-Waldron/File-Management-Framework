package com.studentportal.hibernate;

import com.studentportal.announcement.announcement;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class AnnouncementDAO implements GenericDAO<announcement, Integer> {

    private Session currentSession;
    private Transaction currentTransaction;

    @Override
    public announcement findById(Integer id) {
        announcement a  = (announcement) getCurrentSession().get(announcement.class, id);
        return a;
    }

    @Override
    public List<announcement> findAll() {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<announcement> query = builder.createQuery(announcement.class);
        Root<announcement> aRoot = query.from(announcement.class);
        query.select(aRoot);
        List<announcement> aList = getCurrentSession().createQuery(query)
                .getResultList();
        return aList;
    }

    @Override
    public void save(announcement entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(announcement entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public void delete(announcement entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<announcement> aList = findAll();
        for(announcement a : aList) {
            delete(a);
        }
    }

    public Session openCurrentSession() {
        currentSession = HibernateConfig.getSessionFactory()
                .openSession();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = HibernateConfig.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session session) {
        this.currentSession = session;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction transaction) {
        this.currentTransaction = transaction;
    }
}

