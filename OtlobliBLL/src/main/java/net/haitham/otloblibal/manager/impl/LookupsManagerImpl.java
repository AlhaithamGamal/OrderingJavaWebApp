/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.manager.impl;

import java.util.ArrayList;
import java.util.List;

import net.haitham.otlobli.common.bean.AreaBean;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.CityBean;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import static net.haitham.otlobli.common.constant.OtlobliConstant.LANG_AR;
import net.haitham.otloblibal.manager.LookupsManager;
import net.haitham.otloblibal.transformer.AreaTransformer;
import net.haitham.otloblibal.transformer.BranchTransformer;
import net.haitham.otloblibal.transformer.CityTransformer;
import net.haitham.otloblibal.transformer.CountryTransformer;
import net.haitham.otloblibal.transformer.ProviderTransformer;
import static net.haitham.otloblidal.HibernateUtilMe.*;
import net.haitham.otloblidal.entity.annotation.Area;
import net.haitham.otloblidal.entity.annotation.Branch;
import net.haitham.otloblidal.entity.annotation.City;
import net.haitham.otloblidal.entity.annotation.Country;
import net.haitham.otloblidal.entity.annotation.ProviderUser;
import net.haitham.otloblidal.repo.AreaRepo;
import net.haitham.otloblidal.repo.CityRepo;
import net.haitham.otloblidal.repo.CountryRepo;
import net.haitham.otloblidal.repo.aointerface.AreaRepoInterface;
import net.haitham.otloblidal.repo.aointerface.CityRepoInterface;
import net.haitham.otloblidal.repo.aointerface.CountryRepoInterface;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HaithamGamal
 */
@Service
public class LookupsManagerImpl implements LookupsManager, OtlobliConstant {

    @Autowired
    private AreaTransformer areaTransformer;

    @Autowired
    private CountryRepoInterface countryRepoInterface;

    @Autowired
    private CityRepoInterface cityRepoInterface;
    @Autowired
    private AreaRepoInterface areaRepoInterface;
    @Autowired
    private CountryTransformer countryTransformer;
    @Autowired
    private CityTransformer cityTransformer;
    @Autowired
    private ProviderTransformer providerTransformer;

    public CityRepoInterface getCityRepoInterface() {
        return cityRepoInterface;
    }

    public void setCityRepoInterface(CityRepoInterface cityRepoInterface) {
        this.cityRepoInterface = cityRepoInterface;
    }

    public CountryRepoInterface getCountryRepoInterface() {
        return countryRepoInterface;
    }

    public void setCountryRepoInterface(CountryRepoInterface countryRepoInterface) {
        this.countryRepoInterface = countryRepoInterface;
    }

    public ProviderTransformer getProviderTransformer() {
        return providerTransformer;
    }

    public void setProviderTransformer(ProviderTransformer providerTransformer) {
        this.providerTransformer = providerTransformer;
    }

    public CountryTransformer getCountryTransformer() {
        return countryTransformer;
    }

    public void setCountryTransformer(CountryTransformer countryTransformer) {
        this.countryTransformer = countryTransformer;
    }

    public CityTransformer getCityTransformer() {
        return cityTransformer;
    }

    public void setCityTransformer(CityTransformer cityTransformer) {
        this.cityTransformer = cityTransformer;
    }

    public AreaTransformer getAreaTransformer() {
        return areaTransformer;
    }

    public void setAreaTransformer(AreaTransformer areaTransformer) {
        this.areaTransformer = areaTransformer;
    }

    @Override
    // @Transactional--- All of them not important only 
    /*  
     so any custom methods that you define yourself won't be managed by the EntityManager. 
     The fix for this is quite simple. Just mark the custom method with @Transactional
     and the EntityManager will recognize it as a transaction. ل
     */
    @Transactional
    public CountryBean addCountry(CountryBean countryBean) {
        //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        // CountryRepoInterface countryRepoInterface = countryRepo;//appCon.getBean("countryRepo", CountryRepoInterface.class);

        CountryBean resultCountryBean;
        Country countryEntity = countryTransformer.fromBeanToEntity(countryBean); //tranforming from bean to entity 

        countryEntity = countryRepoInterface.add(countryEntity); //repo that is dao add country to database

        resultCountryBean = countryTransformer.fromEntityToBeanWithCities(countryEntity, LANG_AR);

        return resultCountryBean;

    }

