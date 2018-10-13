/**
 * 
CloseStageController.java
15 Sep. 2018
 */
package controllerFlexi;

import javafx.application.Platform;
import util.DB;

/**
 * @author Harpreet
 *
 *		This class is to close the main Stage when user click on Close button
 */
public class CloseStageController {

	/**
	 * 
	 */
	public CloseStageController() {
		// closing connection after everything done
    	DB.closeConnection();
    	
		Platform.exit();
	}
	
		
}
