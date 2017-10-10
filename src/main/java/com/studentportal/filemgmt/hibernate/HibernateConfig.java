package com.studentportal.filemgmt.hibernate;

import com.studentportal.filemgmt.document.Document;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {

    private static SessionFactory sessionFactory = null;

    private static void configure() {
        Configuration conf = new Configuration();

        // class mappings
        conf.addAnnotatedClass(Document.class);

        // props
        conf.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        conf.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/data-store?useSSL=false");
        conf.setProperty("hibernate.connection.username", "root");
        conf.setProperty("hibernate.connection.password", "root");
        conf.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        conf.setProperty("hibernate.hbm2ddl.auto", "update");
        conf.setProperty("hibernate.show_sql", "true");
        conf.setProperty("hibernate.connection.pool_size", "20");

        StandardServiceRegistryBuilder b = new StandardServiceRegistryBuilder()
                        .applySettings(conf.getProperties());
        sessionFactory = conf.buildSessionFactory(b.build());
    }

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            configure();
        }
        return sessionFactory;
    }

    public static synchronized void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
