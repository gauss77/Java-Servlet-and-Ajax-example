/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;

/**
 *
 * @author EAMirzazade
 */
public class DbConnect {

    public static  Connection connection;

    public DbConnect() throws ServletException {
        try {
            // load the driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/manager", "root", "admin");
        } catch (ClassNotFoundException e) {
            throw new UnavailableException(
                    "Login init() ClassNotFoundException: " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new UnavailableException(
                    "Login init() IllegalAccessException: " + e.getMessage());
        } catch (InstantiationException e) {
            throw new UnavailableException(
                    "Login init() InstantiationException: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     ResultSet rs;
     Statement prst;

     public boolean insertData(String query) {
         boolean result=false;
        try {
            PreparedStatement prst = connection.prepareStatement(query);
            result = prst.execute();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
     
    public ResultSet getData(String query) {

        try {
            prst = connection.createStatement();
            rs = prst.executeQuery(query);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }
    static List<String> myList;

    public  List getListData(String query) {
        myList = new ArrayList<String>();
        try {
            prst = connection.prepareStatement(query);
            rs = prst.executeQuery(query);
            while (rs.next()) {

                myList.add(rs.getString("worker_fullname").toString());
                myList.add(rs.getString("username").toString());
                myList.add(rs.getString("dep_name").toString());
            } 
           // connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }return myList;
       
    }

    
    public  List getDepListData(String query) {
        myList = new ArrayList<String>();
        try {
            prst = connection.prepareStatement(query);
            rs = prst.executeQuery(query);
            while (rs.next()) {

                myList.add(rs.getString("dep_name").toString());
                
            } 
            //connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }return myList;
       
    }
    
    public  List getStuffListData(String query) {
        myList = new ArrayList<String>();
        try {
            prst = connection.prepareStatement(query);
            rs = prst.executeQuery(query);
            while (rs.next()) {

                myList.add(rs.getString("stuff_name").toString());
                
            } 
            //connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }return myList;
       
    }
    
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DeleteData(String query) {

        try {
            PreparedStatement prst = connection.prepareStatement(query);
            prst.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
    }
    
    public void EditData(String query) {

        try {
            prst = connection.createStatement();
            int res = prst.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
    }
    
     public  int getDataSingle(String query, String single) {
        //myList = new ArrayList<String>();
         int id=0;
        try {
            prst = connection.prepareStatement(query);
            rs = prst.executeQuery(query);
            while (rs.next()) {

                id = (rs.getInt(single));
                
            } 
            //connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }return id;
       
    }
    

}
