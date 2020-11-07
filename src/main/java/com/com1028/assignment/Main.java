/**
 * 
 * Main method used temporarily for now to test whether the connection works
 * 
 * 
 */
package com.com1028.assignment;


public class Main{

	public static void main(String[] args) {

		App app = new App();
		
		//Req. 1
		System.out.println("Total payments for 28 October 2004 : $" + app.getTotalCustomerPaymentsFor("2004-10-28"));
		
		System.out.println("\n\nAll customers' total payments:\n");
		
		//Req. 2
		System.out.println(app.getTotalPayments());
		
		System.out.println("\n\nProducts sold under 80% MSRP:\n");
		
		//Req. 3
		System.out.println(app.productsSoldUnder_80percent_MSRP());
	}
}

/**
 * Sequence diagram, alternative (NullPointerException)
 * 
 * SQLException test?
 * 
 * Constraints UML diagram, Aggregation, composition and association
 * 
 * 
 * 
Maven Project
3 marks

Marks	 Criteria
3	Testcases present which can be automatically run using the pom.xml file
1-2	pom.xml file in submission which can be used to build the project


Testing
3 marks x 3 (FAQs contain additional information).

Marks	Criteria
3	Test cases can be run and pass automatically and they use SQL queries (allowed to use any SQL keyword) to directly extract the expected results from the database and then compare it with results from own code.
2	Test cases can be run and pass automatically but make heavy use hardcoded strings without using SQL queries to build the expected results
 *
 */