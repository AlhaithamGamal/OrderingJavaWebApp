/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.manager.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import net.haitham.otlobli.common.bean.AdminUserBean;
import net.haitham.otlobli.common.bean.AreaBean;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.CategoryBean;
import net.haitham.otlobli.common.bean.CityBean;
import net.haitham.otlobli.common.bean.ConsumerAddressBean;
import net.haitham.otlobli.common.bean.ConsumerBean;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otlobli.common.bean.DeliveryAreaBean;
import net.haitham.otlobli.common.bean.ProductBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otlobli.common.bean.ProviderUserBean;
import static net.haitham.otlobli.common.constant.OtlobliConstant.LANG_AR;
import net.haitham.otlobli.common.constant.SendEmail;
import net.haitham.otloblibal.manager.AdminManager;
import net.haitham.otloblibal.transformer.AdminAccountTransformer;
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
import net.haitham.otloblibal.transformer.ProviderUserTransformer;
import static net.haitham.otloblidal.HibernateUtilMe.beginTransaction;
import static net.haitham.otloblidal.HibernateUtilMe.commitTransaction;
import static net.haitham.otloblidal.HibernateUtilMe.openSession;
import net.haitham.otloblidal.entity.annotation.AdminUser;
import net.haitham.otloblidal.entity.annotation.Area;
import net.haitham.otloblidal.entity.annotation.Branch;
import net.haitham.otloblidal.entity.annotation.Category;
import net.haitham.otloblidal.entity.annotation.City;
import net.haitham.otloblidal.entity.annotation.Consumer;
import net.haitham.otloblidal.entity.annotation.ConsumerAddress;
import net.haitham.otloblidal.entity.annotation.Country;
import net.haitham.otloblidal.entity.annotation.DeliveryArea;
import net.haitham.otloblidal.entity.annotation.Product;
import net.haitham.otloblidal.entity.annotation.Provider;
import net.haitham.otloblidal.entity.annotation.ProviderUser;
import net.haitham.otloblidal.repo.AdminUserRepo;
import net.haitham.otloblidal.repo.BranchRepo;
import net.haitham.otloblidal.repo.CategoryRepo;
import net.haitham.otloblidal.repo.ConsumerAddressRepo;
import net.haitham.otloblidal.repo.ConsumerRepo;
import net.haitham.otloblidal.repo.CountryRepo;
import net.haitham.otloblidal.repo.DeliveryAreaRepo;
import net.haitham.otloblidal.repo.ProductRepo;
import net.haitham.otloblidal.repo.ProviderRepo;
import net.haitham.otloblidal.repo.ProviderUserRepo;
import net.haitham.otloblidal.repo.aointerface.AdminUserRepoInterface;
import net.haitham.otloblidal.repo.aointerface.BranchRepoInterface;
import net.haitham.otloblidal.repo.aointerface.CategoryRepoInterface;
import net.haitham.otloblidal.repo.aointerface.ConsumerAddressRepoInterface;
import net.haitham.otloblidal.repo.aointerface.ConsumerRepoInterface;
import net.haitham.otloblidal.repo.aointerface.DeliveryAreaRepoInterface;
import net.haitham.otloblidal.repo.aointerface.ProductRepoInterface;
import net.haitham.otloblidal.repo.aointerface.ProviderRepoInterface;
import net.haitham.otloblidal.repo.aointerface.ProviderUserRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

/**
 *
 * @author HaithamGamal
 */
@Service //transactional above class instead of functions
public class AdminManagerImpl implements AdminManager {

    @Autowired
    private ProviderTransformer providerTransformer;
    @Autowired
    private AreaTransformer areaTransformer;
    @Autowired
    private CityTransformer cityTransformer;
    @Autowired
    private CountryTransformer countryTransformer;
    @Autowired
    private BranchTransformer branchTransformer;
    @Autowired
    private CategoryTransformer categoryTransformer;
    @Autowired
    private ProductTransformer productTransformer;
    @Autowired
    private ConsumerTransformer consumerTransformer;
    @Autowired
    private AdminAccountTransformer adminAccountTransformer;
    @Autowired
    private ProviderUserTransformer providerUserTransformer;
    @Autowired
    private ConsumerAddressTransformer consumerAddressTransformer;
    @Autowired
    private DeliveryAreaTransformer deliveryAreaTransformer;
    @Autowired
    private ProviderRepoInterface providerRepoInterface;
    @Autowired
    private BranchRepoInterface branchRepoInterface;
    @Autowired
    private DeliveryAreaRepoInterface deliveryAreaRepoInterface;
    @Autowired
    private ProviderUserRepoInterface providerUserRepoInterface;
    @Autowired
    private CategoryRepoInterface categoryRepoInterface;
    @Autowired
    private ProductRepoInterface productRepoInterface;
    @Autowired
    private ConsumerRepoInterface consumerRepoInterface;
    @Autowired
    private ConsumerAddressRepoInterface consumerAddressRepoInterface;
    @Autowired
    private AdminUserRepoInterface adminUserRepoInterface;

