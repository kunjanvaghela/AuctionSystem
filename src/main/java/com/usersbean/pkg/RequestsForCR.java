package com.usersbean.pkg;
import java.io.Serializable;

public class RequestsForCR implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long requestId;
    private Long userId;
    private String requestType;
    private Long bidId;
    private Long listingId;
    private String updateDescription;
    private String current_status;
    
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRequestId() {
		return this.requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public Long getBidId() {
		return this.bidId;
	}
	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}
	public String getRequestType() {
		return this.requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getUpdateDescription() {
		return updateDescription;
	}
	public void setUpdateDescription(String updateDescription) {
		this.updateDescription = updateDescription;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCurrent_status() {
		return current_status;
	}
	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}
	public Long getListingId() {
		return listingId;
	}
	public void setListingId(Long listingId) {
		this.listingId = listingId;
	}
    
}