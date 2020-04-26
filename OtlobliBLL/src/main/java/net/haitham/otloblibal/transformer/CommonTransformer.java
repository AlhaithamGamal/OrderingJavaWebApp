/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otloblibal.transformer;

/**
 *
 * @author HaithamGamal
 */
public interface CommonTransformer<E,B>{
     public E fromBeanToEntity(B bean);
    public B fromEntityToBean(E entity,String lang);
   
}
