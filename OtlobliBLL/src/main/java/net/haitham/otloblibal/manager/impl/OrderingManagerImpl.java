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
import net.haitham.otlobli.common.bean.OrderBean;
import net.haitham.otlobli.common.bean.OrderItemBean;
import net.haitham.otlobli.common.bean.ProductBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import static net.haitham.otlobli.common.constant.OtlobliConstant.LANG_AR;
import net.haitham.otloblibal.manager.OrderingManager;
import net.haitham.otloblibal.transformer.AreaTransformer;
import net.haitham.otloblibal.transformer.BranchTransformer;
import net.haitham.otloblibal.transformer.CategoryTransformer;
import net.haitham.otloblibal.transformer.CityTransformer;
import net.haitham.otloblibal.transformer.ConsumerAddressTransformer;
import net.haitham.otloblibal.transformer.ConsumerTransformer;
import net.haitham.otloblibal.transformer.CountryTransformer;
import net.haitham.otloblibal.transformer.OrderItemTransformer;
import net.haitham.otloblibal.transformer.OrderTransformer;
import net.haitham.otloblibal.transformer.ProductTransformer;
import net.haitham.otloblibal.transformer.ProviderTransformer;
import static net.haitham.otloblidal.HibernateUtilMe.beginTransaction;
import static net.haitham.otloblidal.HibernateUtilMe.commitTransaction;
import static net.haitham.otloblidal.HibernateUtilMe.openSession;
import net.haitham.otloblidal.entity.annotation.Order;
import net.haitham.otloblidal.entity.annotation.OrderItem;
import net.haitham.otloblidal.entity.annotation.Product;
import net.haitham.otloblidal.repo.OrderItemRepo;
import net.haitham.otloblidal.repo.OrderRepo;
import net.haitham.otloblidal.repo.aointerface.CountryRepoInterface;
import net.haitham.otloblidal.repo.aointerface.OrderItemRepoInterface;
import net.haitham.otloblidal.repo.aointerface.OrderRepoInterface;
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
public class OrderingManagerImpl implements OrderingManager {

    @Autowired
    private OrderRepoInterface orderRepoInterface;
   @Autowired
    private OrderItemRepoInterface orderItemRepoInterface;
    @Autowired
    private OrderTransformer orderTransformer;
    @Autowired
    private OrderItemTransformer orderItemTransformer;
    @Autowired
    private CountryTransformer countryTransformer;
    @Autowired
    private CityTransformer cityTransformer;
    @Autowired
    private ProviderTransformer providerTransformer;
    @Autowired
    private AreaTransformer areaTransformer;
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