    public DeliveryAreaTransformer getDeliveryAreaTransformer() {
        return deliveryAreaTransformer;
    }

    public void setDeliveryAreaTransformer(DeliveryAreaTransformer deliveryAreaTransformer) {
        this.deliveryAreaTransformer = deliveryAreaTransformer;
    }

    public ProviderUserTransformer getProviderUserTransformer() {
        return providerUserTransformer;
    }

    public void setProviderUserTransformer(ProviderUserTransformer providerUserTransformer) {
        this.providerUserTransformer = providerUserTransformer;
    }

    public ProviderTransformer getProviderTransformer() {
        return providerTransformer;
    }

    public void setProviderTransformer(ProviderTransformer providerTransformer) {
        this.providerTransformer = providerTransformer;
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

    public AdminAccountTransformer getAdminAccountTransformer() {
        return adminAccountTransformer;
    }

    public void setAdminAccountTransformer(AdminAccountTransformer adminAccountTransformer) {
        this.adminAccountTransformer = adminAccountTransformer;
    }

    public ConsumerAddressTransformer getConsumerAddressTransformer() {
        return consumerAddressTransformer;
    }

    public void setConsumerAddressTransformer(ConsumerAddressTransformer consumerAddressTransformer) {
        this.consumerAddressTransformer = consumerAddressTransformer;
    }

    @Override
    @Transactional
    public AdminUserBean findAdminAccountBy(Integer id) {
        //     ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //ppCon.getBean("adminUserRepo", AdminUserRepoInterface.class);

        AdminUserBean resultAdminUserBean;
        AdminUser adminUser = adminUserRepoInterface.findById(id); //repo that is dao add country to database

        resultAdminUserBean = adminAccountTransformer.fromEntityToBean(adminUser, LANG_AR);

        return resultAdminUserBean;
    }

    @Override
    @Transactional
    public List<AdminUserBean> findAdminAccounts() {
        //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("adminUserRepo", AdminUserRepoInterface.class);

        AdminUserBean resultAdminUserBean;
        List<AdminUser> adminUserList = adminUserRepoInterface.findList(); //repo that is dao add country to database
        List<AdminUserBean> adminUserBeanList = new ArrayList<>();
        for (AdminUser iAdminUser : adminUserList) {
            resultAdminUserBean = adminAccountTransformer.fromEntityToBean(iAdminUser, LANG_AR);
            adminUserBeanList.add(resultAdminUserBean);

        }

        return adminUserBeanList;
    }

    @Override
    @Transactional
    public AdminUserBean createAdminAccount(AdminUserBean adminUserBean) {
        // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("adminUserRepo", AdminUserRepoInterface.class);

        AdminUserBean resultAdminUserBean;
        AdminUser adminUserEntity = adminAccountTransformer.fromBeanToEntity(adminUserBean); //tranforming from bean to entity 

        adminUserEntity = adminUserRepoInterface.add(adminUserEntity); //repo that is dao add country to database
        try {
            SendEmail sn = new SendEmail(adminUserEntity.getEmail(), adminUserEntity.getHash());
            sn.sendMail();
        } catch (Exception ex) {

            System.out.println("Exception :" + ex.getMessage());
        }

        resultAdminUserBean = adminAccountTransformer.fromEntityToBean(adminUserEntity, LANG_AR);

        return resultAdminUserBean;
    }

    @Override
    @Transactional
    public AdminUserBean updateAdminAccount(AdminUserBean adminBean) {
        //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("adminUserRepo", AdminUserRepoInterface.class);

        AdminUser adminUserEntity = adminAccountTransformer.fromBeanToEntity(adminBean); //tranforming from bean to entity 

        //repo that is dao add country to database
        Integer id = adminBean.getId();
        AdminUser adminUserEntity2 = adminUserRepoInterface.findById(id);
        adminUserEntity2.setEmail(adminBean.getEmail());
        adminUserEntity2.setUsername(adminBean.getUsername());
        adminUserEntity2.setPassword(adminBean.getPassword());
        adminUserEntity2.setOriginalPassword(adminBean.getOriginalPassword());
        adminUserEntity2.setHash(adminBean.getHash());
        adminUserEntity2.setActive(adminBean.getActive());
        adminUserEntity2 = adminUserRepoInterface.update(adminUserEntity2);
        AdminUserBean resultAdminUserBean = adminAccountTransformer.fromEntityToBean(adminUserEntity2, LANG_AR);

        return resultAdminUserBean;
    }

    @Override
    @Transactional
    public void deleteAdminAccount(Integer id) {
        //    ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("adminUserRepo", AdminUserRepoInterface.class);

        adminUserRepoInterface.delete(id);

    }

    @Override
    @Transactional
    public ProviderUserBean addProviderUser(ProviderUserBean providerUserBean) {
        //ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("providerUserRepo", ProviderUserRepoInterface.class);
        BranchBean resultBranchBean = null;
        CityBean resultCityBean = null;
        AreaBean resultAreaBean = null;
        CountryBean resultCountryBean = null;
        ProviderBean resultProviderBean = null;

        ProviderUserBean resultProviderUserBean = new ProviderUserBean();
        ProviderUser providerUser = providerUserTransformer.fromBeanToEntity(providerUserBean); //tranforming from bean to entity 

        if (null == providerUser) { //may be validation error on pwd

            return null;
        } else {

            ProviderUser providerUserEntity = providerUserRepoInterface.add(providerUser); //repo that is dao add country to database
            resultProviderUserBean = providerUserTransformer.fromEntityToBean(providerUserEntity, LANG_AR);
            Branch branchCheck = providerUserEntity.getBranch();
            if (branchCheck != null) {
                resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(providerUserEntity.getBranch(), LANG_AR);
                resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(providerUserEntity.getBranch().getArea(), LANG_AR);
                resultCityBean = cityTransformer.fromEntityToBeanWithAreas(providerUserEntity.getBranch().getArea().getCity(), LANG_AR);
                resultCountryBean = countryTransformer.fromEntityToBeanWithCities(providerUserEntity.getBranch().getArea().getCity().getCountry(), LANG_AR);
                resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(providerUserEntity.getProvider(), LANG_AR);

                resultCityBean.setCountryBean(resultCountryBean);
                resultAreaBean.setCityBean(resultCityBean);
                resultBranchBean.setAreaBean(resultAreaBean);
                resultBranchBean.setProviderBean(resultProviderBean);
                resultProviderUserBean.setBranch(resultBranchBean);
                resultProviderUserBean.setProvider(resultProviderBean);

            } else {

                resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(providerUserEntity.getProvider(), LANG_AR);

                resultProviderUserBean.setProvider(resultProviderBean);
            }

            return resultProviderUserBean;
        }
    }

    @Override
    @Transactional
    public ProviderUserBean updateProviderUser(ProviderUserBean providerUserBean) {
        //   ApplicationContext appCFon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("providerUserRepo", ProviderUserRepoInterface.class);
        BranchBean resultBranchBean = null;
        CityBean resultCityBean = null;
        AreaBean resultAreaBean = null;
        CountryBean resultCountryBean = null;
        ProviderBean resultProviderBean = null;

        ProviderUserBean resultProviderUserBean = new ProviderUserBean();
        ProviderUser providerUserEntity = providerUserTransformer.fromBeanToEntity(providerUserBean); //tranforming from bean to entity 

        //===============================
        Integer id = providerUserBean.getId();
        ProviderUser providerUserEntity2 = providerUserRepoInterface.findById(id);
        if (providerUserEntity != null) {
            providerUserEntity2.setUsername(providerUserEntity.getUsername());
            providerUserEntity2.setEmail(providerUserEntity.getEmail());
            providerUserEntity2.setPassword(providerUserEntity.getPassword());
            providerUserEntity2.setBranch(providerUserEntity.getBranch());
            providerUserEntity2.setActive(providerUserEntity.getActive());
            providerUserEntity2.setProvider(providerUserEntity.getProvider());
            providerUserEntity2.setEncrypted_password(providerUserEntity.getEncrypted_password());
        }

        //===============
        //repo that is dao add country to database
        //============================
        if (providerUserEntity != null) {
            providerUserEntity = providerUserRepoInterface.update(providerUserEntity2);
            Branch branchCheck = providerUserEntity.getBranch();
            if (branchCheck != null) {
                resultProviderUserBean = providerUserTransformer.fromEntityToBean(providerUserEntity, LANG_AR);
                resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(providerUserEntity.getBranch(), LANG_AR);
                resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(providerUserEntity.getProvider(), LANG_AR);
                resultBranchBean.setProviderBean(resultProviderBean);
                resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(providerUserEntity.getBranch().getArea(), LANG_AR);
                resultCityBean = cityTransformer.fromEntityToBeanWithAreas(providerUserEntity.getBranch().getArea().getCity(), LANG_AR);
                resultCountryBean = countryTransformer.fromEntityToBeanWithCities(providerUserEntity.getBranch().getArea().getCity().getCountry(), LANG_AR);
                resultCityBean.setCountryBean(resultCountryBean);
                resultAreaBean.setCityBean(resultCityBean);
                resultBranchBean.setAreaBean(resultAreaBean);
                resultProviderUserBean.setBranch(resultBranchBean);
                resultProviderUserBean.setProvider(resultProviderBean);

            } else {

                resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(providerUserEntity.getProvider(), LANG_AR);

                resultProviderUserBean.setProvider(resultProviderBean);
            }
        } else {

            return null;
        }

        return resultProviderUserBean;
    }

    @Override
    @Transactional
    public void deleteProviderUser(Integer id) {
        //   ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("providerUserRepo", ProviderUserRepoInterface.class);

        providerUserRepoInterface.delete(id); //repo that is dao add country to database

    }

    @Override
    @Transactional
    public List<ProviderUserBean> findProviderUsers() {
        //    ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("providerUserRepo", ProviderUserRepoInterface.class);

        ProviderUserBean resultProviderUserBean = null;
        List<ProviderUser> providerUserList = providerUserRepoInterface.findList(); //repo that is dao add country to database
        List<ProviderUserBean> providerUserBeanList = new ArrayList<>();
        for (ProviderUser iProviderUser : providerUserList) {
            Branch branchCheck = iProviderUser.getBranch();
            if (branchCheck != null) {
                resultProviderUserBean = providerUserTransformer.fromEntityToBean(iProviderUser, LANG_AR);
                AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(iProviderUser.getBranch().getArea(), LANG_AR);
                CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(iProviderUser.getBranch().getArea().getCity(), LANG_AR);
                CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(iProviderUser.getBranch().getArea().getCity().getCountry(), LANG_AR);
                resultCityBean.setCountryBean(resultCountryBean);
                resultAreaBean.setCityBean(resultCityBean);
                BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(iProviderUser.getBranch(), LANG_AR);
                ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(iProviderUser.getProvider(), LANG_AR);
                resultBranchBean.setProviderBean(resultProviderBean);
                resultBranchBean.setAreaBean(resultAreaBean);

                resultProviderUserBean.setBranch(resultBranchBean);
                resultProviderUserBean.setProvider(resultProviderBean);

            } else {
                resultProviderUserBean = providerUserTransformer.fromEntityToBean(iProviderUser, LANG_AR);

                ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(iProviderUser.getProvider(), LANG_AR);
                resultProviderUserBean.setProvider(resultProviderBean);
            }
            providerUserBeanList.add(resultProviderUserBean);

        }

        return providerUserBeanList;
    }

    @Override
    @Transactional
    public ProviderUserBean findProviderUserById(Integer id) {
        // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("providerUserRepo", ProviderUserRepoInterface.class);

        ProviderUser providerUser = providerUserRepoInterface.findById(id); //repo that is dao add country to database
        ProviderUserBean resultProviderUserBean = providerUserTransformer.fromEntityToBean(providerUser, LANG_AR);
        Branch branchCheck = providerUser.getBranch();
        if (branchCheck != null) {
            BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(providerUser.getBranch(), LANG_AR);
            ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(providerUser.getProvider(), LANG_AR);
            resultBranchBean.setProviderBean(resultProviderBean);
            AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(providerUser.getBranch().getArea(), LANG_AR);
            CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(providerUser.getBranch().getArea().getCity(), LANG_AR);
            CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(providerUser.getBranch().getArea().getCity().getCountry(), LANG_AR);
            resultCityBean.setCountryBean(resultCountryBean);
            resultAreaBean.setCityBean(resultCityBean);
            resultBranchBean.setAreaBean(resultAreaBean);
            resultProviderUserBean.setBranch(resultBranchBean);
            resultProviderUserBean.setProvider(resultProviderBean);

        } else {
            ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(providerUser.getProvider(), LANG_AR);
            resultProviderUserBean.setProvider(resultProviderBean);

        }

        return resultProviderUserBean;
    }

    @Override
    @Transactional
    public ProviderBean addProvider(ProviderBean providerBean) {
        //ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("providerRepo", ProviderRepoInterface.class);

        ProviderBean resultProviderBean;
        Provider providerEntity = providerTransformer.fromBeanToEntity(providerBean); //tranforming from bean to entity 

        providerEntity = providerRepoInterface.add(providerEntity); //repo that is dao add country to database

        resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(providerEntity, LANG_AR);

        return resultProviderBean;
    }

    @Override
    @Transactional
    public ProviderBean updateProvider(ProviderBean providerBean) {
        // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("providerRepo", ProviderRepoInterface.class);
        Integer id = providerBean.getId();
        Provider providerEntity2 = providerRepoInterface.findById(id);

        ProviderBean resultProviderBean;
        Provider providerEntity = providerTransformer.fromBeanToEntity(providerBean); //tranforming from bean to entity 
        if(providerEntity2.getLogoPathEn() != null){
        providerEntity2.setLogoPathEn(providerEntity2.getLogoPathEn());
        }
        providerEntity2.setHotline(providerEntity.getHotline());
        providerEntity2.setNameAr(providerEntity.getNameAr());
        providerEntity2.setNameEn(providerEntity.getNameEn());

//        Provider providerEntityCheckLogo = providerRepo.findById(providerEntity.getId());
        //   providerEntity.setLogoPathEn(providerEntity.getLogoPathEn()); //check the logo if exists let it without alteration not exist change it
        providerEntity = providerRepoInterface.update(providerEntity2); //repo that is dao add country to database

        resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(providerEntity, LANG_AR);

        return resultProviderBean;
    }

    @Override
    @Transactional
    public void deleteProvider(Integer id) {
        //   ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("providerRepo", ProviderRepoInterface.class);

        ProviderBean resultProviderBean;
        providerRepoInterface.delete(id); //repo that is dao add country to database

    }

    @Override
    @Transactional
    public List<ProviderBean> findProviders() {
        //ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("providerRepo", ProviderRepoInterface.class);

        ProviderBean resultProviderBean;
        List<Provider> providerList = providerRepoInterface.findList(); //repo that is dao add country to database
        List<ProviderBean> providerBeanList = new ArrayList<>();
        for (Provider iProvider : providerList) {
            resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(iProvider, LANG_AR);
            providerBeanList.add(resultProviderBean);

        }

        return providerBeanList;
    }

    @Override
    @Transactional
    public ProviderBean findProviderById(Integer id) {
        //ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("providerRepo", ProviderRepoInterface.class);
        Integer index = 0;

        ProviderBean resultProviderBean;
        Provider provider = providerRepoInterface.findById(id); //repo that is dao add country to database

        resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(provider, LANG_AR);
        //get branch from ProviderUser inside provider entity
        //Steps=> create branchbean arraylist loop on provider to get provider users
        //get branch from provider user entity 
        //transform the entity to bean 
        //add the bean to branchbean list 
        List<BranchBean> branchesOfProvUsers = new ArrayList<>();

        for (ProviderUser providerUserBranch : provider.getProviderUsers()) {
            if (providerUserBranch.getBranch() != null) {
                BranchBean branchOfUser = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(providerUserBranch.getBranch(), LANG_AR);
                //  branchesOfProvUsers.add(branchOfUser);
                resultProviderBean.getProviderUserList().get(index).setBranch(branchOfUser);

            } else {
                resultProviderBean.getProviderUserList().get(index).setBranch(null);

            }
            index++;

        }

//        for (int i = 0; i < resultProviderBean.getProviderUserList().size(); i++) {
//            //Loop on the resulted provider bean that contains both branches and users
//            //getting branch from branchesofprovusers bean
//            //setting branch to resultproviderbean in provider users accessing elements
//           
//        }
        return resultProviderBean;
    }

    @Override
    @Transactional
    public ProviderBean findProviderByIdWithoutBranchesOfProviderUsers(Integer id) {
        //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("providerRepo", ProviderRepoInterface.class);

        ProviderBean resultProviderBean;
        Provider provider = providerRepoInterface.findById(id); //repo that is dao add country to database

        resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(provider, LANG_AR);
        //get branch from ProviderUser inside provider entity
        //Steps=> create branchbean arraylist loop on provider to get provider users
        //get branch from provider user entity 
        //transform the entity to bean 
        //add the bean to branchbean list 

        return resultProviderBean;
    }

    @Override
    @Transactional
    public BranchBean addBranch(BranchBean branchBean) {
        //ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("branchRepo", BranchRepoInterface.class);

        BranchBean resultBranchBean;
        Branch branchEntity = branchTransformer.fromBeanToEntity(branchBean); //tranforming from bean to entity 

        branchEntity = branchRepoInterface.add(branchEntity); //repo that is dao add country to database

        resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(branchEntity, LANG_AR);
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(branchEntity.getProvider(), LANG_AR);
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(branchEntity.getArea(), LANG_AR);
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(branchEntity.getArea().getCity(), LANG_AR);
        resultAreaBean.setCityBean(resultCityBean);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(branchEntity.getArea().getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultBranchBean.setProviderBean(resultProviderBean);
        resultBranchBean.setAreaBean(resultAreaBean);

        return resultBranchBean;
    }

    @Override
    @Transactional
    public BranchBean updateBranch(BranchBean branchBean) {
        //    ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("branchRepo", BranchRepoInterface.class);

        BranchBean resultBranchBean;
        Branch branchEntity = branchTransformer.fromBeanToEntity(branchBean); //tranforming from bean to entity 

        //Branch branchEntity2 = branchRepo.findById(branchEntity.getId());
        Integer id = branchBean.getId();
        Branch branchEntity2 = branchRepoInterface.findById(id);
        System.out.println("BRANCH OBJ IS" + branchEntity2);
        System.out.println("BRANCH NAME ARABIC IS " + branchEntity.getNameAr());
        System.out.println("BRANCH NAME ENGLISH IS " + branchEntity.getNameEn());
        System.out.println("BRANCH BEAN ID IS " + branchEntity.getId());
        System.out.println("ID BRANCH" + branchEntity2.getId());
        branchEntity2.setNameAr(branchEntity.getNameAr());
        branchEntity2.setNameEn(branchEntity.getNameEn());
        branchEntity2.setOpenAt(branchEntity.getOpenAt());
        branchEntity2.setCloseAt(branchEntity.getCloseAt());
        branchEntity2.setLat(branchEntity.getLat());
        branchEntity2.setLng(branchEntity.getLng());
        branchEntity2.setPhone(branchEntity.getPhone());
        branchEntity2.setArea(branchEntity.getArea());
        branchEntity2.setProvider(branchEntity.getProvider());
        branchEntity = branchRepoInterface.update(branchEntity2); //repo that is dao add country to database

        resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(branchEntity, LANG_AR);
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(branchEntity.getProvider(), LANG_AR);
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(branchEntity.getArea(), LANG_AR);

        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(branchEntity.getArea().getCity(), LANG_AR);
        resultAreaBean.setCityBean(resultCityBean);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(branchEntity.getArea().getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultBranchBean.setProviderBean(resultProviderBean);
        resultBranchBean.setAreaBean(resultAreaBean);

        return resultBranchBean;
    }

    @Override
    @Transactional
    public void deleteBranch(Integer id) {
        // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("branchRepo", BranchRepoInterface.class);

        BranchBean resultBranchBean;
        branchRepoInterface.delete(id); //repo that is dao add country to database

    }

    @Override
    @Transactional
    public List<BranchBean> findBranches() {
        // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("branchRepo", BranchRepoInterface.class);

        BranchBean resultBranchBean;
        List<Branch> branchList = branchRepoInterface.findList(); //repo that is dao add country to database
        List<BranchBean> branchBeanList = new ArrayList<>();
        for (Branch iBranch : branchList) {
            resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(iBranch, LANG_AR);
            //Like find by ID TRY to get areas from branch in one to many case area has many branches
            ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(iBranch.getProvider(), LANG_AR);
            AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(iBranch.getArea(), LANG_AR);

            CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(iBranch.getArea().getCity(), LANG_AR);
            CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(iBranch.getArea().getCity().getCountry(), LANG_AR);
            resultCityBean.setCountryBean(resultCountryBean);
            resultAreaBean.setCityBean(resultCityBean);

            resultBranchBean.setProviderBean(resultProviderBean);
            resultBranchBean.setAreaBean(resultAreaBean);
            branchBeanList.add(resultBranchBean);

        }

        return branchBeanList;
    }

    @Override
    @Transactional
    public BranchBean findBranchById(Integer id) {
        // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("branchRepo", BranchRepoInterface.class);

        //Error was here should follow the mapping till reach parent
        //As follows branch=>area=>city=>country |branch=>provider and so on
        Branch branch = branchRepoInterface.findById(id); //repo that is dao add country to database
        List<Branch> listOfAreaEntitiesInDelivery = new ArrayList<Branch>();
        BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(branch, LANG_AR);
        //Storing areabeans in deliveryareabeanslist to prevent stackoverflow
        //change set entity to list bean
        //showing area name in viewing delivery area page
        List<DeliveryArea> branchDeliveyryAreasList = new ArrayList<>(branch.getDeliveryAreas());

        for (int i = 0; i < resultBranchBean.getDeliveryAreaList().size(); i++) {
            AreaBean areaBean = areaTransformer.fromEntityToBeanWithBranches(branchDeliveyryAreasList.get(i).getArea(), LANG_AR);
            resultBranchBean.getDeliveryAreaList().get(i).setAreaBean(areaBean);

        }
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(branch.getProvider(), LANG_AR);
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(branch.getArea(), LANG_AR);

        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(branch.getArea().getCity(), LANG_AR);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(branch.getArea().getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultAreaBean.setCityBean(resultCityBean);

        resultBranchBean.setProviderBean(resultProviderBean);
        resultBranchBean.setAreaBean(resultAreaBean);

        return resultBranchBean;
    }

    @Override
    @Transactional
    public DeliveryAreaBean addDeliveryArea(DeliveryAreaBean deliveryAreaBean) {
        //   ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("deliveryAreaRepo", DeliveryAreaRepoInterface.class);

        DeliveryAreaBean resultDeliveryAreaBean = new DeliveryAreaBean();
        DeliveryArea deliveryArea = deliveryAreaTransformer.fromBeanToEntity(deliveryAreaBean); //tranforming from bean to entity 

        DeliveryArea deliveryAreaEntity = deliveryAreaRepoInterface.add(deliveryArea); //repo that is dao add country to database
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(deliveryAreaEntity.getBranch().getProvider(), LANG_AR);
        resultDeliveryAreaBean = deliveryAreaTransformer.fromEntityToBean(deliveryAreaEntity, LANG_AR);
        BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(deliveryAreaEntity.getBranch(), LANG_AR);
        resultBranchBean.setProviderBean(resultProviderBean);
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(deliveryAreaEntity.getArea(), LANG_AR);
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(deliveryAreaEntity.getArea().getCity(), LANG_AR);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(deliveryAreaEntity.getArea().getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultAreaBean.setCityBean(resultCityBean);
        resultBranchBean.setAreaBean(resultAreaBean);
        resultDeliveryAreaBean.setBranchBean(resultBranchBean);
        resultDeliveryAreaBean.setAreaBean(resultAreaBean);

        return resultDeliveryAreaBean;
    }

    @Override
    @Transactional
    public DeliveryAreaBean updateDeliveryArea(DeliveryAreaBean deliveryAreaBean) {
        //    ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("deliveryAreaRepo", DeliveryAreaRepoInterface.class);

        DeliveryArea deliveryAreaEntity = deliveryAreaTransformer.fromBeanToEntity(deliveryAreaBean); //tranforming from bean to entity 
        DeliveryAreaBean resultDeliveryAreaBean = new DeliveryAreaBean();

        Integer id = deliveryAreaBean.getId();
        DeliveryArea deliveryAreaEntity2 = deliveryAreaRepoInterface.findById(id);

        deliveryAreaEntity2.setDeliverInMinutes(deliveryAreaEntity.getDeliverInMinutes());
        deliveryAreaEntity2.setDeliveryFees(deliveryAreaEntity.getDeliveryFees());
        deliveryAreaEntity2.setArea(deliveryAreaEntity.getArea());
        deliveryAreaEntity2.setBranch(deliveryAreaEntity.getBranch());

        deliveryAreaEntity = deliveryAreaRepoInterface.update(deliveryAreaEntity2); //repo that is dao add country to database

        resultDeliveryAreaBean = deliveryAreaTransformer.fromEntityToBean(deliveryAreaEntity, LANG_AR);
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(deliveryAreaEntity.getBranch().getProvider(), LANG_AR);
        BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(deliveryAreaEntity.getBranch(), LANG_AR);
        resultBranchBean.setProviderBean(resultProviderBean);
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(deliveryAreaEntity.getArea(), LANG_AR);
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(deliveryAreaEntity.getArea().getCity(), LANG_AR);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(deliveryAreaEntity.getArea().getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultAreaBean.setCityBean(resultCityBean);
        resultBranchBean.setAreaBean(resultAreaBean);
        resultDeliveryAreaBean.setBranchBean(resultBranchBean);
        resultDeliveryAreaBean.setAreaBean(resultAreaBean);

        return resultDeliveryAreaBean;
    }

    @Override
    @Transactional
    public void deleteDeliveryArea(Integer id) {
        //   ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("deliveryAreaRepo", DeliveryAreaRepoInterface.class);

        BranchBean resultBranchBean;
        deliveryAreaRepoInterface.delete(id); //repo that is dao add country to database

    }

    @Override
    @Transactional
    public DeliveryAreaBean findDeliveryAreaById(Integer id) {
        // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("deliveryAreaRepo", DeliveryAreaRepoInterface.class);

        DeliveryArea deliveryArea = deliveryAreaRepoInterface.findById(id); //repo that is dao add country to database
        DeliveryAreaBean resultDeliveryAreaBean = deliveryAreaTransformer.fromEntityToBean(deliveryArea, LANG_AR);
        BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(deliveryArea.getBranch(), LANG_AR);
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(deliveryArea.getBranch().getProvider(), LANG_AR);
        resultBranchBean.setProviderBean(resultProviderBean);
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(deliveryArea.getArea(), LANG_AR);
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(deliveryArea.getArea().getCity(), LANG_AR);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(deliveryArea.getArea().getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultAreaBean.setCityBean(resultCityBean);
        resultBranchBean.setAreaBean(resultAreaBean);
        resultDeliveryAreaBean.setBranchBean(resultBranchBean);
        resultDeliveryAreaBean.setAreaBean(resultAreaBean);

        return resultDeliveryAreaBean;
    }

    @Override
    @Transactional
    public List<DeliveryAreaBean> findDeliveryAreas() {
        //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("deliveryAreaRepo", DeliveryAreaRepoInterface.class);

        DeliveryAreaBean resultDeliveryAreaBean;
        List<DeliveryArea> deliveryAreaList = deliveryAreaRepoInterface.findList(); //repo that is dao add country to database
        List<DeliveryAreaBean> deliveryAreaBeanList = new ArrayList<>();
        for (DeliveryArea iDeliveryArea : deliveryAreaList) {
            resultDeliveryAreaBean = deliveryAreaTransformer.fromEntityToBean(iDeliveryArea, LANG_AR);
            AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(iDeliveryArea.getArea(), LANG_AR);
            CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(iDeliveryArea.getArea().getCity(), LANG_AR);
            CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(iDeliveryArea.getArea().getCity().getCountry(), LANG_AR);
            resultCityBean.setCountryBean(resultCountryBean);
            resultAreaBean.setCityBean(resultCityBean);
            BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(iDeliveryArea.getBranch(), LANG_AR);
            ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(iDeliveryArea.getBranch().getProvider(), LANG_AR);
            resultBranchBean.setProviderBean(resultProviderBean);
            resultBranchBean.setAreaBean(resultAreaBean);

            resultDeliveryAreaBean.setAreaBean(resultAreaBean);
            resultDeliveryAreaBean.setBranchBean(resultBranchBean);
            deliveryAreaBeanList.add(resultDeliveryAreaBean);

        }

        return deliveryAreaBeanList;
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

    public ProviderUserRepoInterface getProviderUserRepoInterface() {
        return providerUserRepoInterface;
    }

    public void setProviderUserRepoInterface(ProviderUserRepoInterface providerUserRepoInterface) {
        this.providerUserRepoInterface = providerUserRepoInterface;
    }

    public CategoryRepoInterface getCategoryRepoInterface() {
        return categoryRepoInterface;
    }

    public void setCategoryRepoInterface(CategoryRepoInterface categoryRepoInterface) {
        this.categoryRepoInterface = categoryRepoInterface;
    }

    public ProductRepoInterface getProductRepoInterface() {
        return productRepoInterface;
    }

    public void setProductRepoInterface(ProductRepoInterface productRepoInterface) {
        this.productRepoInterface = productRepoInterface;
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

  
    @Override
    @Transactional
    public ProviderBean updateProviderImage(ProviderBean providerBean) {

         // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("providerRepo", ProviderRepoInterface.class);
        Integer id = providerBean.getId();
        Provider providerEntity2 = providerRepoInterface.findById(id);

        ProviderBean resultProviderBean;
        Provider providerEntity = providerTransformer.fromBeanToEntity(providerBean); //tranforming from bean to entity 
        providerEntity2.setLogoPathEn(providerEntity.getLogoPathEn());
        providerEntity2.setHotline(providerEntity.getHotline());
        providerEntity2.setNameAr(providerEntity.getNameAr());
        providerEntity2.setNameEn(providerEntity.getNameEn());

//        Provider providerEntityCheckLogo = providerRepo.findById(providerEntity.getId());
        //   providerEntity.setLogoPathEn(providerEntity.getLogoPathEn()); //check the logo if exists let it without alteration not exist change it
        providerEntity = providerRepoInterface.update(providerEntity2); //repo that is dao add country to database

        resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(providerEntity, LANG_AR);

        return resultProviderBean;
    }

}
