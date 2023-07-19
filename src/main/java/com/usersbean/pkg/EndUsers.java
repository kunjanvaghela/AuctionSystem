package com.usersbean.pkg;

//import java.sql.Date;

//import java.io.Serializable;

public class EndUsers extends User {
    private static final long serialVersionUID = 1L;
    private String address;
	private String phoneNr;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNr() {
		return phoneNr;
	}


	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}
}