    @Override
    @Transactional
    public OrderBean createOrder(OrderBean orderBean) {
       //  ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
       //appCon.getBean("orderRepo", OrderRepoInterface.class);

        Order orderEntity = orderTransformer.fromBeanToEntity(orderBean); //tranforming from bean to entity 

        orderEntity = orderRepoInterface.add(orderEntity); //repo that is dao add country to database
        OrderBean resultOrderBean = orderTransformer.fromEntityToBeanWithOrderItems(orderEntity, LANG_AR);

        BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(orderEntity.getBranch(), LANG_AR);
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(orderEntity.getBranch().getProvider(), LANG_AR);
        resultBranchBean.setProviderBean(resultProviderBean);
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(orderEntity.getArea(), LANG_AR);
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(orderEntity.getArea().getCity(), LANG_AR);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(orderEntity.getArea().getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultAreaBean.setCityBean(resultCityBean);
        ConsumerBean resultConsumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(orderEntity.getConsumer(), LANG_AR);
        ConsumerAddressBean resultConsumerAddressBean = consumerAddressTransformer.fromEntityToBean(orderEntity.getConsumerAddress(), LANG_AR);
        resultConsumerAddressBean.setConsumer(resultConsumerBean);
        resultOrderBean.setAreaBean(resultAreaBean);
        resultOrderBean.setBranchBean(resultBranchBean);
        resultOrderBean.setConsumerAddressBean(resultConsumerAddressBean);
        resultOrderBean.setConsumerBean(resultConsumerBean);

        return resultOrderBean;

    }

    @Override
    @Transactional
    public OrderBean updateOrder(OrderBean orderBean) {
     //    ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
      //appCon.getBean("orderRepo", OrderRepoInterface.class);

        Order orderEntity = orderTransformer.fromBeanToEntity(orderBean); //tranforming from bean to entity 

        Integer id = orderBean.getId();
        Order orderEntity2 = orderRepoInterface.findById(id);
        //  cityEntity2.setAreas(cityEntity.getAreas());
        // cityEntity2.setId(cityEntity.getId());
        orderEntity2.setArea(orderEntity.getArea());
        orderEntity2.setBranch(orderEntity.getBranch());
        orderEntity2.setConsumer(orderEntity.getConsumer());
        orderEntity2.setConsumerAddress(orderEntity.getConsumerAddress());
        orderEntity2.setDeliveryFees(orderEntity.getDeliveryFees());
        orderEntity2.setOrderedAt(orderEntity.getOrderedAt());
        orderEntity2.setStatus(orderEntity.getStatus());

        orderEntity = orderRepoInterface.update(orderEntity2); //repo that is dao add country to database
        OrderBean resultOrderBean = orderTransformer.fromEntityToBeanWithOrderItems(orderEntity, LANG_AR);

        BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(orderEntity.getBranch(), LANG_AR);
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(orderEntity.getBranch().getProvider(), LANG_AR);
        resultBranchBean.setProviderBean(resultProviderBean);
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(orderEntity.getArea(), LANG_AR);
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(orderEntity.getArea().getCity(), LANG_AR);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(orderEntity.getArea().getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultAreaBean.setCityBean(resultCityBean);
        ConsumerBean resultConsumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(orderEntity.getConsumer(), LANG_AR);
        ConsumerAddressBean resultConsumerAddressBean = consumerAddressTransformer.fromEntityToBean(orderEntity.getConsumerAddress(), LANG_AR);
        resultConsumerAddressBean.setConsumer(resultConsumerBean);
        resultOrderBean.setAreaBean(resultAreaBean);
        resultOrderBean.setBranchBean(resultBranchBean);
        resultOrderBean.setConsumerAddressBean(resultConsumerAddressBean);
        resultOrderBean.setConsumerBean(resultConsumerBean);

        return resultOrderBean;
    }

    @Override
    @Transactional
    public OrderBean findOrderById(Integer id) {
      //   ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
       //appCon.getBean("orderRepo", OrderRepoInterface.class);

        Order orderEntity = orderRepoInterface.findById(id); //repo that is dao add country to database
//bind relation 1-many in bean bind cityBean with areas
        OrderBean resultOrderBean = orderTransformer.fromEntityToBeanWithOrderItems(orderEntity, LANG_AR);

        BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(orderEntity.getBranch(), LANG_AR);
        ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(orderEntity.getBranch().getProvider(), LANG_AR);
        resultBranchBean.setProviderBean(resultProviderBean);
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(orderEntity.getArea(), LANG_AR);
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(orderEntity.getArea().getCity(), LANG_AR);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(orderEntity.getArea().getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultAreaBean.setCityBean(resultCityBean);
        ConsumerBean resultConsumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(orderEntity.getConsumer(), LANG_AR);
        ConsumerAddressBean resultConsumerAddressBean = consumerAddressTransformer.fromEntityToBean(orderEntity.getConsumerAddress(), LANG_AR);
        resultConsumerAddressBean.setConsumer(resultConsumerBean);
        resultOrderBean.setAreaBean(resultAreaBean);
        resultOrderBean.setBranchBean(resultBranchBean);
        resultOrderBean.setConsumerAddressBean(resultConsumerAddressBean);
        resultOrderBean.setConsumerBean(resultConsumerBean);

        return resultOrderBean;
    }

    @Override
    @Transactional
    public List<OrderBean> findOrders() {
         //ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
       //appCon.getBean("orderRepo", OrderRepoInterface.class);

        OrderBean resultOrderBean;
        List<Order> orderList = orderRepoInterface.findList(); //repo that is dao add country to database
        List<OrderBean> orderBeanList = new ArrayList<>();
        for (Order orderEntity : orderList) {
            resultOrderBean = orderTransformer.fromEntityToBeanWithOrderItems(orderEntity, LANG_AR);

            BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(orderEntity.getBranch(), LANG_AR);
            ProviderBean resultProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(orderEntity.getBranch().getProvider(), LANG_AR);
            resultBranchBean.setProviderBean(resultProviderBean);
            AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(orderEntity.getArea(), LANG_AR);
            CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(orderEntity.getArea().getCity(), LANG_AR);
            CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(orderEntity.getArea().getCity().getCountry(), LANG_AR);
            resultCityBean.setCountryBean(resultCountryBean);
            resultAreaBean.setCityBean(resultCityBean);
            ConsumerBean resultConsumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(orderEntity.getConsumer(), LANG_AR);
            ConsumerAddressBean resultConsumerAddressBean = consumerAddressTransformer.fromEntityToBean(orderEntity.getConsumerAddress(), LANG_AR);
            resultConsumerAddressBean.setConsumer(resultConsumerBean);
            resultOrderBean.setAreaBean(resultAreaBean);
            resultOrderBean.setBranchBean(resultBranchBean);
            resultOrderBean.setConsumerAddressBean(resultConsumerAddressBean);
            resultOrderBean.setConsumerBean(resultConsumerBean);
            orderBeanList.add(resultOrderBean);

        }

        return orderBeanList;
    }

    @Override
    @Transactional
    public void deleteOrder(Integer id) {
         //ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
      //appCon.getBean("orderRepo", OrderRepoInterface.class);

        orderRepoInterface.delete(id);

    }

    @Override
    @Transactional
    public OrderItemBean createOrderItem(OrderItemBean orderItemBean) {
   //      ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
       //appCon.getBean("orderItemRepo", OrderItemRepoInterface.class);

        OrderItem orderItemEntity = orderItemTransformer.fromBeanToEntity(orderItemBean); //tranforming from bean to entity 

        orderItemEntity = orderItemRepoInterface.add(orderItemEntity); //repo that is dao add country to database
        OrderItemBean resultOrderItemBean = orderItemTransformer.fromEntityToBean(orderItemEntity, LANG_AR);
        OrderBean resultOrderBean = orderTransformer.fromEntityToBeanWithOrderItems(orderItemEntity.getOrder(), LANG_AR);
        ProviderBean resulProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(orderItemEntity.getProduct().getCategory().getProvider(), LANG_AR);
        BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(orderItemEntity.getOrder().getBranch(), LANG_AR);
        ProviderBean resultProviderBeanFromBranch = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(orderItemEntity.getOrder().getBranch().getProvider(), LANG_AR);
        resultBranchBean.setProviderBean(resultProviderBeanFromBranch);
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(orderItemEntity.getOrder().getArea(), LANG_AR);
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(orderItemEntity.getOrder().getArea().getCity(), LANG_AR);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(orderItemEntity.getOrder().getArea().getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultAreaBean.setCityBean(resultCityBean);
        ConsumerBean resultConsumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(orderItemEntity.getOrder().getConsumer(), LANG_AR);
        ConsumerAddressBean resultConsumerAddressBean = consumerAddressTransformer.fromEntityToBean(orderItemEntity.getOrder().getConsumerAddress(), LANG_AR);
        resultConsumerAddressBean.setConsumer(resultConsumerBean);
        resultOrderBean.setAreaBean(resultAreaBean);
        resultOrderBean.setBranchBean(resultBranchBean);
        resultOrderBean.setConsumerAddressBean(resultConsumerAddressBean);
        resultOrderBean.setConsumerBean(resultConsumerBean);
        CategoryBean resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(orderItemEntity.getProduct().getCategory(), LANG_AR);
        resultCategoryBean.setProviderBean(resulProviderBean);
        ProductBean resultProductBean = productTransformer.fromEntityToBeanWithOrderItems(orderItemEntity.getProduct(), LANG_AR);
        resultProductBean.setCategoryBean(resultCategoryBean);
        resultOrderItemBean.setProductBean(resultProductBean);
        resultOrderItemBean.setOrderBean(resultOrderBean);

        return resultOrderItemBean;
    }

    @Override
    @Transactional
    public OrderItemBean updateOrderItem(OrderItemBean orderItemBean) {
        //ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
      //appCon.getBean("orderItemRepo", OrderItemRepoInterface.class);

        OrderItem orderItemEntity = orderItemTransformer.fromBeanToEntity(orderItemBean); //tranforming from bean to entity 

        Integer id = orderItemBean.getId();
        OrderItem orderItemEntity2 = orderItemRepoInterface.findById(id);
        //  cityEntity2.setAreas(cityEntity.getAreas());
        // cityEntity2.setId(cityEntity.getId());
        orderItemEntity2.setPrice(orderItemEntity.getPrice());
        orderItemEntity2.setQuantity(orderItemEntity.getQuantity());
        orderItemEntity2.setOrder(orderItemEntity.getOrder());
        orderItemEntity2.setProduct(orderItemEntity.getProduct());
        orderItemEntity = orderItemRepoInterface.update(orderItemEntity2); //repo that is dao add country to database
        OrderItemBean resultOrderItemBean = orderItemTransformer.fromEntityToBean(orderItemEntity, LANG_AR);
        OrderBean resultOrderBean = orderTransformer.fromEntityToBeanWithOrderItems(orderItemEntity.getOrder(), LANG_AR);
        ProviderBean resulProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(orderItemEntity.getProduct().getCategory().getProvider(), LANG_AR);
        BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(orderItemEntity.getOrder().getBranch(), LANG_AR);
        ProviderBean resultProviderBeanFromBranch = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(orderItemEntity.getOrder().getBranch().getProvider(), LANG_AR);
        resultBranchBean.setProviderBean(resultProviderBeanFromBranch);
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(orderItemEntity.getOrder().getArea(), LANG_AR);
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(orderItemEntity.getOrder().getArea().getCity(), LANG_AR);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(orderItemEntity.getOrder().getArea().getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultAreaBean.setCityBean(resultCityBean);
        ConsumerBean resultConsumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(orderItemEntity.getOrder().getConsumer(), LANG_AR);
        ConsumerAddressBean resultConsumerAddressBean = consumerAddressTransformer.fromEntityToBean(orderItemEntity.getOrder().getConsumerAddress(), LANG_AR);
        resultConsumerAddressBean.setConsumer(resultConsumerBean);
        resultOrderBean.setAreaBean(resultAreaBean);
        resultOrderBean.setBranchBean(resultBranchBean);
        resultOrderBean.setConsumerAddressBean(resultConsumerAddressBean);
        resultOrderBean.setConsumerBean(resultConsumerBean);
        CategoryBean resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(orderItemEntity.getProduct().getCategory(), LANG_AR);
        resultCategoryBean.setProviderBean(resulProviderBean);
        ProductBean resultProductBean = productTransformer.fromEntityToBeanWithOrderItems(orderItemEntity.getProduct(), LANG_AR);
        resultProductBean.setCategoryBean(resultCategoryBean);
        resultOrderItemBean.setProductBean(resultProductBean);
        resultOrderItemBean.setOrderBean(resultOrderBean);

        return resultOrderItemBean;
    }

    @Override
    @Transactional
    public OrderItemBean findOrderItemById(Integer id) {
       // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        //appCon.getBean("orderItemRepo", OrderItemRepoInterface.class);

        OrderItem orderItemEntity = orderItemRepoInterface.findById(id); //repo that is dao add country to database
//bind relation 1-many in bean bind cityBean with areas
        OrderItemBean resultOrderItemBean = orderItemTransformer.fromEntityToBean(orderItemEntity, LANG_AR);
        OrderBean resultOrderBean = orderTransformer.fromEntityToBeanWithOrderItems(orderItemEntity.getOrder(), LANG_AR);
        ProviderBean resulProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(orderItemEntity.getProduct().getCategory().getProvider(), LANG_AR);
        BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(orderItemEntity.getOrder().getBranch(), LANG_AR);
        ProviderBean resultProviderBeanFromBranch = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(orderItemEntity.getOrder().getBranch().getProvider(), LANG_AR);
        resultBranchBean.setProviderBean(resultProviderBeanFromBranch);
        AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(orderItemEntity.getOrder().getArea(), LANG_AR);
        CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(orderItemEntity.getOrder().getArea().getCity(), LANG_AR);
        CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(orderItemEntity.getOrder().getArea().getCity().getCountry(), LANG_AR);
        resultCityBean.setCountryBean(resultCountryBean);
        resultAreaBean.setCityBean(resultCityBean);
        ConsumerBean resultConsumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(orderItemEntity.getOrder().getConsumer(), LANG_AR);
        ConsumerAddressBean resultConsumerAddressBean = consumerAddressTransformer.fromEntityToBean(orderItemEntity.getOrder().getConsumerAddress(), LANG_AR);
        resultConsumerAddressBean.setConsumer(resultConsumerBean);
        resultOrderBean.setAreaBean(resultAreaBean);
        resultOrderBean.setBranchBean(resultBranchBean);
        resultOrderBean.setConsumerAddressBean(resultConsumerAddressBean);
        resultOrderBean.setConsumerBean(resultConsumerBean);
        CategoryBean resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(orderItemEntity.getProduct().getCategory(), LANG_AR);
        resultCategoryBean.setProviderBean(resulProviderBean);
        ProductBean resultProductBean = productTransformer.fromEntityToBeanWithOrderItems(orderItemEntity.getProduct(), LANG_AR);
        resultProductBean.setCategoryBean(resultCategoryBean);
        resultOrderItemBean.setProductBean(resultProductBean);
        resultOrderItemBean.setOrderBean(resultOrderBean);

        return resultOrderItemBean;
    }

    @Override
    @Transactional
    public List<OrderItemBean> findOrdersItems() {
       // ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
       //appCon.getBean("orderItemRepo", OrderItemRepoInterface.class);

        OrderItemBean resultOrderItemBean;
        List<OrderItem> orderItemList = orderItemRepoInterface.findList(); //repo that is dao add country to database
        List<OrderItemBean> resultOrderItemBeanList = new ArrayList<>();
        for (OrderItem orderItemEntity : orderItemList) {
            resultOrderItemBean = orderItemTransformer.fromEntityToBean(orderItemEntity, LANG_AR);
            OrderBean resultOrderBean = orderTransformer.fromEntityToBeanWithOrderItems(orderItemEntity.getOrder(), LANG_AR);
            ProviderBean resulProviderBean = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(orderItemEntity.getProduct().getCategory().getProvider(), LANG_AR);
            BranchBean resultBranchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(orderItemEntity.getOrder().getBranch(), LANG_AR);
            ProviderBean resultProviderBeanFromBranch = providerTransformer.fromEntityToBeanWithBranchesAndProviderUsers(orderItemEntity.getOrder().getBranch().getProvider(), LANG_AR);
            resultBranchBean.setProviderBean(resultProviderBeanFromBranch);
            AreaBean resultAreaBean = areaTransformer.fromEntityToBeanWithBranches(orderItemEntity.getOrder().getArea(), LANG_AR);
            CityBean resultCityBean = cityTransformer.fromEntityToBeanWithAreas(orderItemEntity.getOrder().getArea().getCity(), LANG_AR);
            CountryBean resultCountryBean = countryTransformer.fromEntityToBeanWithCities(orderItemEntity.getOrder().getArea().getCity().getCountry(), LANG_AR);
            resultCityBean.setCountryBean(resultCountryBean);
            resultAreaBean.setCityBean(resultCityBean);
            ConsumerBean resultConsumerBean = consumerTransformer.fromEntityToBeanWithConsumerAddress(orderItemEntity.getOrder().getConsumer(), LANG_AR);
            ConsumerAddressBean resultConsumerAddressBean = consumerAddressTransformer.fromEntityToBean(orderItemEntity.getOrder().getConsumerAddress(), LANG_AR);
            resultConsumerAddressBean.setConsumer(resultConsumerBean);
            resultOrderBean.setAreaBean(resultAreaBean);
            resultOrderBean.setBranchBean(resultBranchBean);
            resultOrderBean.setConsumerAddressBean(resultConsumerAddressBean);
            resultOrderBean.setConsumerBean(resultConsumerBean);
            CategoryBean resultCategoryBean = categoryTransformer.fromEntityToBeanWithProducts(orderItemEntity.getProduct().getCategory(), LANG_AR);
            resultCategoryBean.setProviderBean(resulProviderBean);
            ProductBean resultProductBean = productTransformer.fromEntityToBeanWithOrderItems(orderItemEntity.getProduct(), LANG_AR);
            resultProductBean.setCategoryBean(resultCategoryBean);
            resultOrderItemBean.setProductBean(resultProductBean);
            resultOrderItemBean.setOrderBean(resultOrderBean);
            resultOrderItemBeanList.add(resultOrderItemBean);

        }

        return resultOrderItemBeanList;
    }

    @Override
    @Transactional
    public void deleteOrderItem(Integer id) {
     //   ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
       //appCon.getBean("orderItemRepo", OrderItemRepoInterface.class);

        orderItemRepoInterface.delete(id);

    }

  

    public OrderTransformer getOrderTransformer() {
        return orderTransformer;
    }

    public void setOrderTransformer(OrderTransformer orderTransformer) {
        this.orderTransformer = orderTransformer;
    }

    public OrderItemTransformer getOrderItemTransformer() {
        return orderItemTransformer;
    }

    public void setOrderItemTransformer(OrderItemTransformer orderItemTransformer) {
        this.orderItemTransformer = orderItemTransformer;
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

    public OrderRepoInterface getOrderRepoInterface() {
        return orderRepoInterface;
    }

    public void setOrderRepoInterface(OrderRepoInterface orderRepoInterface) {
        this.orderRepoInterface = orderRepoInterface;
    }

    public OrderItemRepoInterface getOrderItemRepoInterface() {
        return orderItemRepoInterface;
    }

    public void setOrderItemRepoInterface(OrderItemRepoInterface orderItemRepoInterface) {
        this.orderItemRepoInterface = orderItemRepoInterface;
    }

}
