/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal;

import java.util.List;

import net.haitham.otlobli.common.bean.AdminUserBean;
import net.haitham.otlobli.common.bean.AreaBean;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.CategoryBean;
import net.haitham.otlobli.common.bean.CityBean;
import net.haitham.otlobli.common.bean.ConsumerAddressBean;
import net.haitham.otlobli.common.bean.ConsumerBean;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otlobli.common.bean.DeliveryAreaBean;
import net.haitham.otlobli.common.bean.OrderBean;
import net.haitham.otlobli.common.bean.OrderItemBean;
import net.haitham.otlobli.common.bean.ProductBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otlobli.common.bean.ProviderUserBean;
import net.haitham.otloblibal.manager.AdminManager;
import net.haitham.otloblibal.manager.ConsumerManager;
import net.haitham.otloblibal.manager.LookupsManager;
import net.haitham.otloblibal.manager.OrderingManager;
import net.haitham.otloblibal.manager.ProviderManager;
import net.haitham.otloblibal.manager.impl.AdminManagerImpl;
import net.haitham.otloblibal.manager.impl.ConsumerManagerImpl;
import net.haitham.otloblibal.manager.impl.LookupsManagerImpl;
import net.haitham.otloblibal.manager.impl.OrderingManagerImpl;
import net.haitham.otloblibal.manager.impl.ProviderManagerImpl;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HaithamGamal
 */
@Component
public class OtlobliBLGateway {

    @Autowired
    private LookupsManagerImpl lookUpsManagerImpl;

    @Autowired
    private OrderingManagerImpl orderingManagerImpl;
    @Autowired
    private AdminManagerImpl adminManagerImpl;
 //   private AdminManagerImpl adminManagerImpl2 = new AdminManagerImpl();

    public ProviderBean updateProviderImage(ProviderBean provider) {
        return adminManagerImpl.updateProviderImage(provider);
    }

    public CategoryBean updateCategoryImage(CategoryBean categoryBean) {
        return providerManagerImpl.updateCategoryImage(categoryBean);
    }

    public ProductBean updateProductImage(ProductBean productBean) {
        return providerManagerImpl.updateProductImage(productBean);
    }

    @Autowired
    private ProviderManagerImpl providerManagerImpl;
    @Autowired
    private ConsumerManagerImpl consumerManagerImpl;

    public LookupsManagerImpl getLookUpsManagerImpl() {
        return lookUpsManagerImpl;
    }

    public void setLookUpsManagerImpl(LookupsManagerImpl lookUpsManagerImpl) {
        this.lookUpsManagerImpl = lookUpsManagerImpl;
    }

    public OrderingManagerImpl getOrderingManagerImpl() {
        return orderingManagerImpl;
    }

    public void setOrderingManagerImpl(OrderingManagerImpl orderingManagerImpl) {
        this.orderingManagerImpl = orderingManagerImpl;
    }

    public AdminManagerImpl getAdminManagerImpl() {
        return adminManagerImpl;
    }



    public void setAdminManager(AdminManagerImpl adminManagerImpl) {
        this.adminManagerImpl = adminManagerImpl;
    }

    public ProviderManagerImpl getProviderManagerImpl() {
        return providerManagerImpl;
    }

    public void setProviderManagerImpl(ProviderManagerImpl providerManagerImpl) {
        this.providerManagerImpl = providerManagerImpl;
    }

    public ConsumerManagerImpl getConsumerManagerImpl() {
        return consumerManagerImpl;
    }

    public void setConsumerManagerImpl(ConsumerManagerImpl consumerManagerImpl) {
        this.consumerManagerImpl = consumerManagerImpl;
    }

    public ConsumerBean addAccountConsumer(ConsumerBean consumer) {
        return consumerManagerImpl.addAccountConsumer(consumer);
    }

    public ConsumerBean updateAccountConsumer(ConsumerBean consumer) {
        return consumerManagerImpl.updateAccountConsumer(consumer);
    }

    public void deleteAccountConsumer(Integer id) {
        consumerManagerImpl.deleteAccountConsumer(id);
    }

    public ConsumerBean findAccountConsumer(Integer id) {
        return consumerManagerImpl.findAccountConsumer(id);
    }

