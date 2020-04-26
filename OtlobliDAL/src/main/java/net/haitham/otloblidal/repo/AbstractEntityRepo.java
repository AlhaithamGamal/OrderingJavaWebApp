/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblidal.repo;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import static net.haitham.otloblidal.HibernateUtilMe.getCurrentSession;
import net.haitham.otloblidal.entity.annotation.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HaithamGamal
 */
public abstract class AbstractEntityRepo<E> implements CommonRepo<E> {
//abstract class to prevent creating instance obj

    @Autowired

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Class<E> entityType; //City Class
    private String defaultOrderNameAr;
    private String defaultOrderNameEn;

    public AbstractEntityRepo(Class<E> entityType) {
        this.entityType = entityType;
    }

  //  @Transactional
    @Override
    public E add(E entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

   // @Transactional
    @Override
    public E update(E entity) {

//       getCurrentSession().update(entity);
        E e = (E) getCurrentSession().merge(entity);
        return e;
    }
//      public E updateByIdCity(E entity,Integer id) {
//        E e = getCurrentSession().load(entityType,id );
//       getCurrentSession().update(e);
//  //       E e = (E) getCurrentSession().merge(entity);
//        return entity;
//    }

  //  @Transactional
    @Override
    public void delete(Integer id) {
        E foundEntity = getCurrentSession().load(entityType, id); //load sure existence of id
        getCurrentSession().delete(foundEntity);
    }

    @Override
    public E findById(Integer id) {
        return getCurrentSession().get(entityType, id); //get is not sure of id existence

    }

   // @Transactional
    @Override
    public List<E> findList() {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<E> entityCriteriaQuery = builder.createQuery(entityType);
        Root<E> root = entityCriteriaQuery.from(entityType);
        entityCriteriaQuery.select(root);
        if (defaultOrderNameEn != null) {
            entityCriteriaQuery.orderBy(builder.asc(root.get(defaultOrderNameEn)));
        } else if (defaultOrderNameAr != null) {
            entityCriteriaQuery.orderBy(builder.asc(root.get(defaultOrderNameAr)));

        }
        Query<E> queryEntity = getCurrentSession().createQuery(entityCriteriaQuery);
        List<E> cityList = queryEntity.getResultList();
        return cityList;
    }

   // @Transactional
    public Long count() {

        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Long> entityCriteriaQuery = builder.createQuery(Long.class);
        Root<E> root = entityCriteriaQuery.from(entityType);
        if (defaultOrderNameAr != null) {
            entityCriteriaQuery.select(builder.count(root.get("nameAr")));
            Query<Long> queryCity = getCurrentSession().createQuery(entityCriteriaQuery);
            List<Long> cityList = queryCity.getResultList();
            return cityList.get(0);
        } else if (defaultOrderNameEn != null) {

            entityCriteriaQuery.select(builder.count(root.get("nameEn")));
            Query<Long> queryCity = getCurrentSession().createQuery(entityCriteriaQuery);
            List<Long> cityList = queryCity.getResultList();
            return cityList.get(0);
        } else {
            entityCriteriaQuery.select(builder.count(root.get("id")));
            Query<Long> queryCity = getCurrentSession().createQuery(entityCriteriaQuery);
            List<Long> cityList = queryCity.getResultList();
            return cityList.get(0);
        }

    }

  //  @Transactional
    public List<E> findLike(String pattern) {
        String matched = "%" + pattern + "%";

        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<E> entityCriteriaQuery = builder.createQuery(entityType);

        Root<E> root = entityCriteriaQuery.from(entityType);
        if (defaultOrderNameAr != null || defaultOrderNameEn != null) {
            Predicate[] likeConditions = new Predicate[2];
            likeConditions[0] = builder.like(root.<String>get("nameAr"), matched);
            likeConditions[1] = builder.like(root.<String>get("nameEn"), matched);

            entityCriteriaQuery.select(root).where(builder.or(likeConditions));
            entityCriteriaQuery.orderBy(builder.asc(root.get("nameAr")));

            Query<E> queryEntity = getCurrentSession().createQuery(entityCriteriaQuery);
            List<E> entityList = queryEntity.getResultList();
            return entityList;
        } else {

            return null;
        }

    }

  //  @Transactional
    public Long countLike(String pattern) {
        String matched = "%" + pattern + "%";
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Long> cityCriteriaQuery = builder.createQuery(Long.class);
        Root<City> root = cityCriteriaQuery.from(City.class);
        if (defaultOrderNameAr != null || defaultOrderNameEn != null) {
            Predicate[] likeConditions = new Predicate[2];
            likeConditions[0] = builder.like(root.<String>get("nameAr"), matched);
            likeConditions[1] = builder.like(root.<String>get("nameEn"), matched);
            cityCriteriaQuery.select(builder.count(root.get("nameAr"))).where(builder.or(likeConditions));
            Query<Long> queryCity = getCurrentSession().createQuery(cityCriteriaQuery);
            List<Long> cityList = queryCity.getResultList();
            return cityList.get(0);
        }
        return null;

    }

    public void setDefaultOrderNameAr(String defaultOrderNameAr) {
        this.defaultOrderNameAr = defaultOrderNameAr;

    }

    public void setDefaultOrderNameEn(String defaultOrderNameEn) {
        this.defaultOrderNameEn = defaultOrderNameEn;

    }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

}
