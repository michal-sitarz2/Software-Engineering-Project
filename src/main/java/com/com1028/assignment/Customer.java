package com.com1028.assignment;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	/** Number of the customer*/
	private Integer customerNumber;
	
	/** Name of the customer*/
	private String customerName;
	
	/** Last name when contacting the customer*/
	private String contactLastName;
	
	/** First name when contacting the customer*/
	private String contactFirstName;
	
	/** Phone number used to contact the customer*/
	private String phone;
	
	/** The first line of the address of the customer*/
	private String addressLine1;
	
	/** The second line of the address of the customer*/
	private String addressLine2;
	
	/** The city in which the customer is located*/
	private String city;
	
	/** The state in which the customer is located*/
	private String state;
	
	/** The postal code of the location where the customer is located*/
	private String postalCode;
	
	/** The country in which the customer is located*/
	private String country;
	
	/** Sales Representative Employee Number*/
	private Integer salesRepEmployeeNumber;
	
	/** Credit limit of the customer*/
	private Double creditLimit;
	
	/** List of all payments that the customer has done*/
	private List<Payment> payments;
	
	/**
	 * A constructor taking in all of the customer information and initialising the fields.
	 * 
	 * @param customerNumber
	 * @param customerName
	 * @param contactLastName
	 * @param contactFirstName
	 * @param phone
	 * @param addressLine1
	 * @param addressLine2
	 * @param city
	 * @param state
	 * @param postalCode
	 * @param country
	 * @param salesRepEmployeeNumber
	 * @param creditLimit
	 */
	public Customer(Integer customerNumber, String customerName, String contactLastName, String contactFirstName,
			String phone, String addressLine1, String addressLine2, String city, String state, String postalCode,
			String country, Integer salesRepEmployeeNumber, Double creditLimit) {
		super();		 
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.contactLastName = contactLastName;
		this.contactFirstName = contactFirstName;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
		this.creditLimit = creditLimit;
		
		// Initialising the list of payments
		this.payments = new ArrayList<Payment>();
	}

	/**
	 * Method used to add payments to the specific customer.
	 * 
	 * @param payment
	 * @throws IllegalArgumentException
	 */
	public void addPayment(Payment payment) throws IllegalArgumentException {
		/** If the customer already has the payment exception is thrown because we don't want customers to have record of two or more same payments*/
		if(!this.payments.contains(payment)) {
			//adding the payment
			this.payments.add(payment);
		} else {
			throw new IllegalArgumentException("This payment was already added for this customer");
		}
	}
	
	/**
	 * @return sum of all the payments that the customer has saved
	 */
	public Double getTotalPayments() {
		Double total = 0.0;
		/** Iterating through all the payments saved for this customer and summing them up*/
		for(Payment pay : this.payments) {
			total+= pay.getAmount();
		}
		return total;
	}
	
	/**
	 * @return list of the payments made by customer
	 */
	public List<Payment> getPayments(){
		return this.payments;
	}
	
	/**
	 * @return customer's number
	 */
	public Integer getCustomerNumber() {
		return customerNumber;
	}
	
	/**
	 * @return customer's name
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @return last name when contacting the customer
	 */
	public String getContactLastName() {
		return contactLastName;
	}

	/**
	 * @return first name when contacting the customer
	 */
	public String getContactFirstName() {
		return contactFirstName;
	}
	
	/** 
	 * @return the phone number of the customer
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the first line of the address of the customer
	 */
	public String getAddressLine1() {
		return addressLine1;
	}
	
	/**
	 * @return the second line of the address of the customer
	 */
	public String getAddressLine2() {
		return addressLine2;
	}
	
	/**
	 * @return the city in which the customer is situated
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * @return the state in which the customer is situated
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the postal code of the location where the customer is situtated
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @return the country in which the customer is situated in 
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the sales representative employee number
	 */
	public Integer getSalesRepEmployeeNumber() {
		return salesRepEmployeeNumber;
	}

	/**
	 * @return the credit limit of the customer
	 */
	public Double getCreditLimit() {
		return creditLimit;
	}
	
	/**
	 * @return formatted address of the customer
	 */
	public String getAddress() {
		return this.addressLine1 + " (Address Line 2: " + this.getAddressLine2() + "), "
				+ this.getCity() + ", " + this.getState() + " (" + this.getPostalCode() + ") " + this.getCountry();
				
	}
	
	/**
	 * @return formatted information about the customer
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.getCustomerName() + " (" + this.getCustomerNumber() + ")\n");
		buffer.append("Contact: " + this.getContactFirstName() + " " + this.getContactLastName() + ", Phone: " + this.getPhone());
		buffer.append( "\nAddress: " + this.getAddress());
		buffer.append("\nSales Rep Employee Number: " + this.getSalesRepEmployeeNumber());
		buffer.append("\nCredit Limit: " + this.getCreditLimit());
		if(this.payments.size()!=0) {
			buffer.append("\nPayments: ");
			for(Payment payment : this.payments) {
				buffer.append("\n"+ payment.toString());
			}
			buffer.append("\nTotal: " + this.getTotalPayments());
		}
		return buffer.toString();
	}
	
}
