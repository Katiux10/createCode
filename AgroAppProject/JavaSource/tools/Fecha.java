package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {
	
	public static Date stringToDate(String fecha) {
		 try {
			return (new SimpleDateFormat("dd/MM/yyyy").parse(fecha));
			
		} catch (ParseException e) {
			return null;
		}  		 
	}
	
	public static String dateToString(Date fecha) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(fecha);
			
		}catch(Exception e) {
			return null;
		}  		 
	}

}
