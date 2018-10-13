/**
 * 
LoadNewPropertycontroller.java
22 Sep. 2018
 */
package controllerFlexi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Harpreet
 *
 *	This class is generating new FXML from view and showing on frontend
 */
public class LoadNewPropertycontroller {
	
	


	public LoadNewPropertycontroller() {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewFlexi/addNewProperty.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();
	        Stage stage = new Stage();
	      
	        stage.setTitle("Add New Property");
	        stage.setScene(new Scene(root1));  
	        // setting stage ID as parent stage
	        
	        //stage.show();
	        stage.showAndWait();
			}
			catch(Exception exp) {
				exp.printStackTrace();
			}
	}
}
