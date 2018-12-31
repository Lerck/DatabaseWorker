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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 11
 */
public class WorkerInterfaceController implements Initializable {

    @FXML
    private Button buttonStoreInsert;
    @FXML
    private Button buttonStoreUpdate;
    @FXML
    private Button buttonStoreDelete;
    @FXML
    private Button buttonStoreSave;
    @FXML
    private Button buttonStoreExtract;
    @FXML
    private Button buttonProviderInsert;
    @FXML
    private Button buttonProviderUpdate;
    @FXML
    private Button buttonProviderDelete;
    @FXML
    private Button buttonProviderSave;
    @FXML
    private Button buttonProviderExtract;
    @FXML
    private Button buttonSellerInsert;
    @FXML
    private Button buttonSellerUpdate;
    @FXML
    private Button buttonSellerDelete;
    @FXML
    private Button buttonSellerSave;
    @FXML
    private Button buttonSellerExtract;
    @FXML
    private Button buttonRegistryInsert;
    @FXML
    private Button buttonRegistryUpdate;
    @FXML
    private Button buttonRegistryDelete;
    @FXML
    private Button buttonRegistrySave;
    @FXML
    private Button buttonRegistryExtract;
    @FXML
    private TableView<Store> tableViewStore;
    @FXML
    private TableView<Provider> tableViewProvider;
    @FXML
    private TableView<Registry> tableViewRegistry;
    @FXML
    private TableView<Seller> tableViewSeller;
    @FXML
    private TableColumn<?, ?> tableColumnStoreName;
    @FXML
    private TableColumn<?, ?> tableColumnStoreType;
    @FXML
    private Button buttonStoreSelectAll;
    @FXML
    private TableColumn<?, ?> tableColumnProviderType;
    @FXML
    private TableColumn<?, ?> tableColumnProviderProvider;
    @FXML
    private Button buttonProviderSelectAll;
    @FXML
    private TableColumn<?, ?> tableColumnSellerName;
    @FXML
    private TableColumn<?, ?> tableColumnSellerCity;
    @FXML
    private TableColumn<?, ?> tableColumnSellerTitle;
    @FXML
    private TableColumn<?, ?> tableColumnSellerCost;
    @FXML
    private TableColumn<?, ?> tableColumnSellerQuantity;
    @FXML
    private Button buttonSellerSelectAll;
    @FXML
    private TableColumn<?, ?> tableColumnRegistryName;
    @FXML
    private TableColumn<?, ?> tableColumnRegistryRegistryDate;
    @FXML
    private TableColumn<?, ?> tableColumnRegistryPositionQuantity;
    @FXML
    private Button buttonRegistrySelectAll;

    private final ObservableList <Provider> providers = FXCollections.observableArrayList();    
    
    private final ObservableList <Registry> registries = FXCollections.observableArrayList();
    
    private final ObservableList <Seller> sellers = FXCollections.observableArrayList();
    
