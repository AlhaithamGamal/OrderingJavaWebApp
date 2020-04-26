/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otloblibal.OtlobliBLGateway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HaithamGamal
 */

public class Main {

    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
        // TODO code application logic here
        try{
        ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
        OtlobliBLGateway gateway = (OtlobliBLGateway) appCon.getBean("otlobliBLGateway",OtlobliBLGateway.class);
        CountryBean countryBean = new CountryBean();
        countryBean.setNameEn("South Korea");
        countryBean.setNameAr("كوريا الجنوبيه");
        CountryBean countryBeanR = gateway.addCountry(countryBean);
        System.out.println("Inserted"+countryBeanR.getNameEn());
        }
        catch(Exception ex){
            System.out.println("EXCEPTION EX"+ex);
        }
        }
    }

