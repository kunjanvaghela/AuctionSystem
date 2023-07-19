package com.dbconnection.pkg;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;

import com.utility.pkg.dbGetConnection;

public class ApplicationDB {
	
	public ApplicationDB(){
		
	}

	public Connection getConnection() throws SQLException{
		
		//Create a connection string
		Connection connection = null;
		
		//Create a connection to your DB
		connection = dbGetConnection.dbGetConn();
		return connection;
		
	}
	
	public void closeConnection(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void main(String[] args) throws SQLException {
		ApplicationDB dao = new ApplicationDB();
		Connection connection = dao.getConnection();
		
		System.out.println(connection);		
		dao.closeConnection(connection);
	}
	
	

}
