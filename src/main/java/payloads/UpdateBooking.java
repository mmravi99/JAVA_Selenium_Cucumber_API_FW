package payloads;

public class UpdateBooking {
	
	public static String getPayLoad(String fn,String ln,String price,String deppd,String checkin, String checkout,String needs) {
		String req="{\r\n"
				+ "    \"firstname\" : \""+fn+"\",\r\n"
				+ "    \"lastname\" :\""+ln+"\",\r\n"
				+ "    \"totalprice\" : "+price+",\r\n"
				+ "    \"depositpaid\" : "+deppd+",\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \""+checkin+"\",\r\n"
				+ "        \"checkout\" : \""+checkout+"\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \""+needs+"\"\r\n"
				+ "}";
		
		return  req;
	}

}
