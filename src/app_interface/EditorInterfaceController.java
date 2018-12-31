/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_interface;

import databaseworker.provider.Provider;
import databaseworker.provider.ProviderDataAccessObject;
import databaseworker.registry.Registry;
import databaseworker.registry.RegistryDataAccessObject;
import databaseworker.seller.Seller;
import databaseworker.seller.SellerDataAccessObject;
import databaseworker.store.Store;
import databaseworker.store.StoreDataAccessObject;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 11
 */
public class EditorInterfaceController implements Initializable {

    @FXML
    private Label labelFirst;
    @FXML
    private Label labelSecond;
    @FXML
    private Label labelThird;
    @FXML
    private Label labelFourth;
    @FXML
    private Label labelFifth;
    @FXML
    private TextField textFieldFirst;
    @FXML
    private TextField textFieldSecond;
    @FXML
    private TextField textFieldThird;
    @FXML
    private TextField textFieldFourth;
    @FXML
    private TextField textFieldFifth;
    @FXML
    private Button buttonQuit;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonAdd;

    private Stage dialogStage;
    
    private Provider provider = new Provider();   
    private Registry registry = new Registry();
    private Seller seller = new Seller();
    private Store store = new Store();
    
    private Integer state;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buttonQuitAction(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    private void buttonAddAction(ActionEvent event) throws SQLException {
        switch(state){
            
            case 1:
                System.out.print(textFieldFirst.getText());
                System.out.print(textFieldSecond.getText());
                store.setName(textFieldFirst.getText());
                store.setType(textFieldSecond.getText());
                StoreDataAccessObject storeDAO = new StoreDataAccessObject();
                storeDAO.storeInsert(store);
                dialogStage.close();
                break;
                
            case 2:
                provider.setType(textFieldFirst.getText());
                provider.setProvider(textFieldSecond.getText());
                ProviderDataAccessObject providerDAO = new ProviderDataAccessObject();
                providerDAO.providerInsert(provider);
                dialogStage.close();
                break;
                
            case 3:
                seller.setName(textFieldFirst.getText());
                seller.setCity(textFieldSecond.getText());
                seller.setTitle(textFieldThird.getText());
                seller.setCost(Integer.parseInt(textFieldFourth.getText()));
                seller.setQuantity(Integer.parseInt(textFieldFifth.getText()));
                SellerDataAccessObject sellerDAO = new SellerDataAccessObject();
                sellerDAO.sellerInsert(seller);
                dialogStage.close();
                break;
            
            case 4:
                registry.setName(textFieldFirst.getText());
                registry.setRegistryDate(Date.valueOf(textFieldSecond.getText()));
                registry.setPositionQuantity(Integer.parseInt(textFieldThird.getText()));
                RegistryDataAccessObject registryDAO = new RegistryDataAccessObject();
                registryDAO.registryInsert(registry);
                dialogStage.close();
                break;
        }
    }

    @FXML
    private void buttonSaveAction(ActionEvent event) throws SQLException {
        switch(state){
            
            case 1:
                store.setName(textFieldFirst.getText());
                store.setType(textFieldSecond.getText());
                StoreDataAccessObject storeDAO = new StoreDataAccessObject();
                storeDAO.storeUpdate(store);
                dialogStage.close();
                break;
                
            case 2:
                provider.setType(textFieldFirst.getText());
                provider.setProvider(textFieldSecond.getText());
                ProviderDataAccessObject providerDAO = new ProviderDataAccessObject();
                providerDAO.providerUpdate(provider);
                dialogStage.close();
                break;
                
            case 3:
                seller.setName(textFieldFirst.getText());
                seller.setCity(textFieldSecond.getText());
                seller.setTitle(textFieldThird.getText());
                seller.setCost(Integer.parseInt(textFieldFourth.getText()));
                seller.setQuantity(Integer.parseInt(textFieldFifth.getText()));
                SellerDataAccessObject sellerDAO = new SellerDataAccessObject();
                sellerDAO.sellerUpdate(seller);
                dialogStage.close();
                break;
                
            case 4:
                registry.setName(textFieldFirst.getText());
                registry.setRegistryDate(Date.valueOf(textFieldSecond.getText()));
                registry.setPositionQuantity(Integer.parseInt(textFieldThird.getText()));
                RegistryDataAccessObject registryDAO = new RegistryDataAccessObject();
                registryDAO.registryUpdate(registry);
                dialogStage.close();
                break;
        }
    }
    
     @FXML
    private void handleDeny(ActionEvent event) {
        dialogStage.close();
    }
    
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    
    public void prepareInsertStore(){
        
        labelFirst.setVisible(true);
        labelFirst.setText("Название");
        textFieldFirst.setVisible(true);
        textFieldFirst.setText("");
        textFieldFirst.setEditable(true);
        
        labelSecond.setVisible(true);
        labelSecond.setText("Тип");
        textFieldSecond.setVisible(true);
        textFieldSecond.setText("");
        textFieldSecond.setEditable(true);
        
        labelThird.setVisible(false);
        textFieldThird.setVisible(false);
        
        labelFourth.setVisible(false);
        textFieldFourth.setVisible(false);
        
        labelFifth.setVisible(false);
        textFieldFifth.setVisible(false);
        
        buttonAdd.setVisible(true);
        buttonSave.setVisible(false);
        
        state = 1;
    }
    
    public void prepareUpdateStore(Store store){
        
        labelFirst.setVisible(true);
        labelFirst.setText("Название");
        textFieldFirst.setVisible(true);
        textFieldFirst.setText(store.getName());
        textFieldFirst.setEditable(false);
        
        labelSecond.setVisible(true);
        labelSecond.setText("Тип");
        textFieldSecond.setVisible(true);
        textFieldSecond.setText(store.getType());
        textFieldSecond.setEditable(true);
        
        labelThird.setVisible(false);
        textFieldThird.setVisible(false);
        
        labelFourth.setVisible(false);
        textFieldFourth.setVisible(false);
        
        labelFifth.setVisible(false);
        textFieldFifth.setVisible(false);
        
        buttonAdd.setVisible(false);
        buttonSave.setVisible(true);
        
        state = 1;
    }
    
    public void prepareInsertProvider(){
        
        labelFirst.setVisible(true);
        labelFirst.setText("Тип");
        textFieldFirst.setVisible(true);
        textFieldFirst.setText("");
        textFieldFirst.setEditable(true);
        
        labelSecond.setVisible(true);
        labelSecond.setText("Поставщик");
        textFieldSecond.setVisible(true);
        textFieldSecond.setText("");
        textFieldSecond.setEditable(true);
        
        labelThird.setVisible(false);
        textFieldThird.setVisible(false);
        
        labelFourth.setVisible(false);
        textFieldFourth.setVisible(false);
        
        labelFifth.setVisible(false);
        textFieldFifth.setVisible(false);
        
        buttonAdd.setVisible(true);
        buttonSave.setVisible(false);
        
        state = 2;
    }
    
    public void prepareUpdateProvider(Provider provider){
        
        labelFirst.setVisible(true);
        labelFirst.setText("Тип");
        textFieldFirst.setVisible(true);
        textFieldFirst.setText(provider.getType());
        textFieldFirst.setEditable(false);
        
        labelSecond.setVisible(true);
        labelSecond.setText("Поставщик");
        textFieldSecond.setVisible(true);
        textFieldSecond.setText(provider.getProvider());
        textFieldSecond.setEditable(true);
        
        labelThird.setVisible(false);
        textFieldThird.setVisible(false);
        
        labelFourth.setVisible(false);
        textFieldFourth.setVisible(false);
        
        labelFifth.setVisible(false);
        textFieldFifth.setVisible(false);
        
        buttonAdd.setVisible(true);
        buttonSave.setVisible(false);
        
        state = 2;
    }
    
    public void prepareInsertSeller(){
        
        labelFirst.setVisible(true);
        labelFirst.setText("Имя");
        textFieldFirst.setVisible(true);
        textFieldFirst.setText("");
        textFieldFirst.setEditable(true);
        
        labelSecond.setVisible(true);
        labelSecond.setText("Город");
        textFieldSecond.setVisible(true);
        textFieldSecond.setText("");
        textFieldSecond.setEditable(true);
        
        labelThird.setVisible(true);
        labelThird.setText("Название");
        textFieldThird.setVisible(true);
        textFieldThird.setText("");
        textFieldThird.setEditable(true);
        
        labelFourth.setVisible(true);
        labelFourth.setText("Цена");
        textFieldFourth.setVisible(true);
        textFieldFourth.setText("");
        
        labelFifth.setVisible(true);
        labelFifth.setText("Количество");
        textFieldFifth.setVisible(true);
        textFieldFifth.setText("");
        
        buttonAdd.setVisible(true);
        buttonSave.setVisible(false);
        
        state = 3;
    }
    
    public void prepareUpdateSeller(Seller seller){
        
        labelFirst.setVisible(true);
        labelFirst.setText("Имя");
        textFieldFirst.setVisible(true);
        textFieldFirst.setText("");
        textFieldFirst.setEditable(false);
        
        labelSecond.setVisible(true);
        labelSecond.setText("Город");
        textFieldSecond.setVisible(true);
        textFieldSecond.setText("");
        textFieldSecond.setEditable(false);
        
        labelThird.setVisible(true);
        labelThird.setText("Название");
        textFieldThird.setVisible(true);
        textFieldThird.setText("");
        textFieldThird.setEditable(false);
        
        labelFourth.setVisible(true);
        labelFourth.setText("Цена");
        textFieldFourth.setVisible(true);
        textFieldFourth.setText(seller.getCost().toString());
        
        labelFifth.setVisible(true);
        labelFifth.setText("Количество");
        textFieldFifth.setVisible(true);
        textFieldFifth.setText(seller.getQuantity().toString());
        
        buttonAdd.setVisible(true);
        buttonSave.setVisible(false);
        
        state = 3;
    }
    
    public void prepareInsertRegistry(){
        
        labelFirst.setVisible(true);
        labelFirst.setText("Имя");
        textFieldFirst.setVisible(true);
        textFieldFirst.setText("");
        textFieldFirst.setEditable(true);
        
        labelSecond.setVisible(true);
        labelSecond.setText("Дата регистрации");
        textFieldSecond.setVisible(true);
        textFieldSecond.setText("");
        textFieldSecond.setEditable(true);
        
        labelThird.setVisible(true);
        labelThird.setText("Кол-во позиций");
        textFieldThird.setVisible(true);
        textFieldThird.setText("");
        textFieldThird.setEditable(true);
        
        labelFourth.setVisible(false);
        textFieldFourth.setVisible(false);
        
        labelFifth.setVisible(false);
        textFieldFifth.setVisible(false);
        
        buttonAdd.setVisible(true);
        buttonSave.setVisible(false);
        
        state = 4;
    }
    
    public void prepareUpdateRegistry(Registry registry){
        
        labelFirst.setVisible(true);
        labelFirst.setText("Имя");
        textFieldFirst.setVisible(true);
        textFieldFirst.setText(registry.getName());
        textFieldFirst.setEditable(false);
        
        labelSecond.setVisible(true);
        labelSecond.setText("Дата регистрации");
        textFieldSecond.setVisible(true);
        textFieldSecond.setText(registry.getRegistryDate().toString());
        textFieldSecond.setEditable(true);
        
        labelThird.setVisible(true);
        labelThird.setText("Кол-во позиций");
        textFieldThird.setVisible(true);
        textFieldThird.setText(registry.getPositionQuantity().toString());
        textFieldThird.setEditable(true);
        
        labelFourth.setVisible(false);
        textFieldFourth.setVisible(false);
        
        labelFifth.setVisible(false);
        textFieldFifth.setVisible(false);
        
        buttonAdd.setVisible(false);
        buttonSave.setVisible(true);
        
        state = 4;
    }
    
}
