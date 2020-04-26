/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import net.haitham.otlobli.common.bean.CategoryBean;
import net.haitham.otlobli.common.bean.OrderItemBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import net.haitham.otloblidal.entity.annotation.Category;
import net.haitham.otloblidal.entity.annotation.Order;
import net.haitham.otloblidal.entity.annotation.OrderItem;
import net.haitham.otloblidal.entity.annotation.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public class OrderItemTransformer implements OtlobliConstant, CommonTransformer<OrderItem, OrderItemBean> {
    @Autowired
    OrderTransformer orderTransformer;
    @Autowired
    ProductTransformer productTransformer;

    public OrderTransformer getOrderTransformer() {
        return orderTransformer;
    }

    public void setOrderTransformer(OrderTransformer orderTransformer) {
        this.orderTransformer = orderTransformer;
    }

    public ProductTransformer getProductTransformer() {
        return productTransformer;
    }

    public void setProductTransformer(ProductTransformer productTransformer) {
        this.productTransformer = productTransformer;
    }
    

    @Override
    public OrderItem fromBeanToEntity(OrderItemBean orderItemBean) {
        OrderItem orderItem = new OrderItem();

        orderItem.setId(orderItemBean.getId());
        orderItem.setPrice(orderItemBean.getPrice());
        orderItem.setQuantity(orderItemBean.getQuantity());
        Order orderEntity = orderTransformer.fromBeanToEntity(orderItemBean.getOrderBean());
        Product productEntity = productTransformer.fromBeanToEntity(orderItemBean.getProductBean());
        orderItem.setProduct(productEntity);
        orderItem.setOrder(orderEntity);

        return orderItem;
    }

    @Override
    public OrderItemBean fromEntityToBean(OrderItem orderItemEntity, String lang) {
        OrderItemBean orderItemBean = new OrderItemBean();
       orderItemBean.setId(orderItemEntity.getId());
       orderItemBean.setPrice(orderItemEntity.getPrice());
       orderItemBean.setQuantity(orderItemEntity.getQuantity());
    
        return orderItemBean;
    }

}
