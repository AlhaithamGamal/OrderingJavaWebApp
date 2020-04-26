/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblidal.repo.aointerface;

import java.util.List;
import net.haitham.otloblidal.entity.annotation.Country;
import net.haitham.otloblidal.repo.CommonRepo;

/**
 *
 * @author HaithamGamal
 */
public interface CountryRepoInterface extends CommonRepo<Country> {

    public List<Country> findListHQL();

    public List<Country> findLikeByHQL(String keyword);

    public Country addHQL(Country country);

    public Country updateHQL(Country country);
}