    public ConsumerAddressBean addConsumerAddress(ConsumerAddressBean consumerAddressBean) {
        return consumerManagerImpl.addConsumerAddress(consumerAddressBean);
    }

    public ConsumerAddressBean updateConsumerAddress(ConsumerAddressBean consumerAddressBean) {
        return consumerManagerImpl.updateConsumerAddress(consumerAddressBean);
    }

    public ConsumerAddressBean findConsumerAddress(Integer id) {
        return consumerManagerImpl.findConsumerAddress(id);
    }

    public void deleteConsumerAddress(Integer id) {
        consumerManagerImpl.deleteConsumerAddress(id);
    }

    public CategoryBean addCategory(CategoryBean category) {
        return providerManagerImpl.addCategory(category);
    }

    public CategoryBean updateCategory(CategoryBean category) {
        return providerManagerImpl.updateCategory(category);
    }

    public void deleteCategory(Integer id) {
        providerManagerImpl.deleteCategory(id);
    }

    public CategoryBean findCategoryById(Integer id) {
        return providerManagerImpl.findCategoryById(id);
    }

    public List<CategoryBean> findCategories() {
        return providerManagerImpl.findCategories();
    }

    public ProductBean updateProduct(ProductBean product) {
        return providerManagerImpl.updateProduct(product);
    }

    public void deleteProduct(Integer id) {
        providerManagerImpl.deleteProduct(id);
    }

    public ProductBean findProductById(Integer id) {
        return providerManagerImpl.findProductById(id);
    }

    public List<ProductBean> findProducts() {
        return providerManagerImpl.findProducts();
    }

//deligate class for recieving bean from user biew and deliver the bean lookup manager 
    public ProviderBean addProvider(ProviderBean providerBean) {

        return adminManagerImpl.addProvider(providerBean);

    }

    public CityBean addCity(CityBean cityBean) {
        return lookUpsManagerImpl.addCity(cityBean);
    }

    public CityBean updateCity(CityBean cityBean) {
        return lookUpsManagerImpl.updateCity(cityBean);
    }

    public void deleteCity(Integer id) {
        lookUpsManagerImpl.deleteCity(id);
    }

    public List<CityBean> findCities() {
        return lookUpsManagerImpl.findCities();
    }

    public CityBean findCityById(Integer id) {
        return lookUpsManagerImpl.findCityById(id);
    }

    public AreaBean findAreaById(Integer id) {
        return lookUpsManagerImpl.findAreaById(id);
    }

    public AreaBean addArea(AreaBean areaBean) {
        return lookUpsManagerImpl.addArea(areaBean);
    }

    public AreaBean updateArea(AreaBean areaBean) {
        return lookUpsManagerImpl.updateArea(areaBean);
    }

    public void deleteArea(Integer id) {
        lookUpsManagerImpl.deleteArea(id);
    }

    public List<AreaBean> findAreas() {
        return lookUpsManagerImpl.findAreas();
    }

    public void deleteProvider(Integer id) {

        adminManagerImpl.deleteProvider(id);
    }

    public List<ProviderBean> findProviders() {

        return adminManagerImpl.findProviders();
    }

    public ProviderBean findProviderById(Integer id) {

        return adminManagerImpl.findProviderById(id);
    }

    public ProviderBean findProviderByIdWithoutBranchesOfProviderUsers(Integer id) {
        return adminManagerImpl.findProviderByIdWithoutBranchesOfProviderUsers(id);
    }

    public ProviderBean updateProvider(ProviderBean providerBean) {

        return adminManagerImpl.updateProvider(providerBean);
    }

    public CountryBean addCountry(CountryBean countryBean) {
        CountryBean returnValue = null;

        try {
            returnValue = lookUpsManagerImpl.addCountry(countryBean);

        } catch (Exception ex) {
            System.out.println("EXCEPTION 2" + ex);
        }

        return returnValue;
    }

    public List<CountryBean> findCountries() {

        return lookUpsManagerImpl.findCountries();
    }

    public AdminUserBean createAdminAccount(AdminUserBean admin) {
        return adminManagerImpl.createAdminAccount(admin);
    }

    public AdminUserBean updateAdminAccount(AdminUserBean admin) {
        return adminManagerImpl.updateAdminAccount(admin);
    }

    public void deleteAdminAccount(Integer id) {
        adminManagerImpl.deleteAdminAccount(id);
    }

