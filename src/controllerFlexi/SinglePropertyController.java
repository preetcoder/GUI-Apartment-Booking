/**
 * 
SinglePropertyController.java
23 Sep. 2018
 */
package controllerFlexi;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Exceptions.MaintenanceExceptions;
import Exceptions.RentExceptions;
import Exceptions.ReturnException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import modalFlexi.importToDB;
import modalFlexi.property;

/**
 * @author Harpreet
 *
 */
public class SinglePropertyController implements Initializable{

	private static property PropertyDetails;
	
	private ObservableList<String> allPropertyRents = FXCollections.observableArrayList();
	
	// Stage Name to access publically for reloading
		private static Window SingleStageName ;
		
		
	

	@FXML
	private ImageView propertyImage;

	@FXML
	private Text propertyAddress, propertyBeds, propertyType, propertyLMD, propertyLMDlabel;

	
	@FXML
	private TextArea propertyDesc;
	
	@FXML
	private Button rentProperty, returnProperty, performMaintenanceProperty, completMaintenanceProperty;
	
	@FXML
	private ListView rentalRecord;
	
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//allPropertyRents.add(PropertyDetails.getRecords());
		
		rentalRecord.setItems(allPropertyRents);
		
	}
	
	
	/**
	 * @return the singleStageName
	 */
	public static Window getSingleStageName() {
		return SingleStageName;
	}

	/**
	 * @param singleStageName the singleStageName to set
	 */
	public static void setSingleStageName(Window singleStageName) {
		SingleStageName = singleStageName;
	}

	// get Details from calling controller
	public void getpropertyDetails(property singlePropertyDetails) {

		// setting my property variable
		importToDB allRentalforthis = new importToDB();
		ResultSet rentalRecordsforthisProp  = allRentalforthis.getRentalRecord(singlePropertyDetails.getProperty_id(),"all");
		
		//DisplayAllPropertiesController objeRent = new DisplayAllPropertiesController();
		//allPropertyRents.addAll(objeRent.rentalRecordbasedonProperID(rentalRecordsforthisProp));
		allPropertyRents.addAll(singlePropertyDetails.getDetails());
		

		
		
		
		PropertyDetails =  singlePropertyDetails;

		File file;
		// image src
		if ("".equals(singlePropertyDetails.getImage())) {

			file = new File("src/img/notfound.png");
		} else {
			file = new File("src/img/" + singlePropertyDetails.getImage());
		}

		Image image = new Image(file.toURI().toString());
		// Set Image with new URL

		propertyImage.setImage(image);

		// set Address
		propertyAddress.setText(singlePropertyDetails.getStreet_no() + " " + singlePropertyDetails.getStreet_name()
				+ " " + singlePropertyDetails.getSuburb());
		
		// Set Bedrooms
		
		propertyBeds.setText(singlePropertyDetails.getNo_of_beds());
		
		// Set Description
		
		propertyDesc.setText(singlePropertyDetails.getDescription());
		
		// set Property Type
		propertyType.setText(singlePropertyDetails.getProperty_type());
		
		// set last Maintenance Date
		if(singlePropertyDetails.getProperty_id().charAt(0) == 'A') {
			propertyLMDlabel.setVisible(false);
			propertyLMD.setVisible(false);
			
		}
		propertyLMD.setText(singlePropertyDetails.getLastMaintenanceDate());
		
		rentalRecord.setItems(allPropertyRents);
		
		
		
	}
	
	// on Rent click
		public void rentPropertyMethod(ActionEvent e) {
			
			
			// set stage name to close after operation
			final Node source = (Node) e.getSource();
						
			setSingleStageName((source.getParent()).getScene().getWindow());
						
			
			RentController object = new RentController();
			// load xml
			try {
				
				object.RentPropertyxml(PropertyDetails);
			} catch (RentExceptions e1) {
				// TODO Auto-generated catch block
				AlertMessagesController exceptionobj = new AlertMessagesController(e1.getMessage(),"Failure:(");
			}
		
		}
	
		// on Return click
		public void returnPropertyMethod(ActionEvent e) {
			
			// set stage name to close after operation
			final Node source = (Node) e.getSource();
			
			setSingleStageName((source.getParent()).getScene().getWindow());
			
			// calling return controller
			ReturnController obj = new ReturnController();
			
			try {
				obj.ReturnMe(PropertyDetails);
				
			} catch (ReturnException e1) {
				// TODO Auto-generated catch block
				AlertMessagesController exceptionobj = new AlertMessagesController(e1.getMessage(),"Failure:(");
			}
			
		}
		
		// on perform Maintenance click
		public void performMaintenancePropertyMethod(ActionEvent e) {
			
			PerformMaintenance obj = new PerformMaintenance();
			try {
				//obj.perform(PropertyDetails.getProperty_status(), PropertyDetails.getProperty_id());
				obj.perform(PropertyDetails);
				
				MainController.autoclose(e);
				
			} catch (MaintenanceExceptions e1) {
				// show alert for custom exception
				AlertMessagesController exceptionobj = new AlertMessagesController(e1.getMessage(),"Failure:(");
			}
			
		}
		
		// on complete Maintenance click
		public void completeMaintenancePropertyMethod(ActionEvent e)    throws Exception {
			// set stage name to close after operation
						final Node source = (Node) e.getSource();
						
						setSingleStageName((source.getParent()).getScene().getWindow());
			try {
				completeMaintenance obj = new completeMaintenance();
				obj.completeM(PropertyDetails);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				AlertMessagesController exceptionobj = new AlertMessagesController(e1.getMessage(),"Failure:(");
				
			}
			
		}
	
	

}
