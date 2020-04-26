/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import java.util.ArrayList;
import java.util.List;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.CategoryBean;
import net.haitham.otlobli.common.bean.CityBean;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otlobli.common.bean.ProviderUserBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import net.haitham.otloblidal.entity.annotation.Branch;
import net.haitham.otloblidal.entity.annotation.Category;
import net.haitham.otloblidal.entity.annotation.City;
import net.haitham.otloblidal.entity.annotation.Country;
import net.haitham.otloblidal.entity.annotation.Provider;
import net.haitham.otloblidal.entity.annotation.ProviderUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public class ProviderTransformer implements OtlobliConstant, CommonTransformer<Provider, ProviderBean> {

    String logoEn;
    @Autowired
    BranchTransformer branchTransformer;
    @Autowired
    ProviderUserTransformer providerUserTransformer;
    @Autowired
    CategoryTransformer categoryTransformer;

    public String getLogoEn() {
        return logoEn;
    }

    public void setLogoEn(String logoEn) {
        this.logoEn = logoEn;
    }

    public BranchTransformer getBranchTransformer() {
        return branchTransformer;
    }

    public void setBranchTransformer(BranchTransformer branchTransformer) {
        this.branchTransformer = branchTransformer;
    }

    public ProviderUserTransformer getProviderUserTransformer() {
        return providerUserTransformer;
    }

    public void setProviderUserTransformer(ProviderUserTransformer providerUserTransformer) {
        this.providerUserTransformer = providerUserTransformer;
    }

    public CategoryTransformer getCategoryTransformer() {
        return categoryTransformer;
    }

    public void setCategoryTransformer(CategoryTransformer categoryTransformer) {
        this.categoryTransformer = categoryTransformer;
    }

    @Override
    public ProviderBean fromEntityToBean(Provider providerEntity, String lang) {

        ProviderBean providerBean = new ProviderBean();
        providerBean.setNameAr(providerEntity.getNameAr());
        providerBean.setNameEn(providerEntity.getNameEn());
        providerBean.setHotline(providerEntity.getHotline());
        providerBean.setLogoPathAr(providerEntity.getLogoPathAr());
        providerBean.setLogoPathEn(providerEntity.getLogoPathEn());
        providerBean.setId(providerEntity.getId());
        if (LANG_AR.equals(lang)) {

            providerBean.setName(providerEntity.getNameAr());
        } else {

            providerBean.setName(providerEntity.getNameEn());
        }
        return providerBean;
    }

    public ProviderBean fromEntityToBeanWithBranchesAndProviderUsers(Provider providerEntity, String lang) {
        //bean take every thing from the entity including list
        ProviderBean providerBean = fromEntityToBean(providerEntity, lang);
        providerBean.setBranchList(new ArrayList<BranchBean>());
        //Edit2
        if (providerEntity.getBranches() != null) {
            for (Branch branchEntity : providerEntity.getBranches()) {
                BranchBean branchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(branchEntity, lang);
                providerBean.getBranchList().add(branchBean);

            }
        }
        //to add provider users and branches at same time
        //adding branches saved in provider user entity inside a list
        providerBean.setProviderUserList(new ArrayList<ProviderUserBean>());
        for (ProviderUser providerUserEntity : providerEntity.getProviderUsers()) {
            ProviderUserBean providerUserBean = providerUserTransformer.fromEntityToBean(providerUserEntity, lang);
            providerBean.getProviderUserList().add(providerUserBean);

        }
        providerBean.setCategoryBeanList(new ArrayList<CategoryBean>());
        for (Category categoryEntity : providerEntity.getCategories()) {
            CategoryBean cateogryBean = categoryTransformer.fromEntityToBean(categoryEntity, lang);
            providerBean.getCategoryBeanList().add(cateogryBean);

        }
        return providerBean;

    }

    @Override
    public Provider fromBeanToEntity(ProviderBean providerBean) {
        Provider provider = new Provider();
        try {
            logoEn = providerBean.getLogoPathEn();
        } catch (Exception e) {
            System.out.println("EXCEPTION IS :" + e);

        }
        System.out.println("EN IS LOGOOOOOOOOOOO" + logoEn);

        provider.setNameAr(providerBean.getNameAr());
        provider.setNameEn(providerBean.getNameEn());
        provider.setId(providerBean.getId());
        provider.setHotline(providerBean.getHotline());

        if (logoEn != null) {

            provider.setLogoPathEn(providerBean.getLogoPathEn());
        }
        return provider;
    }

}
