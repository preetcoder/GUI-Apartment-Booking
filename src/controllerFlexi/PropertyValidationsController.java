/**
 * 
 */
package controllerFlexi;

import java.util.*;

import Exceptions.MaintenanceExceptions;
import Exceptions.RentExceptions;
import javafx.collections.ObservableList;
import modalFlexi.property;

import java.sql.Date;

public class PropertyValidationsController {

	// addPropertyTypeValidation method

	public String addPropertyType() {

		String property_type;
		boolean property_type_valid = true;

		// ask for property type details
		System.out.println(
				"Enter Property Type(Must be A for Apartment or PS for Premium Suite or exit to see main menu(Case Sensitive))");

		// scanner object
		Scanner _property = new Scanner(System.in);

		do {

			// Getting user Input for type
			property_type = _property.nextLine();

			// validating and setting variable and exiting loop
			if ("A".equals(property_type)) {
				property_type = "Apartment";
				property_type_valid = false;
			}
			// validating and setting variable and exiting loop
			else if ("PS".equals(property_type)) {
				property_type = "PremiumSuite";
				property_type_valid = false;
			}

			// condition for exit
			else if ("exit".equals(property_type)) {

				property_type = "exit";
				property_type_valid = false;
			}
			// error. continue loop
			else {
				System.out.println(
						"Error.Enter Property Type(Must be A for Apartment or PS for Premium Suite or exit to see main menu(Case Sensitive))");
			}
		}

		while (property_type_valid);

		return property_type;
	}

	public String addPropertyStreetnumber() {
		String streetNumber;

		// ask for streetNumber

		System.out.println("Enter Street Number");

		// scanner object
		Scanner streetno = new Scanner(System.in);
		streetNumber = streetno.nextLine();

		return streetNumber;
	}

	public String addProprtyStreetName() {
		String streetName;

		// ask for streetNumber

		System.out.println("Enter Street Name");

		// scanner object
		Scanner streetnm = new Scanner(System.in);
		streetName = streetnm.nextLine();

		return streetName;
	}

	public String addProprtySuburb() {
		String suburb;
		boolean suburb_valid = true;

		// ask for streetNumber

		System.out.println("Enter Suburb (Must be mel)");

		// scanner object
		Scanner sub = new Scanner(System.in);
		do {
			suburb = sub.nextLine();

			if ("mel".equals(suburb)) {
				suburb_valid = false;
			} else {
				System.out.println("Must be \"mel\"");
			}

		} while (suburb_valid);

		return suburb;
	}

	public String addProprtybeds() {
		String beds;
		boolean beds_valid = true;

		// ask for streetNumber

		System.out.println("Enter No. of Beds");

		// scanner object
		Scanner bed = new Scanner(System.in);
		do {
			beds = bed.nextLine();

			// check if number is digit
			if (isNumeric(beds)) {

				int beeds = Integer.parseInt(beds);

				// no. of beds must be from 1 to 3

				if (beeds > 0 && beeds < 4) {

					// converting back to string

					beds = Integer.toString(beeds);
					beds_valid = false;
				} else {
					System.out.println("No. of Beds must be in 1 to 3(inclusive)");
				}

			}

			else {
				System.out.println("No. of Beds must be in 1 to 3(inclusive)");
			}

		} while (beds_valid);

		return beds;
	}

	// method to check if String is number
	public boolean isNumeric(String strNum) {
		return strNum.matches("\\d+?");
	}

	public String lastmaintenanceDate() {

		String lmd;
		boolean last_main_date_valid = true;
		Scanner obje = new Scanner(System.in);
		do {
			lmd = obje.nextLine();

			DateTimeController dateValidate = new DateTimeController();
			if (dateValidate.dateValidate(lmd)) {
				last_main_date_valid = false;
			} else {
				System.out.println("Date must be accurate and in dd/mm/yyyy");
			}
		}

		while (last_main_date_valid);

		return lmd;

	}

	// get Free index of array to store data
	public int arrayFreeIndex(Object[] arr) {

		int openArray = 0;

		if (arr instanceof property[]) {
			for (int i = 0; i < arr.length; i++) {

				if (arr[i] == null) {
					openArray = i;
					break;
				}
			}
		}

		else if (arr instanceof modalFlexi.rentalRecord[]) {
			for (int i = 0; i < arr.length; i++) {

				if (arr[i] == null) {
					openArray = i;
					break;
				}
			}
		}

		else {

			for (int i = 0; i < arr.length; i++) {

				if (arr[i] == null) {
					openArray = i;
					break;
				}
				// check full array condition

				else if (arr[(arr.length) - 1] != null) {
					// sending 11 as index which doesn't exist in array
					openArray = 11;
					break;
				}
			}
		}
		return openArray;
	}

	// method for checking if property exists

	public boolean propertyExists(ObservableList<property> arr, String pro_id) {
		boolean exist = false;

		for (int i = 0; i < arr.size(); i++) {
			// check if data in array
			// if(arr[i] != null) {
			if (pro_id.equals(arr.get(i).getProperty_id())) {
				// found property
				exist = true;
				break;
			}
			// }
			// if delete then need to shift. Plz check if we have delete functionality
			// else {
			// break;
			// }

		}

		return exist;
	}

