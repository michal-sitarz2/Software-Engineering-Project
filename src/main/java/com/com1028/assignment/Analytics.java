package com.com1028.assignment;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Analytics extends BaseQuery{
	
	/** List of all the customers which are in the database */
	private List<Customer> customers;
	
	/** list of all order details that are saved in the database */
	private List<OrderDetails> orderDetails;
	
	/**
	 * Constructor used to connect to the database, as well as to populate both lists.
	 * 
	 * @param uname
	 * @param pwd
	 */
	public Analytics(String uname, String pwd) {
		// calls the BaseQuery constructor and establishes connection to the database
		// username and password are required to connect
		super(uname, pwd);
		
		customers = new ArrayList<Customer>();
		orderDetails = new ArrayList<OrderDetails>();
		try {
			//method which populates the customers list with all the customers in the database
			this.populateCustomers();
			//method which populates order details along with the product that they contain
			this.populateOrderDetails();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return list of customers
	 */
	public List<Customer> getCustomers(){
		return this.customers;
	}
	
	/**
	 * @return list of order details
	 */
	public List<OrderDetails> getOrderDetails(){
		return this.orderDetails;
	}
	
	/**
	 * Method which populates the customers list with all the customers in the database
	 * 
	 * @throws SQLException
	 */
	private void populateCustomers() throws SQLException {
		ResultSet resultSet = null;
		try {
			//calling the useTable() method from the parent class to get the data from customers table
			resultSet = useTable("customers");
			
			/** Iterating through the data and storing each row from the table as a customer object with all data about it*/
			while(resultSet.next()) {
				Integer cusNum = (Integer) resultSet.getObject(1);
				String cusNam = (String) resultSet.getObject(2);
				String contLast = (String) resultSet.getObject(3);
				String contFirst = (String) resultSet.getObject(4);
				String phone = (String) resultSet.getObject(5);
				String address1 = (String) resultSet.getObject(6);
				String address2 = (String) resultSet.getObject(7);
				String city = (String) resultSet.getObject(8);
				String state = (String) resultSet.getObject(9);
				String postal = (String) resultSet.getObject(10);
				String country = (String) resultSet.getObject(11);
				Integer salesRepEmployeeNum = (Integer) resultSet.getObject(12);
				Double creditLimit = ((BigDecimal) resultSet.getObject(13)).doubleValue();
				
				//Creating the Customer object
				Customer customer = new Customer(cusNum, cusNam, contLast, contFirst, phone, address1,
						address2, city, state, postal, country, salesRepEmployeeNum, creditLimit);
				//Adding the customer to the the list of customers
				this.customers.add(customer);
			}
			
			this.mapPayments();
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			/** Closing the ResultSet*/
			if(resultSet!=null) {
				resultSet.close();
			}
		}
	}

	
	/**
	 * A method used to read the payments table and find the corresponding customer based on the customer number
	 * 
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 */
	private void mapPayments() throws SQLException, IllegalArgumentException{
		ResultSet resultSet = null;
		try {
			//calling the useTable() method from the parent class to get the data from payments table
			resultSet = useTable("payments");
				
			/** Iterating through  the data and storing each row from the table as a payment object with all data about it*/
			while(resultSet.next()) {
				Integer cusNum = (Integer) resultSet.getObject(1);
				String checkNum = (String) resultSet.getObject(2);
				Date payDate = (Date) resultSet.getObject(3);
				Double amount = ((BigDecimal) resultSet.getObject(4)).doubleValue();
				
				// using this local variable to check if each payment is mapped to the corresponding customer
				boolean found = false;
				for(Customer c : this.customers) {
					if(c.getCustomerNumber().equals(cusNum)) {
						//Creating an instance of the payment class
						Payment payment = new Payment(cusNum, checkNum, payDate, amount);
						//Adding the payment for that specific customer
						c.addPayment(payment);
						//The customer for a particular payment was found
						found = true;
						//Breaking the loop as the payment is made by only one customer
						break;
					}
				}
				if(!found) {
					throw new IllegalArgumentException("Payment " + cusNum + " was not matched with any customer!");
				}
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * Method which reads the database and stores the data in an array list of order details
	 * 
	 * @throws SQLException
	 */
	private void populateOrderDetails() throws SQLException {
		ResultSet resultSetO = null;
		ResultSet resultSetP = null;
		try {
			// reading the table of order details
			resultSetO = useTable("orderdetails");
			// as order details has to have a product, this method also matches
			// the product to a corresponding order detail object, thus products table also has to be read
			resultSetP = useTable("products");
			
			/** Iterating through all the data in the order details table and storing all the variables*/
			while(resultSetO.next()) {
				
				Integer orderNum = (Integer) resultSetO.getObject(1);
				String productCode = (String) resultSetO.getObject(2);
				Integer quantityOrdered = (Integer) resultSetO.getObject(3);
				Double priceEach = ((BigDecimal) resultSetO.getObject(4)).doubleValue();
				Integer orderLineNum = (Integer) resultSetO.getObject(5);
				Product product = null;
				
				// After iterating each time through the data in products, we don't want to start where the last iteration left of,
				// hence this method call makes the iteration start from the beginning each time
				resultSetP.beforeFirst();
				
				/** Before the order detail object is created the corresponding product has to be found in the products table*/
				while(resultSetP.next()) {
					
					/** Comparing the product code in the order details to the product code in the products */
					if(resultSetP.getObject(1).equals(productCode)) {
						String pCode = (String) resultSetP.getObject(1);
						String pName = (String) resultSetP.getObject(2);
						String pLine = (String) resultSetP.getObject(3);
						String pScale = (String) resultSetP.getObject(4);
						String pVendor = (String) resultSetP.getObject(5);
						String pDescription = (String) resultSetP.getObject(6);
						Integer qInStock = (Integer) resultSetP.getObject(7);
						Double buyPrice = ((BigDecimal) resultSetP.getObject(8)).doubleValue();
						Double MSRP = ((BigDecimal) resultSetP.getObject(9)).doubleValue();
						
						//Creating the product object
						product = new Product(pCode, pName, pLine, pScale, pVendor, pDescription, qInStock, buyPrice, MSRP);
						
						//A single product matches to a single order details object, hence no need to further iterate 
						break;
					}
				}
		
				// Creating the order details object with the corresponding product
				OrderDetails orderDetails = new OrderDetails(orderNum, product, quantityOrdered, priceEach, orderLineNum);
				
				// Adding the order details into the list
				this.orderDetails.add(orderDetails);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if(resultSetO!=null) {
				resultSetO.close();
			}
			if (resultSetP!=null) {
				resultSetP.close();
			}
		}
	}
}
