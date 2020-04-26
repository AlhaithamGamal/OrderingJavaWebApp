/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import java.util.ArrayList;
import net.haitham.otlobli.common.bean.AreaBean;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.DeliveryAreaBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otlobli.common.bean.ProviderUserBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import static net.haitham.otlobli.common.constant.OtlobliConstant.LANG_AR;
import net.haitham.otloblidal.entity.annotation.Area;
import net.haitham.otloblidal.entity.annotation.Branch;
import net.haitham.otloblidal.entity.annotation.DeliveryArea;
import net.haitham.otloblidal.entity.annotation.Provider;
import net.haitham.otloblidal.entity.annotation.ProviderUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public class BranchTransformer implements OtlobliConstant, CommonTransformer<Branch, BranchBean> {
    @Autowired
    ProviderTransformer providerTransformer;
    @Autowired
    ProviderUserTransformer providerUserTransformer;
    @Autowired
    DeliveryAreaTransformer deliveryAreaTransformer;
      @Autowired
    AreaTransformer areaTranformer;

    public ProviderTransformer getProviderTransformer() {
        return providerTransformer;
    }

    public void setProviderTransformer(ProviderTransformer providerTransformer) {
        this.providerTransformer = providerTransformer;
    }

    public ProviderUserTransformer getProviderUserTransformer() {
        return providerUserTransformer;
    }

    public void setProviderUserTransformer(ProviderUserTransformer providerUserTransformer) {
        this.providerUserTransformer = providerUserTransformer;
    }

    public DeliveryAreaTransformer getDeliveryAreaTransformer() {
        return deliveryAreaTransformer;
    }

    public void setDeliveryAreaTransformer(DeliveryAreaTransformer deliveryAreaTransformer) {
        this.deliveryAreaTransformer = deliveryAreaTransformer;
    }

    public AreaTransformer getAreaTranformer() {
        return areaTranformer;
    }

    public void setAreaTranformer(AreaTransformer areaTranformer) {
        this.areaTranformer = areaTranformer;
    }
  
    

    @Override
    public Branch fromBeanToEntity(BranchBean branchBean) {
        Branch branch = new Branch();

        branch.setNameAr(branchBean.getNameAr());
        branch.setNameEn(branchBean.getNameEn());
        branch.setId(branchBean.getId());
        branch.setPhone(branchBean.getPhone());
        branch.setOpenAt(branchBean.getOpenAt());
        branch.setCloseAt(branchBean.getCloseAt());
        branch.setLat(branchBean.getLat());
        branch.setLng(branchBean.getLng());
        Provider providerEntity = providerTransformer.fromBeanToEntity(branchBean.getProviderBean());
        branch.setProvider(providerEntity);
        Area areaEntity = areaTranformer.fromBeanToEntity(branchBean.getAreaBean());
        branch.setArea(areaEntity);

        return branch;
    }

    @Override
    public BranchBean fromEntityToBean(Branch branchEntity, String lang) {

        BranchBean branchBean = new BranchBean();
        branchBean.setNameAr(branchEntity.getNameAr());
        branchBean.setNameEn(branchEntity.getNameEn());
        branchBean.setPhone(branchEntity.getPhone());
        branchBean.setOpenAt(branchEntity.getOpenAt());
        branchBean.setCloseAt(branchEntity.getCloseAt());
        branchBean.setId(branchEntity.getId());
        branchBean.setLat(branchEntity.getLat());
        branchBean.setLng(branchEntity.getLng());

        if (LANG_AR.equals(lang)) {

            branchBean.setName(branchEntity.getNameAr());
        } else {

            branchBean.setName(branchEntity.getNameEn());
        }
        return branchBean;
    }

    public BranchBean fromEntityToBeanWithDeliveryAreasAndProviderUsers(Branch branchEntity, String lang) {
        //bean take every thing from the entity including list
        BranchBean branchBean = fromEntityToBean(branchEntity, lang);
        branchBean.setDeliveryAreaList(new ArrayList<DeliveryAreaBean>());
        for (DeliveryArea deliveryAreaEntity : branchEntity.getDeliveryAreas()) {
            DeliveryAreaBean deliveryAreaBean = deliveryAreaTransformer.fromEntityToBean(deliveryAreaEntity, lang);
            branchBean.getDeliveryAreaList().add(deliveryAreaBean);

        }
        branchBean.setProviderUserList(new ArrayList<ProviderUserBean>());
        for (ProviderUser providerUserEntity : branchEntity.getProviderUsers()) {
            ProviderUserBean providerUserBean = providerUserTransformer.fromEntityToBean(providerUserEntity, lang);
            branchBean.getProviderUserList().add(providerUserBean);

        }
        return branchBean;

    }

 

}
