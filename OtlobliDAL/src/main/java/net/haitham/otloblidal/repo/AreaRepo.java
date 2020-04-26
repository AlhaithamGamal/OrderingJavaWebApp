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

import net.haitham.otloblidal.entity.annotation.Area;
import net.haitham.otloblidal.entity.annotation.City;
import net.haitham.otloblidal.entity.annotation.Country;
import net.haitham.otloblidal.repo.aointerface.AreaRepoInterface;
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
public class AreaRepo extends AbstractEntityRepo<Area> implements AreaRepoInterface {

    public AreaRepo() {
        super(Area.class);
    }



    
}
