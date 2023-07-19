package com.itemInterest.pkg;

import java.sql.Timestamp;

public class Interest {
	  private int interestId;
	  private int userId;
	  private int ItemId;
	  private String addinfo1;
	  private String addinfo2;
	  private String addinfo3;
	  private String addinfo4;
	  private Timestamp createdOn;
	  private String createdBy;
	  private String interestStatus;
	  private String remarks;

	  // Getters and Setters
	  public int getInterestId() {
	    return interestId;
	  }
	  public void setInterestId(int interestId) {
	    this.interestId = interestId;
	  }

	  public int getUserId() {
	    return userId;
	  }
	  public void setUserId(int userId) {
	    this.userId = userId;
	  }

	  public int getItemId() {
	    return ItemId;
	  }
	  public void setItemId(int itemId) {
	    ItemId = itemId;
	  }

	  public String getAddinfo1() {
	    return addinfo1;
	  }
	  public void setAddinfo1(String addinfo1) {
	    this.addinfo1 = addinfo1;
	  }

	  public String getAddinfo2() {
	    return addinfo2;
	  }
	  public void setAddinfo2(String addinfo2) {
	    this.addinfo2 = addinfo2;
	  }

	  public String getAddinfo3() {
	    return addinfo3;
	  }
	  public void setAddinfo3(String addinfo3) {
	    this.addinfo3 = addinfo3;
	  }

	  public String getAddinfo4() {
	    return addinfo4;
	  }
	  public void setAddinfo4(String addinfo4) {
	    this.addinfo4 = addinfo4;
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

	  public String getInterestStatus() {
	    return interestStatus;
	  }
	  public void setInterestStatus(String interestStatus) {
	    this.interestStatus = interestStatus;
	  }

	  public String getRemarks() {
	    return remarks;
	  }
	  public void setRemarks(String remarks) {
	    this.remarks = remarks;
	  }
	}
