/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.manager;

import static java.awt.PageAttributes.MediaType.C;
import java.util.List;
import net.haitham.otlobli.common.bean.CategoryBean;
import net.haitham.otlobli.common.bean.OrderBean;
import net.haitham.otlobli.common.bean.OrderItemBean;
import net.haitham.otlobli.common.bean.ProductBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author HaithamGamal
 */

public interface OrderingManager {

    public OrderBean createOrder(OrderBean order);

    public OrderBean updateOrder(OrderBean order);

    public OrderBean findOrderById(Integer id);

    public List<OrderBean> findOrders();

    public void deleteOrder(Integer id);
     public OrderItemBean createOrderItem(OrderItemBean orderItem);

    public OrderItemBean updateOrderItem(OrderItemBean orderItem);

    public OrderItemBean findOrderItemById(Integer id);

    public List<OrderItemBean> findOrdersItems();

    public void deleteOrderItem(Integer id);
}
