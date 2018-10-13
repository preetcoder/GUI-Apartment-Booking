/**
 * 
DisplayAllPropertiesController.java
15 Sep. 2018
 */
package controllerFlexi;

import java.io.File;
import java.sql.ResultSet;
import java.util.Set;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import modalFlexi.*;
import viewFlexi.SinglePropertyloop;

/**
 * @author Harpreet
 * 
 * This Class will show all properties present inside DB
 */
public class DisplayAllPropertiesController {

	// private Set<property> propertySet;

	private ObservableList<property> allProperty = FXCollections.observableArrayList();

	private ObservableList<Pane> allPanes = FXCollections.observableArrayList();

	/**
	 * @return the allProperty
	 */
	public ObservableList<property> getAllProperty() {
		return allProperty;
	}

	public ObservableList<property> getAllPropertiesfromDB() {

		// getting resultSet from checkData method in importToDB.java
		importToDB checkallData = new importToDB();
		ResultSet alldata = checkallData.checkData("all");
		property obj;

		try {

			while (alldata.next()) {
				// Array to get data
				String[] dbdata = new String[10];

				dbdata[0] = alldata.getString("property_id");
				dbdata[1] = alldata.getString("street_name");
				dbdata[2] = alldata.getString("street_no");
				dbdata[3] = alldata.getString("suburb");
				dbdata[4] = alldata.getString("no_of_beds");
				dbdata[5] = alldata.getString("property_type");
				dbdata[6] = alldata.getString("property_status");
				dbdata[7] = alldata.getString("lastMaintenanceDate");
				dbdata[8] = alldata.getString("description");
				dbdata[9] = alldata.getString("image");

				// get rental record of property
				ResultSet rentalRecordsData = checkallData.getRentalRecord(dbdata[0],"all");

				// getting rental record of this property
				rentalRecord[] Property_rent_data = rentalRecordbasedonProperID(rentalRecordsData);
				
				if ("Apartment".equals(dbdata[5])) {
					
					// without property lastMaintenanceDate
					obj = new apartment(dbdata[0], dbdata[1], dbdata[2], dbdata[3], dbdata[4], dbdata[5], dbdata[6],
							dbdata[8], dbdata[9], Property_rent_data);
				} else {
					// with all data 
					obj = new premiumSuite(dbdata[0], dbdata[1], dbdata[2], dbdata[3], dbdata[4], dbdata[5], dbdata[6],
							dbdata[7], dbdata[8], dbdata[9], Property_rent_data);
				}
				allProperty.add(obj);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return allProperty;

	}

	// generating rental record and returning back
	public rentalRecord[] rentalRecordbasedonProperID(ResultSet rentData) {
		rentalRecord[] rentalRecorddata = new rentalRecord[10];

		// count for checking rentalRecords
		int count = 0;
		// System.out.println(rentData);
		try {

			while (rentData.next()) {
				// everytime create new obj when loop execute
				rentalRecord obj;
				// rentalRecorddata[0] =
				// System.out.println();
				String recordID = rentData.getString("recordID");
				// String property_id = rentData.getString("property_id");
				String rentDate = rentData.getString("rentDate");
				String customerID = rentData.getString("customerID");
				String estReturnDate = rentData.getString("estReturnDate");

				// if actual return date is empty it means property is on rent
				if ("".equals(rentData.getString("actReturnDate"))) {
					// create rental object
					obj = new rentalRecord(recordID, rentDate, customerID, estReturnDate);
				} else {
					// get other fields and then create object

					String actReturnDate = rentData.getString("actReturnDate");
					double rentFee = rentData.getDouble("rentFee");
					double lateFee = rentData.getDouble("lateFee");

					obj = new rentalRecord(recordID, rentDate, customerID, estReturnDate, actReturnDate, rentFee,
							lateFee);
				}

				rentalRecorddata[count] = obj;
				count++;

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return rentalRecorddata;
	}

	// method for all properties loop
	public ObservableList<Pane> displayAllPropView() {

		getAllPropertiesfromDB();

		// getting view of properties loop from SinglePropertyloop.java

		SinglePropertyloop singleloop = new SinglePropertyloop();

		return singleloop.propertyloop(allProperty);

	}

	public ObservableList<Pane> displayAllPropViewWithFilter(String[] filtervalues) {
		getAllPropertiesfromDB();

		ObservableList<property> filteredProperty = FXCollections.observableArrayList();

		// if bedroom filter
		if ("Bedrooms".equals(filtervalues[0])) {

			// iterate through objects in array
			for (int i = 0; i < allProperty.size(); i++) {

				if (filtervalues[1].equals(allProperty.get(i).getNo_of_beds())) {

					filteredProperty.add(allProperty.get(i));
				}
			}

		}

		// if type filter
		if ("Type".equals(filtervalues[0])) {

			// iterate through objects in array
			for (int i = 0; i < allProperty.size(); i++) {

				if (filtervalues[1].equals(allProperty.get(i).getProperty_type())) {

					filteredProperty.add(allProperty.get(i));
				}
			}

		}

		// status filter
		if ("status".equals(filtervalues[0])) {

			// iterate through objects in array
			for (int i = 0; i < allProperty.size(); i++) {

				if (filtervalues[1].equals(allProperty.get(i).getProperty_status())) {

					filteredProperty.add(allProperty.get(i));
				}
			}

		}

		SinglePropertyloop singleloop1 = new SinglePropertyloop();

		return singleloop1.propertyloop(filteredProperty);
	}

}
