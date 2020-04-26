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
import net.haitham.otloblidal.entity.annotation.Branch;
import net.haitham.otloblidal.entity.annotation.Country;
import net.haitham.otloblidal.entity.annotation.DeliveryArea;
import net.haitham.otloblidal.repo.aointerface.DeliveryAreaRepoInterface;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HaithamGamal
 */
@Repository
public class DeliveryAreaRepo extends AbstractEntityRepo<DeliveryArea> implements DeliveryAreaRepoInterface {

    public DeliveryAreaRepo() {
        super(DeliveryArea.class);
    }
     


    
    

}
