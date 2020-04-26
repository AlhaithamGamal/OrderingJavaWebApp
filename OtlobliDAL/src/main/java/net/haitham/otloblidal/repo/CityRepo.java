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
import static net.haitham.otloblidal.HibernateUtilMe.getCurrentSession;
import net.haitham.otloblidal.entity.annotation.Area;
import net.haitham.otloblidal.entity.annotation.City;
import net.haitham.otloblidal.entity.annotation.Country;
import net.haitham.otloblidal.repo.aointerface.CityRepoInterface;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HaithamGamal
 */
@Repository
public class CityRepo extends AbstractEntityRepo<City> implements CityRepoInterface {

    public CityRepo() {
        super(City.class);
        this.setDefaultOrderNameAr("nameAr");
    }
    
//    public CityRepo() {
//        
//        this.setDefaultOrderNameAr("nameEn");
//    }
//    
 
    
 
    

    public List<Object[]> group() {
        
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Object[]> cityCriteriaQuery = builder.createQuery(Object[].class);
        
        Root<City> root = cityCriteriaQuery.from(City.class);
        
        cityCriteriaQuery.multiselect(root.get("nameEn"), root.get("country"));
        cityCriteriaQuery.orderBy(builder.asc(root.get("nameAr")));
        cityCriteriaQuery.groupBy(root.get("country"));
        
        Query<Object[]> queryCity = getCurrentSession().createQuery(cityCriteriaQuery);
        List<Object[]> cityList = queryCity.getResultList();
        return cityList;
        
    }
    
  
}
