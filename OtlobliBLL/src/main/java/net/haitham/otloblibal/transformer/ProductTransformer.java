/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import java.util.ArrayList;
import net.haitham.otlobli.common.bean.CategoryBean;
import net.haitham.otlobli.common.bean.OrderItemBean;
import net.haitham.otlobli.common.bean.ProductBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import net.haitham.otloblidal.entity.annotation.Category;
import net.haitham.otloblidal.entity.annotation.OrderItem;
import net.haitham.otloblidal.entity.annotation.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public class ProductTransformer implements OtlobliConstant, CommonTransformer<Product, ProductBean> {
    @Autowired
    CategoryTransformer categoryTransformer;
    @Autowired
    OrderItemTransformer orderItemTransformer;

    public CategoryTransformer getCategoryTransformer() {
        return categoryTransformer;
    }

    public void setCategoryTransformer(CategoryTransformer categoryTransformer) {
        this.categoryTransformer = categoryTransformer;
    }

    public OrderItemTransformer getOrderItemTransformer() {
        return orderItemTransformer;
    }

    public void setOrderItemTransformer(OrderItemTransformer orderItemTransformer) {
        this.orderItemTransformer = orderItemTransformer;
    }

    @Override
    public Product fromBeanToEntity(ProductBean productBean) {
        Product product = new Product();
        String imagePath = "";
        try {
            imagePath = productBean.getImagePath();
        } catch (Exception e) {
            System.out.println("EXCEPTION IS :" + e);

        }
        System.out.println("EN IS LOGOOOOOOOOOOO" + imagePath);

        product.setNameAr(productBean.getNameAr());
        product.setNameEn(productBean.getNameEn());
        product.setActive(productBean.getActive());
        product.setPrice(productBean.getPrice());
        product.setDescriptionAr(productBean.getDescriptionAr());
        product.setDescriptionEn(productBean.getDescriptionEn());
        product.setId(productBean.getId());
        Category categoryEntity = categoryTransformer.fromBeanToEntity(productBean.getCategoryBean());
        product.setCategory(categoryEntity);
        if (imagePath != null) {

            product.setImagePath(productBean.getImagePath());
        }
        return product;
    }

    @Override
    public ProductBean fromEntityToBean(Product productEntity, String lang) {
        ProductBean productBean = new ProductBean();
        productBean.setNameAr(productEntity.getNameAr());
        productBean.setNameEn(productEntity.getNameEn());
        productBean.setActive(productEntity.getActive());
        productBean.setPrice(productEntity.getPrice());
        productBean.setDescriptionAr(productEntity.getDescriptionAr());
        productBean.setDescriptionEn(productEntity.getDescriptionEn());
        productBean.setId(productEntity.getId());
        productBean.setImagePath(productEntity.getImagePath());
        if (LANG_AR.equals(lang)) {

            productBean.setName(productEntity.getNameAr());
        } else {

            productBean.setName(productEntity.getNameEn());
        }
        return productBean;
    }

    public ProductBean fromEntityToBeanWithOrderItems(Product productEntity, String lang) {

        ProductBean productBean = fromEntityToBean(productEntity, lang);
        productBean.setOrderItemListBeans(new ArrayList<OrderItemBean>());
        //Edit-4
        for (OrderItem orderItemEntity : productEntity.getOrderItems()) {
            OrderItemBean orderItemBean = orderItemTransformer.fromEntityToBean(orderItemEntity, lang);
            productBean.getOrderItemListBeans().add(orderItemBean);

        }
        return productBean;
    }
}
