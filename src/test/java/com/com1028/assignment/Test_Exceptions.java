package com.com1028.assignment;

import java.sql.Date;
import org.junit.Test;

public class Test_Exceptions {

	@Test(expected = NullPointerException.class)
	public void testInvalidOrderDetailsCreation() {
		Product product = null;
		
		OrderDetails od = new OrderDetails(10100, product, 30, 136.00, 3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIncorrectPaymentAdd() {
		Payment payment = new Payment(12345, "STR-213", new Date(2000,10,20), 213.00);
		
		Customer c = new Customer(103, "Atelier graphique", "Schmitt", "Carine", "40.32.2555", "54, rue Royale", null, "Nantes", null, "44000", "France", 1370, 21000.00);
		
		c.addPayment(payment);
		c.addPayment(payment);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullDateInput() {
		App app = new App();
		
		String date = null;
		app.getTotalCustomerPaymentsFor(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIncorrectDateFormat() {
		String date = "10-12-1999";
		
		App app = new App();
		
		app.getTotalCustomerPaymentsFor(date);
	}

}
