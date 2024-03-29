/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblidal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author HaithamGamal
 */
public class HibernateUtilMe {

    private static SessionFactory sessionFactory;

    private HibernateUtilMe() {
//make constructor private to avoid instantiating object from class 
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            buildSessionFactory();
        }
        return sessionFactory;
    }

    private static void buildSessionFactory() {
        //initialize session to call database
        StandardServiceRegistry registry = null; //reference for interface standard service registery
        try {
            registry = new StandardServiceRegistryBuilder() //initialize new class standard service registery builder 
                    .configure() //calling config file XML asking for permission of session
                    .build();

            MetadataSources sources = new MetadataSources(registry); //recieving permission

            Metadata metadata = sources.getMetadataBuilder().build(); //verifying permission of metadata

            sessionFactory = metadata.getSessionFactoryBuilder().build(); //building sesssion from sessionsession factory

        } catch (Exception e) {
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static Session getCurrentSession() {

        return getSessionFactory().getCurrentSession();
    }

    public static Session openSession() {

        return getSessionFactory().openSession();
    }

    public static void cloaseSession() {

        getSessionFactory().getCurrentSession().close();

    }

    public static void beginTransaction() {

        getCurrentSession().beginTransaction();
    }

    public static void commitTransaction() {

        getCurrentSession().getTransaction().commit();
    }

    public static void rollBackTransaction() {

        getCurrentSession().getTransaction().rollback();
    }

    public static boolean isActiveTransaction() {

        return getCurrentSession().getTransaction().isActive();
    }
}