    @Override
    @Transactional
    public List<CountryBean> findCountries() {
        //    ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        // CountryRepoInterface countryRepoInterface = countryRepo;//appCon.getBean("countryRepo", CountryRepoInterface.class);

        List<Country> countryList = countryRepoInterface.findList(); //repo that is dao add country to database
        List<CountryBean> countryBeanList = new ArrayList<>();
        int i = 0;
        for (Country iCountry : countryList) {
            CountryBean resultCBean = new CountryBean();
            resultCBean = countryTransformer.fromEntityToBeanWithCities(iCountry, LANG_AR);
            countryBeanList.add(i, resultCBean);
            i++;

        }

        return countryBeanList;

    }

    @Override
    @Transactional
    public CountryBean findCountryById(Integer id) {
        //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //  CountryRepoInterface countryRepoInterface = countryRepo;//appCon.getBean("countryRepo", CountryRepoInterface.class);
        CountryBean resultCountryBean;
        Country country = countryRepoInterface.findById(id); //repo that is dao add country to database

        resultCountryBean = countryTransformer.fromEntityToBeanWithCities(country, LANG_AR);

        return resultCountryBean;
    }

    @Override
    @Transactional
    public CityBean addCity(CityBean cityBean) {
        //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
      //appCon.getBean("cityRepo", CityRepoInterface.class);

        City cityEntity = cityTransformer.fromBeanToEntity(cityBean); //tranforming from bean to entity 

        cityEntity = cityRepoInterface.add(cityEntity); //repo that is dao add country to database
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(cityEntity, LANG_AR);

        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(cityEntity.getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);

        return resultCityBean;

    }

    @Override
    @Transactional
    public CityBean updateCity(CityBean cityBean) {
        // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
   //appCon.getBean("cityRepo", CityRepoInterface.class);

        City cityEntity = cityTransformer.fromBeanToEntity(cityBean); //tranforming from bean to entity 

        Integer id = cityBean.getId();
        City cityEntity2 = cityRepoInterface.findById(id);
        //  cityEntity2.setAreas(cityEntity.getAreas());
        // cityEntity2.setId(cityEntity.getId());
        cityEntity2.setNameAr(cityEntity.getNameAr());
        cityEntity2.setNameEn(cityEntity.getNameEn());
        cityEntity2.setCountry(cityEntity.getCountry());
        cityEntity = cityRepoInterface.update(cityEntity2); //repo that is dao add country to database
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(cityEntity, LANG_AR);

        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(cityEntity.getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);

        return resultCityBean;
    }

    @Override
    @Transactional
    public List<CityBean> findCities() {
        //   ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
       //appCon.getBean("cityRepo", CityRepoInterface.class);

        CityBean resultCityBean;
        List<City> cityList = cityRepoInterface.findList(); //repo that is dao add country to database
        List<CityBean> cityBeanList = new ArrayList<>();
        for (City iCity : cityList) {
            resultCityBean = cityTransformer.fromEntityToBeanWithAreas(iCity, LANG_AR);
            CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(iCity.getCountry(), LANG_AR);
            resultCityBean.setCountryBean(resultCountryBean);
            cityBeanList.add(resultCityBean);

        }

        return cityBeanList;
    }

    @Override
    @Transactional
    public CityBean findCityById(Integer id) {
        //   ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("cityRepo", CityRepoInterface.class);

        City city = cityRepoInterface.findById(id); //repo that is dao add country to database
//bind relation 1-many in bean bind cityBean with areas
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(city, LANG_AR);
        //bind the relations to the parent many-1 binding area tp country
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(city.getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);

        return resultCityBean;
    }

