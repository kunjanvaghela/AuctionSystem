package com.usersbean.pkg;

import java.io.Serializable;
import java.sql.Timestamp;
//import java.sql.Date;


//import java.io.Serializable;

public class ItemListingReport implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long listingId;
    private Integer itemId;
	private Long sellerId;
	private float initialPrice;
	private float bidIncrement;
	private float minBidPrice;
	private Timestamp closingDateTime;
	private String currentStatus;
	private Integer bestBidId;
	private float currentBestBidAmount;
	private Timestamp createdOn;
	private String createdBy;
	private Timestamp updatedOn;
	private String updatedBy;
	private String remarks;
	
	private String itemName;
	private String sellerName;
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

	private String itemSummary;
	
    
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
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

	public Long getListingId() {
		return listingId;
	}

	public void setListingId(Long listingId) {
		this.listingId = listingId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public float getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(float initialPrice) {
		this.initialPrice = initialPrice;
	}

	public float getMinBidPrice() {
		return minBidPrice;
	}

	public void setMinBidPrice(float minBidPrice) {
		this.minBidPrice = minBidPrice;
	}

	public float getBidIncrement() {
		return bidIncrement;
	}

	public void setBidIncrement(float bidIncrement) {
		this.bidIncrement = bidIncrement;
	}

	public Timestamp getClosingDateTime() {
		return closingDateTime;
	}

	public void setClosingDateTime(Timestamp closingDateTime) {
		this.closingDateTime = closingDateTime;
	}

	public float getCurrentBestBidAmount() {
		return currentBestBidAmount;
	}

	public void setCurrentBestBidAmount(float currentBestBidAmount) {
		this.currentBestBidAmount = currentBestBidAmount;
	}

	public Integer getBestBidId() {
		return bestBidId;
	}

	public void setBestBidId(Integer bestBidId) {
		this.bestBidId = bestBidId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
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

	public String getItemSummary() {
		return itemSummary;
	}

	public void setItemSummary(String itemSummary) {
		this.itemSummary = itemSummary;
	}

	public String getAddInfo1() {
		return addInfo1;
	}

	public void setAddInfo1(String addInfo1) {
		this.addInfo1 = addInfo1;
	}
	
}