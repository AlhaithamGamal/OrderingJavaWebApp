/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import java.util.ArrayList;
import net.haitham.otlobli.common.bean.AreaBean;
import net.haitham.otlobli.common.bean.CityBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import net.haitham.otloblidal.entity.annotation.Area;
import net.haitham.otloblidal.entity.annotation.City;
import net.haitham.otloblidal.entity.annotation.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public class CityTransformer implements OtlobliConstant, CommonTransformer<City, CityBean> {
  @Autowired
    AreaTransformer areaTransformer  ;
      @Autowired
    CountryTransformer countryTransformer;

    public CountryTransformer getCountryTransformer() {
        return countryTransformer;
    }

    public void setCountryTransformer(CountryTransformer countryTransformer) {
        this.countryTransformer = countryTransformer;
    }

   //old: CountryTransformer countryTransformer = new CountryTransformer();

    public AreaTransformer getAreaTransformer() {
        return areaTransformer;
    }

    public void setAreaTransformer(AreaTransformer areaTransformer) {
        this.areaTransformer = areaTransformer;
    }

    @Override
    public City fromBeanToEntity(CityBean cityBean) { //uses when adding or updating
        City city = new City();
        city.setNameAr(cityBean.getNameAr());
        city.setNameEn(cityBean.getNameEn());
        city.setId(cityBean.getId());
        Country countryEntity = countryTransformer.fromBeanToEntity(cityBean.getCountryBean());
        city.setCountry(countryEntity);
        
        return city;
    }

    @Override
    public CityBean fromEntityToBean(City cityEntity, String lang) {
//uses when retrieving
        CityBean cityBean = new CityBean();
        cityBean.setNameAr(cityEntity.getNameAr());
        cityBean.setNameEn(cityEntity.getNameEn());
        cityBean.setId(cityEntity.getId());
     //=>STACK OVERFLOW error recursive   cityBean.setCountryBean(new CountryTransformer().fromEntityToBeanWithCities(cityEntity.getCountry(), lang));
    
        if (LANG_AR.equals(lang)) {

            cityBean.setName(cityEntity.getNameAr());
        } else {

            cityBean.setName(cityEntity.getNameEn());
        }
        return cityBean;
    }
    public CityBean fromEntityToBeanWithAreas(City cityEntity, String lang) {
        CityBean cityBean = fromEntityToBean(cityEntity, lang);
        cityBean.setAreaList(new ArrayList<AreaBean>());
        //Edit-4
        for (Area areaEntity : cityEntity.getAreas()) {
            AreaBean areaBean = areaTransformer.fromEntityToBeanWithBranches(areaEntity, lang);
            cityBean.getAreaList().add(areaBean);
        
        }
        return cityBean;

    }

}
