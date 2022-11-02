Feature: Bookings Test

 	
  #@smoke
 #Scenario: Create Booking and Verify booking Details
  #	Given I create booking with below details
    #|firstname   |lastname   |totalprice|checkin    |checkout   |additionalneeds|
    #|Rohit       |Sharma     |4300      |2022-10-06 |2022-10-08 |Parking        |
    #When I retrieve booking details
    #Then verify booking details
  #
  #@smoke
  #Scenario: Get All Booking Details
  #	Given I hit Get Booking All details request
  #	When Status code is "200"
  #	Then verify booking details 
	
	@smoke
  Scenario: Create Booking
  	Given I hit Create Booking request
  	When Status code is "200"
  	Then verify booking details 
    
  @smoke
  Scenario: Update Booking
  	Given I hit Update Booking request for booking with "ID"
  	When Status code is "200"
  	Then verify updated booking details 
  
  @smoke
  Scenario: Delete Booking
  	Given I hit delete Booking request for booking with "ID" 
  	When Status code is "201"
  	Then verify deleted booking not exists 
       