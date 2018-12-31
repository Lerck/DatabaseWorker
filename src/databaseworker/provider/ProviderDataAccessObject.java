/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseworker.provider;

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
public class ProviderDataAccessObject {
    
    public ProviderDataAccessObject(){
        
    }
    
    public void providerInsert(Provider provider) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("INSERT INTO Поставщик (Тип, Поставщик) values (?, ?)")) {
                stmt.setString(1, provider.getType());
                stmt.setString(2, provider.getProvider());
                stmt.execute();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProviderDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void providerUpdate(Provider provider) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("UPDATE Поставщик SET (Тип, Поставщик) = (?, ?) WHERE Тип = ?")) {
                stmt.setString(1, provider.getType());
                stmt.setString(2, provider.getProvider());
                stmt.setString(3, provider.getType());
                stmt.executeUpdate();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProviderDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void providerDeleteByKey(Provider provider) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("DELETE From Поставщик WHERE Тип = ?")) {
                stmt.setString(1, provider.getType());
                stmt.execute();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProviderDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Provider> providerGetAll() throws SQLException{
        ArrayList<Provider> listOfProviders = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Поставщик")) {
                while (rs.next()){
                    Provider provider = new Provider();
                    provider.setType(rs.getString("Тип"));
                    provider.setProvider(rs.getString("Поставщик"));
                    listOfProviders.add(provider);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProviderDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfProviders;
    }
    
    public ArrayList<Provider> providerGetByKey(Provider providerStmt) throws SQLException{
        ArrayList<Provider> listOfProviders = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("SELECT * FROM Поставщик WHERE type = ?")) {
                stmt.setString(1, providerStmt.getType());
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        Provider provider = new Provider();
                        provider.setType(rs.getString("Тип"));
                        provider.setProvider(rs.getString("Поставщик"));
                        listOfProviders.add(provider);
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProviderDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfProviders;
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
                stmt.execute("DROP TABLE IF EXISTS Поставщик");
                stmt.execute("CREATE TABLE Поставщик(Тип text PRIMARY KEY NOT NULL, Поставщик text)");
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
    
    public void saveFiles(ArrayList<Provider> provider){
        try{
            File file = new File("src/databaseworker/provider/provider.txt");
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            try (PrintWriter pw = new PrintWriter(bw)) {
                if(!provider.isEmpty()){
                    for(int i = 0; i < provider.size(); i++){
                        pw.println(provider.get(i).getType() + ";" + provider.get(i).getProvider());
                    }
                }
            }

        }catch(IOException ioe){
    	   System.out.println("Exception occurred:");
        }
    }
    
}
