/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblidal;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import net.haitham.otloblidal.repo.CountryRepo;
import net.haitham.otloblidal.entity.annotation.Area;
import net.haitham.otloblidal.entity.annotation.City;
import net.haitham.otloblidal.entity.annotation.Country;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import net.haitham.otloblidal.repo.CityRepo;
import static net.haitham.otloblidal.HibernateUtilMe.*;
import org.hibernate.query.Query;
import sun.awt.image.ImageWatched.Link;

/**
 *
 * @author HaithamGamal
 */
public class OtlobliDalMain {

    /**
     * @param args the command line arguments
     */
    private static int getFirstResultRelativeToPageNumber(int pageNumber, int pageSize) {
        return (pageNumber - 1) * pageSize;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        // TODO code application logic here
        // TODO code application logic here
        // 1. Open Connection

        try {
//            Session session = openSession();
//            beginTransaction();
//            System.out.println("START");
//            CountryRepo countryDao = new CountryRepo();
//            CityRepo cityDao = new CityRepo();
//            countryDao.add(new Country("Test City 01","Test City A"));
//             countryDao.add(new Country("Test City B","Test City 02"));
//             countryDao.add(new Country("Test City 03","Test City 03"));
//            List<Country> countryList = countryDao.findLike("0");
//                 Long countryListCount = countryDao.count();
//                 System.out.println("COUNT OF ARABIC NAMES IS :"+countryListCount);
//            
//            for (Country country : countryList) {
//
//                System.out.println("COUNTRY IS:" + country.getNameAr());
//
//            }

//            List<Object[]> cityList = cityDao.group();
//            for (Object[] objects : cityList) {
//                Country country = (Country) objects[1];
//                System.out.println("ID:"+country.getId() +"NAME:"+ country.getNameAr());
////                Long count = (Long) objects[0];
////                System.out.println("COUNT"+count);
//               
//               Set<City> cit = country.getCities();
//                for(City city : cit){
//                
//                System.out.println("CITY IS :"+city.getNameAr());
//                }
//                
//               
//
//            }
//           Country country2 = new Country("Haitham AR","Haitham EN");
//           country2.setId(2);
//           new CountryRepo().updateHQL(country2);
//            
//            List<Country> countryResultList = countryDao.findListHQL();
//              commitTransaction();
//            for(Country country : countryResultList){
//            
//            System.out.println("COUNTRY NAME AR"+country.getNameAr()+" | "+"COUNTRY NAME EN :"+country.getNameEn());
//                for(City city:country.getCities()){
//                System.out.println("CITY|"+city.getNameAr());
//                
//                }
//                    
//            
//            }
//            CountryRepo countryRepoTry = new CountryRepo();
//            countryRepoTry.delete(3);
//            System.out.println("END");
//
//           
//            cloaseSession();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
//            if (isActiveTransaction()) {
//                rollBackTransaction();
//
//            }

        }

    }
}
