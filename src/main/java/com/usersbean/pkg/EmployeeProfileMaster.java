package com.usersbean.pkg;

//import java.sql.Date;
import java.time.LocalDate;

//import java.io.Serializable;

public class EmployeeProfileMaster extends User {
    private static final long serialVersionUID = 1L;
//    private Long userId;
//    private String name;
//    private String encryptedName;
//    private String password;
//    private String encryptedPassword;
//    private String emailId;
//    private String encryptedEmailId;
//	public Long getUserId() {
//		return userId;
//	}
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getEncryptedName() {
//		return encryptedName;
//	}
//	public void setEncryptedName(String encryptedName) {
//		this.encryptedName = encryptedName;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public String getEncryptedPassword() {
//		return encryptedPassword;
//	}
//	public void setEncryptedPassword(String encryptedPassword) {
//		this.encryptedPassword = encryptedPassword;
//	}
//	public String getEmailId() {
//		return emailId;
//	}
//	public void setEmailId(String emailId) {
//		this.emailId = emailId;
//	}
//	public String getEncryptedEmailId() {
//		return encryptedEmailId;
//	}
//	public void setEncryptedEmailId(String encryptedEmailId) {
//		this.encryptedEmailId = encryptedEmailId;
//	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private Integer ssn;
	private LocalDate dob;
	
	public Integer getssn() {
		return ssn;
	}
	public void setssn(Integer ssn) {
		this.ssn = ssn;
	}

	public LocalDate getdob() {
		return dob;
	}
	public void setdob(String dob) {
		this.dob = LocalDate.parse(dob);
	}
}