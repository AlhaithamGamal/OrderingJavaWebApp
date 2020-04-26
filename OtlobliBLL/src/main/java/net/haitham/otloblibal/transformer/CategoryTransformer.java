/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import java.util.ArrayList;
import net.haitham.otlobli.common.bean.CategoryBean;
import net.haitham.otlobli.common.bean.ProductBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import net.haitham.otloblidal.entity.annotation.Category;
import net.haitham.otloblidal.entity.annotation.Product;
import net.haitham.otloblidal.entity.annotation.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public class CategoryTransformer implements OtlobliConstant, CommonTransformer<Category, CategoryBean> {
    @Autowired
ProviderTransformer providerTransformer;
    @Autowired
ProductTransformer productTransformer;

    public ProviderTransformer getProviderTransformer() {
        return providerTransformer;
    }

    public void setProviderTransformer(ProviderTransformer providerTransformer) {
        this.providerTransformer = providerTransformer;
    }

    public ProductTransformer getProductTransformer() {
        return productTransformer;
    }

    public void setProductTransformer(ProductTransformer productTransformer) {
        this.productTransformer = productTransformer;
    }
  
    @Override
    public Category fromBeanToEntity(CategoryBean categoryBean) {
        Category category = new Category();
        String imagePath = "";
        try {
            imagePath = categoryBean.getImagePath();
        } catch (Exception e) {
            System.out.println("EXCEPTION IS :" + e);

        }
        System.out.println("EN IS LOGOOOOOOOOOOO" + imagePath);

        category.setNameAr(categoryBean.getNameAr());
        category.setNameEn(categoryBean.getNameEn());
        category.setDescriptionAr(categoryBean.getDescriptionAr());
        category.setDescriptionEn(categoryBean.getDescriptionEn());
        category.setId(categoryBean.getId());
        Provider providerEntity = providerTransformer.fromBeanToEntity(categoryBean.getProviderBean());
        category.setProvider(providerEntity);
        if (imagePath != null) {

            category.setImagePath(categoryBean.getImagePath());
        }
        return category;

    }

    @Override
    public CategoryBean fromEntityToBean(Category categoryEntity, String lang) {
        CategoryBean categoryBean = new CategoryBean();
        categoryBean.setNameAr(categoryEntity.getNameAr());
        categoryBean.setNameEn(categoryEntity.getNameEn());
        categoryBean.setDescriptionAr(categoryEntity.getDescriptionAr());
        categoryBean.setDescriptionEn(categoryEntity.getDescriptionEn());
        categoryBean.setId(categoryEntity.getId());
        categoryBean.setImagePath(categoryEntity.getImagePath());
        if (LANG_AR.equals(lang)) {

            categoryBean.setName(categoryEntity.getNameAr());
        } else {

            categoryBean.setName(categoryEntity.getNameEn());
        }
        return categoryBean;
    }

    public CategoryBean fromEntityToBeanWithProducts(Category categoryEntity, String lang) {

        CategoryBean categoryBean = fromEntityToBean(categoryEntity, lang);
        categoryBean.setProductBeanList(new ArrayList<ProductBean>());
        //Edit-4
        for (Product productEntity : categoryEntity.getProducts()) {
            ProductBean productBean =  productTransformer.fromEntityToBeanWithOrderItems(productEntity, lang);
            categoryBean.getProductBeanList().add(productBean);

        }
        return categoryBean;
    }
}
