package com.items.database.pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpSession;

import com.itembean.pkg.catalogMaster;
import com.utility.pkg.dbGetConnection;

public class itemDAO {


    public int addItem(catalogMaster item, Long userID) throws ClassNotFoundException {
    	Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
    	System.out.println(currentTimestamp);
    	String INSERT_ITEM_SQL ="INSERT INTO catalog_master"
        		+ "(`name`,`description`,  `category` ,  `cpu` ,  `gpu` ,  `ram` ,  `storage` ,  `operating_system` ,  `screen_size` ,  `screen_type` ,  `screen_resolution` ,  `front_camera`,  `rear_camera` ,  `listing_Status` ,  `approval_Status` ,  `created_on` ,  `created_by`,  `itemImage`)"
        		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int result = 0;
//        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = dbGetConnection.dbGetConn();
        
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM_SQL)) {
            preparedStatement.setString(1, item.getItemName());
            preparedStatement.setString(2, item.getItemDescription()); //PII PENDING
            preparedStatement.setString(3, item.getCategory());
            preparedStatement.setString(4, item.getCpu()); //PII PENDING
            preparedStatement.setString(5, item.getGpu());
            preparedStatement.setString(6, item.getRam()); //PII PENDING
            preparedStatement.setString(7, item.getStorage()); //PII PENDING
            preparedStatement.setString(8, item.getOperatingSystem()); //PII PENDING
            preparedStatement.setString(9, item.getScreenSize()); //PII PENDING
            preparedStatement.setString(10, item.getScreenType()); //PII PENDING
            preparedStatement.setString(11, item.getScreenResolution()); //PII PENDING
            preparedStatement.setString(12, item.getFrontCamera()); //PII PENDING
            preparedStatement.setString(13, item.getRearCamera()); //PII PENDING
            preparedStatement.setString(14, item.getListingStatus()); //PII PENDING
            preparedStatement.setString(15, item.getApprovalStatus()); //PII PENDING
            preparedStatement.setTimestamp(16,currentTimestamp); //PII PENDING
            preparedStatement.setLong(17, userID); //PII PENDING
            preparedStatement.setBlob(18, item.getItemImage()); //PII PENDING

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }
        
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
    
    
    
    public List<catalogMaster> getItemsAvailable() throws ClassNotFoundException{
	    List<catalogMaster> list=new ArrayList<catalogMaster>();
	    Class.forName("com.mysql.jdbc.Driver");
	    
		try(Connection connection = dbGetConnection.dbGetConn()) {
			/*String querry="select * from catalog_master";*/
	    	String querry="SELECT `catalog_master`.`itemId`,\r\n"
	    	+ "    `catalog_master`.`name`,\r\n"
	    	+ "    `catalog_master`.`description`,\r\n"
	    	+ "    `catalog_master`.`category`,\r\n"
	    	+ "    `catalog_master`.`cpu`,\r\n"
	    	+ "    `catalog_master`.`gpu`,\r\n"
	    	+ "    `catalog_master`.`ram`,\r\n"
	    	+ "    `catalog_master`.`storage`,\r\n"
	    	+ "    `catalog_master`.`operating_system`,\r\n"
	    	+ "    `catalog_master`.`screen_size`,\r\n"
	    	+ "    `catalog_master`.`screen_type`,\r\n"
	    	+ "    `catalog_master`.`screen_resolution`,\r\n"
	    	+ "    `catalog_master`.`front_camera`,\r\n"
	    	+ "    `catalog_master`.`rear_camera`,\r\n"
	    	+ "    `catalog_master`.`itemImage`,\r\n"
	    	+ "    `catalog_master`.`listing_Status`,\r\n"
	    	+ "    `catalog_master`.`approval_Status`,\r\n"
	    	+ "    `catalog_master`.`addinfo1`,\r\n"
	    	+ "    `catalog_master`.`addinfo2`,\r\n"
	    	+ "    `catalog_master`.`addinfo3`,\r\n"
	    	+ "    `catalog_master`.`addinfo4`,\r\n"
	    	+ "    `catalog_master`.`addinfo5`,\r\n"
	    	+ "    `catalog_master`.`addinfo6`,\r\n"
	    	+ "    `catalog_master`.`created_on`,\r\n"
	    	+ "    `catalog_master`.`created_by`,\r\n"
	    	+ "    `catalog_master`.`remarks`,\r\n"
	    	+ "    `catalog_master`.`updated_by`,\r\n"
	    	+ "    `catalog_master`.`updated_on`\r\n"
	    	+ "FROM `catalog_master`";
	    	PreparedStatement ps = connection.prepareStatement(querry);	
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()){
	    		catalogMaster obj_catalogMaster=new catalogMaster();
	    		obj_catalogMaster.setItemid(rs.getLong("itemId"));
	    		obj_catalogMaster.setItemName(rs.getString("name"));
	    		obj_catalogMaster.setItemDescription(rs.getString("description"));
	    		obj_catalogMaster.setCategory(rs.getString("category"));
	    		obj_catalogMaster.setCpu(rs.getString("cpu"));
	    		obj_catalogMaster.setGpu(rs.getString("gpu"));
	    		obj_catalogMaster.setRam(rs.getString("ram"));
	    		obj_catalogMaster.setStorage(rs.getString("storage"));
	    		obj_catalogMaster.setOperatingSystem(rs.getString("operating_system"));
	    		obj_catalogMaster.setScreenSize(rs.getString("screen_size"));
	    		obj_catalogMaster.setScreenType(rs.getString("screen_type"));
	    		obj_catalogMaster.setScreenResolution(rs.getString("screen_resolution"));
	    		obj_catalogMaster.setFrontCamera(rs.getString("front_camera"));
	    		obj_catalogMaster.setRearCamera(rs.getString("rear_camera"));
	    		obj_catalogMaster.setListingStatus(rs.getString("listing_Status"));
	    		obj_catalogMaster.setApprovalStatus(rs.getString("approval_Status"));
	    		
	    		list.add(obj_catalogMaster);
	    	}
		}
		catch (Exception e) {
	    	System.out.println(e);
	    }
		return list;
    }
    
    // To get distinct categories present in the catalog_master table
    public List<String> getUniqueCategory() {
    	List<String> listOfUniqueCategory = new ArrayList<String>();
    	String SELECT_UNIQUE_CATEGORY_CATALOGMASTER = "SELECT DISTINCT(category) from catalog_master;";
    	
    	try (Connection connection = dbGetConnection.dbGetConn();

        		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_UNIQUE_CATEGORY_CATALOGMASTER)) {
                System.out.println("Unique catalog_master: " + preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                
                while (rs.next()){
                	listOfUniqueCategory.add(rs.getString(1));
                	rs.close();
                }
                
            } catch (SQLException e) {
                printSQLException(e);
            }
    	return listOfUniqueCategory;
    }
    
 // To get distinct categories present in the catalog_master table
    public List<String> getUniqueAttribute(String attr) {
    	List<String> listOfUniqueAttr = new ArrayList<String>();
    	String SELECT_UNIQUE_ATTR_CATALOGMASTER = "SELECT DISTINCT("+attr+") from catalog_master;";
    	
    	try (Connection connection = dbGetConnection.dbGetConn();

        		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_UNIQUE_ATTR_CATALOGMASTER)) {
                System.out.println("Unique catalog_master: " + SELECT_UNIQUE_ATTR_CATALOGMASTER);
                ResultSet rs = preparedStatement.executeQuery();
                
                while (rs.next()){
                	if (rs.getString(1) != null) {
                		listOfUniqueAttr.add(rs.getString(1));
                	}
                }
            	rs.close();
                
            } catch (SQLException e) {
                printSQLException(e);
            }
    	return listOfUniqueAttr;
    }
    
    // To get distinct categories present in the catalog_master table
    public List<catalogMaster> getFilterResult(String category, String cpu, String gpu, String ram, String storage, String os, String screenSize, String screenType, String screenRes, String frontCam, String rearCam,  String itemNameF) throws ClassNotFoundException {
    	List<catalogMaster> listOfFilteredOutput = new ArrayList<catalogMaster>();
//    	Class.forName("com.mysql.jdbc.Driver");
	    
		try(Connection connection = dbGetConnection.dbGetConn()) {
//	    	String querrry = "SELECT * FROM catalog_master WHERE category LIKE '%" + category + "%' " +
//	    	        "AND cpu LIKE '%" + cpu + "%' " +
//	    	        "AND gpu LIKE '%" + gpu + "%' " +
//	    	        "AND ram LIKE '%" + ram + "%' " +
//	    	        "AND storage LIKE '%" + storage + "%' " +
//	    	        "AND operating_system LIKE '%" + os + "%' " +
//	    	        "AND screen_size LIKE '%" + screenSize + "%' " +
//	    	        "AND screen_type LIKE '%" + screenType + "%' " +
//	    	        "AND screen_resolution LIKE '%" + screenRes + "%' " +
//	    	        "AND front_camera LIKE '%" + frontCam + "%' " +
//	    	        "AND rear_camera LIKE '%" + rearCam + "%'";
	    	String querry = "SELECT * FROM catalog_master WHERE 1=1 ";
	    	if(category != null && !category.isEmpty()) {
	    	    querry += "AND category LIKE '%" + category + "%' ";
	    	}
	    	
	    	if (cpu == null) {
	    		System.out.println("Value of cpu is null");
	    	}
	    	else if (cpu == "") {
	    		System.out.println("Value of cpu is empty");
	    	}
	    	else if (!cpu.isEmpty()) {
	    		System.out.println("Value of cpu is !cpu.isEmpty();");
	    	}
	    	else if (cpu.isEmpty()) {
	    		System.out.println("Value of cpu is cpu.isEmpty()");
	    	}
	    	else{
	    		System.out.println("Value of cpu is something else : "+cpu);
	    	}
	    	
	    	if(cpu != null && !cpu.isEmpty()) {
	    	    querry += "AND cpu LIKE '%" + cpu + "%' ";
	    	}

	    	if(gpu != null && !gpu.isEmpty()) {
	    	    querry += "AND gpu LIKE '%" + gpu + "%' ";
	    	}

	    	if(ram != null && !ram.isEmpty()) {
	    	    querry += "AND ram LIKE '%" + ram + "%' ";
	    	}

	    	if(storage != null && !storage.isEmpty()) {
	    	    querry += "AND storage LIKE '%" + storage + "%' ";
	    	}

	    	if(os != null && !os.isEmpty()) {
	    	    querry += "AND operating_system LIKE '%" + os + "%' ";
	    	}

	    	if(screenSize != null && !screenSize.isEmpty()) {
	    	    querry += "AND screen_size LIKE '%" + screenSize + "%' ";
	    	}

	    	if(screenType != null && !screenType.isEmpty()) {
	    	    querry += "AND screen_type LIKE '%" + screenType + "%' ";
	    	}

	    	if(screenRes != null && !screenRes.isEmpty()) {
	    	    querry += "AND screen_resolution LIKE '%" + screenRes + "%' ";
	    	}

	    	if(frontCam != null && !frontCam.isEmpty()) {
	    	    querry += "AND front_camera LIKE '%" + frontCam + "%' ";
	    	}

	    	if(rearCam != null && !rearCam.isEmpty()) {
	    	    querry += "AND rear_camera LIKE '%" + rearCam + "%' ";
	    	}
	    	
	    	
	    	if(itemNameF != null && !itemNameF.isEmpty()) {
	    	    querry += "AND name LIKE '%" + itemNameF + "%' ";
	    	}

	    	querry += " ORDER BY created_on DESC ";
	    	System.out.println(querry);
	    	PreparedStatement ps = connection.prepareStatement(querry);
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()){
	    		catalogMaster obj_catalogMaster=new catalogMaster();
	    		obj_catalogMaster.setItemid(rs.getLong("itemId"));
	    		obj_catalogMaster.setItemName(rs.getString("name"));
	    		obj_catalogMaster.setItemDescription(rs.getString("description"));
	    		obj_catalogMaster.setCategory(rs.getString("category"));
	    		obj_catalogMaster.setCpu(rs.getString("cpu"));
	    		obj_catalogMaster.setGpu(rs.getString("gpu"));
	    		obj_catalogMaster.setRam(rs.getString("ram"));
	    		obj_catalogMaster.setStorage(rs.getString("storage"));
	    		obj_catalogMaster.setOperatingSystem(rs.getString("operating_system"));
	    		obj_catalogMaster.setScreenSize(rs.getString("screen_size"));
	    		obj_catalogMaster.setScreenType(rs.getString("screen_type"));
	    		obj_catalogMaster.setScreenResolution(rs.getString("screen_resolution"));
	    		obj_catalogMaster.setFrontCamera(rs.getString("front_camera"));
	    		obj_catalogMaster.setRearCamera(rs.getString("rear_camera"));
	    		obj_catalogMaster.setListingStatus(rs.getString("listing_Status"));
	    		obj_catalogMaster.setApprovalStatus(rs.getString("approval_Status"));
	    		
	    		try {
	    			if(rs.getBinaryStream("itemImage")!=null)
		    		{byte[] bytes = rs.getBinaryStream("itemImage").readAllBytes();
		    		String base64 = Base64.getEncoder().encodeToString(bytes);
		    		obj_catalogMaster.setAddInfo1(base64);}
	    		}
	    		catch (Exception e) {
	    	    	System.out.println(e);
	    	    	e.printStackTrace();
	    	    }
	    		
	    		listOfFilteredOutput.add(obj_catalogMaster);
	    	}
		}
		catch (Exception e) {
	    	System.out.println(e);
	    }

    	
    	return listOfFilteredOutput;
    }

}
