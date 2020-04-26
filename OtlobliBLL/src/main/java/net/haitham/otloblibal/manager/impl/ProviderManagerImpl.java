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
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otlobli.common.bean.DeliveryAreaBean;
import net.haitham.otlobli.common.bean.OrderBean;
import net.haitham.otlobli.common.bean.ProductBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otlobli.common.bean.ProviderUserBean;
import static net.haitham.otlobli.common.constant.OtlobliConstant.LANG_AR;
import net.haitham.otloblibal.manager.ProviderManager;
import net.haitham.otloblibal.transformer.AreaTransformer;
import net.haitham.otloblibal.transformer.BranchTransformer;
import net.haitham.otloblibal.transformer.CategoryTransformer;
import net.haitham.otloblibal.transformer.CityTransformer;
import net.haitham.otloblibal.transformer.CountryTransformer;
import net.haitham.otloblibal.transformer.DeliveryAreaTransformer;
import net.haitham.otloblibal.transformer.ProductTransformer;
import net.haitham.otloblibal.transformer.ProviderTransformer;
import net.haitham.otloblibal.transformer.ProviderUserTransformer;
import static net.haitham.otloblidal.HibernateUtilMe.beginTransaction;
import static net.haitham.otloblidal.HibernateUtilMe.commitTransaction;
import static net.haitham.otloblidal.HibernateUtilMe.openSession;
import net.haitham.otloblidal.entity.annotation.Branch;
import net.haitham.otloblidal.entity.annotation.Category;
import net.haitham.otloblidal.entity.annotation.DeliveryArea;
import net.haitham.otloblidal.entity.annotation.Product;
import net.haitham.otloblidal.entity.annotation.ProviderUser;
import net.haitham.otloblidal.repo.BranchRepo;
import net.haitham.otloblidal.repo.CategoryRepo;
import net.haitham.otloblidal.repo.DeliveryAreaRepo;
import net.haitham.otloblidal.repo.ProductRepo;
import net.haitham.otloblidal.repo.ProviderUserRepo;
import net.haitham.otloblidal.repo.aointerface.BranchRepoInterface;
import net.haitham.otloblidal.repo.aointerface.CategoryRepoInterface;
import net.haitham.otloblidal.repo.aointerface.DeliveryAreaRepoInterface;
import net.haitham.otloblidal.repo.aointerface.OrderItemRepoInterface;
import net.haitham.otloblidal.repo.aointerface.ProductRepoInterface;
import net.haitham.otloblidal.repo.aointerface.ProviderUserRepoInterface;
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
public class ProviderManagerImpl implements ProviderManager {

    @Autowired
    private CategoryTransformer categoryTransformer;
    @Autowired
    private ProductTransformer productTransformer;
    @Autowired
    private ProviderTransformer providerTransformer;
    @Autowired
    private BranchTransformer branchTransformer;
    @Autowired
    private CategoryRepoInterface categoryRepoInterface;
    @Autowired
    private ProductRepoInterface productRepoInterface;
    @Autowired
    private BranchRepoInterface branchRepoInterface;
    @Autowired
    private DeliveryAreaRepoInterface deliveryAreaRepoInterface;
    @Autowired
    private ProviderUserRepoInterface providerUserRepoInterface;

    @Override
    @Transactional
    public CategoryBean addCategory(CategoryBean categoryBean) {
      //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("categoryRepo", CategoryRepoInterface.class);

        Category categoryEntity = categoryTransformer.fromBeanToEntity(categoryBean); //tranforming from bean to entity 

        categoryEntity = categoryRepoInterface.add(categoryEntity); //repo that is dao add country to database
        CategoryBean resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(categoryEntity, LANG_AR);

        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(categoryEntity.getProvider(), LANG_AR);
        resultCategoryBean.setProviderBean(resultProviderBean);

        return resultCategoryBean;
    }

