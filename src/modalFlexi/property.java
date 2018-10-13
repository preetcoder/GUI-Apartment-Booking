/**
 * 
 */
package modalFlexi;

import controllerFlexi.DateTimeController;

/**
 *
 */
public abstract class property implements rentable, maintenable{
	
	// instance variables
	
	private String property_id;
	
	private String street_name;
	
	private String street_no;
	
	private String suburb;
	
	private String no_of_beds;
	
	private String property_type;
	
	private String property_status;		// available, rented, maintenance	
	
	private String lastMaintenanceDate;		// using DateTimeController class but making string to save
	
	private String description;
	
	private String image;
	
	protected rentalRecord[] records = new rentalRecord[10];
	
	/**
	 * if we dont have property status, lastMaintenanceDate data usually adding new  property - Apartment without rental record
	 * @param property_id
	 * @param street_name
	 * @param street_no
	 * @param suburb
	 * @param no_of_beds
	 * @param property_type
	 * @param description
	 * @param image
	 */
	public property(String property_id, String street_name, String street_no, String suburb, String no_of_beds,
			String property_type, String description, String image) {
		
		this.property_id = property_id;
		this.street_name = street_name;
		this.street_no = street_no;
		this.suburb = suburb;
		this.no_of_beds = no_of_beds;
		this.property_type = property_type;
		this.property_status = "available";
		this.description = description;
		this.image = image;
		
		// setting current Date as first maintenance date
		DateTimeController ob = new DateTimeController();	
		this.lastMaintenanceDate = ob.getFormattedDate();	
	}
	
	// if we dont have property status usually adding new  property - premium suite without rental record
	
	
	public property(String property_id, String street_name, String street_no, String suburb, String no_of_beds,
			String property_type, String lastmaintenance, String description, String image) {
		
		this.property_id = property_id;
		this.street_name = street_name;
		this.street_no = street_no;
		this.suburb = suburb;
		this.no_of_beds = no_of_beds;
		this.property_type = property_type;
		this.property_status = "available";	
		this.lastMaintenanceDate = lastmaintenance;	
		this.description = description;
		this.image = image;
	}

	/**
	 * if we have property status, lastMaintenanceDate  from import - premium Suite with rental record
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
	public property(String property_id, String street_name, String street_no, String suburb, String no_of_beds,
			String property_type, String property_status, String lastMaintenanceDate, String description, String image, rentalRecord[] records_val) {
		this.property_id = property_id;
		this.street_name = street_name;
		this.street_no = street_no;
		this.suburb = suburb;
		this.no_of_beds = no_of_beds;
		this.property_type = property_type;
		this.property_status = property_status;
		this.lastMaintenanceDate = lastMaintenanceDate;
		this.description = description;
		this.image = image;
		this.records = records_val;
	}

	/**
	 *  if we have property status only, no lastMaintenanceDate  from import - Apartment with rental record
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
	public property(String property_id, String street_name, String street_no, String suburb, String no_of_beds,
			String property_type, String property_status, String description, String image, rentalRecord[] records_val) {
		this.property_id = property_id;
		this.street_name = street_name;
		this.street_no = street_no;
		this.suburb = suburb;
		this.no_of_beds = no_of_beds;
		this.property_type = property_type;
		this.property_status = property_status;
		
		// setting current Date as first maintenance date
				DateTimeController ob = new DateTimeController();
		this.lastMaintenanceDate = ob.getFormattedDate();
		
		this.description = description;
		this.image = image;
		this.records = records_val;
	}

	
	/**
	 * @return the property_id
	 */
	public String getProperty_id() {
		return property_id;
	}

	/**
	 * @param property_id the property_id to set
	 */
	public void setProperty_id(String property_id) {
		this.property_id = property_id;
	}

	/**
	 * @return the street_name
	 */
	public String getStreet_name() {
		return street_name;
	}

	/**
	 * @param street_name the street_name to set
	 */
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}

	/**
	 * @return the street_no
	 */
	public String getStreet_no() {
		return street_no;
	}

	/**
	 * @param street_no the street_no to set
	 */
	public void setStreet_no(String street_no) {
		this.street_no = street_no;
	}

	/**
	 * @return the suburb
	 */
	public String getSuburb() {
		return suburb;
	}

	/**
	 * @param suburb the suburb to set
	 */
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	/**
	 * @return the no_of_beds
	 */
	public String getNo_of_beds() {
		return no_of_beds;
	}

	/**
	 * @param no_of_beds the no_of_beds to set
	 */
	public void setNo_of_beds(String no_of_beds) {
		this.no_of_beds = no_of_beds;
	}

	/**
	 * @return the property_type
	 */
	public String getProperty_type() {
		return property_type;
	}

	/**
	 * @param property_type the property_type to set
	 */
	public void setProperty_type(String property_type) {
		this.property_type = property_type;
	}

	/**
	 * @return the property_status
	 */
	public String getProperty_status() {
		return property_status;
	}

	/**
	 * @param property_status the property_status to set
	 */
	public void setProperty_status(String property_status) {
		this.property_status = property_status;
	}

	/**
	 * @return the lastMaintenanceDate
	 */
	public String getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}
	
	/**
	 * @param lastMaintenanceDate the lastMaintenanceDate to set
	 */
	public void setLastMaintenanceDate(String lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}
	

	/**
	 * @return the records
	 */
	public rentalRecord[] getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public void setRecords(rentalRecord[] records) {
		this.records = records;
	}
	

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	public abstract String getDetails();

	
	
	
}
