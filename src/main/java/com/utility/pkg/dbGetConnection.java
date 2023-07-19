package com.utility.pkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class dbGetConnection {
	public static Connection dbGetConn() {
		Properties prop = null;
		Connection connection = null;
		
		String fileName = "/Users/kunjanvaghela/Projects/527 DBDS/AuctionSystem/AuctionSystem/src/main/resources/config.properties";
        File file = new File(fileName);
        String absolutePath = file.getAbsolutePath();
        
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(absolutePath);
//		System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
//		try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
		try (InputStream input = new FileInputStream(absolutePath)) {
			prop = new Properties();
			prop.load(input);
			
			// Getting all the values out of properties file
			String driverName = prop.getProperty("db.drivername");
			String userName = prop.getProperty("db.user");
			String password = prop.getProperty("db.password");
			String dbUrl = prop.getProperty("db.url");
			
			System.out.println(driverName);
			System.out.println(userName);
			System.out.println(password);
			System.out.println(dbUrl);
			
			Class.forName(prop.getProperty("db.drivername"));
			connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return connection;
		
	}
}