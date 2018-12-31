/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseworker.store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 11
 */
public class StoreDataAccessObject {
    
    public StoreDataAccessObject(){
        
    }
    
    public void storeInsert(Store store) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("INSERT INTO Магазин (Название, Тип) values (?, ?)")) {
                stmt.setString(1, store.getName());
                stmt.setString(2, store.getType());
                stmt.execute();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void storeUpdate(Store store) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("UPDATE Магазин SET (Название, Тип) = (?, ?) WHERE Название = ?")) {
                stmt.setString(1, store.getName());
                stmt.setString(2, store.getType());
                stmt.setString(3, store.getName());
                stmt.execute();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void storeDeleteByKey(Store store) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("DELETE From Магазин WHERE Название = ?")) {
                stmt.setString(1, store.getName());
                stmt.execute();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Store> storeGetAll() throws SQLException{
        ArrayList<Store> listOfStores = new ArrayList<>();       
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Магазин")) {
                while (rs.next()){
                    Store store = new Store();
                    store.setName(rs.getString("Название"));
                    store.setType(rs.getString("Тип"));
                    listOfStores.add(store);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfStores;
    }
    
    public ArrayList<Store> storeGetByKey(Store storeStmt) throws SQLException{
        ArrayList<Store> listOfStores = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("SELECT * FROM Магазин WHERE name = ?")) {
                stmt.setString(1, storeStmt.getName());
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        Store store = new Store();
                        store.setName(rs.getString("Название"));
                        store.setType(rs.getString("Тип"));
                        listOfStores.add(store);
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfStores;
    }
 
    public void extractFiles() throws SQLException, FileNotFoundException{
        
            try { 
            Class.forName("org.postgresql.Driver"); 
            } catch (ClassNotFoundException e) { 
            System.out.println("Class not found " + e); 
            }
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); Statement stmt = con.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS Магазин");
                stmt.execute("CREATE TABLE Магазин(Название text NOT NULL, Тип text NOT NULL, PRIMARY KEY (Название))");
            }
        
        String line;
        
        BufferedReader br = new BufferedReader(new FileReader("src/databaseworker/store/store.txt"));
          
        try {
            while((line = br.readLine()) != null){
                String []splittedOrderProperties = line.split(";");
                Store store = new Store();
                StoreDataAccessObject storeDataAccessObject = new StoreDataAccessObject();
                store.setName(splittedOrderProperties[0]);
                store.setType(splittedOrderProperties[1]);
                storeDataAccessObject.storeInsert(store);
            }
        } catch (IOException ex) {
            Logger.getLogger(StoreDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void saveFiles(ArrayList<Store> store){
        try{
            File file = new File("src/databaseworker/store/store.txt");
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            try (PrintWriter pw = new PrintWriter(bw)) {
                if(!store.isEmpty())
                    for(int i = 0; i < store.size(); i++){
                        pw.println(store.get(i).getName() + ";" + store.get(i).getType());
                    }
            }

        }catch(IOException ioe){
    	   System.out.println("Exception occurred:");
        }
    }
    
}
