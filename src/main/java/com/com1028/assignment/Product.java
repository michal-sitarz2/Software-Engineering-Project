package com.com1028.assignment;

import java.math.BigDecimal;

public class Product {
	
	/** The code of the product */
	private String productCode;
	
	/** The name of the product */
	private String productName;
	
	/** The product line */
	private String productLine;
	
	/** The scale of the product*/
	private String productScale;
	
	/** The vendor of the product*/
	private String productVendor;
	
	/** Description of the product; includes all the important information about it */
	private String productDescription;
	
	/** What is the quantity in stock*/
	private Integer quantityInStock;
	
	/** Amount that the vendors pay for the products to the manufacturer */
	private Double buyPrice;
	
	/** The price that the manufacturer recommends the products should be sold at */
	private Double MSRP;
	
	/**
	 * Default constructor taking in all the fields and initialising all of them
	 * 
	 * @param productCode
	 * @param productName
	 * @param productLine
	 * @param productScale
	 * @param productVendor
	 * @param productDescription
	 * @param quantityInStock
	 * @param buyPrice
	 * @param MSRP
	 */
	public Product(String productCode, String productName, String productLine, String productScale,
			String productVendor, String productDescription, Integer quantityInStock, Double buyPrice, Double MSRP) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productLine = productLine;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescription = productDescription;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		this.MSRP = MSRP;
	}

	/**
	 * @return the code of the product
	 */
	public String getProductCode() {
		return productCode;
	}
	
	/**
	 * @return the name of the product
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * @return the product line
	 */
	public String getProductLine() {
		return productLine;
	}
	
	/**
	 * @return the scale of the product
	 */
	public String getProductScale() {
		return productScale;
	}

	/**
	 * @return the product vendor
	 */
	public String getProductVendor() {
		return productVendor;
	}

	/**
	 * @return the description of the product
	 */
	public String getProductDescription() {
		return productDescription;
	}
	
	/**
	 * @return the quantity of this product available in stock
	 */
	public Integer getQuantityInStock() {
		return quantityInStock;
	}
	
	/**
	 * @return Amount that the vendors pay for the products to the manufacturer
	 */
	public Double getBuyPrice() {
		return buyPrice;
	}
	
	/**
	 * @return The price that the manufacturer recommends the products should be sold at
	 */
	public Double getMSRP() {
		return MSRP;
	}
	
}
