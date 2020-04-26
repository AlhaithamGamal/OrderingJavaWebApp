/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.manager.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import net.haitham.otlobli.common.bean.AreaBean;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.CategoryBean;
import net.haitham.otlobli.common.bean.CityBean;
import net.haitham.otlobli.common.bean.ConsumerAddressBean;
import net.haitham.otlobli.common.bean.ConsumerBean;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otlobli.common.bean.DeliveryAreaBean;
import net.haitham.otlobli.common.bean.OrderBean;
import net.haitham.otlobli.common.bean.ProductBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import static net.haitham.otlobli.common.constant.OtlobliConstant.LANG_AR;
import net.haitham.otloblibal.manager.ConsumerManager;
import net.haitham.otloblibal.transformer.AreaTransformer;
import net.haitham.otloblibal.transformer.BranchTransformer;
import net.haitham.otloblibal.transformer.CategoryTransformer;
import net.haitham.otloblibal.transformer.CityTransformer;
import net.haitham.otloblibal.transformer.ConsumerAddressTransformer;
import net.haitham.otloblibal.transformer.ConsumerTransformer;
import net.haitham.otloblibal.transformer.CountryTransformer;
import net.haitham.otloblibal.transformer.DeliveryAreaTransformer;
import net.haitham.otloblibal.transformer.ProductTransformer;
import net.haitham.otloblibal.transformer.ProviderTransformer;
import static net.haitham.otloblidal.HibernateUtilMe.beginTransaction;
import static net.haitham.otloblidal.HibernateUtilMe.commitTransaction;
import static net.haitham.otloblidal.HibernateUtilMe.openSession;
import net.haitham.otloblidal.entity.annotation.Branch;
import net.haitham.otloblidal.entity.annotation.Category;
import net.haitham.otloblidal.entity.annotation.Consumer;
import net.haitham.otloblidal.entity.annotation.ConsumerAddress;
import net.haitham.otloblidal.entity.annotation.DeliveryArea;
import net.haitham.otloblidal.entity.annotation.Product;
import net.haitham.otloblidal.entity.annotation.Provider;
import net.haitham.otloblidal.entity.annotation.ProviderUser;
import net.haitham.otloblidal.repo.BranchRepo;
import net.haitham.otloblidal.repo.CategoryRepo;
import net.haitham.otloblidal.repo.ConsumerAddressRepo;
import net.haitham.otloblidal.repo.ConsumerRepo;
import net.haitham.otloblidal.repo.DeliveryAreaRepo;
import net.haitham.otloblidal.repo.ProductRepo;
import net.haitham.otloblidal.repo.ProviderRepo;
import net.haitham.otloblidal.repo.aointerface.BranchRepoInterface;
import net.haitham.otloblidal.repo.aointerface.CategoryRepoInterface;
import net.haitham.otloblidal.repo.aointerface.ConsumerAddressRepoInterface;
import net.haitham.otloblidal.repo.aointerface.ConsumerRepoInterface;
import net.haitham.otloblidal.repo.aointerface.DeliveryAreaRepoInterface;
import net.haitham.otloblidal.repo.aointerface.ProductRepoInterface;
import net.haitham.otloblidal.repo.aointerface.ProviderRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author HaithamGamal
 */

@Service
public class ConsumerManagerImpl implements ConsumerManager {

    @Autowired
    private ProviderTransformer providerTransformer;
    @Autowired
    private BranchTransformer branchTransformer;
    @Autowired
    private CategoryTransformer categoryTransformer;
    @Autowired
    private ProductTransformer productTransformer;
    @Autowired
    private ConsumerTransformer consumerTransformer;
    @Autowired
    private ConsumerAddressTransformer consumerAddressTransformer;
    @Autowired
    private AreaTransformer areaTransformer;
    @Autowired
    private CityTransformer cityTransformer;
    @Autowired
    private CountryTransformer countryTransformer;
    @Autowired
    private CategoryRepoInterface categoryRepoInterface;
   @Autowired
    private ConsumerRepoInterface consumerRepoInterface;
    @Autowired
    private ConsumerAddressRepoInterface consumerAddressRepoInterface;
    @Autowired
    private ProductRepoInterface productRepoInterface;
    @Autowired
    private ProviderRepoInterface providerRepoInterface;
    @Autowired
    private BranchRepoInterface branchRepoInterface;
    @Autowired
    private DeliveryAreaRepoInterface deliveryAreaRepoInterface;

    public ProviderTransformer getProviderTransformer() {
        return providerTransformer;
    }

    public void setProviderTransformer(ProviderTransformer providerTransformer) {
        this.providerTransformer = providerTransformer;
    }

