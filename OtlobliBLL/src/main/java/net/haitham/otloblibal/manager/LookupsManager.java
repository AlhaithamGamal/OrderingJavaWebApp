/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.manager;

import java.util.List;
import net.haitham.otlobli.common.bean.AreaBean;
import net.haitham.otlobli.common.bean.CityBean;
import net.haitham.otlobli.common.bean.CountryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author HaithamGamal
 */

public interface LookupsManager {

    // lookup module it contains methods like country,city area that doesn't change
    public CountryBean addCountry(CountryBean countryBean);

    public CountryBean updateCountry(CountryBean countryBean);

    public void deleteCountry(Integer id);

    public List<CountryBean> findCountries();

    public CountryBean findCountryById(Integer id);

    public CityBean addCity(CityBean cityBean);

    public CityBean updateCity(CityBean cityBean);

    public void deleteCity(Integer id);

    public List<CityBean> findCities();

    public CityBean findCityById(Integer id);

    public AreaBean findAreaById(Integer id);

    public AreaBean addArea(AreaBean areaBean);

    public AreaBean updateArea(AreaBean areaBean);

    public void deleteArea(Integer id );

    public List<AreaBean> findAreas();

}
