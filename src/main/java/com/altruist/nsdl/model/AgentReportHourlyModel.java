package com.altruist.nsdl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "icici_agent_report_hourly")
public class AgentReportHourlyModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "date")
	private Date date;

	@Column(name = "agent_username")
	private String agentUsername;

	@Column(name = "application_incomplete")
	private Integer applicationIncomplete;

	@Column(name = "application_complete")
	private Integer applicationComplete;

	@Column(name = "total_revenue")
	private Double totalRevenue;

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public String getAgentUsername() {
		return agentUsername;
	}

	public Integer getApplicationIncomplete() {
		return applicationIncomplete;
	}

	public Integer getApplicationComplete() {
		return applicationComplete;
	}

	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setAgentUsername(String agentUsername) {
		this.agentUsername = agentUsername;
	}

	public void setApplicationIncomplete(Integer applicationIncomplete) {
		this.applicationIncomplete = applicationIncomplete;
	}

	public void setApplicationComplete(Integer applicationComplete) {
		this.applicationComplete = applicationComplete;
	}

	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	@Override
	public String toString() {
		return "AgentReportModel [id=" + id + ", date=" + date + ", agentUsername=" + agentUsername
				+ ", applicationIncomplete=" + applicationIncomplete + ", applicationComplete=" + applicationComplete
				+ ", totalRevenue=" + totalRevenue + "]";
	}

}
