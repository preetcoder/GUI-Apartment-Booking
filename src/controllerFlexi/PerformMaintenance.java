/**
 * 
PerformMaintenance.java
23 Sep. 2018
 */
package controllerFlexi;

import Exceptions.MaintenanceExceptions;
import modalFlexi.importToDB;
import modalFlexi.property;

/**
 * @author Harpreet
 *
 *This class will perform Maintenance on a property
 */
public class PerformMaintenance {
	
	public void perform(property data)    throws MaintenanceExceptions{
		//System.out.println(propertyStatus);
		try {
			data.performMaintenance();
		} catch (MaintenanceExceptions e) {
			// TODO Auto-generated catch block
			 throw new MaintenanceExceptions(e.getMessage());
		}
		

	}
}
