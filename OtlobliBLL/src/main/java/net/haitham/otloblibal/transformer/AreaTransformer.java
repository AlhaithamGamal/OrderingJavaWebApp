/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

import java.util.ArrayList;
import net.haitham.otlobli.common.bean.AreaBean;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otlobli.common.constant.OtlobliConstant;
import static net.haitham.otlobli.common.constant.OtlobliConstant.LANG_AR;
import net.haitham.otloblidal.entity.annotation.Area;
import net.haitham.otloblidal.entity.annotation.Branch;
import net.haitham.otloblidal.entity.annotation.City;
import net.haitham.otloblidal.entity.annotation.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author HaithamGamal
 */
@Component
public class AreaTransformer implements OtlobliConstant,CommonTransformer<Area,AreaBean> {
    @Autowired
    BranchTransformer branchTransformer ;
    @Autowired
    CityTransformer cityTransformer;

    public CityTransformer getCityTransformer() {
        return cityTransformer;
    }

    public void setCityTransformer(CityTransformer cityTransformer) {
        this.cityTransformer = cityTransformer;
    }

    public BranchTransformer getBranchTransformer() {
        return branchTransformer;
    }

    public void setBranchTransformer(BranchTransformer branchTransformer) {
        this.branchTransformer = branchTransformer;
    }

   
    @Override
    public Area fromBeanToEntity(AreaBean areaBean) {
        Area area = new Area();
        area.setNameAr(areaBean.getNameAr());
        area.setNameEn(areaBean.getNameEn());
       area.setId(areaBean.getId());
       City cityEntity =  cityTransformer.fromBeanToEntity(areaBean.getCityBean());
       area.setCity(cityEntity);
        return area;
    }

    @Override
    public AreaBean fromEntityToBean(Area areaEntity, String lang) {
     AreaBean areaBean = new AreaBean();
        areaBean.setNameAr(areaEntity.getNameAr());
        areaBean.setNameEn(areaEntity.getNameEn());
         areaBean.setId(areaEntity.getId());
    //Stack overflow=>     areaBean.setCityBean(new CityTransformer().fromEntityToBeanWithAreas(areaEntity.getCity(),LANG_AR));
        if (LANG_AR.equals(lang)) {

             areaBean.setName(areaEntity.getNameAr());
        } else {

            areaBean.setName(areaEntity.getNameEn());
        }
        return areaBean;    }
    
    
    
        public AreaBean fromEntityToBeanWithBranches(Area areaEntity, String lang) {
        //bean take every thing from the entity including list
     AreaBean areaBean = fromEntityToBean(areaEntity, lang);
        areaBean.setBranchList(new ArrayList<BranchBean>());
        //Edit one
        for (Branch branchEntity : areaEntity.getBranches()) {
            BranchBean branchBean = branchTransformer.fromEntityToBeanWithDeliveryAreasAndProviderUsers(branchEntity, lang);
           areaBean.getBranchList().add(branchBean);
        
        }
        return areaBean;

    }
    
}
