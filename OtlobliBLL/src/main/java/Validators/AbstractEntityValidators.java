/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validators;

/**
 *
 * @author HaithamGamal
 */
 public abstract class AbstractEntityValidators<E> implements CommonValidators<E> {
    private Class<E> entityType; 

    public AbstractEntityValidators() {
    }

    
    public AbstractEntityValidators(Class<E> entityType) {
        this.entityType = entityType;
    }

     
    @Override
    public boolean validateEntity(E entity) {
        //Taking entity as a parameter and comparing it to another entity in database 
        //like taking entity of employee and comparing its salary to min and max salary in the department table that has relation with it
        //before inserting into database 
        return false;
    }
    
    
}
