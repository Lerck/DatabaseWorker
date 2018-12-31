/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseworker.registry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
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
public class RegistryDataAccessObject {
    
    public RegistryDataAccessObject(){
        
    }
    
    public void registryInsert(Registry registry) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("INSERT INTO Регистрация (Имя, Дата_регистрации, Количество_позиций) values (?, ?, ?)")) {
                stmt.setString(1, registry.getName());
                stmt.setDate(2, registry.getRegistryDate());
                stmt.setInt(3, registry.getPositionQuantity());
                stmt.execute();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistryDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registryUpdate(Registry registry) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("UPDATE Регистрация SET (Имя, Дата_регистрации, Количество_позиций) = (?, ?, ?) WHERE Имя = ?")) {
                stmt.setString(1, registry.getName());
                stmt.setDate(2, registry.getRegistryDate());
                stmt.setInt(3, registry.getPositionQuantity());
                stmt.setString(4, registry.getName());
                stmt.execute();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistryDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registryDeleteByKey(Registry registry) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("DELETE From Регистрация WHERE Имя = ?")) {
                stmt.setString(1, registry.getName());
                stmt.execute();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistryDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Registry> registryGetAll() throws SQLException{
        ArrayList<Registry> listOfRegistries = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Регистрация")) {
                while (rs.next()){
                    Registry registry = new Registry();
                    registry.setName(rs.getString("Имя"));
                    registry.setRegistryDate(rs.getDate("Дата_регистрации"));
                    registry.setPositionQuantity(rs.getInt("Количество_позиций"));
                    listOfRegistries.add(registry);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistryDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfRegistries;
    }
    
    public ArrayList<Registry> registryGetByKey(Registry registryStmt) throws SQLException{
        ArrayList<Registry> listOfRegistries = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("SELECT * FROM Регистрация WHERE name = ?")) {
                stmt.setString(1, registryStmt.getName());
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        Registry registry = new Registry();
                        registry.setName(rs.getString("Имя"));
                        registry.setRegistryDate(rs.getDate("Дата_регистрации"));
                        registry.setPositionQuantity(rs.getInt("Количество_позиций"));
                        listOfRegistries.add(registry);
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistryDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfRegistries;
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
                stmt.execute("DROP TABLE IF EXISTS Регистрация");
                stmt.execute("CREATE TABLE Регистрация(Имя text PRIMARY KEY NOT NULL, Дата_регистрации date, Количество_позиций integer)");
            }
        
        String line;
        
        BufferedReader br = new BufferedReader(new FileReader("src/databaseworker/registry/registry.txt"));
          
        try {
            while((line = br.readLine()) != null){
                String []splittedOrderProperties = line.split(";");
                Registry registry = new Registry();
                RegistryDataAccessObject registryDataAccessObject = new RegistryDataAccessObject();
                registry.setName(splittedOrderProperties[0]);
                registry.setRegistryDate(Date.valueOf(splittedOrderProperties[1]));
                registry.setPositionQuantity(Integer.valueOf(splittedOrderProperties[2]));
                registryDataAccessObject.registryInsert(registry);
            }
        } catch (IOException ex) {
            Logger.getLogger(RegistryDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveFiles(ArrayList<Registry> registries){
        try{
            File file = new File("src/databaseworker/registry/registry.txt");
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            try (PrintWriter pw = new PrintWriter(bw)) {
                if(!registries.isEmpty())
                    for(int i = 0; i < registries.size(); i++){
                        pw.println(registries.get(i).getName() + ";" + registries.get(i).getRegistryDate() + ";" + registries.get(i).getPositionQuantity());
                    }
            }

        }catch(IOException ioe){
    	   System.out.println("Exception occurred:");
        }
    }
    
}
