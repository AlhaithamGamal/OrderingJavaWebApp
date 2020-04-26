/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import java.util.ArrayList;
import net.haitham.otlobli.common.bean.ConsumerAddressBean;
import net.haitham.otlobli.common.bean.ConsumerBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import net.haitham.otloblidal.entity.annotation.Consumer;
import net.haitham.otloblidal.entity.annotation.ConsumerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public class ConsumerTransformer implements OtlobliConstant, CommonTransformer<Consumer, ConsumerBean> {
@Autowired
ConsumerAddressTransformer consumerAddressTransformer;

    public ConsumerAddressTransformer getConsumerAddressTransformer() {
        return consumerAddressTransformer;
    }

    public void setConsumerAddressTransformer(ConsumerAddressTransformer consumerAddressTransformer) {
        this.consumerAddressTransformer = consumerAddressTransformer;
    }
    @Override
    public Consumer fromBeanToEntity(ConsumerBean consumerBean) {
        Consumer consumer = new Consumer();
        String imagePath = "";
        try {
            imagePath = consumerBean.getImagePath();
        } catch (Exception e) {
            System.out.println("EXCEPTION IS :" + e);

        }
        System.out.println("EN IS LOGOOOOOOOOOOO" + imagePath);

        consumer.setId(consumerBean.getId());
        consumer.setFullname(consumerBean.getFullname());
        consumer.setEmail(consumerBean.getEmail());
        consumer.setPassword(consumerBean.getPhone());
        consumer.setGender(consumerBean.getGender());
        consumer.setPhone(consumerBean.getPhone());
        consumer.setEncrypted_password(consumerBean.getEncrypted_password());

        if (imagePath != null) {

            consumer.setImagePath(consumerBean.getImagePath());
        }
        return consumer;
    }

    @Override
    public ConsumerBean fromEntityToBean(Consumer consumerEntity, String lang) {
        ConsumerBean consumerBean = new ConsumerBean();
        consumerBean.setId(consumerEntity.getId());
        consumerBean.setFullname(consumerEntity.getFullname());
        consumerBean.setEmail(consumerEntity.getEmail());
        consumerBean.setPassword(consumerEntity.getPhone());
        consumerBean.setGender(consumerEntity.getGender());
        consumerBean.setPhone(consumerEntity.getPhone());
        consumerBean.setEncrypted_password(consumerEntity.getEncrypted_password());

        consumerBean.setImagePath(consumerEntity.getImagePath());
        return consumerBean;
    }
        public ConsumerBean fromEntityToBeanWithConsumerAddress(Consumer consumerEntity, String lang) {

       ConsumerBean consumerBean = fromEntityToBean(consumerEntity, lang);
        consumerBean.setConsumerAddressBeanList(new ArrayList<ConsumerAddressBean>());
        //Edit-4
        for (ConsumerAddress consumerAddressEntity : consumerEntity.getConsumerAddresses()) {
            ConsumerAddressBean consumerAddressBean =  consumerAddressTransformer.fromEntityToBean(consumerAddressEntity, lang);
            consumerBean.getConsumerAddressBeanList().add(consumerAddressBean);

        }
        return consumerBean;
    }

}
