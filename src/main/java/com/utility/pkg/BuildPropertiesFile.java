package com.utility.pkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class BuildPropertiesFile {

    public static void main(String[] args) {
    	String fileName = "src/main/resources/config2.properties";
        File file = new File(fileName);
        String absolutePath = file.getAbsolutePath();

        try (OutputStream output = new FileOutputStream(absolutePath)) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("db.url", "jdbc:mysql://localhost:3306/AuctionSystem2?allowPublicKeyRetrieval=true&useSSL=false");
            prop.setProperty("db.user", "root");
            prop.setProperty("db.password", "password");
            prop.setProperty("db.drivername", "com.mysql.jdbc.Driver");

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}