    @Override
    @Transactional
    public AreaBean findAreaById(Integer id) {
        //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
       //appCon.getBean("areaRepo", AreaRepoInterface.class);

        Area area = areaRepoInterface.findById(id); //repo that is dao add country to database

        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(area, LANG_AR);
        //EDIT ON VIWING PROVIDER BEANS IN LIST OF BRANCHES BEANS INSIDE AREA BEAN
        List<ProviderBean> providersOfBranchesOfareas = new ArrayList<>();

        for (Branch areaBranch : area.getBranches()) {
            ProviderBean provider = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(areaBranch.getProvider(), LANG_AR);
            providersOfBranchesOfareas.add(provider);

        }
        for (int i = 0; i < resultAreaBean.getBranchList().size(); i++) {

            resultAreaBean.getBranchList().get(i).setProviderBean(providersOfBranchesOfareas.get(i));
        }
        //=========================================END==========
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(area.getCity(), LANG_AR);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(area.getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultAreaBean.setCityBean(resultCityBean);

        return resultAreaBean;
    }

    @Override
    @Transactional
    public AreaBean addArea(AreaBean areaBean) {
        //   ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
      //appCon.getBean("areaRepo", AreaRepoInterface.class);

        Area areaEntity = areaTransformer.fromBeanToEntity(areaBean); //tranforming from bean to entity 

        areaEntity = areaRepoInterface.add(areaEntity); //repo that is dao add country to database
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(areaEntity, LANG_AR);
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(areaEntity.getCity(), LANG_AR);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(areaEntity.getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultAreaBean.setCityBean(resultCityBean);

        return resultAreaBean;
    }

    @Override
    @Transactional
    public AreaBean updateArea(AreaBean areaBean) {
        //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("areaRepo", AreaRepoInterface.class);

        Area areaEntity = areaTransformer.fromBeanToEntity(areaBean); //tranforming from bean to entity 

        //repo that is dao add country to database
        Integer id = areaBean.getId();
        Area areaEntity2 = areaRepoInterface.findById(id);
        areaEntity2.setNameAr(areaEntity.getNameAr());
        areaEntity2.setNameEn(areaEntity.getNameEn());
        areaEntity2.setCity(areaEntity.getCity());
        areaEntity = areaRepoInterface.update(areaEntity2);
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(areaEntity, LANG_AR);
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(areaEntity.getCity(), LANG_AR);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(areaEntity.getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultAreaBean.setCityBean(resultCityBean);

        return resultAreaBean;
    }

    @Override
    @Transactional
    public List<AreaBean> findAreas() {
        // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
     //appCon.getBean("areaRepo", AreaRepoInterface.class);

        AreaBean resultAreaBean;
        List<Area> areaList = areaRepoInterface.findList(); //repo that is dao add country to database
        List<AreaBean> areaBeanList = new ArrayList<>();
        for (Area iArea : areaList) {
            resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(iArea, LANG_AR);
            CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(iArea.getCity(), LANG_AR);
            CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(iArea.getCity().getCountry(), LANG_AR);
            resultCityBean.setCountryBean(resultCountryBean);
            resultAreaBean.setCityBean(resultCityBean);
            areaBeanList.add(resultAreaBean);

        }

        return areaBeanList;
    }

    @Override
    @Transactional
    public CountryBean updateCountry(CountryBean countryBean) {
        //   ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //   CountryRepoInterface countryRepoInterface = countryRepo;//appCon.getBean("countryRepo", CountryRepoInterface.class);

        CountryBean resultCountryBean;
        Country countryEntity = countryTransformer.fromBeanToEntity(countryBean); //tranforming from bean to entity 

        countryEntity = countryRepoInterface.update(countryEntity); //repo that is dao add country to database

        resultCountryBean = countryTransformer.fromEntityToBeanWithCities(countryEntity, LANG_AR);

        return resultCountryBean;
    }

    @Override
    @Transactional
    public void deleteCountry(Integer id) {
        // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //  CountryRepoInterface countryRepoInterface = countryRepo;//appCon.getBean("countryRepo", CountryRepoInterface.class);

        countryRepoInterface.delete(id);

    }

    @Override
    @Transactional
    public void deleteCity(Integer id) {
        // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("cityRepo", CityRepoInterface.class);

        cityRepoInterface.delete(id);

    }

    @Override
    @Transactional
    public void deleteArea(Integer id) {
        //ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
      //appCon.getBean("areaRepo", AreaRepoInterface.class);

        areaRepoInterface.delete(id);

    }

    public AreaRepoInterface getAreaRepoInterface() {
        return areaRepoInterface;
    }

    public void setAreaRepoInterface(AreaRepoInterface areaRepoInterface) {
        this.areaRepoInterface = areaRepoInterface;
    }

}
