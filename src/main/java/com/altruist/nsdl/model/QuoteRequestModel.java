package com.altruist.nsdl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "icici_quote_request")
public class QuoteRequestModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "msisdn")
	private String msisdn;

	@Column(name = "correlator_id")
	private String correlatorId;

	@Column(name = "request")
	private String request;

	@Column(name = "response")
	private String response;

	@Column(name = "date_time")
	private Date dateTime;

	@Column(name = "basic_premium")
	private String basicPremium;

	@Column(name = "total_tax")
	private String totalTax;

	@Column(name = "total_premium")
	private String totalPremium;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getCorrelatorId() {
		return correlatorId;
	}

	public void setCorrelatorId(String correlatorId) {
		this.correlatorId = correlatorId;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getBasicPremium() {
		return basicPremium;
	}

	public void setBasicPremium(String basicPremium) {
		this.basicPremium = basicPremium;
	}

	public String getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}

	public String getTotalPremium() {
		return totalPremium;
	}

	public void setTotalPremium(String totalPremium) {
		this.totalPremium = totalPremium;
	}

	@Override
	public String toString() {
		return "QuoteRequestModel [id=" + id + ", msisdn=" + msisdn + ", correlatorId=" + correlatorId + ", request="
				+ request + ", response=" + response + ", dateTime=" + dateTime + ", basicPremium=" + basicPremium
				+ ", totalTax=" + totalTax + ", totalPremium=" + totalPremium + "]";
	}

}
