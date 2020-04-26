/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.manager;

import java.util.List;
import net.haitham.otlobli.common.bean.AdminUserBean;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.CategoryBean;
import net.haitham.otlobli.common.bean.ConsumerAddressBean;
import net.haitham.otlobli.common.bean.ConsumerBean;
import net.haitham.otlobli.common.bean.DeliveryAreaBean;
import net.haitham.otlobli.common.bean.ProductBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otlobli.common.bean.ProviderUserBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public interface AdminManager {

    public AdminUserBean createAdminAccount(AdminUserBean admin);

    public AdminUserBean updateAdminAccount(AdminUserBean admin);

    public void deleteAdminAccount(Integer id);

    public AdminUserBean findAdminAccountBy(Integer id);

    public List<AdminUserBean> findAdminAccounts();



    public ProviderUserBean addProviderUser(ProviderUserBean providerUser);

    public ProviderUserBean updateProviderUser(ProviderUserBean providerUser);

    public void deleteProviderUser(Integer id);

    public List<ProviderUserBean> findProviderUsers();

    public ProviderUserBean findProviderUserById(Integer id);

    public ProviderBean addProvider(ProviderBean provider);

    public ProviderBean updateProvider(ProviderBean provider);
        public ProviderBean updateProviderImage(ProviderBean provider);

    public void deleteProvider(Integer id);

    public List<ProviderBean> findProviders();

    public ProviderBean findProviderById(Integer id);

    public ProviderBean findProviderByIdWithoutBranchesOfProviderUsers(Integer id);

    public BranchBean addBranch(BranchBean branch);

    public BranchBean updateBranch(BranchBean branch);

    public void deleteBranch(Integer id);

    public List<BranchBean> findBranches();

    public BranchBean findBranchById(Integer id);

    public DeliveryAreaBean addDeliveryArea(DeliveryAreaBean deliveryArea);

    public DeliveryAreaBean updateDeliveryArea(DeliveryAreaBean deliveryArea);

    public void deleteDeliveryArea(Integer id);

    public DeliveryAreaBean findDeliveryAreaById(Integer id);

    public List<DeliveryAreaBean> findDeliveryAreas();


}
