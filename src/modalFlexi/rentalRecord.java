/**
 * 
 */
package modalFlexi;

import java.util.*;

public class rentalRecord {

	// instance variables
	private String recordID;
	private String rentDate;
	private String customerID;
	private String estReturnDate;
	private String actReturnDate;
	private double rentFee;
	private double lateFee;

	/**
	 * @param recordID
	 * @param rentDate
	 * @param customerID
	 * @param estReturnDate
	 * @param actReturnDate
	 * @param rentFee
	 * @param lateFee
	 */
	public rentalRecord(String recordID, String rentDate, String customerID, String estReturnDate, String actReturnDate,
			double rentFee, double lateFee) {
		// super();
		this.recordID = recordID;
		this.rentDate = rentDate;
		this.customerID = customerID;
		this.estReturnDate = estReturnDate;
		this.actReturnDate = actReturnDate;
		this.rentFee = rentFee;
		this.lateFee = lateFee;
	}

	// constructor when renting property
	public rentalRecord(String recordID, String rentDate, String customerID, String estReturnDate) {
		// super();
		this.recordID = recordID;
		this.rentDate = rentDate;
		this.customerID = customerID;
		this.estReturnDate = estReturnDate;
		this.actReturnDate = ""; // not returned yet
		this.rentFee = 0.00; // fee 0
		this.lateFee = 0.00; // fee 0
	}

	// getDetails method

	/**
	 * @return the recordID
	 */
	public String getRecordID() {
		return recordID;
	}

	/**
	 * @param recordID
	 *            the recordID to set
	 */
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}

	/**
	 * @return the rentDate
	 */
	public String getRentDate() {
		return rentDate;
	}

	/**
	 * @param rentDate
	 *            the rentDate to set
	 */
	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}

	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID
	 *            the customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * @return the estReturnDate
	 */
	public String getEstReturnDate() {
		return estReturnDate;
	}

	/**
	 * @param estReturnDate
	 *            the estReturnDate to set
	 */
	public void setEstReturnDate(String estReturnDate) {
		this.estReturnDate = estReturnDate;
	}

	/**
	 * @return the actReturnDate
	 */
	public String getActReturnDate() {
		return actReturnDate;
	}

	/**
	 * @param actReturnDate
	 *            the actReturnDate to set
	 */
	public void setActReturnDate(String actReturnDate) {
		this.actReturnDate = actReturnDate;
	}

	/**
	 * @return the rentFee
	 */
	public double getRentFee() {
		return rentFee;
	}

	/**
	 * @param rentFee
	 *            the rentFee to set
	 */
	public void setRentFee(double rentFee) {
		this.rentFee = rentFee;
	}

	/**
	 * @return the lateFee
	 */
	public double getLateFee() {
		return lateFee;
	}

	/**
	 * @param lateFee
	 *            the lateFee to set
	 */
	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}

	public String getDetails() {
		String rentalRecordDetails;
		if ("".equals(this.getActReturnDate())) {
			rentalRecordDetails = "Record ID : \t" + this.getRecordID() + " \n Rent Date \t: " + this.getRentDate()
					+ " \n Estimated Return Date:\t" + this.getEstReturnDate();

		} else {
			rentalRecordDetails = "Record ID : \t" + this.getRecordID() + " \n Rent Date \t: " + this.getRentDate()
					+ " \n Estimated Return Date:\t" + this.getEstReturnDate() + "\n Actual Return Date:\t"
					+ this.getActReturnDate() + "\n Rental fee:\t" + String.format("%1.2f", this.getRentFee())
					+ "\n Late Fee: \t" + String.format("%1.2f", this.getLateFee());

		}
		return rentalRecordDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return getRecordID() + ":" + getRentDate() + ":" + getEstReturnDate() + ":" + getActReturnDate() + ":"
				+ getRentFee() + ":" + String.format("%1.2f", getLateFee());
	}

}
