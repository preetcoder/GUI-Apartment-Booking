/**
 * 
	importToDB.java
	15 Sep. 2018
	
	This file is importing data to database after reading the files from data.txt file in util package
 */
package modalFlexi;

import java.io.*;
import java.sql.*;
import java.util.Arrays;

import controllerFlexi.DateTimeController;
import controllerFlexi.MainController;
import util.DB;

/**
 * @author Harpreet
 *
 */
public class importToDB {
	ResultSet rs = null;

	public importToDB() {

		// String[] tmpData = new String[10];

		try {
			// Reading file
			BufferedReader br = new BufferedReader(new FileReader("src/util/data.txt"));

			String line = null;
			int count = 0;

			// check if data exists. If not then insert else we will use previous data

			try {
				if (!checkData("all").isBeforeFirst()) {
					// insert data
					while ((line = br.readLine()) != null) {

						if (line != null && !line.equals("")) {

							String[] tmpData = line.split(":");
							if (tmpData.length == 6) {
								// insert Rental Record Data
								insertRentalRecord(tmpData);
							} else {
								// inserting properties data
								insert(tmpData);

							}

						}
					}

					// MainController obj = new MainController();
					// obj.initialize(null, null);
					System.out.println("New data loaded in DB");
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ResultSet checkData(String val) {
		ResultSet rs = null;
		String sql = "";
		if ("all".equals(val)) {
			sql = "Select * from RentalProperty";
		} else {
			sql = "Select * from RentalProperty WHERE property_id = '" + val + "'";
		}

		try {
			Statement pstmt = DB.getConnection().createStatement();

			return rs = pstmt.executeQuery(sql);

		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	// get rental record based on property ID
	public ResultSet getRentalRecord(String prop_ID, String val) {
		ResultSet rs = null;
		String sql = "";
		if ("all".equals(val)) {
			sql = "Select * from RentalRecord WHERE property_id = '" + prop_ID + "'";
		} else {
			sql = "Select * from RentalRecord WHERE property_id = '" + prop_ID + "' AND actReturnDate = ''";
		}

		try {
			Statement pstmt = DB.getConnection().createStatement();

			return rs = pstmt.executeQuery(sql);

		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	// insert query method
	public boolean insert(String[] property_data) {

		// making variables

		String property_id = property_data[0];
		String street_no = property_data[1];
		String street_name = property_data[2];
		String suburb = property_data[3];
		String property_type = property_data[4];
		String no_of_beds = property_data[5];
		String property_status = property_data[6];

		// get current date for apartment last maintenance date
		DateTimeController currentDate = new DateTimeController();

		String lastMaintenanceDate, image, description;
		// if Apartment then getting only 8 colon separated strings
		if (property_id.charAt(0) == 'A') {
			lastMaintenanceDate = currentDate.getFormattedDate();
			image = property_data[7];
			description = property_data[8];
		} else {
			lastMaintenanceDate = property_data[7];
			image = property_data[8];
			description = property_data[9];
		}

		// Insert Query

		String sql = "INSERT INTO RentalProperty VALUES('" + property_id + "','" + street_no + "','" + street_name
				+ "','" + suburb + "','" + no_of_beds + "','" + property_type + "','" + property_status + "','"
				+ lastMaintenanceDate + "','" + description + "','" + image + "')";
		try {
			// creating object
			Statement pstmt = DB.getConnection().createStatement();

			// Executing query

			pstmt.execute(sql);
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	// insert rental Record
	public boolean insertRentalRecord(String[] record_data) {
		String[] property_id = record_data[0].split("_");
		String prop_val = "", cust_val = "";
		// get property ID from record ID
		for (int i = 0; i <= 3; i++) {
			prop_val += property_id[i] + "_";
		}

		// get customer ID

		for (int i = 4; i <= 5; i++) {
			cust_val += property_id[i] + "_";
		}

		String Prop_ID = prop_val.replaceAll("_$", "");
		String record_ID = record_data[0];
		String rentDate = record_data[1];
		String EstdreturnDate = record_data[2];
		String ActreturnDate = record_data[3];
		String rentFee = record_data[4];
		String lateFee = record_data[5];
		String custID = cust_val.replaceAll("_$", "");

		// System.out.println(custID);

		// Insert Query

		String sql = "INSERT INTO RentalRecord VALUES('" + record_data[0] + "','" + Prop_ID + "','" + rentDate + "','"
				+ custID + "','" + EstdreturnDate + "','" + ActreturnDate + "','" + rentFee + "','" + lateFee + "')";
		try {
			// creating object
			Statement pstmt = DB.getConnection().createStatement();

			// Executing query

			pstmt.execute(sql);
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;

	}

	// delete whole data with truncate optimizer
	public void truncateTables() {
		String sql_deleteRentalProperty = "DELETE FROM RentalProperty";
		String sql_deleteRentalRecord = "DELETE  FROM RentalRecord";

		try {
			// creating object
			Statement pstmt = DB.getConnection().createStatement();

			// Executing query

			pstmt.execute(sql_deleteRentalProperty);
			pstmt.executeQuery(sql_deleteRentalRecord);
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	// Update maintenance
	public boolean updateMaintenance(String propertyID, String dateVal, String status) {
		// if dateVal is empty then perform else complete
		String sql_updateProperty = "";

		if (dateVal.isEmpty()) {
			// if available then rent it
			if ("available".equals(status)) {
				sql_updateProperty = "UPDATE RentalProperty SET property_status = 'rented' WHERE property_id ='"
						+ propertyID + "'";

			}
			else if("rented".equals(status)) {
				sql_updateProperty = "UPDATE RentalProperty SET property_status = 'available' WHERE property_id ='"
						+ propertyID + "'";
			}
			else {
				sql_updateProperty = "UPDATE RentalProperty SET property_status = 'maintenance' WHERE property_id ='"
						+ propertyID + "'";

			}
		}

		else {
			// complete maintenance
			sql_updateProperty = "UPDATE RentalProperty SET property_status = 'available', lastMaintenanceDate = '"
					+ dateVal + "' WHERE property_id ='" + propertyID + "'";
		}
		try {
			// System.out.println(sql_updateProperty);
			// creating object
			Statement pstmt = DB.getConnection().createStatement();

			// Executing query

			pstmt.execute(sql_updateProperty);
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean updateRentalRecordProperty(String propertyID, Double RentFee, Double LateFee, String actReturnDate) {
		String sql_updateRental = "UPDATE RentalRecord SET rentFee =" +RentFee+",lateFee = "+LateFee+", actReturnDate='"+actReturnDate+"'  WHERE property_id ='"
				+ propertyID + "' AND actReturnDate = '' ";
		try {
			// System.out.println(sql_updateProperty);
			// creating object
			Statement pstmt = DB.getConnection().createStatement();

			// Executing query

			pstmt.execute(sql_updateRental);
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;

	}

}
