package com.usersbean.pkg;

import java.io.Serializable;
//import java.sql.Date;
import java.time.LocalDate;

//import java.io.Serializable;

public class Bids implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long bidId;
	private Long buyerId;
	private Long listingId;
	private float lowest_bid;
	private float highest_bid;
	private String currentStatus;
	private LocalDate createdOn;
	private String createdBy;
	private LocalDate updatedOn;
	private String updatedBy;
	private String remarks;
	
	private String bidderName;
	private String bidderEmail;
	
    
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Long getBidId() {
		return bidId;
	}
	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
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

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public float getLowest_bid() {
		return lowest_bid;
	}

	public void setLowest_bid(float lowest_bid) {
		this.lowest_bid = lowest_bid;
	}

	public Long getListingId() {
		return listingId;
	}

	public void setListingId(Long listingId) {
		this.listingId = listingId;
	}

	public float getHighest_bid() {
		return highest_bid;
	}

	public void setHighest_bid(float highest_bid) {
		this.highest_bid = highest_bid;
	}

	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}

	public String getBidderEmail() {
		return bidderEmail;
	}

	public void setBidderEmail(String bidderEmail) {
		this.bidderEmail = bidderEmail;
	}

	
	
	
}