    private final ObservableList <Store> stores = FXCollections.observableArrayList();
    @FXML
    private Button buttonSelect;
    @FXML
    private TextField textFieldSelect;
    @FXML
    private TextArea textAreaSelect;
    
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
    private void buttonStoreInsertAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditorInterfaceController.class.getResource("/app_interface/EditorInterface.fxml"));
        Parent root = loader.load();
        
        // Create Stage
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        dialogStage.setTitle("Вставить элемент");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(null);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
                
        // Адресат в контроллер.
        EditorInterfaceController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.prepareInsertStore();
                
        // Отображение диалогового окна
        dialogStage.showAndWait();
    }

    @FXML
    private void buttonStoreUpdateAction(ActionEvent event) throws IOException {
        Store store = new Store();
        store = tableViewStore.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditorInterfaceController.class.getResource("/app_interface/EditorInterface.fxml"));
        Parent root = loader.load();
        
        // Create Stage
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        dialogStage.setTitle("Редактировать элемент");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(null);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
                
        // Адресат в контроллер.
        EditorInterfaceController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.prepareUpdateStore(store);
                
        // Отображение диалогового окна
        dialogStage.showAndWait();
    }

    @FXML
    private void buttonStoreDeleteAction(ActionEvent event) throws SQLException {
        Store store = new Store();
        store = tableViewStore.getSelectionModel().getSelectedItem();
        StoreDataAccessObject storeDataAccessObject = new StoreDataAccessObject();
        storeDataAccessObject.storeDeleteByKey(store);
        stores.remove(store);
    }

    @FXML
    private void buttonStoreSelectAllAction(ActionEvent event) throws SQLException {  
        stores.clear();
        ArrayList<Store> listOfStores = new ArrayList<>();
        StoreDataAccessObject storeDataAccessObject = new StoreDataAccessObject();
        listOfStores = storeDataAccessObject.storeGetAll();
        for (int i = 0; i < listOfStores.size(); i++){
            stores.add(new Store(listOfStores.get(i).getName(), listOfStores.get(i).getType()));         
        }
        tableColumnStoreName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableColumnStoreType.setCellValueFactory(new PropertyValueFactory<>("Type"));                 
        tableViewStore.setItems(stores);
    }
    
    @FXML
    private void buttonStoreSaveAction(ActionEvent event) throws SQLException {
        ArrayList<Store> listOfStores = new ArrayList<>();
        StoreDataAccessObject storeDataAccessObject = new StoreDataAccessObject();
        listOfStores = storeDataAccessObject.storeGetAll();
        storeDataAccessObject.saveFiles(listOfStores);
    }

    @FXML
    private void buttonStoreExtractAction(ActionEvent event) throws SQLException, FileNotFoundException {
        StoreDataAccessObject storeDataAccessObject = new StoreDataAccessObject();
        storeDataAccessObject.extractFiles();
    }

    @FXML
    private void buttonProviderInsertAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditorInterfaceController.class.getResource("/app_interface/EditorInterface.fxml"));
        Parent root = loader.load();
        
        // Create Stage
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        dialogStage.setTitle("Вставить элемент");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(null);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
                
        // Адресат в контроллер.
        EditorInterfaceController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.prepareInsertProvider();
                
        // Отображение диалогового окна
        dialogStage.showAndWait();
    }

    @FXML
    private void buttonProviderUpdateAction(ActionEvent event) throws IOException {
        Provider provider = new Provider();
        provider = tableViewProvider.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditorInterfaceController.class.getResource("/app_interface/EditorInterface.fxml"));
        Parent root = loader.load();
        
        // Create Stage
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        dialogStage.setTitle("Редактировать элемент");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(null);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
                
        // Адресат в контроллер.
        EditorInterfaceController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.prepareUpdateProvider(provider);
                
        // Отображение диалогового окна
        dialogStage.showAndWait();
    }

    @FXML
    private void buttonProviderDeleteAction(ActionEvent event) throws SQLException {
        Provider provider = new Provider();
        provider = tableViewProvider.getSelectionModel().getSelectedItem();
        ProviderDataAccessObject providerDataAccessObject = new ProviderDataAccessObject();
        providerDataAccessObject.providerDeleteByKey(provider);
        providers.remove(provider);
    }
    
    @FXML
    private void buttonProviderSelectAllAction(ActionEvent event) throws SQLException {
        providers.clear();
        ArrayList<Provider> listOfProviders = new ArrayList<>();
        ProviderDataAccessObject providerDataAccessObject = new ProviderDataAccessObject();
        listOfProviders = providerDataAccessObject.providerGetAll();
        for (int i = 0; i < listOfProviders.size(); i++){
            providers.add(new Provider(listOfProviders.get(i).getType(), listOfProviders.get(i).getProvider()));        
        }
        tableColumnProviderType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        tableColumnProviderProvider.setCellValueFactory(new PropertyValueFactory<>("Provider"));                 
        tableViewProvider.setItems(providers);
    }

    @FXML
    private void buttonProviderSaveAction(ActionEvent event) throws SQLException {
        ArrayList<Provider> listOfProviders = new ArrayList<>();
        ProviderDataAccessObject providerDataAccessObject = new ProviderDataAccessObject();
        listOfProviders = providerDataAccessObject.providerGetAll();
        providerDataAccessObject.saveFiles(listOfProviders);
    }

    @FXML
    private void buttonProviderExtractAction(ActionEvent event) throws SQLException, FileNotFoundException{
        ProviderDataAccessObject providerDataAccessObject = new ProviderDataAccessObject();
        providerDataAccessObject.extractFiles();
    }

    @FXML
    private void buttonSellerInsertAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditorInterfaceController.class.getResource("/app_interface/EditorInterface.fxml"));
        Parent root = loader.load();
        
        // Create Stage
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        dialogStage.setTitle("Вставить элемент");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(null);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
                
        // Адресат в контроллер.
        EditorInterfaceController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.prepareInsertSeller();
                
        // Отображение диалогового окна
        dialogStage.showAndWait();
    }

    @FXML
    private void buttonSellerUpdateAction(ActionEvent event) throws IOException {
        Seller seller = new Seller();
        seller = tableViewSeller.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditorInterfaceController.class.getResource("/app_interface/EditorInterface.fxml"));
        Parent root = loader.load();
        
        // Create Stage
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        dialogStage.setTitle("Редактировать элемент");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(null);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
                
        // Адресат в контроллер.
        EditorInterfaceController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.prepareUpdateSeller(seller);
                
        // Отображение диалогового окна
        dialogStage.showAndWait();
    }

    @FXML
    private void buttonSellerDeleteAction(ActionEvent event) throws SQLException {
        Seller seller = new Seller();
        seller = tableViewSeller.getSelectionModel().getSelectedItem();
        SellerDataAccessObject sellerDataAccessObject = new SellerDataAccessObject();
        sellerDataAccessObject.sellerDeleteByKey(seller);
        sellers.remove(seller);
    }

    @FXML
    private void buttonSellerSelectAllAction(ActionEvent event) throws SQLException {
        sellers.clear();
        ArrayList<Seller> listOfSellers = new ArrayList<>();
        SellerDataAccessObject sellerDataAccessObject = new SellerDataAccessObject();
        listOfSellers = sellerDataAccessObject.sellerGetAll();
        for (int i = 0; i < listOfSellers.size(); i++){
            sellers.add(new Seller(listOfSellers.get(i).getName(), listOfSellers.get(i).getCity(), listOfSellers.get(i).getTitle(), listOfSellers.get(i).getCost(), listOfSellers.get(i).getQuantity()));        
        }
        tableColumnSellerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableColumnSellerCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        tableColumnSellerTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));     
        tableColumnSellerCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));     
        tableColumnSellerQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));     
        tableViewSeller.setItems(sellers);  
    }
    
    @FXML
    private void buttonSellerSaveAction(ActionEvent event) throws SQLException {
        ArrayList<Seller> listOfSellers = new ArrayList<>();
        SellerDataAccessObject sellerDataAccessObject = new SellerDataAccessObject();
        listOfSellers = sellerDataAccessObject.sellerGetAll();
        sellerDataAccessObject.saveFiles(listOfSellers);
    }

    @FXML
    private void buttonSellerExtractAction(ActionEvent event) throws SQLException, FileNotFoundException {
        SellerDataAccessObject sellerDataAccessObject = new SellerDataAccessObject();
        sellerDataAccessObject.extractFiles();
    }

    @FXML
    private void buttonRegistryInsertAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditorInterfaceController.class.getResource("/app_interface/EditorInterface.fxml"));
        Parent root = loader.load();
        
        // Create Stage
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        dialogStage.setTitle("Редактировать элемент");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(null);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
                
        // Адресат в контроллер.
        EditorInterfaceController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.prepareInsertRegistry();
                
        // Отображение диалогового окна
        dialogStage.showAndWait();
    }

    @FXML
    private void buttonRegistryUpdateAction(ActionEvent event) throws IOException {
        Registry registry = new Registry();
        registry = tableViewRegistry.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditorInterfaceController.class.getResource("/app_interface/EditorInterface.fxml"));
        Parent root = loader.load();
        
        // Create Stage
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        dialogStage.setTitle("Редактировать элемент");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(null);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
                
        // Адресат в контроллер.
        EditorInterfaceController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.prepareUpdateRegistry(registry);
                
        // Отображение диалогового окна
        dialogStage.showAndWait();
    }

    @FXML
    private void buttonRegistryDeleteAction(ActionEvent event) throws SQLException {
        Registry registry = new Registry();
        registry = tableViewRegistry.getSelectionModel().getSelectedItem();
        RegistryDataAccessObject registryDataAccessObject = new RegistryDataAccessObject();
        registryDataAccessObject.registryDeleteByKey(registry);
        registries.remove(registry);
    }

    @FXML
    private void buttonRegistrySaveAction(ActionEvent event) throws SQLException {
        ArrayList<Registry> listOfRegistries = new ArrayList<>();
        RegistryDataAccessObject registryDataAccessObject = new RegistryDataAccessObject();
        listOfRegistries = registryDataAccessObject.registryGetAll();
        registryDataAccessObject.saveFiles(listOfRegistries);
    }

    @FXML
    private void buttonRegistryExtractAction(ActionEvent event) throws SQLException, FileNotFoundException {
        RegistryDataAccessObject registryDataAccessObject = new RegistryDataAccessObject();
        registryDataAccessObject.extractFiles();
    }
    
    @FXML
    private void buttonRegistrySelectAllAction(ActionEvent event) throws SQLException {
        registries.clear();
        ArrayList<Registry> listOfRegistries = new ArrayList<>();
        RegistryDataAccessObject registryDataAccessObject = new RegistryDataAccessObject();
        listOfRegistries = registryDataAccessObject.registryGetAll();
        for (int i = 0; i < listOfRegistries.size(); i++){
            registries.add(new Registry(listOfRegistries.get(i).getName(), listOfRegistries.get(i).getRegistryDate(), listOfRegistries.get(i).getPositionQuantity()));        
        }
        tableColumnRegistryName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableColumnRegistryRegistryDate.setCellValueFactory(new PropertyValueFactory<>("RegistryDate"));
        tableColumnRegistryPositionQuantity.setCellValueFactory(new PropertyValueFactory<>("PositionQuantity"));   
        tableViewRegistry.setItems(registries);
    }

    @FXML
    private void buttonSelectAction(ActionEvent event) throws SQLException {
        textAreaSelect.clear();
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(textFieldSelect.getText())) {
                while (rs.next()){
                    ResultSetMetaData rsmd = rs.getMetaData();
                    for(int i = 0; i < rsmd.getColumnCount(); i++){
                        textAreaSelect.setText(textAreaSelect.getText() + " " + rs.getString(i+1));
                    }
                    textAreaSelect.setText(textAreaSelect.getText() + "\n");
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    
}
