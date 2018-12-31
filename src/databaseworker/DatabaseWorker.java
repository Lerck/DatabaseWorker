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
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author 11
 */
public class DatabaseWorker extends Application {

    @Override
    public void start(Stage stage) throws Exception {   ///
        Parent root = FXMLLoader.load(getClass().getResource("/app_interface/WorkerInterface.fxml"));
        stage.setTitle("DatabaseWorker");
        stage.setResizable(false);
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.io.IOException
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
        
        /*
            ПЛАНЫ:
            1. Организовать Initializer соединения с БД. (DECLINE)
            2. Построить GUI с помощью JavaFX. (DONE)
            3. Связать имеющиеся запросы с GUI через контроллеры. (DONE)
            3,1. Адаптировать ввод запросов.
            4. Протестировать работу запросов.
            5. Организовать команды по выгрузке данных из таблицы и загрузки данных в таблицу. (DONE)
            6. Организовать механизм работы SELECT.
            7. Финальное тестирование.
            8. Сдача работы.
        */
        
        launch(args);
        
    }

    
}
