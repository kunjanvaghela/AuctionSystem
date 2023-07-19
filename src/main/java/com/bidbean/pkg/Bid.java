package com.bidbean.pkg;
import java.sql.Timestamp;

public class Bid {
    private int bidId;
    private int buyerId;
    private int listingId;
    private float lowestBid;
    private float highestBid;
    private Float currentBid;
    private Timestamp createdOn;
    private String currentStatus;
    private String createdBy;
    private String remarks;
    private String updatedBy;
    private Timestamp updatedOn;
    
    public Bid() {
        // Default constructor
    }
    
    // Getters and setters for all fields
    public int getBidId() {
        return bidId;
    }
    
    public void setBidId(int bidId) {
        this.bidId = bidId;
    }
    
    public int getBuyerId() {
        return buyerId;
    }
    
    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }
    
    public int getListingId() {
        return listingId;
    }
    
    public void setListingId(int listingId) {
        this.listingId = listingId;
    }
    
    public float getLowestBid() {
        return lowestBid;
    }
    
    public void setLowestBid(float lowestBid) {
        this.lowestBid = lowestBid;
    }
    
    public float getHighestBid() {
        return highestBid;
    }
    
    public void setHighestBid(float highestBid) {
        this.highestBid = highestBid;
    }
    
    public Float getCurrentBid() {
        return currentBid;
    }
    
    public void setCurrentBid(Float currentBid) {
        this.currentBid = currentBid;
    }
    
    public Timestamp getCreatedOn() {
        return createdOn;
    }
    
    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
    
    public String getCurrentStatus() {
        return currentStatus;
    }
    
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getUpdatedBy() {
        return updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    
    public Timestamp getUpdatedOn() {
        return updatedOn;
    }
    
    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }
}

