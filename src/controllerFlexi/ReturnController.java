/**
 * 
ReturnController.java
30 Sep. 2018
 */
package controllerFlexi;


import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Exceptions.RentExceptions;
import Exceptions.ReturnException;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import modalFlexi.property;

/**
 * @author Harpreet
 *
 *This class will deal with property return operations
 */
public class ReturnController implements Initializable{
	
	private static property propertyData;
	
	
	
	@FXML
	private DatePicker returnDate;
	
	@FXML
	private Button returnPropertySave;
	
	@FXML
	public void getReturnPropertyVal(ActionEvent e2 ) {
		
		try {
			getDate( returnDate.getValue());
			
			// get single property stage
			final Stage parent = (Stage) SinglePropertyController.getSingleStageName().getScene().getWindow();
			// auto close 
			MainController.autoclose(e2, parent);
			
		} catch (ReturnException e) {
			// TODO Auto-generated catch block
			AlertMessagesController exceptionobj = new AlertMessagesController(e.getMessage(),"Failure:(");
		}
		
		
		
		
	}
	
	/**
	 * @return the propertyData
	 */
	public static property getPropertyData() {
		return propertyData;
	}



	/**
	 * @param propertyData the propertyData to set
	 */
	public static void setPropertyData(property propertyData) {
		ReturnController.propertyData = propertyData;
	}
	
	public void ReturnMe(property data)   throws ReturnException{
		
		// setting private instancxe variables

		setPropertyData(data);
        
        //System.out.println(getThislastMaintenancedate() + "inn");
        
		if("rented".equals(data.getProperty_status())) {
			
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewFlexi/ReturnProperty.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
           
           
            stage.setTitle("Property Operations");
            stage.setScene(new Scene(root1));  
            stage.show();
            

            
			}
		catch(Exception e) {
	           e.printStackTrace();
	          }
		}
		else {
			throw new ReturnException("Property Couldn't complete  Return operation.");
			
		}
	}
	
public void getDate(LocalDate date) throws ReturnException{
		
		// Getting date in String format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate dateVal = date;
		
		String stringDateVal = formatter.format(dateVal);
		
		
		// make dateTime object of string date
		String[] correctCompletionDateparts = stringDateVal.split("/");
		
		DateTimeController dateobj = new DateTimeController(Integer.parseInt(correctCompletionDateparts[0]), Integer.parseInt(correctCompletionDateparts[1]), Integer.parseInt(correctCompletionDateparts[2]));
		
			try {
				propertyData.returnProperty(dateobj);
			} catch (ReturnException e) {
				// TODO Auto-generated catch block
				throw new ReturnException(e.getMessage());
			} 
		
	
		
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		validateScene();
	}
	
	public void validateScene() {
		//completeperformanacesave.setDisable(true);
		returnDate.setEditable(false);

		// binding for button diabled 
		returnPropertySave.disableProperty().bind(
			    Bindings.isNull((returnDate.valueProperty()))
			);
		
//    
	}

	
	
}