    @Override
    @Transactional
    public CategoryBean updateCategory(CategoryBean categoryBean) {
        // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("categoryRepo", CategoryRepoInterface.class);

        Category categoryEntity = categoryTransformer.fromBeanToEntity(categoryBean); //tranforming from bean to entity 

        Integer id = categoryBean.getId();
        Category categoryEntity2 = categoryRepoInterface.findById(id);

 //check the logo if exists let it without alteration not exist change it
if(categoryEntity2.getImagePath() != null){
        categoryEntity2.setImagePath(categoryEntity2.getImagePath()); //to avoid access null obj if image not found

}
        //  cityEntity2.setAreas(cityEntity.getAreas());
        // cityEntity2.setId(cityEntity.getId());
        categoryEntity2.setNameAr(categoryEntity.getNameAr());
        categoryEntity2.setNameEn(categoryEntity.getNameEn());
        categoryEntity2.setDescriptionAr(categoryEntity.getDescriptionAr());
        categoryEntity2.setDescriptionEn(categoryEntity.getDescriptionEn());

        categoryEntity2.setProvider(categoryEntity.getProvider());
        categoryEntity = categoryRepoInterface.update(categoryEntity2); //repo that is dao add country to database
        CategoryBean resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(categoryEntity, LANG_AR);

        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(categoryEntity.getProvider(), LANG_AR);
        resultCategoryBean.setProviderBean(resultProviderBean);

        return resultCategoryBean;
    }

    @Override
    @Transactional
    public void deleteCategory(Integer id) {
        // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("categoryRepo", CategoryRepoInterface.class);

        categoryRepoInterface.delete(id);

    }

    @Override
    @Transactional
    public CategoryBean findCategoryById(Integer id) {
 //ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("categoryRepo", CategoryRepoInterface.class);
        Category category = categoryRepoInterface.findById(id); //repo that is dao add country to database
//bind relation 1-many in bean bind cityBean with areas
        CategoryBean resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(category, LANG_AR);
        //bind the relations to the parent many-1 binding area tp country
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(category.getProvider(), LANG_AR);
        resultCategoryBean.setProviderBean(resultProviderBean);

        return resultCategoryBean;
    }

    @Override
    @Transactional
    public List<CategoryBean> findCategories() {
       //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("categoryRepo", CategoryRepoInterface.class);

        CategoryBean resultCategoryBean;
        List<Category> categoryList = categoryRepoInterface.findList(); //repo that is dao add country to database
        List<CategoryBean> categoryBeanList = new ArrayList<>();
        for (Category iCategory : categoryList) {
            resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(iCategory, LANG_AR);
            ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(iCategory.getProvider(), LANG_AR);
            resultCategoryBean.setProviderBean(resultProviderBean);
            categoryBeanList.add(resultCategoryBean);

        }

        return categoryBeanList;
    }

    @Override
    @Transactional
    public ProductBean addProduct(ProductBean productBean) {
         //ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("productRepo", ProductRepoInterface.class);

        Product productEntity = productTransformer.fromBeanToEntity(productBean); //tranforming from bean to entity 

        productEntity = productRepoInterface.add(productEntity); //repo that is dao add country to database
        ProductBean resultProductBean = productTransformer.fromEntityToBeanWithOrderItems(productEntity, LANG_AR);

        CategoryBean resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(productEntity.getCategory(), LANG_AR);
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(productEntity.getCategory().getProvider(), LANG_AR);
        resultCategoryBean.setProviderBean(resultProviderBean);
        resultProductBean.setCategoryBean(resultCategoryBean);

        return resultProductBean;
    }

    @Override
    @Transactional
    public ProductBean updateProduct(ProductBean productBean) {
                // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("productRepo", ProductRepoInterface.class);

        Product productEntity = productTransformer.fromBeanToEntity(productBean); //tranforming from bean to entity 

        Integer id = productBean.getId();
        Product productEntity2 = productRepoInterface.findById(id);
           if(productEntity2.getImagePath() != null){
        productEntity2.setImagePath(productEntity2.getImagePath());
           }
        //check the logo if exists let it without alteration not exist change it

        //  cityEntity2.setAreas(cityEntity.getAreas());
        // cityEntity2.setId(cityEntity.getId());
        String nameAr = productEntity.getNameAr();
        productEntity2.setNameAr(productEntity.getNameAr());
        productEntity2.setNameEn(productEntity.getNameEn());
        productEntity2.setDescriptionAr(productEntity.getDescriptionAr());
        productEntity2.setDescriptionEn(productEntity.getDescriptionEn());
        productEntity2.setActive(productEntity.getActive());
        productEntity2.setPrice(productEntity.getPrice());

        productEntity2.setCategory(productEntity.getCategory());
        productEntity = productRepoInterface.update(productEntity2); //repo that is dao add country to database
        ProductBean resultProductBean = productTransformer.fromEntityToBeanWithOrderItems(productEntity, LANG_AR);

        CategoryBean resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(productEntity.getCategory(), LANG_AR);
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(productEntity.getCategory().getProvider(), LANG_AR);
        resultCategoryBean.setProviderBean(resultProviderBean);
        resultProductBean.setCategoryBean(resultCategoryBean);

        return resultProductBean;
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
                // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("productRepo", ProductRepoInterface.class);

        productRepoInterface.delete(id);

    }

