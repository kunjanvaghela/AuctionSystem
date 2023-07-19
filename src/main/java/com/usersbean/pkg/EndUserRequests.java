package com.usersbean.pkg;


//import java.io.Serializable;

public class EndUserRequests {
    private static final long serialVersionUID = 1L;

    private Long requestId;
	private Long userId;
	private String requestType;
	private Long bidId;
	private Long auctionId;
	private String update_description;
	private String current_status;
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Long getRequestId() {
		return requestId;
	}


	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getBidId() {
		return bidId;
	}


	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}


	public String getRequestType() {
		return requestType;
	}


	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}


	public String getUpdate_description() {
		return update_description;
	}


	public void setUpdate_description(String update_description) {
		this.update_description = update_description;
	}


	public String getCurrent_status() {
		return current_status;
	}


	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}


	public Long getAuctionId() {
		return auctionId;
	}


	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}
	
	
}