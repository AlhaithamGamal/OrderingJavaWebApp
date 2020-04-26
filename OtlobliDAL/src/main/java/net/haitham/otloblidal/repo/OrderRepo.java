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
import net.haitham.otloblidal.entity.annotation.Country;
import net.haitham.otloblidal.entity.annotation.Order;
import net.haitham.otloblidal.entity.annotation.ProviderUser;
import net.haitham.otloblidal.repo.aointerface.OrderRepoInterface;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HaithamGamal
 */
// @Scope("prototype")
@Repository
public class OrderRepo extends AbstractEntityRepo<Order> implements OrderRepoInterface {

    public OrderRepo() {
        super(Order.class);
    }
    
  
}
