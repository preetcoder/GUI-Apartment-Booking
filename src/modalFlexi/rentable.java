/**
 * 
 */
package modalFlexi;

import Exceptions.MaintenanceExceptions;
import Exceptions.RentExceptions;
import Exceptions.ReturnException;
import controllerFlexi.DateTimeController;

public interface rentable {
	
	void rent(String customerId, DateTimeController rentDate, int numOfRentDay) throws RentExceptions;
	
	void returnProperty(DateTimeController returnDate)  throws ReturnException;
	
}
