package com.com1028.assignment;

import static org.junit.Assert.*;
import org.junit.Test;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class App_Test {
	
	private ResultSet query(String query) throws SQLException {
		BaseQuery bq = new BaseQuery("root", "");
		Statement s = bq.getConnection().createStatement();
		ResultSet rs = s.executeQuery(query);
		return rs;
	}
	
	@Test
	public void testRequirement1() throws SQLException {
		ResultSet rs = this.query("SELECT SUM(amount) FROM payments WHERE paymentDate = '2004-10-28'");
		
		BigDecimal amount = null;
	
		while(rs.next()) {
			amount = (BigDecimal) rs.getObject("SUM(amount)");
		}
		
		App app = new App();
		
		assertEquals(amount.doubleValue(), app.getTotalCustomerPaymentsFor("2004-10-28"), 0.0);
		
		if(rs!=null) {
			rs.close();
		}
	}
	
	private static DecimalFormat df = new DecimalFormat("#.##");
	
	@Test
	public void testRequirement2() throws SQLException {
		ResultSet rs = this.query("SELECT customers.customerName, SUM(payments.amount) FROM customers LEFT JOIN payments ON customers.customerNumber=payments.customerNumber GROUP BY customerName;");
		StringBuffer buffer = new StringBuffer();
		
		while(rs.next()) {
			buffer.append(rs.getObject(1) + ", Total: $");
			if(rs.getObject(2) != null) {
				buffer.append(rs.getObject(2) + "\n");
			} else {
				buffer.append("0.00\n");	
			}
			
		}
	
		App app = new App();
		
		assertEquals(buffer.toString(), app.getTotalPayments());

		if(rs!=null) {
			rs.close();
		}
	}
	
	
	@Test
	public void testRequirement3() throws SQLException{
		ResultSet rs = this.query("SELECT products.productName, orderdetails.orderNumber, orderdetails.priceEach FROM orderdetails INNER JOIN products ON orderdetails.productCode=products.productCode WHERE orderdetails.priceEach < (products.MSRP*0.80) ORDER BY orderdetails.orderNumber");
		
		StringBuffer buffer = new StringBuffer();
		
		while(rs.next()) {
			buffer.append(rs.getObject(1) + " (Order Number: " + rs.getObject(2) + ") -> Sold for: $" + rs.getObject(3) + "\n");
		}
		
		App app = new App();
		
		assertEquals(buffer.toString(), app.productsSoldUnder_80percent_MSRP());
		
		if(rs!=null) {
			rs.close();
		}
	}
}
