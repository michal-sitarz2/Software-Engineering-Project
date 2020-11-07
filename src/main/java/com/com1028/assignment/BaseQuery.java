package com.com1028.assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseQuery {
	/** Connection */
	private Connection con;
	
	/** The database to which this program connects*/
	private final String db = "jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	/**
	 * Constructor takes in the username and the password to the database in order to connect
	 * 
	 * @param uname
	 * @param pwd
	 */
	public BaseQuery(String uname, String pwd){
		try {
		    //Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
			con = DriverManager.getConnection( db, uname, pwd);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return this.con;
	}
	
	/**
	 * Method used to access the table in the database.
	 * 
	 * @param tableName
	 * @return an object of the data in a specific table in the database
	 * @throws SQLException
	 */
	protected ResultSet useTable(String tableName) throws SQLException{
		String query = "select * from " + tableName; 
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(query);
		return rs;
	}

}

