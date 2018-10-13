/**
 * 
 */
package modalFlexi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import Exceptions.MaintenanceExceptions;
import Exceptions.RentExceptions;
import Exceptions.ReturnException;
import controllerFlexi.AlertMessagesController;
import controllerFlexi.DateTimeController;
import controllerFlexi.DisplayAllPropertiesController;
import controllerFlexi.PropertyValidationsController;
import javafx.collections.ObservableList;

/**
 * @author preet
 *
 */
public class premiumSuite extends property {

	/**
	 * while Adding property from user input without property status
	 * 
	 * @param property_id
	 * @param street_name
	 * @param street_no
	 * @param suburb
	 * @param no_of_beds
	 * @param property_type
	 * @param lastMaintenanceDate
	 * @param description
	 * @param image
	 */
	public premiumSuite(String property_id, String street_name, String street_no, String suburb, String no_of_beds,
			String property_type, String lastmaintenancedate, String description, String image) {

		super(property_id, street_name, street_no, suburb, no_of_beds, property_type, lastmaintenancedate, description,
				image);

	}

	/**
	 * while Adding property from import with rental records
	 * 
	 * @param property_id
	 * @param street_name
	 * @param street_no
	 * @param suburb
	 * @param no_of_beds
	 * @param property_type
	 * @param property_status
	 * @param lastMaintenanceDate
	 * @param description
	 * @param image
	 */
	public premiumSuite(String property_id, String street_name, String street_no, String suburb, String no_of_beds,
			String property_type, String property_status, String lastmaintenancedate, String description, String image,
			rentalRecord[] records) {
		super(property_id, street_name, street_no, suburb, no_of_beds, property_type, property_status,
				lastmaintenancedate, description, image, records);

	}

	// /**
	// * constructor for records
	// * @param property_id
	// * @param street_name
	// * @param street_no
	// * @param suburb
	// * @param no_of_beds
	// * @param property_type
	// * @param property_status
	// * @param lastMaintenanceDate
	// * @param description
	// * @param image
	// */
	// public premiumSuite(String property_id, String street_name, String street_no,
	// String suburb, String no_of_beds,
	// String property_type, String property_status, String lastmaintenancedate,
	// String description, String image, rentalRecord[] records) {
	// super(property_id, street_name, street_no, suburb, no_of_beds, property_type,
	// property_status, lastmaintenancedate, description, image, records);
	// // TODO Auto-generated constructor stub
	// }

	/*
	 * (non-Javadoc)
	 * 
	 *
	 */
	@Override
	public void rent(String customerId, DateTimeController rentDate, int numOfRentDay) throws RentExceptions {
		// validating 1 more time

		// getting all properties
		DisplayAllPropertiesController obj = new DisplayAllPropertiesController();
		ObservableList<property> allprop = obj.getAllPropertiesfromDB();

		// System.out.println(this.getClass());

		// passing values to validation class for validating data
		PropertyValidationsController validd = new PropertyValidationsController();
		try {
			if(validd.validateRentdate(rentDate.toString(), this.getProperty_id())) {
				
		// get dayname to check min days
		DateTimeController dayobj = new DateTimeController();
		String rentdayName = dayobj.getDayName(rentDate.toString());

		int min_no_of_days = 1;

		// estimated return date

		String estdReturnDate = dayobj.increaseDate(rentDate.toString(), numOfRentDay);
		String recordId = this.getProperty_id() + "_" + customerId + "_" + rentDate.getEightDigitDate();

		// System.out.println(validd.checkPropertyAvailableStatus(allprop,
		// getProperty_id(), "status"));
		try {
			if (validd.validateRentdays(Integer.toString(numOfRentDay), min_no_of_days, this.getProperty_id(), allprop,
					rentDate.toString())) {
	
				// get next free index of rental record of this object
				// System.out.println(valid.arrayFreeIndex(this.records));
	
				// make : separated array with only record 3 column data rest other will be null or 0
				
				String[] rentalcolon = new String[10];
				rentalcolon[0] = recordId;
				rentalcolon[1] = rentDate.toString() ;
				rentalcolon[2] = estdReturnDate ;
				rentalcolon[3] = "";
				rentalcolon[4] = "0.0";
				rentalcolon[5] = "0.0";
			
				
				//Insert rental record
				importToDB InsertRental = new importToDB();
				
				if (InsertRental.insertRentalRecord(rentalcolon)){
					// set staus to rented
					if (InsertRental.updateMaintenance(this.getProperty_id(), "","available")) {
						// alert Success

						AlertMessagesController successobj = new AlertMessagesController(
								"Property is rented now.", "Success:)");
					}
				}
				
	
			}
		}catch (RentExceptions e) {
			// TODO Auto-generated catch block
			throw new RentExceptions(e.getMessage());
		}
			}
		}
			catch(RentExceptions e) {
				// send Exception
				throw new RentExceptions(e.getMessage());
			}

	}

