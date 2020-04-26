/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import java.util.ArrayList;
import net.haitham.otlobli.common.bean.CategoryBean;
import net.haitham.otlobli.common.bean.OrderBean;
import net.haitham.otlobli.common.bean.OrderItemBean;
import net.haitham.otlobli.common.bean.ProductBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import net.haitham.otloblidal.entity.annotation.Area;
import net.haitham.otloblidal.entity.annotation.Branch;
import net.haitham.otloblidal.entity.annotation.Category;
import net.haitham.otloblidal.entity.annotation.Consumer;
import net.haitham.otloblidal.entity.annotation.ConsumerAddress;
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
public class OrderTransformer implements OtlobliConstant, CommonTransformer<Order, OrderBean> {

    @Autowired
    BranchTransformer branchTransformer;
    @Autowired
    AreaTransformer areaTransformer;
    @Autowired
    ConsumerTransformer consumerTransformer;
    @Autowired
    ConsumerAddressTransformer consumerAddressTransformer;
    @Autowired
    OrderItemTransformer orderItemTransformer;

    public BranchTransformer getBranchTransformer() {
        return branchTransformer;
    }

    public void setBranchTransformer(BranchTransformer branchTransformer) {
        this.branchTransformer = branchTransformer;
    }

    public AreaTransformer getAreaTransformer() {
        return areaTransformer;
    }

    public void setAreaTransformer(AreaTransformer areaTransformer) {
        this.areaTransformer = areaTransformer;
    }

    public ConsumerTransformer getConsumerTransformer() {
        return consumerTransformer;
    }

    public void setConsumerTransformer(ConsumerTransformer consumerTransformer) {
        this.consumerTransformer = consumerTransformer;
    }

    public ConsumerAddressTransformer getConsumerAddressTransformer() {
        return consumerAddressTransformer;
    }

    public void setConsumerAddressTransformer(ConsumerAddressTransformer consumerAddressTransformer) {
        this.consumerAddressTransformer = consumerAddressTransformer;
    }

    public OrderItemTransformer getOrderItemTransformer() {
        return orderItemTransformer;
    }

    public void setOrderItemTransformer(OrderItemTransformer orderItemTransformer) {
        this.orderItemTransformer = orderItemTransformer;
    }

    @Override
    public Order fromBeanToEntity(OrderBean orderBean) {
        Order order = new Order();

        order.setDeliveryFees(orderBean.getDeliveryFees());
        order.setOrderedAt(orderBean.getOrderedAt());
        order.setId(orderBean.getId());
        order.setStatus(orderBean.getStatus());
        Area area = areaTransformer.fromBeanToEntity(orderBean.getAreaBean());
        Branch branch = branchTransformer.fromBeanToEntity(orderBean.getBranchBean());
        Consumer consumer = consumerTransformer.fromBeanToEntity(orderBean.getConsumerBean());
        ConsumerAddress consumerAddress = consumerAddressTransformer.fromBeanToEntity(orderBean.getConsumerAddressBean());
        order.setArea(area);
        order.setBranch(branch);
        order.setConsumer(consumer);
        order.setConsumerAddress(consumerAddress);

        return order;
    }

    @Override
    public OrderBean fromEntityToBean(Order orderEntity, String lang) {
        OrderBean orderBean = new OrderBean();
        orderBean.setId(orderEntity.getId());
        orderBean.setDeliveryFees(orderEntity.getDeliveryFees());
        orderBean.setOrderedAt(orderEntity.getOrderedAt());
        orderBean.setStatus(orderEntity.getStatus());

        return orderBean;
    }

    public OrderBean fromEntityToBeanWithOrderItems(Order orderEntity, String lang) {

        OrderBean orderBean = fromEntityToBean(orderEntity, lang);
        orderBean.setOrderItemListBeans(new ArrayList<OrderItemBean>());
        //Edit-4
        for (OrderItem orderItemEntity : orderEntity.getOrderItems()) {
            OrderItemBean orderItemBean = orderItemTransformer.fromEntityToBean(orderItemEntity, lang);
            orderBean.getOrderItemListBeans().add(orderItemBean);

        }
        return orderBean;
    }

}
