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
@Table(name = "branches")
public class BranchModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "location")
	private String location;

	@Column(name = "code")
	private String code;

	@Column(name = "serial_no")
	private String serialNo;

	@Column(name = "creator_id")
	private Integer creatorId;

	@Column(name = "status")
	private Integer status;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "type")
	private String type;

	@Column(name = "email")
	private String email;

	@Column(name = "email2")
	private String email2;

	@Column(name = "contact1")
	private String contact1;

	@Column(name = "contact2")
	private String contact2;

	@Column(name = "pam_id")
	private String pamId;

	@Column(name = "dsc_serial_no")
	private String dscSerialNo;

	@Column(name = "branch_master_code")
	private String branchMasterCode;

	@Column(name = "masterBranchName")
	private String masterBranchName;

	@Column(name = "sdAmount")
	private Integer sdAmount;

	@Column(name = "virtualaccount")
	private String virtualAccount;

	@Column(name = "bill_virtualaccount")
	private String billVirtualAccount;

	@Column(name = "creditlimit")
	private String creditLimit;

	@Column(name = "nrmname")
	private String nrmName;

	@Column(name = "state")
	private String state;

	@Column(name = "branchtype")
	private String branchType;

	@Column(name = "payoutslab")
	private String payOutSlab;

	@Column(name = "mf_code")
	private String mfCode;

	@Column(name = "documentpath")
	private String documentPath;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "legal_remark")
	private String legalRemark;

	@Column(name = "qrcode")
	private String qrCode;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getCode() {
		return code;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public Integer getStatus() {
		return status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public String getType() {
		return type;
	}

	public String getEmail() {
		return email;
	}

	public String getEmail2() {
		return email2;
	}

	public String getContact1() {
		return contact1;
	}

	public String getContact2() {
		return contact2;
	}

	public String getPamId() {
		return pamId;
	}

	public String getDscSerialNo() {
		return dscSerialNo;
	}

	public String getBranchMasterCode() {
		return branchMasterCode;
	}

	public String getMasterBranchName() {
		return masterBranchName;
	}

	public Integer getSdAmount() {
		return sdAmount;
	}

	public String getVirtualAccount() {
		return virtualAccount;
	}

	public String getBillVirtualAccount() {
		return billVirtualAccount;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public String getNrmName() {
		return nrmName;
	}

	public String getState() {
		return state;
	}

	public String getBranchType() {
		return branchType;
	}

	public String getPayOutSlab() {
		return payOutSlab;
	}

	public String getMfCode() {
		return mfCode;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getLegalRemark() {
		return legalRemark;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}

	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}

	public void setPamId(String pamId) {
		this.pamId = pamId;
	}

	public void setDscSerialNo(String dscSerialNo) {
		this.dscSerialNo = dscSerialNo;
	}

	public void setBranchMasterCode(String branchMasterCode) {
		this.branchMasterCode = branchMasterCode;
	}

	public void setMasterBranchName(String masterBranchName) {
		this.masterBranchName = masterBranchName;
	}

	public void setSdAmount(Integer sdAmount) {
		this.sdAmount = sdAmount;
	}

	public void setVirtualAccount(String virtualAccount) {
		this.virtualAccount = virtualAccount;
	}

	public void setBillVirtualAccount(String billVirtualAccount) {
		this.billVirtualAccount = billVirtualAccount;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public void setNrmName(String nrmName) {
		this.nrmName = nrmName;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setPayOutSlab(String payOutSlab) {
		this.payOutSlab = payOutSlab;
	}

	public void setMfCode(String mfCode) {
		this.mfCode = mfCode;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setLegalRemark(String legalRemark) {
		this.legalRemark = legalRemark;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	@Override
	public String toString() {
		return "BranchModel [id=" + id + ", name=" + name + ", location=" + location + ", code=" + code + ", serialNo="
				+ serialNo + ", creatorId=" + creatorId + ", status=" + status + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", type=" + type + ", email=" + email + ", email2=" + email2
				+ ", contact1=" + contact1 + ", contact2=" + contact2 + ", pamId=" + pamId + ", dscSerialNo="
				+ dscSerialNo + ", branchMasterCode=" + branchMasterCode + ", masterBranchName=" + masterBranchName
				+ ", sdAmount=" + sdAmount + ", virtualAccount=" + virtualAccount + ", billVirtualAccount="
				+ billVirtualAccount + ", creditLimit=" + creditLimit + ", nrmName=" + nrmName + ", state=" + state
				+ ", branchType=" + branchType + ", payOutSlab=" + payOutSlab + ", mfCode=" + mfCode + ", documentPath="
				+ documentPath + ", remarks=" + remarks + ", legalRemark=" + legalRemark + ", qrCode=" + qrCode + "]";
	}

}
