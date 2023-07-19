package com.usersbean.pkg;

import java.io.Serializable;
import java.sql.Timestamp;
//import java.sql.Date;


//import java.io.Serializable;

public class ItemListing implements Serializable {
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
	
	
	
}