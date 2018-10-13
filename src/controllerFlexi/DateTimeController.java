/**
 * 
 */
package controllerFlexi;

import java.text.SimpleDateFormat;
import java.util.*;

import Exceptions.ReturnException;

//import java.sql.Date;
import java.text.ParseException;
import java.text.ParsePosition;


public class DateTimeController
{
	
	private long advance;
	private long time;
	
	public DateTimeController()
	{
		time = System.currentTimeMillis();
	}
	
	public DateTimeController(int setClockForwardInDays)
	{
		advance = ((setClockForwardInDays * 24L + 0) * 60L) * 60000L;
		time = System.currentTimeMillis() + advance;
	}
	
	public DateTimeController(DateTimeController startDate, int setClockForwardInDays)
	{
		advance = ((setClockForwardInDays * 24L + 0) * 60L) * 60000L;
		time = startDate.getTime() + advance;
	}
	
	public DateTimeController(int day, int month, int year)
	{
		setDate(day, month, year);
	}
	
	public long getTime()
	{
		return time;
	}
	
	public String toString()
	{
//		long currentTime = getTime();
//		Date gct = new Date(currentTime);
//		return gct.toString();
		return getFormattedDate();
	}
	
	
	
	public static String getCurrentTime()
	{
		Date date = new Date(System.currentTimeMillis());  // returns current Date/Time
		return date.toString();
	}
	
	public String getFormattedDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		long currentTime = getTime();
		Date gct = new Date(currentTime);
		
		return sdf.format(gct);
	}
	
	public String getEightDigitDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		long currentTime = getTime();
		Date gct = new Date(currentTime);
		
		return sdf.format(gct);
	}
	
	// returns difference in days
	public static int diffDays(DateTimeController endDate, DateTimeController startDate)
	{
		final long HOURS_IN_DAY = 24L;
		final int MINUTES_IN_HOUR = 60;
		final int SECONDS_IN_MINUTES = 60;
		final int MILLISECONDS_IN_SECOND = 1000;
		long convertToDays = HOURS_IN_DAY * MINUTES_IN_HOUR * SECONDS_IN_MINUTES * MILLISECONDS_IN_SECOND;
		long hirePeriod = endDate.getTime() - startDate.getTime();
		double difference = (double)hirePeriod / (double)convertToDays;
		int round = (int)Math.round(difference);
		return round;
	}
	
	private void setDate(int day, int month, int year)
	{
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, 0, 0);   

		java.util.Date date = calendar.getTime();

		time = date.getTime();
	}
	
	// Advances date/time by specified days, hours and mins for testing purposes
		public void setAdvance(int days, int hours, int mins)
		{
			advance = ((days * 24L + hours) * 60L) * 60000L;
		}
		
		// my methods to test  validity
		
		public boolean dateValidate(String dat) {
			 try {
				 // array of date string
				 String[] myDate = dat.split("/");
				 // checking year if true. ParseException doesn't check for year value correctly.
				 PropertyValidationsController validate = new PropertyValidationsController();
				 if(!validate.isNumeric(myDate[2])) {
					 return false;
				 }
				 SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		            df.setLenient(false);
		            df.parse(dat);
		            return true;
		        } catch (ParseException e) {
		            return false;
		        }
			       //return df.parse(myDate, new ParsePosition(0)) != null;
			   
		}
		
		// dayName conversion
		public String getDayName(String dateval) {
			
			Date date = null;
	        try {
	            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateval);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
		}
		
		// increase date by any no of days
		
		public String increaseDate(String actualDate, int no_of_days) {
			SimpleDateFormat dateFormat = new SimpleDateFormat( "dd/MM/yyyy" );   
			Calendar cal = Calendar.getInstance();   
			    try{
			cal.setTime( dateFormat.parse(actualDate));    
			cal.add( Calendar.DATE, no_of_days );    
			String convertedDate=dateFormat.format(cal.getTime());    
			//System.out.println("Date increase by one.."+convertedDate);
			      return convertedDate;
			    }
			    catch(ParseException e){
			    //System.out.print("ssas");
			    return "invalid";
			    }
			  }
		
		
		// compare 2 dates
		// 1st must be large
		public boolean  compareDates(String maintenancedate,String returndate) {
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date1 = sdf.parse(maintenancedate);
				Date date2 = sdf.parse(returndate);
				
				if(date1.after(date2)){
	                return true;
	            }
				else {
					return false;
				}
			}
			catch(ParseException ex){
	            return false;
	        }
		}
		
		// compare 2 dates
				// 1st must be large
				public boolean  compareDatesVal(String date1,String date2)  throws ReturnException{
					
						if(compareDates(date1, date2)) {
							return true;
						}
						else {
							throw new ReturnException("Actual return Date must be greater than Rent Date.");
						}
					
					 //return false;
				}
				
}
