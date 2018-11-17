/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseworker;

import databaseworker.provider.ProviderDataAccessObject;
import databaseworker.registry.RegistryDataAccessObject;
import databaseworker.seller.SellerDataAccessObject;
import databaseworker.store.StoreDataAccessObject;
import java.io.IOException;
import java.sql.SQLException;
/**
 *
 * @author 11
 */
public class DatabaseWorker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        // TODO code application logic here
        ProviderDataAccessObject providerDataAccessObject = new ProviderDataAccessObject();
        providerDataAccessObject.extractFiles();
        System.out.println("Таблица 'Поставщик' загружена успешно!");
        
        RegistryDataAccessObject registryDataAccessObject = new RegistryDataAccessObject();
        registryDataAccessObject.extractFiles();
        System.out.println("Таблица 'Регистрация' загружена успешно!");
        
        SellerDataAccessObject sellerDataAccessObject = new SellerDataAccessObject();
        sellerDataAccessObject.extractFiles();
        System.out.println("Таблица 'Продавец' загружена успешно!");
        
        StoreDataAccessObject storeDataAccessObject = new StoreDataAccessObject();
        storeDataAccessObject.extractFiles();
        System.out.println("Таблица 'Магазин' загружена успешно!");
        
        System.out.println("Все таблицы успешно загружены!");
        
    }

    
}
