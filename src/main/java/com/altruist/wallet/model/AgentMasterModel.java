package com.altruist.wallet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agent_master")
public class AgentMasterModel {

	@Id
	@Column(name = "Agent_Code")
	private String agentCode;

	@Column(name = "Branch_Code")
	private String branchCode;

	@Column(name = "Agent_Name")
	private String agentName;

	@Column(name = "Agent_Mobile")
	private String agentMobile;

	@Column(name = "Circle")
	private String circle;

	@Column(name = "Agent_Type")
	private String agentType;

	@Column(name = "Agent_Status")
	private String agentStatus;

	@Column(name = "Agent_Balance")
	private Double agentBalance;

	@Column(name = "Opening_Balance")
	private Double openingBalance;

	@Column(name = "Opening_Balance_Date")
	private Date openingBalanceDate;

	@Column(name = "Closing_Balance")
	private Double closingBalance;

	@Column(name = "Closing_Balance_Date")
	private Date closingBalanceDate;

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

	public String getAgentMobile() {
		return agentMobile;
	}

	public void setAgentMobile(String agentMobile) {
		this.agentMobile = agentMobile;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getAgentStatus() {
		return agentStatus;
	}

	public void setAgentStatus(String agentStatus) {
		this.agentStatus = agentStatus;
	}

	public Double getAgentBalance() {
		return agentBalance;
	}

	public void setAgentBalance(Double agentBalance) {
		this.agentBalance = agentBalance;
	}

	public Double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public Date getOpeningBalanceDate() {
		return openingBalanceDate;
	}

	public void setOpeningBalanceDate(Date openingBalanceDate) {
		this.openingBalanceDate = openingBalanceDate;
	}

	public Double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(Double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public Date getClosingBalanceDate() {
		return closingBalanceDate;
	}

	public void setClosingBalanceDate(Date closingBalanceDate) {
		this.closingBalanceDate = closingBalanceDate;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	@Override
	public String toString() {
		return "AgentMasterModel [agentCode=" + agentCode + ", branchCode=" + branchCode + ", agentName=" + agentName
				+ ", agentMobile=" + agentMobile + ", circle=" + circle + ", agentType=" + agentType + ", agentStatus="
				+ agentStatus + ", agentBalance=" + agentBalance + ", openingBalance=" + openingBalance
				+ ", openingBalanceDate=" + openingBalanceDate + ", closingBalance=" + closingBalance
				+ ", closingBalanceDate=" + closingBalanceDate + "]";
	}

}
