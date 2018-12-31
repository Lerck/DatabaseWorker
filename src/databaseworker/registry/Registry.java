/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseworker.registry;

import java.sql.Date;

/**
 *
 * @author 11
 */
public class Registry {
    
    private String name;
    private Date registryDate;
    private Integer positionQuantity;
    
    public Registry(){
        
    }
    
    public Registry(String name, Date registryDate, Integer positionQuantity){
        this.name = name;
        this.registryDate = registryDate;
        this.positionQuantity = positionQuantity;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Date getRegistryDate(){
        return registryDate;
    }
    
    public void setRegistryDate(Date registryDate){
        this.registryDate = registryDate;
    }
    
    public Integer getPositionQuantity(){
        return positionQuantity;
    }
    
    public void setPositionQuantity(Integer positionQuantity){
        this.positionQuantity = positionQuantity;
    }
    
}
