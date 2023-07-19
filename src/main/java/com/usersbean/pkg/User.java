package com.usersbean.pkg;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String name;
    private String encryptedName;
    private String password;
    private String encryptedPassword;
    private String emailId;
    private String encryptedEmailId;
    private String add_info1;
    private String add_info2;
    private String add_info3;
    private String add_info4;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEncryptedName() {
		return encryptedName;
	}
	public void setEncryptedName(String encryptedName) {
		this.encryptedName = encryptedName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getEncryptedEmailId() {
		return encryptedEmailId;
	}
	public void setEncryptedEmailId(String encryptedEmailId) {
		this.encryptedEmailId = encryptedEmailId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/*
	 * public String getAdd_info1() { return add_info3; } public void
	 * setAdd_info1(String add_info3) { this.add_info3 = add_info3; } public String
	 * getAdd_info4() { return add_info4; } public void setAdd_info4(String
	 * add_info4) { this.add_info4 = add_info4; }
	 */
	public String getAdd_info1() {
		return add_info1;
	}
	public void setAdd_info1(String add_info1) {
		this.add_info1 = add_info1;
	}
	public String getAdd_info2() {
		return add_info2;
	}
	public void setAdd_info2(String add_info2) {
		this.add_info2 = add_info2;
	}
	public String getAdd_info3() {
		return add_info3;
	}
	public void setAdd_info3(String add_info3) {
		this.add_info3 = add_info3;
	}
	public String getAdd_info4() {
		return add_info4;
	}
	public void setAdd_info4(String add_info4) {
		this.add_info4 = add_info4;
	}
	
}