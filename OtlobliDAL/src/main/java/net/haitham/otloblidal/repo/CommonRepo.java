/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblidal.repo;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import static net.haitham.otloblidal.HibernateUtilMe.getCurrentSession;
import net.haitham.otloblidal.entity.annotation.City;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */

public interface CommonRepo<E> {

    public E add(E entity);

    public E update(E entity);

    public void delete(Integer id);

    public E findById(Integer id);

    public List<E> findList();
}
