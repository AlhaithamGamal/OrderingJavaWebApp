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
import net.haitham.otloblidal.entity.annotation.OrderItem;
import net.haitham.otloblidal.repo.aointerface.OrderItemRepoInterface;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HaithamGamal
 */
@Repository
public class OrderItemRepo extends AbstractEntityRepo<OrderItem> implements OrderItemRepoInterface{

    public OrderItemRepo() {
        super(OrderItem.class);
    }

    
}
