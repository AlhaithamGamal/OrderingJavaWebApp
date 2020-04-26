/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import java.util.ArrayList;
import net.haitham.otlobli.common.bean.AreaBean;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.CityBean;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otlobli.common.bean.DeliveryAreaBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import static net.haitham.otlobli.common.constant.OtlobliConstant.LANG_AR;
import net.haitham.otloblidal.entity.annotation.Area;
import net.haitham.otloblidal.entity.annotation.Branch;
import net.haitham.otloblidal.entity.annotation.Country;
import net.haitham.otloblidal.entity.annotation.DeliveryArea;
import net.haitham.otloblidal.entity.annotation.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public class DeliveryAreaTransformer implements OtlobliConstant, CommonTransformer<DeliveryArea, DeliveryAreaBean> {
@Autowired
AreaTransformer areaTransformer;
@Autowired
BranchTransformer branchTransformer;

    public AreaTransformer getAreaTransformer() {
        return areaTransformer;
    }

    public void setAreaTransformer(AreaTransformer areaTransformer) {
        this.areaTransformer = areaTransformer;
    }

    public BranchTransformer getBranchTransformer() {
        return branchTransformer;
    }

    public void setBranchTransformer(BranchTransformer branchTransformer) {
        this.branchTransformer = branchTransformer;
    }
    @Override
    public DeliveryArea fromBeanToEntity(DeliveryAreaBean deliveryAreaBean) {
        DeliveryArea deliveryAreaEntity = new DeliveryArea();
//        System.out.println("After Transforming \nCOUNTRY NAME AR"+countryBean.getNameAr()+"Country name en"+"ID"+countryBean.getId());
        deliveryAreaEntity.setDeliverInMinutes(deliveryAreaBean.getDeliverInMinutes());
        deliveryAreaEntity.setDeliveryFees(deliveryAreaBean.getDeliveryFees());
        Branch branchEntity = branchTransformer.fromBeanToEntity(deliveryAreaBean.getBranchBean());
        Area areaEntity =  areaTransformer.fromBeanToEntity(deliveryAreaBean.getAreaBean());
        deliveryAreaEntity.setArea(areaEntity);
        deliveryAreaEntity.setBranch(branchEntity);
        deliveryAreaEntity.setId(deliveryAreaBean.getId());

        return deliveryAreaEntity;

    }

    @Override
    public DeliveryAreaBean fromEntityToBean(DeliveryArea deliveryAreaentity, String lang) {
        DeliveryAreaBean deliveryAreaBean = new DeliveryAreaBean();
        deliveryAreaBean.setDeliverInMinutes(deliveryAreaentity.getDeliverInMinutes());
        deliveryAreaBean.setDeliveryFees(deliveryAreaentity.getDeliveryFees());
        deliveryAreaBean.setId(deliveryAreaentity.getId());

        return deliveryAreaBean;
    }
    
    
    


}
