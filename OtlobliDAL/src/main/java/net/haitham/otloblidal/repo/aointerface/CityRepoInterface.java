/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblidal.repo.aointerface;

import java.util.List;
import net.haitham.otloblidal.entity.annotation.City;
import net.haitham.otloblidal.repo.CommonRepo;

/**
 *
 * @author HaithamGamal
 */
public interface CityRepoInterface extends CommonRepo<City> {
    public List<Object[]> group();
    
}