	// method of checking property status
	public String checkPropertyAvailableStatus(ObservableList<property> allprop, String proID, String val) {
		// 3rd parameter is to check whether its status or any other like lastmainenance
		// etc.
		// must have a status and property because exists conditions already checked
		// before

		for (int i = 0; i < allprop.size(); i++) {

			// System.out.println(allProperties);

			if (proID.equals(allprop.get(i).getProperty_id())) {
				// System.out.println(proID);
				if ("status".equals(val)) {
					// get property status and return
					return allprop.get(i).getProperty_status();
				} else if ("maintenancedate".equals(val)) {
					// get property last maintenance status and return

					// System.out.println(allProperties[i].getLastMaintenanceDate() );

					return allprop.get(i).getLastMaintenanceDate();

				} else if ("index".equals(val)) {
					// returning index value after [parsing
					return Integer.toString(i);
				}

				else if ("latestrent".equals(val)) {
					return allprop.get(i).getRecords()[0].getRentDate();
				}
			}

		}

		return null;
	}

	// method for getting user Input
	public String userInput() {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		return input;
	}

//	public String addRentDate(String propID, String val) {
//		String inputDate;
//
//		boolean rentDate_valid = true;
//
//		do {
//			System.out.println(val + " date (dd/mm/yyyy)");
//
//			inputDate = userInput();
//
//			if (validateRentdate(inputDate, propID)) {
//
//				rentDate_valid = false;
//			} else {
//				System.out.println("Date must be correct, greater than today date and in dd/mm/yyyy format.");
//
//			}
//		} while (rentDate_valid);
//
//		return inputDate;
//	}

	public boolean validateRentdate(String rentDate, String propertyID) throws RentExceptions {
		// boolean rentDate_valid = true;

		// valid date
		DateTimeController obj = new DateTimeController();

		if (obj.dateValidate(rentDate)) {

			// if rent date is greater than or equal to current date
			String[] rentDateparts = rentDate.split("/");

			String[] currentDate = obj.getFormattedDate().split("/");

			DateTimeController rentDateobj = new DateTimeController(Integer.parseInt(rentDateparts[0]),
					Integer.parseInt(rentDateparts[1]), Integer.parseInt(rentDateparts[2]));

			DateTimeController currentDateobj = new DateTimeController(Integer.parseInt(currentDate[0]),
					Integer.parseInt(currentDate[1]), Integer.parseInt(currentDate[2]));

			// equal or greater than todays date
			if (obj.diffDays(rentDateobj, currentDateobj) >= 0) {
				// System.out.println(obj.diffDays(rentDateobj, currentDateobj));
				return true;
			} else {
				// System.out.println(obj.diffDays(rentDateobj, currentDateobj));
				throw new RentExceptions("Rent Date must be greater than today's date.");
			}

		}

		return false;

	}

	public boolean validateRentdays(String days, int min_days, String propertyID,
			ObservableList<property> allProperties, String rentDate) throws RentExceptions {

		if (isNumeric(days)) {

			// parsing no_of_days

			int days_no = Integer.parseInt(days);

			// check last maintenance if premium Suite
			if (propertyID.charAt(0) == 'S') {

				// max days 10
				if (days_no >= 1 && days_no <= 10) {
					// get last maintenance date

					// System.out.println(propertyID);
					String last_m_d = checkPropertyAvailableStatus(allProperties, propertyID, "maintenancedate");
					// System.out.println("from rent to validate1");
					// get next maintenance date
					DateTimeController dateobj = new DateTimeController();
					String nxt_maintenance_date = dateobj.increaseDate(last_m_d, 10);
					// System.out.println(nxt_maintenance_date);

					// get returning date from no_of_days
					String returningDate = dateobj.increaseDate(rentDate, days_no);

					// System.out.println(returningDate);

					if (dateobj.compareDates(nxt_maintenance_date, returningDate)) {
						// System.out.println("you can rent me");

						return true;
					} else {
						// System.out.println("You can't rent this property.");
						//throw new RentExceptions("You can't rent this property. Check Maintenance Date and then set your values");
						throw new RentExceptions("You can't rent this property. Next maintenance for this property is "+nxt_maintenance_date);
						//return false;
					}

				}
				else {
					throw new RentExceptions("Premium Suite must be in " + min_days + " to 10(inclusive)");
				}

			} else {
				if (days_no >= min_days && days_no <= 28) {
					return true;
				} else {

					throw new RentExceptions("Apartment must be in " + min_days + " to 28(inclusive)");
				}

			}

		}

		return false;

	}

//	public String addrentdays(int min_no_days, String propID, ObservableList<property> allProperties, String rentDate) {
//
//		boolean add_days_valid = true;
//		String no_of_days;
//		Scanner sc = new Scanner(System.in);
//		do {
//			System.out.println("How many days? or exit to leave the program");
//
//			no_of_days = sc.nextLine();
//
//			if ("exit".equals(no_of_days)) {
//				System.out.println("You are exit now.");
//				no_of_days = "0";
//				add_days_valid = false;
//			} else {
//
//				if (validateRentdays(no_of_days, min_no_days, propID, allProperties, rentDate)) {
//
//					add_days_valid = false;
//				}
//
//				else {
//					// if apartment then different output
//					if (propID.charAt(0) == 'A') {
//						System.out.println("must be in " + min_no_days + " to 28(inclusive)");
//					} else {
//						System.out.println("must be in 1 to 10(inclusive) and less than next maintenance date");
//					}
//				}
//			}
//			// validating days
//
//		} while (add_days_valid);
//
//		return no_of_days;
//	}

}
