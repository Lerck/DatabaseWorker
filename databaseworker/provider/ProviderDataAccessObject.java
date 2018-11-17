/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseworker.provider;

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
public class ProviderDataAccessObject {
    
    public ProviderDataAccessObject(){
        
    }
    
    private void providerInsert(Provider provider) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                PreparedStatement stmt = con.prepareStatement("INSERT INTO Поставщик (Тип, Поставщик) values (?, ?)");
                stmt.setString(1, provider.getType());
                stmt.setString(2, provider.getProvider());
                stmt.execute();
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProviderDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void providerUpdate(Provider provider) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                PreparedStatement stmt = con.prepareStatement("UPDATE Поставщик SET (Тип, Поставщик) = (?, ?) WHERE Тип = '?'");
                stmt.setString(1, provider.getType());
                stmt.setString(2, provider.getProvider());
                stmt.setString(3, provider.getType());
                stmt.executeUpdate();
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProviderDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void providerDeleteByKey(Provider provider) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                PreparedStatement stmt = con.prepareStatement("DELETE From Поставщик (Тип, Поставщик) WHERE Тип = '?'");
                stmt.setString(1, provider.getType());
                ResultSet rs = stmt.executeQuery();
                rs.close();
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProviderDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void providerGetAll() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                Statement stmt = con.createStatement();
                stmt.execute("SELECT * FROM Поставщик");
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProviderDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void providerGetByKey(Provider provider) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM Поставщик WHERE type = '?'");
                stmt.setString(1, provider.getType());
                ResultSet rs = stmt.executeQuery();
                rs.close();
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProviderDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
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
                stmt.execute("DROP TABLE IF EXISTS Поставщик");
                stmt.execute("CREATE TABLE Поставщик(Тип text PRIMARY KEY NOT NULL, Поставщик text)");
                stmt.close();
            } finally{
                con.close();
            }
        
        String line;
        
        BufferedReader br = new BufferedReader(new FileReader("src/databaseworker/provider/provider.txt"));
          
        try {
            while((line = br.readLine()) != null){
                String []splittedOrderProperties = line.split(";");
                Provider provider = new Provider();
                ProviderDataAccessObject providerDataAccessObject = new ProviderDataAccessObject();
                provider.setType(splittedOrderProperties[0]);
                provider.setProvider(splittedOrderProperties[1]);
                providerDataAccessObject.providerInsert(provider);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProviderDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
