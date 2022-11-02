package csg.tests;

import payloads.CreateBooking;
import payloads.UpdateBooking;

public class PlayArea {

	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		
	String req = CreateBooking.getPayLoad("Ravi","Kumar", "1200", "false","2022-10-31", "2022-11-03", "Parking");
	
	System.out.println(req);
	req = UpdateBooking.getPayLoad("Gopi","Mohan", "1245", "true","2022-11-02", "2022-11-06", "Parking, Breakfast");
	
	System.out.println(req);

	}

}
