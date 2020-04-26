/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import net.haitham.otlobli.common.bean.AreaBean;
import net.haitham.otlobli.common.bean.ConsumerAddressBean;
import net.haitham.otlobli.common.bean.ConsumerBean;
import net.haitham.otlobli.common.bean.ProductBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import net.haitham.otloblidal.entity.annotation.Area;
import net.haitham.otloblidal.entity.annotation.Consumer;
import net.haitham.otloblidal.entity.annotation.ConsumerAddress;
import net.haitham.otloblidal.entity.annotation.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public class ConsumerAddressTransformer implements OtlobliConstant, CommonTransformer<ConsumerAddress, ConsumerAddressBean> {
@Autowired
AreaTransformer areaTransformer;
@Autowired
ConsumerTransformer consumerTransformer;

    public ConsumerTransformer getConsumerTransformer() {
        return consumerTransformer;
    }

    public void setConsumerTransformer(ConsumerTransformer consumerTransformer) {
        this.consumerTransformer = consumerTransformer;
    }

    public AreaTransformer getAreaTransformer() {
        return areaTransformer;
    }

    public void setAreaTransformer(AreaTransformer areaTransformer) {
        this.areaTransformer = areaTransformer;
    }

 
    @Override
    public ConsumerAddress fromBeanToEntity(ConsumerAddressBean consumerAddressBean) {
        ConsumerAddress consumerAddress = new ConsumerAddress();

        consumerAddress.setId(consumerAddressBean.getId());
        consumerAddress.setStreet(consumerAddressBean.getStreet());
        consumerAddress.setBuilding(consumerAddressBean.getBuilding());
        consumerAddress.setFlat(consumerAddressBean.getFlat());
        consumerAddress.setActive(consumerAddressBean.getActive());
        consumerAddress.setFloor(consumerAddressBean.getFloor());
        consumerAddress.setLabel(consumerAddressBean.getLabel());
        consumerAddress.setLandmark(consumerAddressBean.getLandmark());
        consumerAddress.setLat(consumerAddressBean.getLat());
        consumerAddress.setLng(consumerAddressBean.getLng());
        Area area = areaTransformer.fromBeanToEntity(consumerAddressBean.getArea());
        Consumer consumer = consumerTransformer.fromBeanToEntity(consumerAddressBean.getConsumer());
        consumerAddress.setArea(area);
        consumerAddress.setConsumer(consumer);

        return consumerAddress;
    }

    @Override
    public ConsumerAddressBean fromEntityToBean(ConsumerAddress consumerAddressEntity, String lang) {
        ConsumerAddressBean consumerAddressBean = new ConsumerAddressBean();
        consumerAddressBean.setId(consumerAddressEntity.getId());
        consumerAddressBean.setStreet(consumerAddressEntity.getStreet());
        consumerAddressBean.setBuilding(consumerAddressEntity.getBuilding());
        consumerAddressBean.setFlat(consumerAddressEntity.getFlat());
        consumerAddressBean.setActive(consumerAddressEntity.getActive());
        consumerAddressBean.setFloor(consumerAddressEntity.getFloor());
        consumerAddressBean.setLabel(consumerAddressEntity.getLabel());
        consumerAddressBean.setLandmark(consumerAddressEntity.getLandmark());
        consumerAddressBean.setLat(consumerAddressEntity.getLat());
        consumerAddressBean.setLng(consumerAddressEntity.getLng());

        return consumerAddressBean;

    }

}
