/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseworker.seller;

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
public class SellerDataAccessObject {
    
    public SellerDataAccessObject(){
        
    }
    
    public void sellerInsert(Seller seller) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("INSERT INTO Продавец (Имя, Город, Название, Цена, Количество) values (?, ?, ?, ?, ?)")) {
                stmt.setString(1, seller.getName());
                stmt.setString(2, seller.getCity());
                stmt.setString(3, seller.getTitle());
                stmt.setInt(4, seller.getCost());
                stmt.setInt(5, seller.getQuantity());
                stmt.execute();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SellerDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sellerUpdate(Seller seller) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("UPDATE Продавец SET (Имя, Город, Название, Цена, Количество) = (?, ?, ?, ?, ?) WHERE Имя = ?, Город = ?, Название = ?")) {
                stmt.setString(1, seller.getName());
                stmt.setString(2, seller.getCity());
                stmt.setString(3, seller.getTitle());
                stmt.setInt(4, seller.getCost());
                stmt.setInt(5, seller.getQuantity());
                stmt.setString(6, seller.getName());
                stmt.setString(7, seller.getCity());
                stmt.setString(8, seller.getTitle());
                stmt.execute();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SellerDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sellerDeleteByKey(Seller seller) throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("DELETE From Продавец WHERE Имя = ? AND Город = ? AND Название = ?")) {
                stmt.setString(1, seller.getName());
                stmt.setString(2, seller.getCity());
                stmt.setString(3, seller.getTitle());
                stmt.execute();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SellerDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Seller> sellerGetAll() throws SQLException{
        ArrayList<Seller> listOfSellers = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Продавец")) {
                while (rs.next()){
                    Seller seller = new Seller();
                    seller.setName(rs.getString("Имя"));
                    seller.setCity(rs.getString("Город"));
                    seller.setTitle(rs.getString("Название"));
                    seller.setCost(rs.getInt("Цена"));
                    seller.setQuantity(rs.getInt("Количество"));
                    listOfSellers.add(seller);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SellerDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfSellers;
    }
    
    public ArrayList<Seller> sellerGetByKey(Seller sellerStmt) throws SQLException{
        ArrayList<Seller> listOfSellers = new ArrayList<>();
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/JyJyNaNa";
            String login = "postgres";
            String password = "1";
            try(Connection con = DriverManager.getConnection(url, login, password); PreparedStatement stmt = con.prepareStatement("SELECT * FROM Продавец WHERE name = ?, city = ?, title = ?")) {
                stmt.setString(1, sellerStmt.getName());
                stmt.setString(2, sellerStmt.getCity());
                stmt.setString(3, sellerStmt.getTitle());
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        Seller seller = new Seller();
                        seller.setName(rs.getString("Имя"));
                        seller.setCity(rs.getString("Город"));
                        seller.setTitle(rs.getString("Название"));
                        seller.setCost(rs.getInt("Цена"));
                        seller.setQuantity(rs.getInt("Количество"));
                        listOfSellers.add(seller);
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SellerDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfSellers;
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
                stmt.execute("DROP TABLE IF EXISTS Продавец");
                stmt.execute("CREATE TABLE Продавец(Имя text NOT NULL, Город text NOT NULL, Название text NOT NULL, Цена integer, Количество integer, PRIMARY KEY (Имя, Город, Название))");
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
    
    public void saveFiles(ArrayList<Seller> seller){
        try{
            File file = new File("src/databaseworker/seller/seller.txt");
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            try (PrintWriter pw = new PrintWriter(bw)) {
                if(!seller.isEmpty())
                    for(int i = 0; i < seller.size(); i++){
                        pw.println(seller.get(i).getName() + ";" + seller.get(i).getCity() + ";" + seller.get(i).getTitle() + ";" + seller.get(i).getCost() + ";" + seller.get(i).getQuantity());
                    }
            }

        }catch(IOException ioe){
    	   System.out.println("Exception occurred:");
        }
    }
    
}