    @Override
    @Transactional
    public ProductBean findProductById(Integer id) {
             //    ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("productRepo", ProductRepoInterface.class);

        Product product = productRepoInterface.findById(id); //repo that is dao add country to database
//bind relation 1-many in bean bind cityBean with areas
        ProductBean resultProductBean = productTransformer.fromEntityToBeanWithOrderItems(product, LANG_AR);
        //bind the relations to the parent many-1 binding area tp country
        CategoryBean resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(product.getCategory(), LANG_AR);
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(product.getCategory().getProvider(), LANG_AR);
        resultCategoryBean.setProviderBean(resultProviderBean);
        resultProductBean.setCategoryBean(resultCategoryBean);

        return resultProductBean;
    }

    @Override
    @Transactional
    public List<ProductBean> findProducts() {
         //        ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("productRepo", ProductRepoInterface.class);

        ProductBean resultProductBean;
        List<Product> productList = productRepoInterface.findList(); //repo that is dao add country to database
        List<ProductBean> productBeanList = new ArrayList<>();
        for (Product iProduct : productList) {
            resultProductBean = productTransformer.fromEntityToBeanWithOrderItems(iProduct, LANG_AR);
            CategoryBean resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(iProduct.getCategory(), LANG_AR);
            ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(iProduct.getCategory().getProvider(), LANG_AR);
            resultCategoryBean.setProviderBean(resultProviderBean);
            resultProductBean.setCategoryBean(resultCategoryBean);
            productBeanList.add(resultProductBean);

        }

        return productBeanList;
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

    @Override
     @Transactional
    public CategoryBean updateCategoryImage(CategoryBean categoryBean) {
         Category categoryEntity = categoryTransformer.fromBeanToEntity(categoryBean); //tranforming from bean to entity 

        Integer id = categoryBean.getId();
        Category categoryEntity2 = categoryRepoInterface.findById(id);

        categoryEntity2.setImagePath(categoryEntity.getImagePath()); //check the logo if exists let it without alteration not exist change it

        //  cityEntity2.setAreas(cityEntity.getAreas());
        // cityEntity2.setId(cityEntity.getId());
        categoryEntity2.setNameAr(categoryEntity.getNameAr());
        categoryEntity2.setNameEn(categoryEntity.getNameEn());
        categoryEntity2.setDescriptionAr(categoryEntity.getDescriptionAr());
        categoryEntity2.setDescriptionEn(categoryEntity.getDescriptionEn());

        categoryEntity2.setProvider(categoryEntity.getProvider());
        categoryEntity = categoryRepoInterface.update(categoryEntity2); //repo that is dao add country to database
        CategoryBean resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(categoryEntity, LANG_AR);

        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(categoryEntity.getProvider(), LANG_AR);
        resultCategoryBean.setProviderBean(resultProviderBean);

        return resultCategoryBean;
    }

    @Override
     @Transactional
    public ProductBean updateProductImage(ProductBean productBean) {
        Product productEntity = productTransformer.fromBeanToEntity(productBean); //tranforming from bean to entity 

        Integer id = productBean.getId();
        Product productEntity2 = productRepoInterface.findById(id);

        productEntity2.setImagePath(productEntity.getImagePath()); //check the logo if exists let it without alteration not exist change it

        //  cityEntity2.setAreas(cityEntity.getAreas());
        // cityEntity2.setId(cityEntity.getId());
        String nameAr = productEntity.getNameAr();
        productEntity2.setNameAr(productEntity.getNameAr());
        productEntity2.setNameEn(productEntity.getNameEn());
        productEntity2.setDescriptionAr(productEntity.getDescriptionAr());
        productEntity2.setDescriptionEn(productEntity.getDescriptionEn());
        productEntity2.setActive(productEntity.getActive());
        productEntity2.setPrice(productEntity.getPrice());

        productEntity2.setCategory(productEntity.getCategory());
        productEntity = productRepoInterface.update(productEntity2); //repo that is dao add country to database
        ProductBean resultProductBean = productTransformer.fromEntityToBeanWithOrderItems(productEntity, LANG_AR);

        CategoryBean resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(productEntity.getCategory(), LANG_AR);
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(productEntity.getCategory().getProvider(), LANG_AR);
        resultCategoryBean.setProviderBean(resultProviderBean);
        resultProductBean.setCategoryBean(resultCategoryBean);

        return resultProductBean;
    }

}
