package com.altruist.wallet.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "balanceInfo")
public class WalletModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "Date_Time")
	private Date dateTime;

	@Column(name = "Master_Distributor")
	private String masterDistributor;

	@Column(name = "Agent_Code")
	private String agentCode;

	@Column(name = "Agent_Name")
	private String agentName;

	@Column(name = "Circle")
	private String circle;

	@Column(name = "Ref_ID")
	private String refId;

	@Column(name = "Customer_MSISDN")
	private String customerMsisdn;

	@Column(name = "Status")
	private String status;

	@Column(name = "Description")
	private String description;

	@Column(name = "Value")
	private Double value;

	@Column(name = "Wallet_Deduction")
	private Double walletDeduction;

	@Column(name = "Commission")
	private Double commission;

	@Column(name = "TDS")
	private Double tds;

	@Column(name = "Opening_Balance")
	private Double openingBalance;

	@Column(name = "Closing_Balance")
	private Double closingBalance;

	@Column(name = "Service_Type")
	private String serviceType;

	@Column(name = "Policy_Number")
	private String policyNumber;

	@Column(name = "Policy_Status")
	private String policyStatus;

	@Column(name = "Distributer_Commission")
	private Double distributerCommission;

	@Column(name = "Distributer_TDS")
	private Double distributerTds;

	@Column(name = "User")
	private String user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getMasterDistributor() {
		return masterDistributor;
	}

	public void setMasterDistributor(String masterDistributor) {
		this.masterDistributor = masterDistributor;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getCustomerMsisdn() {
		return customerMsisdn;
	}

	public void setCustomerMsisdn(String customerMsisdn) {
		this.customerMsisdn = customerMsisdn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getWalletDeduction() {
		return walletDeduction;
	}

	public void setWalletDeduction(Double walletDeduction) {
		this.walletDeduction = walletDeduction;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Double getTds() {
		return tds;
	}

	public void setTds(Double tds) {
		this.tds = tds;
	}

	public Double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public Double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(Double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	public Double getDistributerCommission() {
		return distributerCommission;
	}

	public void setDistributerCommission(Double distributerCommission) {
		this.distributerCommission = distributerCommission;
	}

	public Double getDistributerTds() {
		return distributerTds;
	}

	public void setDistributerTds(Double distributerTds) {
		this.distributerTds = distributerTds;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "WalletModel [id=" + id + ", dateTime=" + dateTime + ", masterDistributor=" + masterDistributor
				+ ", agentCode=" + agentCode + ", agentName=" + agentName + ", circle=" + circle + ", refId=" + refId
				+ ", customerMsisdn=" + customerMsisdn + ", status=" + status + ", description=" + description
				+ ", value=" + value + ", walletDeduction=" + walletDeduction + ", commission=" + commission + ", tds="
				+ tds + ", openingBalance=" + openingBalance + ", closingBalance=" + closingBalance + ", serviceType="
				+ serviceType + ", policyNumber=" + policyNumber + ", policyStatus=" + policyStatus
				+ ", distributerCommission=" + distributerCommission + ", distributerTds=" + distributerTds + ", user="
				+ user + "]";
	}

}