    public BranchTransformer getBranchTransformer() {
        return branchTransformer;
    }

    public void setBranchTransformer(BranchTransformer branchTransformer) {
        this.branchTransformer = branchTransformer;
    }

    public CategoryTransformer getCategoryTransformer() {
        return categoryTransformer;
    }

    public void setCategoryTransformer(CategoryTransformer categoryTransformer) {
        this.categoryTransformer = categoryTransformer;
    }

    public ProductTransformer getProductTransformer() {
        return productTransformer;
    }

    public void setProductTransformer(ProductTransformer productTransformer) {
        this.productTransformer = productTransformer;
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

    public AreaTransformer getAreaTransformer() {
        return areaTransformer;
    }

    public void setAreaTransformer(AreaTransformer areaTransformer) {
        this.areaTransformer = areaTransformer;
    }

    public CityTransformer getCityTransformer() {
        return cityTransformer;
    }

    public void setCityTransformer(CityTransformer cityTransformer) {
        this.cityTransformer = cityTransformer;
    }

    public CountryTransformer getCountryTransformer() {
        return countryTransformer;
    }

    public void setCountryTransformer(CountryTransformer countryTransformer) {
        this.countryTransformer = countryTransformer;
    }

    @Override
     @Transactional
    public ConsumerBean addAccountConsumer(ConsumerBean consumerBean) {
        //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
         //appCon.getBean("consumerRepo", ConsumerRepoInterface.class);

      
        Consumer consumerEntity = consumerTransformer.fromBeanToEntity(consumerBean); //tranforming from bean to entity 
       
        consumerEntity = consumerRepoInterface.add(consumerEntity); //repo that is dao add country to database
        ConsumerBean resultConsumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(consumerEntity, LANG_AR);

       
        return resultConsumerBean;
    }

    @Override
     @Transactional
    public ConsumerBean updateAccountConsumer(ConsumerBean consumerBean) {
                 // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
       //appCon.getBean("consumerRepo", ConsumerRepoInterface.class);
     

        Consumer consumerEntity = consumerTransformer.fromBeanToEntity(consumerBean); //tranforming from bean to entity 
       
        Integer id = consumerBean.getId();
        Consumer consumerEntity2 = consumerRepoInterface.findById(id);
        //  cityEntity2.setAreas(cityEntity.getAreas());
        // cityEntity2.setId(cityEntity.getId());
        consumerEntity2.setFullname(consumerEntity.getFullname());
        consumerEntity2.setEmail(consumerEntity.getEmail());
        consumerEntity2.setEncrypted_password(consumerEntity.getEncrypted_password());
        consumerEntity2.setPassword(consumerEntity.getPassword());
        consumerEntity2.setImagePath(consumerEntity.getImagePath());
        consumerEntity2.setGender(consumerEntity.getGender());
        consumerEntity2.setPhone(consumerEntity.getPhone());
        consumerEntity = consumerRepoInterface.update(consumerEntity2); //repo that is dao add country to database
        ConsumerBean resultConsumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(consumerEntity, LANG_AR);

      
        return resultConsumerBean;
    }

    @Override
     @Transactional
    public void deleteAccountConsumer(Integer id) {
                 // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
       //appCon.getBean("consumerRepo", ConsumerRepoInterface.class);
        

        consumerRepoInterface.delete(id); //repo that is dao add country to database

      
    }

    @Override
     @Transactional
    public ConsumerBean findAccountConsumer(Integer id) {
              //    ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
      //appCon.getBean("consumerRepo", ConsumerRepoInterface.class);
      
        Consumer consumer = consumerRepoInterface.findById(id); //repo that is dao add country to database
//bind relation 1-many in bean bind cityBean with areas
        ConsumerBean resultConsumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(consumer, LANG_AR);
        //bind the relations to the parent many-1 binding area tp country

        
        return resultConsumerBean;
    }

    @Override
     @Transactional
    public ConsumerAddressBean addConsumerAddress(ConsumerAddressBean consumerAddressBean) {
               //   ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
     //appCon.getBean("consumerAddressRepo", ConsumerAddressRepoInterface.class);
      
        ConsumerAddress consumerAddressEntity = consumerAddressTransformer.fromBeanToEntity(consumerAddressBean); //tranforming from bean to entity 
       
        consumerAddressEntity = consumerAddressRepoInterface.add(consumerAddressEntity); //repo that is dao add country to database
        ConsumerAddressBean resultConsumerAddressBean = consumerAddressTransformer.fromEntityToBean(consumerAddressEntity, LANG_AR);
        ConsumerBean consumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(consumerAddressEntity.getConsumer(), LANG_AR);
        AreaBean areaBean = areaTransformer.fromEntityToBeanWithBranches(consumerAddressEntity.getArea(), LANG_AR);
        CityBean cityBean = cityTransformer.fromEntityToBeanWithAreas(consumerAddressEntity.getArea().getCity(), LANG_AR);
        CountryBean countryBean = countryTransformer.fromEntityToBeanWithCities(consumerAddressEntity.getArea().getCity().getCountry(), LANG_AR);
        cityBean.setCountryBean(countryBean);
        areaBean.setCityBean(cityBean);
        resultConsumerAddressBean.setConsumer(consumerBean);
        resultConsumerAddressBean.setArea(areaBean);

        return resultConsumerAddressBean;

    }

    @Override
     @Transactional
    public ConsumerAddressBean updateConsumerAddress(ConsumerAddressBean consumerAddressBean) {
                    //     ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
      //appCon.getBean("consumerAddressRepo", ConsumerAddressRepoInterface.class);

        ConsumerAddress consumerAddressEntity = consumerAddressTransformer.fromBeanToEntity(consumerAddressBean); //tranforming from bean to entity 
       
        Integer id = consumerAddressBean.getId();
        ConsumerAddress consumerAddressEntity2 = consumerAddressRepoInterface.findById(id);
        //  cityEntity2.setAreas(cityEntity.getAreas());
        // cityEntity2.setId(cityEntity.getId());
        consumerAddressEntity2.setActive(consumerAddressEntity.getActive());
        consumerAddressEntity2.setBuilding(consumerAddressEntity.getBuilding());
        consumerAddressEntity2.setFlat(consumerAddressEntity.getFlat());
        consumerAddressEntity2.setFloor(consumerAddressEntity.getFloor());
        consumerAddressEntity2.setLabel(consumerAddressEntity.getLabel());
        consumerAddressEntity2.setLandmark(consumerAddressEntity.getLandmark());
        consumerAddressEntity2.setLat(consumerAddressEntity.getLat());
        consumerAddressEntity2.setLng(consumerAddressEntity.getLng());
        consumerAddressEntity2.setArea(consumerAddressEntity.getArea());
        consumerAddressEntity2.setConsumer(consumerAddressEntity.getConsumer());
        consumerAddressEntity = consumerAddressRepoInterface.update(consumerAddressEntity2); //repo that is dao add country to database
        ConsumerAddressBean resultConsumerAddressBean = consumerAddressTransformer.fromEntityToBean(consumerAddressEntity, LANG_AR);
        ConsumerBean consumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(consumerAddressEntity.getConsumer(), LANG_AR);
        AreaBean areaBean = areaTransformer.fromEntityToBeanWithBranches(consumerAddressEntity.getArea(), LANG_AR);
        CityBean cityBean = cityTransformer.fromEntityToBeanWithAreas(consumerAddressEntity.getArea().getCity(), LANG_AR);
        CountryBean countryBean = countryTransformer.fromEntityToBeanWithCities(consumerAddressEntity.getArea().getCity().getCountry(), LANG_AR);
        cityBean.setCountryBean(countryBean);
        areaBean.setCityBean(cityBean);
        resultConsumerAddressBean.setConsumer(consumerBean);
        resultConsumerAddressBean.setArea(areaBean);

       
        return resultConsumerAddressBean;
    }

    @Override
     @Transactional
    public ConsumerAddressBean findConsumerAddress(Integer id) {
                     //     ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
       //appCon.getBean("consumerAddressRepo", ConsumerAddressRepoInterface.class);
      
        ConsumerAddress consumerAddressEntity = consumerAddressRepoInterface.findById(id); //repo that is dao add country to database
//bind relation 1-many in bean bind cityBean with areas
        ConsumerAddressBean resultConsumerAddressBean = consumerAddressTransformer.fromEntityToBean(consumerAddressEntity, LANG_AR);
        //bind the relations to the parent many-1 binding area tp country
        ConsumerBean consumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(consumerAddressEntity.getConsumer(), LANG_AR);
        AreaBean areaBean = areaTransformer.fromEntityToBeanWithBranches(consumerAddressEntity.getArea(), LANG_AR);
        CityBean cityBean = cityTransformer.fromEntityToBeanWithAreas(consumerAddressEntity.getArea().getCity(), LANG_AR);
        CountryBean countryBean = countryTransformer.fromEntityToBeanWithCities(consumerAddressEntity.getArea().getCity().getCountry(), LANG_AR);
        cityBean.setCountryBean(countryBean);
        areaBean.setCityBean(cityBean);
        resultConsumerAddressBean.setConsumer(consumerBean);
        resultConsumerAddressBean.setArea(areaBean);

       
        return resultConsumerAddressBean;
    }

    @Override
     @Transactional
    public void deleteConsumerAddress(Integer id) {
                       //   ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
     //appCon.getBean("consumerAddressRepo", ConsumerAddressRepoInterface.class);
        

        consumerAddressRepoInterface.delete(id); //repo that is dao add country to database

      

    }

    @Override
     @Transactional
    public List<ConsumerAddressBean> findConsumersAddress() {
                        //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("consumerAddressRepo", ConsumerAddressRepoInterface.class);

       
        ConsumerAddressBean resultConsumerAddressBean;
        List<ConsumerAddress> consumerAddressList = consumerAddressRepoInterface.findList(); //repo that is dao add country to database
        List<ConsumerAddressBean> consumerAddressBeanList = new ArrayList<>();
        for (ConsumerAddress iConsumerAddress : consumerAddressList) {
            resultConsumerAddressBean = consumerAddressTransformer.fromEntityToBean(iConsumerAddress, LANG_AR);
            ConsumerBean consumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(iConsumerAddress.getConsumer(), LANG_AR);
            AreaBean areaBean = areaTransformer.fromEntityToBeanWithBranches(iConsumerAddress.getArea(), LANG_AR);
            CityBean cityBean = cityTransformer.fromEntityToBeanWithAreas(iConsumerAddress.getArea().getCity(), LANG_AR);
            CountryBean countryBean = countryTransformer.fromEntityToBeanWithCities(iConsumerAddress.getArea().getCity().getCountry(), LANG_AR);
            cityBean.setCountryBean(countryBean);
            areaBean.setCityBean(cityBean);
            resultConsumerAddressBean.setConsumer(consumerBean);
            resultConsumerAddressBean.setArea(areaBean);

            consumerAddressBeanList.add(resultConsumerAddressBean);

        }
        
        return consumerAddressBeanList;
    }

    @Override
     @Transactional
    public List<ConsumerBean> findConsumersAccounts() {
                     //     ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
      //appCon.getBean("consumerRepo", ConsumerRepoInterface.class);
       
        ConsumerBean resultConsumerBean;
        List<Consumer> consumerList = consumerRepoInterface.findList(); //repo that is dao add country to database
        List<ConsumerBean> consumerBeanList = new ArrayList<>();
        for (Consumer iConsumer : consumerList) {
            resultConsumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(iConsumer, LANG_AR);

            consumerBeanList.add(resultConsumerBean);

        }
       
        return consumerBeanList;
    }

    public CategoryRepoInterface getCategoryRepoInterface() {
        return categoryRepoInterface;
    }

    public void setCategoryRepoInterface(CategoryRepoInterface categoryRepoInterface) {
        this.categoryRepoInterface = categoryRepoInterface;
    }

    public ConsumerRepoInterface getConsumerRepoInterface() {
        return consumerRepoInterface;
    }

    public void setConsumerRepoInterface(ConsumerRepoInterface consumerRepoInterface) {
        this.consumerRepoInterface = consumerRepoInterface;
    }

    public ConsumerAddressRepoInterface getConsumerAddressRepoInterface() {
        return consumerAddressRepoInterface;
    }

    public void setConsumerAddressRepoInterface(ConsumerAddressRepoInterface consumerAddressRepoInterface) {
        this.consumerAddressRepoInterface = consumerAddressRepoInterface;
    }

    public ProductRepoInterface getProductRepoInterface() {
        return productRepoInterface;
    }

    public void setProductRepoInterface(ProductRepoInterface productRepoInterface) {
        this.productRepoInterface = productRepoInterface;
    }

    public ProviderRepoInterface getProviderRepoInterface() {
        return providerRepoInterface;
    }

    public void setProviderRepoInterface(ProviderRepoInterface providerRepoInterface) {
        this.providerRepoInterface = providerRepoInterface;
    }

    public BranchRepoInterface getBranchRepoInterface() {
        return branchRepoInterface;
    }

    public void setBranchRepoInterface(BranchRepoInterface branchRepoInterface) {
        this.branchRepoInterface = branchRepoInterface;
    }

    public DeliveryAreaRepoInterface getDeliveryAreaRepoInterface() {
        return deliveryAreaRepoInterface;
    }

    public void setDeliveryAreaRepoInterface(DeliveryAreaRepoInterface deliveryAreaRepoInterface) {
        this.deliveryAreaRepoInterface = deliveryAreaRepoInterface;
    }

}
