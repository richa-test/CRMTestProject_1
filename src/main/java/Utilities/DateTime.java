package Utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

	public String getDate(int days) {
		Date dt = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd'T'HHmmss");

		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, days);
		dt = c.getTime();

		System.out.println("Current Date: " + ft.format(dt));
		return  ft.format(dt).toString();
	}
	
}
