package com.altruist.nsdl.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UsersModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "password")
	private String password;

	@Column(name = "username")
	private String username;

	@Column(name = "branch_code")
	private String branchCode;

	@Column(name = "branch_location")
	private String branchLocation;

	@Column(name = "name")
	private String name;

	@Column(name = "empid")
	private String empId;

	@Column(name = "date_of_creation")
	private Timestamp dateOfCreation;

	@Column(name = "category")
	private String category;

	@Column(name = "logindatetime")
	private Date loginDateTime;

	@Column(name = "mf_code")
	private String mfCode;

	@Column(name = "Agent_Code")
	private String agentCode;

	@Column(name = "agent_mn")
	private String agentMn;

	@Column(name = "url")
	private String url;

	@Column(name = "aadhaarno")
	private String aadhaarNo;

	@Column(name = "panno")
	private String panNo;

	@Column(name = "shopname")
	private String shopName;

	@Column(name = "address")
	private String address;

	@Column(name = "pincode")
	private String pinCode;

	@Column(name = "date_of_password")
	private Date dateOfPassword;

	@Column(name = "email")
	private String email;

	@Column(name = "emailvarificationtime")
	private Date emailVarificationTime;

	@Column(name = "ismobilevarified")
	private Boolean isMobileVarified;

	@Column(name = "isemailvarified")
	private Boolean isEmailVarified;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "mobilevarificationtime")
	private Date mobileVarificationTime;

	@Column(name = "varificationexpiry")
	private Date varificationExpiry;

	@Column(name = "source")
	private String source;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchLocation() {
		return branchLocation;
	}

	public void setBranchLocation(String branchLocation) {
		this.branchLocation = branchLocation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Timestamp getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Timestamp dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getLoginDateTime() {
		return loginDateTime;
	}

	public void setLoginDateTime(Date loginDateTime) {
		this.loginDateTime = loginDateTime;
	}

	public String getMfCode() {
		return mfCode;
	}

	public void setMfCode(String mfCode) {
		this.mfCode = mfCode;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentMn() {
		return agentMn;
	}

	public void setAgentMn(String agentMn) {
		this.agentMn = agentMn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAadhaarNo() {
		return aadhaarNo;
	}

	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Date getDateOfPassword() {
		return dateOfPassword;
	}

	public void setDateOfPassword(Date dateOfPassword) {
		this.dateOfPassword = dateOfPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEmailVarificationTime() {
		return emailVarificationTime;
	}

	public void setEmailVarificationTime(Date emailVarificationTime) {
		this.emailVarificationTime = emailVarificationTime;
	}

	public Boolean getIsMobileVarified() {
		return isMobileVarified;
	}

	public void setIsMobileVarified(Boolean isMobileVarified) {
		this.isMobileVarified = isMobileVarified;
	}

	public Boolean getIsEmailVarified() {
		return isEmailVarified;
	}

	public void setIsEmailVarified(Boolean isEmailVarified) {
		this.isEmailVarified = isEmailVarified;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getMobileVarificationTime() {
		return mobileVarificationTime;
	}

	public void setMobileVarificationTime(Date mobileVarificationTime) {
		this.mobileVarificationTime = mobileVarificationTime;
	}

	public Date getVarificationExpiry() {
		return varificationExpiry;
	}

	public void setVarificationExpiry(Date varificationExpiry) {
		this.varificationExpiry = varificationExpiry;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "UsersModel [userId=" + userId + ", enabled=" + enabled + ", password=" + password + ", username="
				+ username + ", branchCode=" + branchCode + ", branchLocation=" + branchLocation + ", name=" + name
				+ ", empId=" + empId + ", dateOfCreation=" + dateOfCreation + ", category=" + category
				+ ", loginDateTime=" + loginDateTime + ", mfCode=" + mfCode + ", agentCode=" + agentCode + ", agentMn="
				+ agentMn + ", url=" + url + ", aadhaarNo=" + aadhaarNo + ", panNo=" + panNo + ", shopName=" + shopName
				+ ", address=" + address + ", pinCode=" + pinCode + ", dateOfPassword=" + dateOfPassword + ", email="
				+ email + ", emailVarificationTime=" + emailVarificationTime + ", isMobileVarified=" + isMobileVarified
				+ ", isEmailVarified=" + isEmailVarified + ", mobile=" + mobile + ", mobileVarificationTime="
				+ mobileVarificationTime + ", varificationExpiry=" + varificationExpiry + ", source=" + source + "]";
	}

}
