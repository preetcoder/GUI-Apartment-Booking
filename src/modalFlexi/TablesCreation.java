package modalFlexi;
import java.sql.*;
/**
 * 
	TableCreation.java
	14 Sep. 2018
	@author Harpreet
	
	This file is to create new Tables in DB
 */

import util.DB;



public class TablesCreation {

	public boolean createTable(Connection con) {
		  // SQL statement for creating a new table
	    String sql_createRentalProperty = "CREATE TABLE IF NOT EXISTS RentalProperty (\n"
	            + "	property_id varchar(20) PRIMARY KEY,\n"
	            + "	street_no varchar(20) NOT NULL,\n"
	            + "	street_name varchar(20)  NOT NULL,\n"
	            + " suburb varchar(20) NOT NULL,\n"
	            + " no_of_beds varchar(2) NOT NULL,\n"
	            + " property_type varchar(20) NOT NULL,\n"
	            + " property_status varchar(20) NOT NULL,\n"
	            + " lastMaintenanceDate varchar(20),\n"
	            + " description text,\n"
	            + " image varchar(20)\n"
	            + ");";
	    
	    String sql_RentalRecord = "CREATE TABLE IF NOT EXISTS RentalRecord (\n"
	    		+ "	recordID varchar(20) PRIMARY KEY,\n"
	            + "	property_id varchar(20)  NOT NULL,\n"
	            + "	rentDate varchar(20) NOT NULL,\n"
	            + "	customerID varchar(20) NOT NULL,\n"
	            + " estReturnDate varchar(20) NOT NULL,\n"
	            + " actReturnDate varchar(20) ,\n"		// can be null 
	            + " rentFee REAL NOT NULL DEFAULT 0.0,\n"
	            + " lateFee REAL NOT NULL DEFAULT 0.0,\n"
	            + "FOREIGN KEY(property_id) REFERENCES RentalProperty(property_id) \n"
	            + ");";
	    
//	    String sql_RentalRecord = "CREATE TABLE IF NOT EXISTS RentalRecord (\n"
//	    		+ " ID INTEGER PRIMARY KEY AUTOINCREMENT, \n"
//	    		+ "	recordID varchar(50) ,\n"
//	            + "	property_id varchar(20)  NOT NULL,\n"
//	            + "	rentDate varchar(20) NOT NULL,\n"
//	            + "	customerID varchar(20) NOT NULL,\n"
//	            + " estReturnDate varchar(20) NOT NULL,\n"
//	            + " actReturnDate varchar(2) ,\n"		// can be null 
//	            + " rentFee REAL NOT NULL DEFAULT 0.0,\n"
//	            + " lateFee REAL NOT NULL DEFAULT 0.0,\n"
//	            + "FOREIGN KEY(property_id) REFERENCES RentalProperty(property_id) \n"
//	            + ");";
	
	//Connection conn = DB.connect();
	
	 try (Statement stmt = con.createStatement()) {
         if (con != null) {
   
             // create a new table
             stmt.execute(sql_createRentalProperty);
             stmt.execute(sql_RentalRecord);
             return true;
         }

     } catch (SQLException e) {
         System.out.println(e.getMessage());
         
     }
	 
	 return false;

	}
}
