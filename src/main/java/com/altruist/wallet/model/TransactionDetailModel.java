package com.altruist.wallet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_details")
public class TransactionDetailModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(name = "Consumer_Number")
	private String consumerNumber;

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

	@Column(name = "request_type")
	private String requestType;

	public Long getId() {
		return id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public String getMasterDistributor() {
		return masterDistributor;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public String getCircle() {
		return circle;
	}

	public String getRefId() {
		return refId;
	}

	public String getCustomerMsisdn() {
		return customerMsisdn;
	}

	public String getConsumerNumber() {
		return consumerNumber;
	}

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	public Double getValue() {
		return value;
	}

	public Double getWalletDeduction() {
		return walletDeduction;
	}

	public Double getCommission() {
		return commission;
	}

	public Double getTds() {
		return tds;
	}

	public Double getOpeningBalance() {
		return openingBalance;
	}

	public Double getClosingBalance() {
		return closingBalance;
	}

	public String getServiceType() {
		return serviceType;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public String getPolicyStatus() {
		return policyStatus;
	}

	public Double getDistributerCommission() {
		return distributerCommission;
	}

	public Double getDistributerTds() {
		return distributerTds;
	}

	public String getUser() {
		return user;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public void setMasterDistributor(String masterDistributor) {
		this.masterDistributor = masterDistributor;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public void setCustomerMsisdn(String customerMsisdn) {
		this.customerMsisdn = customerMsisdn;
	}

	public void setConsumerNumber(String consumerNumber) {
		this.consumerNumber = consumerNumber;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public void setWalletDeduction(Double walletDeduction) {
		this.walletDeduction = walletDeduction;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public void setTds(Double tds) {
		this.tds = tds;
	}

	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public void setClosingBalance(Double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	public void setDistributerCommission(Double distributerCommission) {
		this.distributerCommission = distributerCommission;
	}

	public void setDistributerTds(Double distributerTds) {
		this.distributerTds = distributerTds;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@Override
	public String toString() {
		return "TransactionDetailModel [id=" + id + ", dateTime=" + dateTime + ", masterDistributor="
				+ masterDistributor + ", agentCode=" + agentCode + ", agentName=" + agentName + ", circle=" + circle
				+ ", refId=" + refId + ", customerMsisdn=" + customerMsisdn + ", consumerNumber=" + consumerNumber
				+ ", status=" + status + ", description=" + description + ", value=" + value + ", walletDeduction="
				+ walletDeduction + ", commission=" + commission + ", tds=" + tds + ", openingBalance=" + openingBalance
				+ ", closingBalance=" + closingBalance + ", serviceType=" + serviceType + ", policyNumber="
				+ policyNumber + ", policyStatus=" + policyStatus + ", distributerCommission=" + distributerCommission
				+ ", distributerTds=" + distributerTds + ", user=" + user + ", requestType=" + requestType + "]";
	}

}
