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

import net.haitham.otloblidal.HibernateUtilMe;
import net.haitham.otloblidal.entity.annotation.Country;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import static net.haitham.otloblidal.HibernateUtilMe.getCurrentSession;
import net.haitham.otloblidal.entity.annotation.City;
import net.haitham.otloblidal.repo.aointerface.CountryRepoInterface;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

/**
 *
 * @author HaithamGamal
 */
@Repository
public class CountryRepo extends AbstractEntityRepo<Country> implements CountryRepoInterface {

    public CountryRepo() {
        super(Country.class);
        this.setDefaultOrderNameAr("nameAr");
    }

    @Override
    public List<Country> findListHQL() {

        String hqlQuery = "FROM Country";
        Query countryQuery = getCurrentSession().createQuery(hqlQuery);
        List<Country> countryResultList = countryQuery.getResultList();
        return countryResultList;

    }

    @Override
    public List<Country> findLikeByHQL(String keyword) {

        String hqlQuery = "FROM Country "
                + "WHERE nameAr LIKE :n OR nameEn LIKE :n"
                + " ORDER BY nameAr";
        Query countryQuery = getCurrentSession().createQuery(hqlQuery);
        String pattern = "%" + keyword + "%";
        countryQuery.setParameter("n", pattern);
        List<Country> countryResultList = countryQuery.getResultList();
        return countryResultList;

    }

    @Override
    public Country addHQL(Country country) {

        String hqlQuery = "INSERT INTO COUNTRY(nameAr,nameEn) :na, :ne";
        Query insertQuery = getCurrentSession().createQuery(hqlQuery);
        insertQuery.setParameter("na", country.getNameAr());
        insertQuery.setParameter("ne", country.getNameEn());
        int affectedRows = insertQuery.executeUpdate();
        System.out.println("AFFECTED ROWS :" + affectedRows);
        return country;
    }

    @Override
    public Country updateHQL(Country country) {

        getCurrentSession().merge(country);
        return country;
    }
}
