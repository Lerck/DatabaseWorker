/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseworker.seller;

/**
 *
 * @author 11
 */
public class Seller {
    
    private String name;
    private String city;
    private String title;
    private Integer cost;
    private Integer quantity;
    
    public Seller(){
        
    }
    
    public Seller(String name, String city, String title, Integer cost, Integer quantity){
        this.name = name;
        this.city = city;
        this.title = title;
        this.cost = cost;
        this.quantity = quantity;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getCity(){
        return city;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public Integer getCost(){
        return cost;
    }
    
    public void setCost(Integer cost){
        this.cost = cost;
    }
    
    public Integer getQuantity(){
        return quantity;
    }
    
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }
    
}
