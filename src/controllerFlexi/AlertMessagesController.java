/**
 * 
AlertMessagesController.java
22 Sep. 2018
 */
package controllerFlexi;

import java.time.LocalDate;
import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

/**
 * @author Harpreet
 *
 *This class will show alert messages
 *
 */
public class AlertMessagesController {
	
	
	// Show a Information Alert without Header Text
    public AlertMessagesController(String message, String title) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(message);
 
        alert.showAndWait();
    }
    
   
	
}
