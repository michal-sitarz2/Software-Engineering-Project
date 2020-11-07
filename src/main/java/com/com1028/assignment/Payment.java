package com.com1028.assignment;

import java.sql.Date;

public class Payment {
	
	/** The number of the customer that made the payment */
	private Integer customerNumber; 
	
	/** The check number of the payment */
	private String checkNumber; 
	
	/** The date when the payment was made */
	private Date paymentDate;
	
	/** The amount that was payed by that customer */
	private Double amount;
	
	/**
	 * Default constructor initialising all the fields.
	 * 
	 * @param customerNumber
	 * @param checkNumber
	 * @param paymentDate
	 * @param amount
	 */
	public Payment(Integer customerNumber, String checkNumber, Date paymentDate, Double amount) {
		super();
		this.customerNumber = customerNumber;
		this.checkNumber = checkNumber;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	/**
	 * @return the number of the customer that made this payment
	 */
	public Integer getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @return the check number of the payment
	 */
	public String getCheckNumber() {
		return checkNumber;
	}

	/**
	 * @return the date when the payment was conducted
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @return the amount payed by the customer
	 */
	public Double getAmount() {
		return amount;
	}
	
	/**
	 * @return structured information about the payment
	 */
	@Override
	public String toString() {
		return this.getCheckNumber() + "       " + this.getPaymentDate() + "       " + this.getAmount();
	}
	
	
	
	
	
}