	/*
	 * (non-Javadoc)
	 * 
	 *
	 */
	@Override
	public void returnProperty(DateTimeController returnDate) throws ReturnException {
		// revalidating again

				// get details of this property from DB

				String latestRentDate = "", latestEstdDate = "", no_of_beds = "";

				importToDB get_and_updateRental = new importToDB();
				ResultSet rentalData = get_and_updateRental.getRentalRecord(this.getProperty_id(), "specific");
				ResultSet specificPropertyData = get_and_updateRental.checkData(this.getProperty_id());

				try {
					// must meet the condition
					latestRentDate = rentalData.getString("rentDate");
					latestEstdDate = rentalData.getString("estReturnDate");
					no_of_beds = specificPropertyData.getString("no_of_beds");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				DateTimeController dateobj = new DateTimeController();

				double totalprice = 0, lateFine = 0;

				try {
					if (dateobj.compareDatesVal(returnDate.toString(), latestRentDate)) {
						// System.out.println("you can rent me");
						// converting both dates to DateTime so can find the difference in days

						String[] latestrentparts = latestRentDate.split("/");

						String[] latestEstdDateparts = latestEstdDate.split("/");

						DateTimeController datelatestRent = new DateTimeController(Integer.parseInt(latestrentparts[0]),
								Integer.parseInt(latestrentparts[1]), Integer.parseInt(latestrentparts[2]));

						DateTimeController datelatestEstdDateparts = new DateTimeController(
								Integer.parseInt(latestEstdDateparts[0]), Integer.parseInt(latestEstdDateparts[1]),
								Integer.parseInt(latestEstdDateparts[2]));

						// got difference b/w rent and actual date
						int rent_to_actual_diff = dateobj.diffDays(returnDate, datelatestRent);

						// got difference b/w estd and actual return date
						int estd_to_actual_diff = dateobj.diffDays(returnDate, datelatestEstdDateparts);

						// got difference b/w estd and rent return date
						int estd_to_rent_diff = dateobj.diffDays(datelatestEstdDateparts, datelatestRent);

						// rent date is less than actual return date
						// if(rent_to_actual_diff > 0) {

						// check apartment no_of_bedrooms

						// String no_of_beds = this.getNo_of_beds();
						int beds = Integer.parseInt(no_of_beds);

						if (estd_to_actual_diff > 0) {
							// property left after estd return date so fine

							if (beds == 3) {
								totalprice = (estd_to_rent_diff * 554) ;
								 lateFine =  (662 * estd_to_actual_diff );
							}

						} else {
							if (beds == 3) {
								totalprice = rent_to_actual_diff * 554;
							}

						}
						
						// setting data in DB

						if (get_and_updateRental.updateRentalRecordProperty(this.getProperty_id(), totalprice, lateFine,
								returnDate.toString())) {
						
							// set Property Status to available
							if (get_and_updateRental.updateMaintenance(this.getProperty_id(), "", "rented")) {
								// alert Success

								AlertMessagesController successobj = new AlertMessagesController(
										"Property is returned Successfully!!.", "Success:)");
							}
						}

						 
						

					}

				} catch (ReturnException e) {
					throw new ReturnException(e.getMessage());
				}
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * 
	 */
	@Override
	public void performMaintenance() throws MaintenanceExceptions {

		DisplayAllPropertiesController obj = new DisplayAllPropertiesController();

		ObservableList<property> allprop = obj.getAllPropertiesfromDB();

		// passing values to validation class for validating data
		PropertyValidationsController valid = new PropertyValidationsController();

		// property found. check status

		String InputpropertyStaus = valid.checkPropertyAvailableStatus(allprop, this.getProperty_id(), "status");

		// if available only then set to maintenance

		if ("available".equals(InputpropertyStaus)) {

			// set status to maintenance
			// this.setProperty_status("maintenance");
			importToDB updateStatus = new importToDB();
			if (updateStatus.updateMaintenance(this.getProperty_id(), "", "maintenance")) {
				// alert Success

				AlertMessagesController successObj = new AlertMessagesController("Property is now under Maintenance",
						"Success:)");
			}

		} else {
			// send Exception
			throw new MaintenanceExceptions("Property Can't go under Maintenance.");
		}

	}

	@Override
	public void completeMaintenance(DateTimeController completionDate) throws MaintenanceExceptions {

		// getting all properties
		DisplayAllPropertiesController obj = new DisplayAllPropertiesController();

		ObservableList<property> allprop = obj.getAllPropertiesfromDB();

		// passing values to validation class for validating data
		PropertyValidationsController valid = new PropertyValidationsController();

		String InputpropertyStaus = valid.checkPropertyAvailableStatus(allprop, this.getProperty_id(), "status");
		if ("maintenance".equals(InputpropertyStaus)) {

			// get last maintenance date of property so validate
			String property_last_maintenance_date = valid.checkPropertyAvailableStatus(allprop, this.getProperty_id(),
					"maintenancedate");
			//System.out.println(property_last_maintenance_date + " ------- "+completionDate.toString());

			DateTimeController dateobj = new DateTimeController();
			if (dateobj.compareDates(completionDate.toString(), property_last_maintenance_date)) {

				importToDB updateStatus = new importToDB();
				if (updateStatus.updateMaintenance(this.getProperty_id(), completionDate.toString(), "available")) {
					// alert Success

					AlertMessagesController successobj = new AlertMessagesController(
							"Property has completed Maintenance", "Success:)");
				}

			} else {
				throw new MaintenanceExceptions("Date can't be less than Property Last Maintenance Date");
			}

		} else {
			throw new MaintenanceExceptions("Property Couldn't complete  Maintenance.");
		}
	}

	/*
	 * (non-Javadoc)
	 */
	@Override
	public String getDetails() {

		System.out.println("************************************");
		if ("available".equals(this.getProperty_status())) {
			System.out.println("A new Premium Suite is available for rent.");
		}
		PropertyValidationsController valid = new PropertyValidationsController();

		int len = valid.arrayFreeIndex(this.getRecords());
		String rental_record_string = "";
		String property_details = "";
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				rental_record_string += this.records[i].getDetails() + "\n----------------------------\n";

			}
		}

		if (len > 0) {
			property_details = "Property ID:" + this.getProperty_id() + "\n Address : " + this.getStreet_no() + " "
					+ this.getStreet_name() + " " + this.getSuburb() + "\n Type : " + this.getProperty_type()
					+ "\n Bedroom : " + this.getNo_of_beds() + "\n Status: " + this.getProperty_status()
					+ "\n Last Maintenance date:" + this.getLastMaintenanceDate()
					+ "\n Rental Record \n-------------------\n" + rental_record_string;
		} else {
			property_details = "Property ID:" + this.getProperty_id() + "\n Address : " + this.getStreet_no() + " "
					+ this.getStreet_name() + " " + this.getSuburb() + "\n Type : " + this.getProperty_type()
					+ "\n Bedroom : " + this.getNo_of_beds() + "\n Status: " + this.getProperty_status()
					+ "\n Last Maintenance date:" + this.getLastMaintenanceDate() + "\n Rental Record : empty";
		}

		System.out.println("************************************");

		return property_details;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		PropertyValidationsController valid = new PropertyValidationsController();
		int len = valid.arrayFreeIndex(this.getRecords());
		String rental_record_string = "";
		String property_details = "";
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				rental_record_string += this.records[i].toString() + "\n";

			}
		}

		return getProperty_id() + ":" + getStreet_no() + ":" + getStreet_name() + ":" + getSuburb() + ":"
				+ getProperty_type() + ":" + getNo_of_beds() + ":" + getProperty_status() + ":"
				+ getLastMaintenanceDate() + ":" + getImage() + ":" + getDescription() + "\n" + rental_record_string;
	}

}
