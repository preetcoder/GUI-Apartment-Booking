/**
 * 
LoadCompleteMaintenance.java
23 Sep. 2018
 */
package controllerFlexi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Harpreet
 *
 */
public class LoadCompleteMaintenance {
	
	public void pressButton() throws Exception {               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewFlexi/completeMaintenance.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }
}

}
