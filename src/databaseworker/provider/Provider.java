/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseworker.provider;

/**
 *
 * @author 11
 */
public class Provider {
    
    private String type;
    private String provider;
    
    public Provider(){
        
    }
    
    public Provider(String type, String provider){
        this.type = type;
        this.provider = provider;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public String getProvider(){
        return provider;
    }
    
    public void setProvider(String provider){
        this.provider = provider;
    }
    
}
