package com.itembean.pkg;

import java.sql.Date;
import java.io.InputStream;
//select * from catalog_masterCREATE TABLE `catalog_master` (
//		  `itemId` int unsigned NOT NULL AUTO_INCREMENT,
//		  `name` varchar(30) NOT NULL,
//		  `description` varchar(80) NOT NULL,
//		  `quantity` int NOT NULL,
//		  `category` varchar(50) DEFAULT NULL,
//		  `cpu` varchar(200) DEFAULT NULL,
//		  `gpu` varchar(200) DEFAULT NULL,
//		  `ram` varchar(200) DEFAULT NULL,
//		  `storage` varchar(200) DEFAULT NULL,
//		  `operating_system` varchar(200) DEFAULT NULL,
//		  `screen_size` varchar(200) DEFAULT NULL,
//		  `screen_type` varchar(200) DEFAULT NULL,
//		  `screen_resolution` varchar(200) DEFAULT NULL,
//		  `front_camera` varchar(200) DEFAULT NULL,
//		  `rear_camera` varchar(200) DEFAULT NULL,
//		  `listing_Status` varchar(10) DEFAULT NULL,
//		  `approval_Status` varchar(10) DEFAULT NULL,
//		  `addinfo1` varchar(200) DEFAULT NULL,
//		  `addinfo2` varchar(200) DEFAULT NULL,
//		  `addinfo3` varchar(200) DEFAULT NULL,
//		  `addinfo4` varchar(200) DEFAULT NULL,
//		  `addinfo5` bigint DEFAULT NULL,
//		  `addinfo6` bigint DEFAULT NULL,
//		  `created_on` datetime DEFAULT NULL,
//		  `created_by` varchar(20) DEFAULT NULL,
//		  `remarks` varchar(100) DEFAULT NULL,
//		  `updated_by` varchar(20) DEFAULT NULL,
//		  `updated_on` datetime DEFAULT NULL,
//		  PRIMARY KEY (`itemId`)
//		) ENGINE=InnoDB DEFAULT CHARSET=latin1;
//

public class catalogMaster {

	private Long itemid;
	private String itemName;
	private String itemDescription;
	private Long itemQuantity;
	private String category;
	private String cpu;
	private String gpu;
	private String ram;
	private String storage;
	private String operatingSystem;
	private String screenSize;
	private String screenType;
	private String screenResolution;
	private String frontCamera;
	private String rearCamera;
	private String listingStatus;
	private String approvalStatus;
	private String addInfo1;
	private String addInfo2;
	private String addInfo3;
	private String addInfo4;
	private Long addInfo5;
	private Long addInfo6;
	private Date createdOn;
	private String createdBy;
	private Date updatedOn;
	private String updatedBy;
	private String remarks;
	private InputStream itemImage;

	public Long getItemid() {
		return itemid;
	}

	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Long getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getGpu() {
		return gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public String getScreenType() {
		return screenType;
	}

	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	public String getScreenResolution() {
		return screenResolution;
	}

	public void setScreenResolution(String screenResolution) {
		this.screenResolution = screenResolution;
	}

	public String getFrontCamera() {
		return frontCamera;
	}

	public void setFrontCamera(String frontCamera) {
		this.frontCamera = frontCamera;
	}

	public String getRearCamera() {
		return rearCamera;
	}

	public void setRearCamera(String rearCamera) {
		this.rearCamera = rearCamera;
	}

	public String getListingStatus() {
		return listingStatus;
	}

	public void setListingStatus(String listingStatus) {
		this.listingStatus = listingStatus;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getAddInfo1() {
		return addInfo1;
	}

	public void setAddInfo1(String addInfo1) {
		this.addInfo1 = addInfo1;
	}

	public String getAddInfo2() {
		return addInfo2;
	}

	public void setAddInfo2(String addInfo2) {
		this.addInfo2 = addInfo2;
	}

	public String getAddInfo3() {
		return addInfo3;
	}

	public void setAddInfo3(String addInfo3) {
		this.addInfo3 = addInfo3;
	}

	public String getAddInfo4() {
		return addInfo4;
	}

	public void setAddInfo4(String addInfo4) {
		this.addInfo4 = addInfo4;
	}

	public Long getAddInfo5() {
		return addInfo5;
	}

	public void setAddInfo5(Long addInfo5) {
		this.addInfo5 = addInfo5;
	}

	public Long getAddInfo6() {
		return addInfo6;
	}

	public void setAddInfo6(Long addInfo6) {
		this.addInfo6 = addInfo6;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public InputStream getItemImage() {
		return itemImage;
	}

	public void setItemImage(InputStream itemImage) {
		this.itemImage = itemImage;
	}

}
