/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseworker.seller;

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
public class SellerDataAccessObject {
    
    public SellerDataAccessObject(){
        
    }
    
    private void sellerInsert(Seller seller) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                PreparedStatement stmt = con.prepareStatement("INSERT INTO Продавец (Имя, Город, Название, Цена, Количество) values (?, ?, ?, ?, ?)");
                stmt.setString(1, seller.getName());
                stmt.setString(2, seller.getCity());
                stmt.setString(3, seller.getTitle());
                stmt.setInt(4, seller.getCost());
                stmt.setInt(5, seller.getQuantity());
                stmt.execute();
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SellerDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sellerUpdate(Seller seller) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                PreparedStatement stmt = con.prepareStatement("UPDATE Продавец SET (Имя, Город, Название, Цена, Количество) = (?, ?, ?, ?, ?) WHERE Имя = '?', Город = '?', Название = '?'");
                stmt.setString(1, seller.getName());
                stmt.setString(2, seller.getCity());
                stmt.setString(3, seller.getTitle());
                stmt.setInt(4, seller.getCost());
                stmt.setInt(5, seller.getQuantity());
                stmt.setString(6, seller.getName());
                stmt.setString(7, seller.getCity());
                stmt.setString(8, seller.getTitle());
                stmt.execute();
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SellerDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sellerDeleteByKey(Seller seller) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                PreparedStatement stmt = con.prepareStatement("DELETE From Продавец WHERE Имя = '?', Город = '?', Название = '?'");
                stmt.setString(1, seller.getName());
                stmt.setString(2, seller.getCity());
                stmt.setString(3, seller.getTitle());
                ResultSet rs = stmt.executeQuery();
                rs.close();
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SellerDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sellerGetAll() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                Statement stmt = con.createStatement();
                stmt.execute("SELECT * FROM Продавец");
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SellerDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sellerGetByKey(Seller seller) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            Connection con = DriverManager.getConnection(url, login, password);
            try{
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM Продавец WHERE name = '?', city = '?', title = '?'");
                stmt.setString(1, seller.getName());
                stmt.setString(2, seller.getCity());
                stmt.setString(3, seller.getTitle());
                ResultSet rs = stmt.executeQuery();
                rs.close();
                stmt.close();
            } finally{
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SellerDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
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
                stmt.execute("DROP TABLE IF EXISTS Продавец");
                stmt.execute("CREATE TABLE Продавец(Имя text NOT NULL, Город text NOT NULL, Название text NOT NULL, Цена integer, Количество integer, PRIMARY KEY (Имя, Город, Название))");
                stmt.close();
            } finally{
                con.close();
            }
        
        String line;
        
        BufferedReader br = new BufferedReader(new FileReader("src/databaseworker/seller/seller.txt"));
          
        try {
            while((line = br.readLine()) != null){
                String []splittedOrderProperties = line.split(";");
                Seller seller = new Seller();
                SellerDataAccessObject sellerDataAccessObject = new SellerDataAccessObject();
                seller.setName(splittedOrderProperties[0]);
                seller.setCity(splittedOrderProperties[1]);
                seller.setTitle(splittedOrderProperties[2]);
                seller.setCost(Integer.valueOf(splittedOrderProperties[3]));
                seller.setQuantity(Integer.valueOf(splittedOrderProperties[4]));
                sellerDataAccessObject.sellerInsert(seller);
            }
        } catch (IOException ex) {
            Logger.getLogger(SellerDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
