/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.manager;

import java.util.List;
import net.haitham.otlobli.common.bean.AreaBean;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.CategoryBean;
import net.haitham.otlobli.common.bean.ConsumerAddressBean;
import net.haitham.otlobli.common.bean.ConsumerBean;
import net.haitham.otlobli.common.bean.DeliveryAreaBean;
import net.haitham.otlobli.common.bean.OrderBean;
import net.haitham.otlobli.common.bean.ProductBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otloblidal.entity.annotation.Area;
import net.haitham.otloblidal.entity.annotation.Provider;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public interface ConsumerManager {

    public ConsumerBean addAccountConsumer(ConsumerBean consumer);

    public ConsumerBean updateAccountConsumer(ConsumerBean consumer);

    public void deleteAccountConsumer(Integer id);

    public ConsumerBean findAccountConsumer(Integer id);

    public ConsumerAddressBean addConsumerAddress(ConsumerAddressBean consumerAddress);

    public ConsumerAddressBean updateConsumerAddress(ConsumerAddressBean consumerAddressBean);

    public ConsumerAddressBean findConsumerAddress(Integer id);
        public List<ConsumerAddressBean> findConsumersAddress();
          public List<ConsumerBean> findConsumersAccounts();


    public void deleteConsumerAddress(Integer id);

  

}
