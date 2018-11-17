/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseworker.store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 11
 */
public class StoreDataAccessObject {
    
    public StoreDataAccessObject(){
        
    }
    
    private void storeInsert(Store store) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                PreparedStatement stmt = con.prepareStatement("INSERT INTO Магазин (Название, Тип) values (?, ?)");
                stmt.setString(1, store.getName());
                stmt.setString(2, store.getType());
                stmt.execute();
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void storeUpdate(Store store) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                PreparedStatement stmt = con.prepareStatement("UPDATE Магазин SET (Название, Тип) = (?, ?) WHERE Название = '?'");
                stmt.setString(1, store.getName());
                stmt.setString(2, store.getType());
                stmt.setString(3, store.getName());
                stmt.execute();
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void storeDeleteByKey(Store store) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                PreparedStatement stmt = con.prepareStatement("DELETE From Магазин (Название, Тип) WHERE Название = '?'");
                stmt.setString(1, store.getName());
                ResultSet rs = stmt.executeQuery();
                rs.close();
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void storeGetAll() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                Statement stmt = con.createStatement();
                stmt.execute("SELECT * FROM Магазин");
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void storeGetByKey(Store store) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM Магазин WHERE name = '?'");
                stmt.setString(1, store.getName());
                ResultSet rs = stmt.executeQuery();
                rs.close();
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                Statement stmt = con.createStatement();
                stmt.execute("DROP TABLE IF EXISTS Магазин");
                stmt.execute("CREATE TABLE Магазин(Название text NOT NULL, Тип text NOT NULL, PRIMARY KEY (Название))");
                stmt.close();
            } finally{
                con.close();
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
    
}