    public AdminUserBean findAdminAccountBy(Integer id) {
        return adminManagerImpl.findAdminAccountBy(id);
    }

    public List<AdminUserBean> findAdminAccounts() {
        return adminManagerImpl.findAdminAccounts();
    }

    public CountryBean findCountryById(Integer id) {

        return lookUpsManagerImpl.findCountryById(id);
    }

    public void deleteCountry(Integer id) {

        lookUpsManagerImpl.deleteCountry(id);
    }

    public CountryBean updateCountry(CountryBean countryBean) {

        return lookUpsManagerImpl.updateCountry(countryBean);
    }

    public BranchBean addBranch(BranchBean branchBean) {

        return adminManagerImpl.addBranch(branchBean);
    }

    public List<ConsumerAddressBean> findConsumersAddress() {
        return consumerManagerImpl.findConsumersAddress();
    }

    public ProductBean addProduct(ProductBean product) {
        return providerManagerImpl.addProduct(product);
    }

    public List<ConsumerBean> findConsumersAccounts() {
        return consumerManagerImpl.findConsumersAccounts();
    }

    public List<BranchBean> findBranches() {
        return adminManagerImpl.findBranches();
    }

    public List<BranchBean> findbranches() {

        return adminManagerImpl.findBranches();
    }

    public BranchBean findBranchById(Integer id) {

        return adminManagerImpl.findBranchById(id);
    }

    public OrderBean createOrder(OrderBean order) {
        return orderingManagerImpl.createOrder(order);
    }

    public OrderBean updateOrder(OrderBean order) {
        return orderingManagerImpl.updateOrder(order);
    }

    public OrderBean findOrderById(Integer id) {
        return orderingManagerImpl.findOrderById(id);
    }

    public List<OrderBean> findOrders() {
        return orderingManagerImpl.findOrders();
    }

    public void deleteOrder(Integer id) {
        orderingManagerImpl.deleteOrder(id);
    }

    public OrderItemBean createOrderItem(OrderItemBean orderItem) {
        return orderingManagerImpl.createOrderItem(orderItem);
    }

    public OrderItemBean updateOrderItem(OrderItemBean orderItem) {
        return orderingManagerImpl.updateOrderItem(orderItem);
    }

    public OrderItemBean findOrderItemById(Integer id) {
        return orderingManagerImpl.findOrderItemById(id);
    }

    public List<OrderItemBean> findOrdersItems() {
        return orderingManagerImpl.findOrdersItems();
    }

    public void deleteOrderItem(Integer id) {
        orderingManagerImpl.deleteOrderItem(id);
    }

    public void deleteBranch(Integer id) {

        adminManagerImpl.deleteBranch(id);
    }

    public BranchBean updateBranch(BranchBean branchBean) {

        return adminManagerImpl.updateBranch(branchBean);
    }

    public DeliveryAreaBean addDeliveryArea(DeliveryAreaBean deliveryAreaBean) {

        return adminManagerImpl.addDeliveryArea(deliveryAreaBean);
    }

    public DeliveryAreaBean updateDeliveryArea(DeliveryAreaBean deliveryArea) {
        return adminManagerImpl.updateDeliveryArea(deliveryArea);
    }

    public void deleteDeliveryArea(Integer id) {
        adminManagerImpl.deleteDeliveryArea(id);
    }

    public DeliveryAreaBean findDeliveryAreaById(Integer id) {
        return adminManagerImpl.findDeliveryAreaById(id);
    }

    public List<DeliveryAreaBean> findDeliveryAreas() {
        return adminManagerImpl.findDeliveryAreas();
    }

    public ProviderUserBean addProviderUser(ProviderUserBean providerUser) {
        return adminManagerImpl.addProviderUser(providerUser);
    }

    public ProviderUserBean updateProviderUser(ProviderUserBean providerUser) {
        return adminManagerImpl.updateProviderUser(providerUser);
    }

    public void deleteProviderUser(Integer id) {
        adminManagerImpl.deleteProviderUser(id);
    }

    public List<ProviderUserBean> findProviderUsers() {
        return adminManagerImpl.findProviderUsers();
    }

    public ProviderUserBean findProviderUserById(Integer id) {
        return adminManagerImpl.findProviderUserById(id);
    }

}
