/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.manager;

import java.util.List;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.CategoryBean;
import net.haitham.otlobli.common.bean.DeliveryAreaBean;
import net.haitham.otlobli.common.bean.OrderBean;
import net.haitham.otlobli.common.bean.ProductBean;
import net.haitham.otlobli.common.bean.ProviderUserBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public interface ProviderManager {



    public CategoryBean addCategory(CategoryBean category);

    public CategoryBean updateCategory(CategoryBean category);
    public CategoryBean updateCategoryImage(CategoryBean category);

    public void deleteCategory(Integer id);

    public CategoryBean findCategoryById(Integer id);

    public List<CategoryBean> findCategories();

    public ProductBean addProduct(ProductBean product);

    public ProductBean updateProduct(ProductBean product);
    public ProductBean updateProductImage(ProductBean product);

    public void deleteProduct(Integer id);

    public ProductBean findProductById(Integer id);

    public List<ProductBean> findProducts();

  

}
