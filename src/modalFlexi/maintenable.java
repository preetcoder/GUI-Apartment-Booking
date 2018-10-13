/**
 * 
 */
package modalFlexi;

import Exceptions.MaintenanceExceptions;
import controllerFlexi.DateTimeController;

public interface maintenable {
	
	 void performMaintenance() throws MaintenanceExceptions;
	
	 void  completeMaintenance(DateTimeController completionDate) throws MaintenanceExceptions;;
	
}
