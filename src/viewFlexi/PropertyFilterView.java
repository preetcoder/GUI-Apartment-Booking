/**
 * 
PropertyFilterView.java
16 Sep. 2018
 */
package viewFlexi;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * @author Harpreet
 *
 *	This class is setting filter values 
 */
public class PropertyFilterView {
	
	private ComboBox<String> firstcomboBox = new ComboBox<String>();
	private ComboBox<String> secondcomboBox = new ComboBox<String>();
	
	// Array for storing filter values
	private static String[]  filtersvalue = {"Type","Apartment"};	// initial values
	
	public PropertyFilterView() {
		
		// setting initially
		
		firstcomboBox.getItems().addAll("Type","Bedrooms", "Status");
		firstcomboBox.setValue("Type");
		
		// setting 2nd filter values as 1st filter will be Type initially
		secondcomboBox.getItems().addAll("Apartment","Premium Suite");
		
		secondcomboBox.setValue("Apartment");
	
		
		
		// on value change handler
		firstcomboBox.setOnAction(new EventHandler<ActionEvent>() {
			
            @Override 
            public void handle(ActionEvent e) {
            	if("Bedrooms".equals(firstcomboBox.getValue())) {
            		
            		secondcomboBox.getSelectionModel().clearSelection();
            		secondcomboBox.getItems().clear();
            		secondcomboBox.getItems().addAll("1","2", "3");
            		secondcomboBox.setValue("1");
            			
            		// setting  filter value in array
            		
            		filtersvalue[0] = "Bedrooms";	
            		filtersvalue[1] = "1";	
            	}else if("Type".equals(firstcomboBox.getValue())){
            		
            		secondcomboBox.getSelectionModel().clearSelection();
            		secondcomboBox.getItems().clear();
            		secondcomboBox.getItems().addAll("Apartment","Premium Suite");
            		secondcomboBox.setValue("Apartment");
            			
            		// setting  filter value in array
            		filtersvalue[0] = "Type";	
            		filtersvalue[1] = "Apartment";	
            	}
            	else {
            		
            		secondcomboBox.getSelectionModel().clearSelection();
            		secondcomboBox.getItems().clear();
            		secondcomboBox.getItems().addAll("available", "rented", "maintenance");
            		secondcomboBox.setValue("available");
            				
            		// setting  filter value in array
            		filtersvalue[0] = "status";	
            		filtersvalue[1] = "available";	
            	}
            }
        });
		
		// 2nd filter handler
		// on value change handler
		secondcomboBox.setOnAction(new EventHandler<ActionEvent>() {
					
		            @Override 
		            public void handle(ActionEvent e) {
		            	
		            		filtersvalue[1] = secondcomboBox.getValue();	
		            	
		            }
		        });
	}
	
	
	/**
	 * @return the firstcomboBox
	 */
	public ComboBox<String> getFirstcomboBox() {
		return firstcomboBox;
	}

	/**
	 * @return the secondcomboBox
	 */
	public ComboBox<String> getSecondcomboBox() {
		return secondcomboBox;
	}

	// first filter
	public ComboBox<String> filter1() {
		firstcomboBox.setLayoutX(112.0);
		firstcomboBox.setLayoutY(44.0);
		firstcomboBox.setPrefWidth(150.0);
		return firstcomboBox;
	}
	
	// 2nd filter
	public ComboBox<String> filter2() {
		secondcomboBox.setLayoutX(298.0);
		secondcomboBox.setLayoutY(44.0);
		secondcomboBox.setPrefWidth(150.0);
		return secondcomboBox;
	}
	
	public String[] filtervalues() {
		
		return filtersvalue;
	}
}
