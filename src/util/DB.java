package util;
import java.sql.*;

import modalFlexi.TablesCreation;
/**
 * 
 */


/**
 * @author Harpreet
 *
 */
public class DB {
	
	

	// connection
	static Connection connection = null;
	
	/**
	 * @return the connection
	 */
	
	public static Connection getConnection() {
		return connection;
	}
	public DB() {
        //Connection conn = null;
		if(connection == null) {
	        try {
	            // db parameters
	            String url = "jdbc:sqlite:src/util/myProperties.db";
	            // create a connection to the database
	            connection = DriverManager.getConnection(url);
	            
	            System.out.println("Connection to SQLite has been established.");
	            
	            // calling Database method to create tables
	            Database();
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } 
		}

	}
	
	// create new table
	
	public static void Database() {
        
        TablesCreation tablecreation = new TablesCreation();
        if(tablecreation.createTable(connection)) {
        	System.out.println("New Tables created in DB" );
        }
        else{
        	System.out.println("Error creating tables");
        }
		

    }
	
	public static void closeConnection() {
		
		 try {
           if (connection != null) {
           	connection.close();
           }
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
	}
	
}