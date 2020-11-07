package com.com1028.assignment;

import java.math.BigDecimal;

public class OrderDetails {
	
	/** The number of the order*/
	private Integer orderNumber;
	
	/** The quantity that was ordered*/
	private Integer quantityOrdered;
	
	/** The price payed for a single product*/
	private Double priceEach;
	
	/** The order line number*/
	private Integer orderLineNumber;
	
	/** The product that was sold in the order with the order number*/
	private Product product;
	
	/**
	 * Default constructor taking in all the parameters and initialising all of them.
	 * 
	 * @param orderNumber
	 * @param product
	 * @param quantityOrdered
	 * @param priceEach
	 * @param orderLineNumber
	 * 
	 * @throws NullPointerException
	 */
	public OrderDetails(Integer orderNumber, Product product, Integer quantityOrdered, Double priceEach,
			Integer orderLineNumber) throws NullPointerException{
		super();
		this.orderNumber = orderNumber;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
		
		if(product==null) {
			throw new NullPointerException("A product must be defined.");
		}
		
		this.product = product;
	}

	/**
	 * @return the number of the order
	 */
	public Integer getOrderNumber() {
		return orderNumber;
	}
	
	/**
	 * @return the product sold in this order
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @return the quantity of the product ordered
	 */
	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}
	
	/**
	 * @return the price of a single quantity of product sold in this order
	 */
	public Double getPriceEach() {
		return priceEach;
	}

	/**
	 * @return the order line number
	 */
	public Integer getOrderLineNumber() {
		return orderLineNumber;
	}

	/**
	 * comparing the price of the product sold in this order,
	 * with the MSRP price in the Product class
	 * 
	 * @return true if the price is lower than 80% of MSRP, and false otherwise
	 */
	public boolean comparePrice() {
		// getting the MSRP price and getting only the significant decimal places 
		BigDecimal msrp80 = new BigDecimal(this.getProduct().getMSRP()).setScale(3, BigDecimal.ROUND_HALF_EVEN);
		
		// this is the percentage, also getting only significant decimal places
		BigDecimal percentage = new BigDecimal(0.8).setScale(3, BigDecimal.ROUND_HALF_EVEN);
		
		// this is the price of the product
		BigDecimal product = new BigDecimal(this.getPriceEach()).setScale(3, BigDecimal.ROUND_HALF_EVEN);
		
		/** Comparing the percentage of MSRP for this product with the price that the product was sold at*/
		if((msrp80.multiply(percentage)).compareTo(product) == 1) {
			return true;
		} 
		return false;
	}
}
