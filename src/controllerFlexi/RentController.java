/**
 * 
RentController.java
25 Sep. 2018
 */
package controllerFlexi;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Exceptions.MaintenanceExceptions;
import Exceptions.RentExceptions;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modalFlexi.property;

/**
 * @author Harpreet
 *
 *	This class will deal with property Rent
 */
public class RentController  implements Initializable {
	
	private static property propertydata;
	
	@FXML
	private TextField userID, NoOfDays;
	
	@FXML
	private DatePicker rentDate;
	
	@FXML
	private Button rentsave;
	
	
	@FXML
	private void rentPropertySave(ActionEvent e2 ) {
		
		try {
			// check if no of days in integer
			//PropertyValidationsController validateNoOfDays = new PropertyValidationsController();
			if(ValidateNoOfDays(NoOfDays.getText())) {
				callRent(rentDate.getValue(),Integer.parseInt(NoOfDays.getText()), userID.getText());
				
				// get single property stage
				final Stage parent = (Stage) SinglePropertyController.getSingleStageName().getScene().getWindow();
				// auto close 
				MainController.autoclose(e2, parent);
			}
			else {
				AlertMessagesController exceptionobj = new AlertMessagesController("No. of Days should be number","Failure:(");
			}
			
			
		} catch (RentExceptions e) {
			// TODO Auto-generated catch block
			AlertMessagesController exceptionobj = new AlertMessagesController(e.getMessage(),"Failure:(");
		}
		
		
		// auto close 
//		final Node source = (Node) e2.getSource();
//		final Stage stage = (Stage) source.getScene().getWindow();
//		//System.out.println(source + "   " + stage);
//		stage.close();
		
		
	}
	
	private boolean ValidateNoOfDays(String DaysVal){
		
		PropertyValidationsController validateNoOfDays = new PropertyValidationsController();
		if(validateNoOfDays.isNumeric(DaysVal)) {
			return true;
		}
		
			return false;
			
		
		
	}
	
	
	// load my FXML
	
	public void RentPropertyxml(property data)   throws RentExceptions{
		
		
		// setting private instancxe variables
		

		this.propertydata = data;
        
       // System.out.println(propertydata.toString());
		
		
        
		if("available".equals(data.getProperty_status())) {
			
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewFlexi/rentProperty.fxml"));
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
			throw new RentExceptions("Property is already rented or under Maintenance.");
			
		}
	}
	
	private void callRent(LocalDate date, int noOfDays, String userID)  throws RentExceptions{
		
		// Getting date in String format
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				LocalDate dateVal = date;
				
				String stringDateVal = formatter.format(dateVal);
				
				
				// make dateTime object of string date
				String[] correctCompletionDateparts = stringDateVal.split("/");
				
				DateTimeController dateobj = new DateTimeController(Integer.parseInt(correctCompletionDateparts[0]), Integer.parseInt(correctCompletionDateparts[1]), Integer.parseInt(correctCompletionDateparts[2]));
				System.out.println(dateobj.toString()+"ccccccc");
				try {
					propertydata.rent(userID, dateobj, noOfDays);
					
					
				}
				catch(RentExceptions e) {
					throw new RentExceptions(e.getMessage());
				}
	}


	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		validateDate();
		
	}
	
	public void validateDate() {

		// binding for button diabled 
		rentsave.disableProperty().bind(
			   (rentDate.valueProperty().isNull()).or(userID.textProperty().isEmpty()).or(NoOfDays.textProperty().isEmpty())
			);
		
//    
	}
	

	
	

}
