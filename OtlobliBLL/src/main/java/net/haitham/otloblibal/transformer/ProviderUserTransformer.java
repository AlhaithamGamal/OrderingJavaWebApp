/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import Exceptions.InvalidPasswordException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.DeliveryAreaBean;
import net.haitham.otlobli.common.bean.ProviderUserBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
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
public class ProviderUserTransformer implements OtlobliConstant, CommonTransformer<ProviderUser, ProviderUserBean> {

    @Autowired
    BranchTransformer branchTransformer;
    @Autowired
    ProviderTransformer providerTransformer;

    public BranchTransformer getBranchTransformer() {
        return branchTransformer;
    }

    public void setBranchTransformer(BranchTransformer branchTransformer) {
        this.branchTransformer = branchTransformer;
    }

    public ProviderTransformer getProviderTransformer() {
        return providerTransformer;
    }

    public void setProviderTransformer(ProviderTransformer providerTransformer) {
        this.providerTransformer = providerTransformer;
    }

    @Override
    public ProviderUser fromBeanToEntity(ProviderUserBean providerUserBean) {
        ProviderUser providerUserEntity = new ProviderUser();

        //        System.out.println("After Transforming \nCOUNTRY NAME AR"+countryBean.getNameAr()+"Country name en"+"ID"+countryBean.getId());
        if (validateBean(providerUserBean)) {

            providerUserEntity.setId(providerUserBean.getId());
            providerUserEntity.setUsername(providerUserBean.getUsername());
            providerUserEntity.setEmail(providerUserBean.getEmail());
            providerUserEntity.setPassword(providerUserBean.getPassword());
            providerUserEntity.setEncrypted_password(providerUserBean.getEncrypted_password());
            providerUserEntity.setActive(providerUserBean.getActive());
            //in case branch is null
            BranchBean branchCheck = providerUserBean.getBranch();
            if (branchCheck != null) {
                Branch branchEntity = branchTransformer.fromBeanToEntity(providerUserBean.getBranch());
                providerUserEntity.setBranch(branchEntity);
            }
            Provider providerEntity = providerTransformer.fromBeanToEntity(providerUserBean.getProvider());
            providerUserEntity.setProvider(providerEntity);

            return providerUserEntity;
        } else {

            return null;
        }
    }

    @Override
    public ProviderUserBean fromEntityToBean(ProviderUser providerUser, String lang) {
        ProviderUserBean providerUserBean = new ProviderUserBean();
        providerUserBean.setUsername(providerUser.getUsername());
        providerUserBean.setEmail(providerUser.getEmail());
        providerUserBean.setPassword(providerUser.getPassword());
        providerUserBean.setId(providerUser.getId());
        providerUserBean.setActive(providerUser.getActive());
        providerUserBean.setEncrypted_password(providerUser.getEncrypted_password());

        return providerUserBean;
    }

    public boolean validateBean(ProviderUserBean providerUserBean) {
        if (providerUserBean.getPassword().length() < 4 || providerUserBean.getPassword().length() > 10) {
            return false;
        }
        return true;

    }

}
