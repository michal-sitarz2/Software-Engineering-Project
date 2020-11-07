package com.com1028.assignment;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

public class App {
	
	/** Analytics object which contains all the necessary data*/
	private Analytics analytics;
	
	/** Decimal format object used to round the amount to two decimal places*/
	private DecimalFormat df = new DecimalFormat("0.00"); 
	
	/**
	 * Default constructor without any parameters, that initialises the 
	 * Analytics object with the username and password
	 */
	public App() {
		this.analytics = new Analytics("root", "");
	}
	
	/**
	 * (User requirement 1)
	 * 
	 * Method which allows a date as an input in the parameters, and the it sums up
	 * the total amount that was payed on that specific day.
	 * 
	 * @param date
	 * @return the amount of money payed on a particular day
	 * 
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public double getTotalCustomerPaymentsFor(String date) throws NullPointerException, IllegalArgumentException {
		double total = 0;
		List<Customer> data = null;
		
		if(date == null) {
			throw new NullPointerException("The date has to be defined.");
		}
		
		String date_regex = "[12][0-9]{3}-[0-9][0-2]{0,1}-[0-3][0-9]{0,1}";
		
		if(!Pattern.matches(date_regex, date)) {
			throw new IllegalArgumentException("Incorrect date format; please use: YYYY-MM-DD");
		}
		
		//using the Analytics object to get the list of the customers from the database 
		data = this.analytics.getCustomers();
		
		/** Iterating through all the customers in the database as they contain the list of all payments*/
		for(Customer customer : data) {
			/** Iterating through all the payments made by customers*/
			for(Payment payment : customer.getPayments()) {
				/** Comparing the payment date passed as the parameter to all the payment dates in the payment */
				if(payment.getPaymentDate().toString().contentEquals(date)) {
					// summing up the amount of payments on that date 
					total += payment.getAmount();
				}
			}
		}
		return total;
	}
	
	
	/**
	 * (User requirement 2)
	 * 
	 * Method that gets total amount of payments that each customer has made
	 * and then outputs all the customers with their total prices.
	 * 
	 * @return a string of all the total payments that the customers made,
	 * along with the name of that customer.
	 */
	public String getTotalPayments() {
		// Using the analytics object to get the list of all customers from the database
		List<Customer> customers =  analytics.getCustomers();
		StringBuffer buffer = new StringBuffer();
		/** Iterating through all the customers */
		for(Customer customer : customers) {
			// Adding to the output all the customers along with their total payments (calculated in the customer class)
			buffer.append(customer.getCustomerName() + ", Total: $" + df.format(customer.getTotalPayments()) + "\n");	
			
		}
		return buffer.toString();
	}
	
	/**
	 * @return a string of all the products (with the order number they have been sold in, and the price at which they were sold)
	 * that have been sold under 80% of MSRP for that product
	 */
	public String productsSoldUnder_80percent_MSRP(){
		StringBuffer buffer = new StringBuffer();
		/** Iterates through all the order details which are sent from the analytics object which store all this data from the database */
		for(OrderDetails o : analytics.getOrderDetails()) {
			/** Calling the method from order details class which returns true if the price is lower*/
			if(o.comparePrice()) {
				buffer.append(o.getProduct().getProductName() + " (Order Number: " + o.getOrderNumber() + ") -> Sold for: $" + o.getPriceEach() + "\n");
			}
		}
		return buffer.toString();
	}
	
	
	
}
