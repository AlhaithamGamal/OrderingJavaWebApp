/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import java.util.ArrayList;
import net.haitham.otlobli.common.bean.CityBean;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import net.haitham.otloblidal.entity.annotation.City;
import net.haitham.otloblidal.entity.annotation.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public class CountryTransformer implements OtlobliConstant, CommonTransformer<Country, CountryBean> {
    @Autowired
    CityTransformer cityTransformer ;
    
   

    public CityTransformer getCityTransformer() {
        return cityTransformer;
    }

    public void setCityTransformer(CityTransformer cityTransformer) {
        this.cityTransformer = cityTransformer;


    }

    @Override
    public CountryBean fromEntityToBean(Country countryEntity, String lang) {
CountryBean countryBean=new CountryBean();
 
        countryBean.setNameAr(countryEntity.getNameAr());
        countryBean.setNameEn(countryEntity.getNameEn());
        countryBean.setId(countryEntity.getId());
        if (LANG_AR.equals(lang)) {

            countryBean.setName(countryEntity.getNameAr());
        } else {

            countryBean.setName(countryEntity.getNameEn());
        }
        return countryBean;
    }

    public CountryBean fromEntityToBeanWithCities(Country countryEntity, String lang) {
        //bean take every thing from the entity including list
        //problem adding last element in list and repeating it because country bean was global and common reference so 
        //by making countrybean local in every method not common reference
        //exception because we should use object of dependency 
        CountryBean countryBean = fromEntityToBean(countryEntity, lang);
        countryBean.setCityList(new ArrayList<CityBean>());
        //Edit-3 
        for (City cityEntity : countryEntity.getCities()) {
            CityBean cityBean = cityTransformer.fromEntityToBeanWithAreas(cityEntity, lang);
            countryBean.getCityList().add(cityBean);
        
        }
        return countryBean;

    }

    @Override
    public Country fromBeanToEntity(CountryBean countryBean) {

        Country country = new Country();
        System.out.println("After Transforming \nCOUNTRY NAME AR"+countryBean.getNameAr()+"Country name en"+"ID"+countryBean.getId());
        country.setNameAr(countryBean.getNameAr());
        country.setNameEn(countryBean.getNameEn());
        country.setId(countryBean.getId());
        return country;
    }

}
