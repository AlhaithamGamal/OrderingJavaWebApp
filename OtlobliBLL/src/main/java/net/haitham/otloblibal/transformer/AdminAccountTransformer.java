/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import java.util.ArrayList;
import net.haitham.otlobli.common.bean.AdminUserBean;
import net.haitham.otlobli.common.bean.CityBean;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import static net.haitham.otlobli.common.constant.OtlobliConstant.LANG_AR;
import net.haitham.otloblidal.entity.annotation.AdminUser;
import net.haitham.otloblidal.entity.annotation.City;
import net.haitham.otloblidal.entity.annotation.Country;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public class AdminAccountTransformer implements CommonTransformer<AdminUser, AdminUserBean> {

    @Override
    public AdminUser fromBeanToEntity(AdminUserBean adminUserBean) {
        AdminUser adminUserEntity = new AdminUser();
        adminUserEntity.setUsername(adminUserBean.getUsername());
        adminUserEntity.setEmail(adminUserBean.getEmail());
        adminUserEntity.setPassword(adminUserBean.getPassword());
        adminUserEntity.setOriginalPassword(adminUserBean.getOriginalPassword());
        adminUserEntity.setActive(adminUserBean.getActive());

        adminUserEntity.setHash(adminUserBean.getHash());
        adminUserBean.setId(adminUserBean.getId());

        return adminUserEntity;
    }

    @Override
    public AdminUserBean fromEntityToBean(AdminUser adminUserEntity, String lang) {

        AdminUserBean adminUserBean = new AdminUserBean();
        adminUserBean.setUsername(adminUserEntity.getUsername());
        adminUserBean.setEmail(adminUserEntity.getEmail());
        adminUserBean.setPassword(adminUserEntity.getPassword());
        adminUserBean.setOriginalPassword(adminUserEntity.getOriginalPassword());
        adminUserBean.setId(adminUserEntity.getId());
        adminUserBean.setActive(adminUserEntity.getActive());
        adminUserBean.setHash(adminUserEntity.getHash());

        return adminUserBean;
    }

}
